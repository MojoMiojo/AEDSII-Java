#include <stdlib.h>
#include <stdio.h> 
#include <string.h> 
 
 int main(){ 
   
   int n = 0,bits = 0; 
   double valor = 0;
   char s[1000];
   FILE *arq;
   
   //abrindo arquivo para gravacao
   arq = fopen("reais.txt","w");
   //reais a serem lidos
   scanf("%i", &n); 
    
   //teste tamanho arquivo a ser criado
   if( arq == NULL && n > 0 ){
     printf("ERRO na criacao do arquivo");
   }else{
      //loop de gravacao
     for(int i = 0; i < n; i++){ 
     
        //łeitura do arquivo a ser gravado
        scanf("%lf", &valor);
	//objeto responsavel por gravar no arquivo( dados, tamanhoDoDado, ?, objetoFile)
        fwrite(&valor, sizeof(double), 1, arq );
        
     }//end for 
     
     fclose(arq);
    
    //Abrindo arquivo para leitura--------------- 
     FILE *arq;
     
     arq = fopen("reais.txt","r");

     if(arq == NULL){
       
       printf("ERRO ao ler arquivo");
       
     }else{
     
     //loop de leitura do arquivo
     for ( int i = n - 1 ; i >= 0; i-- ){
      
       //entradas*tamanhoDeDouble=totalDeBitsDoArquivo
       bits = i*sizeof(double);

       //posicionar o ponteiro (objeto, tamanho, funcao)
	fseek(arq, bits, SEEK_SET);

       //ler do arquivo e armazenar em variavel
       fread(&valor, sizeof(double), 1, arq);

       printf( "%g\n", valor );
     }//end for
   
     }//end else
   
     fclose(arq);
   
   }//end else
   
   return 1;
}//end main
