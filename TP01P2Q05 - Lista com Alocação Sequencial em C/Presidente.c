#include <string.h>
#include <stdio.h>
#include <stdlib.h>
#include <iconv.h>
#include <time.h>

#define MAXTAM    40
#define bool      short
#define true      1
#define false     0
int mov = 0;
int comp = 0;

char *converteISO(char *teste) {
  iconv_t cd = iconv_open("ISO_8859-1", "UTF-8");
  if (cd == (iconv_t) -1) {
    perror("iconv_open failed!");
    return "erro";
  }

  char input[] = "";
  strcpy(input, teste);
  char *in_buf = &input[0];
  size_t in_left = sizeof(input) - 1;

  char output[100];
  char *out_buf = &output[0];
  size_t out_left = sizeof(output) - 1;

  do {
    if (iconv(cd, &in_buf, &in_left, &out_buf, &out_left) == (size_t) -1) {
      perror("iconv failed!");
      return "erro";
    }
  } while (in_left > 0 && out_left > 0);
  *out_buf = 0;

  iconv_close(cd);

  return strdup(output);
}

typedef struct LocalDateTime
{
  int dia, mes, ano;
} LocalDateTime;

typedef struct Presidente
{
  int id, idade;
  char nome[50], localNascimento[50], localMorte[50], antecessor[50], sucessor[50], vice[100], pagina[55];
  LocalDateTime dataNascimento, inicioMandato, fimMandato, dataMorte;
  long paginaTam;
} Presidente;

char *append(char *s, char c)
{
  int len = strlen(s);
  s[len] = c;
  s[len + 1] = '\0';
  return strdup(s);
}

char *replace(char *s, char *lixo, char *novo)
{
  char *saida = (char *)malloc(sizeof(s));

  for (int i = 0; i < strlen(s); i++)
  {
    int tmp = i;
    if (s[i] == lixo[0])
    {
      int flag = 1;
      for (int j = 0; j < strlen(lixo); j++, tmp++)
      {
        if (s[tmp] != lixo[j])
        {
          j = strlen(lixo);
          flag = 0;
        }
      }
      if (flag == 1)
      {
        i = tmp;
        strcat(saida, novo);
      }
    }
    strcpy(saida, append(saida, s[i]));
  }
  return strdup(saida);
}

char *tiraTag(char x[], char begin, char end)
{
  char saida[500];
  strcpy(saida, "");

  for (int i = 0; i < strlen(x); i++)
  {
    if (x[i] == begin)
    {
      while (x[i] != end)
      {
        i++;
      }
      i++;
    }
    if (x[i] != begin)
    {
      strcpy(saida, append(saida, x[i]));
    }
    else
    {
      i--;
    }
  }
  return strdup(saida);
}

int converteMes(const char *mes)
{
  int saida = 0;
  if (strcmp(mes, "janeiro") == 0)
  saida = 1;
  else if (strcmp(mes, "fevereiro") == 0)
  saida = 2;
  else if (strcmp(mes, "março") == 0)
  saida = 3;
  else if (strcmp(mes, "abril") == 0)
  saida = 4;
  else if (strcmp(mes, "maio") == 0)
  saida = 5;
  else if (strcmp(mes, "junho") == 0)
  saida = 6;
  else if (strcmp(mes, "julho") == 0)
  saida = 7;
  else if (strcmp(mes, "agosto") == 0)
  saida = 8;
  else if (strcmp(mes, "setembro") == 0)
  saida = 9;
  else if (strcmp(mes, "outubro") == 0)
  saida = 10;
  else if (strcmp(mes, "novembro") == 0)
  saida = 11;
  else if (strcmp(mes, "dezembro") == 0)
  saida = 12;
  return saida;
}

Presidente leArquivo(char path[])
{
  struct Presidente presidente;
  strcpy(presidente.nome, "");
  strcpy(presidente.localNascimento, "");
  strcpy(presidente.localMorte, "");
  strcpy(presidente.antecessor, "");
  strcpy(presidente.sucessor, "");
  strcpy(presidente.vice, "");
  strcpy(presidente.pagina, "");

  struct LocalDateTime data;
  FILE *fp;
  char linha[500];
  char *linhaPtr;

  fp = fopen(path, "r");

  fseek(fp, 0L, SEEK_END);
  presidente.paginaTam = ftell(fp);
  rewind(fp);

  if (fp == NULL)
  {
    perror("Error while opening the file.\n");
    exit(EXIT_FAILURE);
  }

  fgets(linha, 500, fp);
  linha[strcspn(linha, "\n")] = '\0';
  while (strstr(linha, "Lista de presidentes do Brasil") == NULL)
  {
    fgets(linha, 500, fp);
  }
  linha[strcspn(linha, "\n")] = '\0';

  char id[2];
  linhaPtr = tiraTag(linha, '<', '>');
  strcpy(id, "");
  for (int i = 0; i < strlen(linhaPtr); i++)
  {
    if (linhaPtr[i] >= '0' && linhaPtr[i] <= '9')
    {
      strcpy(id, append(id, linhaPtr[i]));
    }
    else
    {
      i = strlen(linhaPtr);
    }
  }
  presidente.id = atoi(id);

  while (strstr(linha, "Período") == NULL)
  {
    fgets(linha, 500, fp);
  }
  fgets(linha, 500, fp);
  fgets(linha, 500, fp);
  linha[strcspn(linha, "\n")] = '\0';
  linhaPtr = tiraTag(linha, '<', '>');

  strcpy(linhaPtr, replace(linhaPtr, "&#32;", " "));
  strcpy(linhaPtr, replace(linhaPtr, "[nota 1]", ""));
  strcpy(linhaPtr, replace(linhaPtr, "º", ""));
  strcpy(linhaPtr, replace(linhaPtr, "até", " a"));
  strcpy(linhaPtr, replace(linhaPtr, "  ", " "));

  char tmp[strlen(linhaPtr)];
  for (int i = 0; i < strlen(linhaPtr); i++)
  {
    if (linhaPtr[i + 1] == 'a' && (linhaPtr[i] >= '0' && linhaPtr[i] <= '9'))
    {
      strcpy(tmp, append(tmp, linhaPtr[i]));
      strcpy(tmp, append(tmp, ' '));
      i++;
    }
    strcpy(tmp, append(tmp, linhaPtr[i]));
  }
  strcpy(linhaPtr, tmp);

  char *tok;
  tok = strtok(linhaPtr, " ");

  int tokCounter = 0;
  while (tokCounter != 5)
  {
    if (tokCounter == 0)
    {
      data.dia = atoi(tok);
    }
    else if (tokCounter == 2)
    {
      data.mes = converteMes(tok);
    }
    else if (tokCounter == 4)
    {
      data.ano = atoi(tok);
    }
    tok = strtok(0, " ");
    tokCounter++;
  }
  presidente.inicioMandato = data;

  while (tokCounter != 11)
  {
    if (tokCounter == 6)
    {
      data.dia = atoi(tok);
    }
    else if (tokCounter == 8)
    {
      data.mes = converteMes(tok);
    }
    else if (tokCounter == 10)
    {
      data.ano = atoi(tok);
    }
    tok = strtok(0, " ");
    tokCounter++;
  }
  presidente.fimMandato = data;
  int cVice = 0;
  while (strstr(linha, "Vice-presidente") == NULL)
  {
    if (strstr(linha, "Antecessor") != NULL)
    {
      // printf("Passou aqui\n");
      cVice = 1;
      fgets(linha, 500, fp);
      fgets(linha, 500, fp);
      linha[strcspn(linha, "\n")] = '\0';
      linhaPtr = tiraTag(linha, '<', '>');
      strcpy(presidente.antecessor, converteISO(linhaPtr));

      if (strcmp(path, "/tmp/presidente/Jair_Bolsonaro.html") != 0)
      {
        while (strstr(linha, "Sucessor") == NULL)
        {
          fgets(linha, 500, fp);
        }
        fgets(linha, 500, fp);
        fgets(linha, 500, fp);
        linha[strcspn(linha, "\n")] = '\0';
        linhaPtr = tiraTag(linha, '<', '>');
        strcpy(presidente.sucessor, converteISO(linhaPtr));
      }
    }
    fgets(linha, 500, fp);
  }

  fgets(linha, 500, fp);
  fgets(linha, 500, fp);
  linha[strcspn(linha, "\n")] = '\0';
  linhaPtr = tiraTag(linha, '<', '>');
  strcpy(presidente.vice, converteISO(linhaPtr));

  if (cVice == 0)
  {
    while (strstr(linha, "Antecessor") == NULL)
    {
      fgets(linha, 500, fp);
    }
    fgets(linha, 500, fp);
    fgets(linha, 500, fp);
    linha[strcspn(linha, "\n")] = '\0';
    linhaPtr = tiraTag(linha, '<', '>');
    strcpy(presidente.antecessor, converteISO(linhaPtr));

    if (strcmp(path, "/tmp/presidente/Jair_Bolsonaro.html") != 0)
    {
      while (strstr(linha, "Sucessor") == NULL)
      {
        fgets(linha, 500, fp);
      }
      fgets(linha, 500, fp);
      fgets(linha, 500, fp);
      linha[strcspn(linha, "\n")] = '\0';
      linhaPtr = tiraTag(linha, '<', '>');
      strcpy(presidente.sucessor, converteISO(linhaPtr));
    }
  }

  while (strstr(linha, "Nome completo") == NULL)
  {
    fgets(linha, 500, fp);
  }
  fgets(linha, 500, fp);
  fgets(linha, 500, fp);
  linha[strcspn(linha, "\n")] = '\0';
  linhaPtr = tiraTag(linha, '<', '>');
  strcpy(presidente.nome, converteISO(linhaPtr));

  while (strstr(linha, "Nascimento") == NULL)
  {
    fgets(linha, 500, fp);
  }
  fgets(linha, 500, fp);
  fgets(linha, 500, fp);
  linha[strcspn(linha, "\n")] = '\0';
  linhaPtr = tiraTag(linha, '<', '>');
  strcpy(linhaPtr, replace(linhaPtr, "&#160;", ""));
  strcpy(linhaPtr, tiraTag(linhaPtr, '(', ')'));

  tok = strtok(linhaPtr, " ");

  tokCounter = 0;
  char local[100];
  while (tok != 0)
  {
    if (tokCounter == 0)
    {
      data.dia = atoi(tok);
    }
    else if (tokCounter == 2)
    {
      data.mes = converteMes(tok);
    }
    else if (tokCounter == 4)
    {
      data.ano = atoi(tok);
    }
    else if (tokCounter > 4)
    {
      strcat(local, tok);
      strcpy(local, append(local, ' '));
    }
    tok = strtok(0, " ");
    tokCounter++;
  }

  local[strlen(local) - 1] = '\0';

  presidente.dataNascimento = data;
  strcpy(presidente.localNascimento, converteISO(local));
  // printf("%s\n", presidente.localNascimento);

  presidente.idade = 2019 - data.ano;

  strcpy(local, "");

  char aux[500];
  while (strstr(linha, "Morte") == NULL)
  {
    fgets(linha, 500, fp);
    strcpy(aux, linha);
    if (strstr(linha, "</tbody></table>") != NULL)
    {
      strcpy(aux, linha);
      strcpy(linha, "Morte");
      strcpy(presidente.localMorte, "");
    }
  }

  strcpy(linha, aux);
  if (strstr(linha, "Morte"))
  {
    fgets(linha, 500, fp);
    fgets(linha, 500, fp);
    linha[strcspn(linha, "\n")] = '\0';
    strcpy(linhaPtr, tiraTag(linha, '<', '>'));
    strcpy(linhaPtr, tiraTag(linhaPtr, '(', ')'));
    strcpy(linhaPtr, replace(linhaPtr, "&#160;", ""));
  }

  tok = strtok(linhaPtr, " ");
  tokCounter = 0;
  while (tok != 0)
  {
    if (tokCounter == 0)
    {
      data.dia = atoi(tok);
    }
    else if (tokCounter == 2)
    {
      data.mes = converteMes(tok);
    }
    else if (tokCounter == 4)
    {
      data.ano = atoi(tok);
    }
    else if (tokCounter > 4)
    {
      strcat(local, tok);
      strcpy(local, append(local, ' '));
    }
    tok = strtok(0, " ");
    tokCounter++;
  }
  local[strlen(local) - 1] = '\0';
  presidente.dataMorte = data;
  strcpy(presidente.localMorte, converteISO(local));

  strcpy(local, "");

  strcpy(presidente.pagina, converteISO(path));

  fclose(fp);

  return presidente;
}

void imprimir(Presidente presidente)
{
  if (strcmp(presidente.localMorte, "") == 0)
  {
    printf("%d ## %s ## %d/%d/%d (I) ## %d/%d/%d (F) ## %d/%d/%d em %s (N) ## %d ## %s ## %ld ## %s ## %s ## %s\n", presidente.id, presidente.nome, presidente.inicioMandato.dia, presidente.inicioMandato.mes, presidente.inicioMandato.ano, presidente.fimMandato.dia, presidente.fimMandato.mes, presidente.fimMandato.ano, presidente.dataNascimento.dia, presidente.dataNascimento.mes, presidente.dataNascimento.ano, presidente.localNascimento, presidente.idade, presidente.pagina, presidente.paginaTam, presidente.antecessor, presidente.sucessor, presidente.vice);
  }
  else
  {
    printf("%d ## %s ## %d/%d/%d (I) ## %d/%d/%d (F) ## %d/%d/%d em %s (N) ## %d ## %d/%d/%d em %s (M) ## %s ## %ld ## %s ## %s ## %s\n", presidente.id, presidente.nome, presidente.inicioMandato.dia, presidente.inicioMandato.mes, presidente.inicioMandato.ano, presidente.fimMandato.dia, presidente.fimMandato.mes, presidente.fimMandato.ano, presidente.dataNascimento.dia, presidente.dataNascimento.mes, presidente.dataNascimento.ano, presidente.localNascimento, presidente.idade, presidente.dataMorte.dia, presidente.dataMorte.mes, presidente.dataMorte.ano, presidente.localMorte, presidente.pagina, presidente.paginaTam, presidente.antecessor, presidente.sucessor, presidente.vice);
  }
}

Presidente array[MAXTAM];   // Elementos da pilha
int n;               // Quantidade de array.


/**
* Inicializacoes
*/
void start(){
  n = 0;
}


/**
* Insere um elemento na primeira posicao da lista e move os demais
* elementos para o fim da
* @param x int elemento a ser inserido.
*/
void inserirInicio(Presidente x) {
  int i;

  //validar insercao
  if(n >= MAXTAM){
    printf("Erro ao inserir!");
    exit(1);
  }

  //levar elementos para o fim do array
  for(i = n; i > 0; i--){
    array[i] = array[i-1];
  }

  array[0] = x;
  n++;
}


/**
* Insere um elemento na ultima posicao da
* @param x int elemento a ser inserido.
*/
void inserirFim(Presidente x) {

  //validar insercao
  if(n >= MAXTAM){
    printf("Erro ao inserir!");
    exit(1);
  }

  array[n] = x;
  n++;
}


/**
* Insere um elemento em uma posicao especifica e move os demais
* elementos para o fim da
* @param x int elemento a ser inserido.
* @param pos Posicao de insercao.
*/
void inserir(Presidente x, int pos) {
  int i;

  //validar insercao
  if(n >= MAXTAM || pos < 0 || pos > n){
    printf("Erro ao inserir!");
    exit(1);
  }

  //levar elementos para o fim do array
  for(i = n; i > pos; i--){
    array[i] = array[i-1];
  }

  array[pos] = x;
  n++;
}


/**
* Remove um elemento da primeira posicao da lista e movimenta
* os demais elementos para o inicio da mesma.
* @return resp int elemento a ser removido.
*/
Presidente removerInicio() {
  int i;
  Presidente resp;

  //validar remocao
  if (n == 0) {
    printf("Erro ao remover!");
    exit(1);
  }

  resp = array[0];
  n--;

  for(i = 0; i < n; i++){
    array[i] = array[i+1];
  }
  printf("(R) %s\n", resp.nome);
  return resp;
}


/**
* Remove um elemento da ultima posicao da
* @return resp int elemento a ser removido.
*/
Presidente removerFim() {

  //validar remocao
  if (n == 0) {
    printf("Erro ao remover!");
    exit(1);
  }
  Presidente resp = array[--n];
  printf("(R) %s\n", resp.nome);
  return resp;
}


/**
* Remove um elemento de uma posicao especifica da lista e
* movimenta os demais elementos para o inicio da mesma.
* @param pos Posicao de remocao.
* @return resp int elemento a ser removido.
*/
Presidente remover(int pos) {
  int i;
  Presidente resp;

  //validar remocao
  if (n == 0 || pos < 0 || pos >= n) {
    printf("Erro ao remover!");
    exit(1);
  }

  resp = array[pos];
  n--;

  for(i = pos; i < n; i++){
    array[i] = array[i+1];
  }
  printf("(R) %s\n", resp.nome);
  return resp;
}


/**
* Mostra os array separados por espacos.
*/
void mostrar (){
  int i;

  for(i = 0; i < n; i++){
    imprimir(array[i]);
  }
}


/**
* Procura um array e retorna se ele existe.
* @param x int elemento a ser pesquisado.
* @return <code>true</code> se o array existir,
* <code>false</code> em caso contrario.
*/
bool pesquisar(Presidente x) {
  bool retorno = false;
  int i;

  for (i = 0; i < n && retorno == false; i++) {
    retorno = (array[i].id == x.id);
  }
  return retorno;
}

void swap(int i, int j) {
   Presidente temp = array[i];
   array[i] = array[j];
   array[j] = temp;
}

int dateCompare(LocalDateTime l1 , LocalDateTime l2){
  int resp = 0;
  if(l1.ano > l2.ano){
    resp = 2;
  }else if(l1.ano < l2.ano){
    resp = 1;
  }else if(l1.mes > l2.mes){
    resp = 2;
  }else if(l1.mes < l2.mes){
    resp = 1;
  }else if(l1.dia > l2.dia){
    resp = 2;
  }else if(l1.dia < l2.dia){
    resp = 1;
  }

  return resp;
}

void quicksortRec (int esq, int dir) {
   int i = esq, j = dir;
   Presidente pivo = array[(dir+esq)/2];
   while (i <= j) {
     while (dateCompare(array[i].inicioMandato, pivo.inicioMandato) == 1) i++;
     while (dateCompare(array[i].inicioMandato, pivo.inicioMandato) == 2) j--;
     // while (dateCompare(array[j].inicioMandato, pivo.inicioMandato) == -1) i++;
     // while (dateCompare(array[j].inicioMandato, pivo.inicioMandato) == 1) j--;
      if (i <= j) {
         swap(i, j);
         i++;
         j--;
      }
   }
   if (esq < j)  quicksortRec(esq, j);
   if (i < dir)  quicksortRec(i, dir);
}

// Algoritmo de ordenacao
void quicksort() {
   quicksortRec(0, n-1);
}

int main()
{
  char input[100];
  start();

  fgets(input, 100, stdin);
  input[strcspn(input, "\n")] = '\0';
  int i = 0;
  printf("teste");
  while (strcmp(input, "FIM") != false)
  {
    i++;
    inserirFim(leArquivo(input));
    fgets(input, 100, stdin);
    input[strcspn(input, "\n")] = '\0';
  }

  clock_t comeco = clock();
  quicksort();
  clock_t fim = clock();

  mostrar();
  printf("\n");

  double total = (clock() - comeco) / (double)CLOCKS_PER_SEC / 1000.0;

  FILE *fptr;
  fptr = fopen("matrícula_quicksort.txt", "w");
  if(fptr == NULL) {
    printf("ERRO");
    exit(1);
  }

  char *matricula = "553378\t";
  fprintf(fptr, "553378\t%f\t%d\t%d", total, numComparacoes, numMovimentacoes);
  fclose(fptr);

  return 0;
}
