//Mozart Junio Alves de Sousa
//608507     AedsII    2019/1

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.io.*;

class No{
  public char elemento;
  public int tamanho = 255;
  public No[] prox;
  public boolean folha;
  
  public No( ){
      this(' ');
  }
 
   public No (char elemento){
      this.elemento = elemento;
 
      prox = new No [tamanho];
 
      for (int i = 0; i < tamanho; i++){
         prox[i] = null;
      }
 
      folha = false;
   }
 
   public static int hash (char x){
      return (int)x;
   }

}//end class No

//classe ArvoreTrie
  class ArvoreTrie {
   private No raiz;

   int comp = 0;

   public int getComp( ){
    return comp;
   }
   
   public ArvoreTrie(){
      raiz = new No();
   }
 
   public void inserir(String s){
      inserir(s, raiz, 0);
   }

   public void inserir(String s, No no, int i){
  
      if(no.prox[s.charAt(i)] == null){
         no.prox[s.charAt(i)] = new No(s.charAt(i));
 
         if(i == s.length() - 1){
            no.prox[s.charAt(i)].folha = true;
         }else{
            inserir(s, no.prox[s.charAt(i)], i + 1);
         }
 
      } else if (no.prox[s.charAt(i)].folha == false && i < s.length() - 1){
         inserir(s, no.prox[s.charAt(i)], i + 1);
 
      } else {
         //throw new Exception("Erro ao inserir!");
      } 
   }
 
 
   public boolean pesquisar(String s) throws Exception {
      return pesquisar(s, raiz, 0);
   }
   
   //inserir na segunda arvore
   private ArvoreTrie merge( String nome, No i, ArvoreTrie ar ){
      if( i.folha == true ){
	ar.inserir( (nome + i.elemento).trim( ) );
      }else{
	for(int j = 0; j < i.prox.length; j++){
	  if( i.prox[j] != null ){
	    ar = merge( nome + i.elemento, i.prox[j], ar ); 
	  }//end if
	}//end for
      }//end else
      return ar;
   }//merge

   public ArvoreTrie merge ( ArvoreTrie i ){
    ArvoreTrie ar = new ArvoreTrie( );

      ar = merge( "", raiz, ar);
      ar = merge( "", i.raiz, ar);

    return ar;
   }//end merge

   public boolean pesquisar(String s, No no, int i) throws Exception {
      boolean resp = false;
 
      if(no.prox[s.charAt(i)] == null){
         resp = false;
	 comp++;
      } else if(i == s.length() - 1){
         resp = (no.prox[s.charAt(i)].folha == true);
	 comp++;
      } else if(i < s.length() - 1 ){
         resp = pesquisar(s, no.prox[s.charAt(i)], i + 1);
	 comp++;
      } else {
         //throw new Exception("Erro ao pesquisar!");
      }
 
      return resp;
   }
 
   public void mostrar(){
      mostrar("", raiz);
   }
 
   public void mostrar(String s, No no) {
      if(no.folha == true){
         System.out.println("Palavra: " + (s + no.elemento));
      } else {
         for(int i = 0; i < no.prox.length; i++){
            if(no.prox[i] != null){
               System.out.println("ESTOU EM (" + no.elemento + ") E VOU PARA (" + no.prox[i].elemento + ")");
               mostrar(s + no.elemento, no.prox[i]);
            }
         }
      }
   }
 }//end classe ArvoreTrie

public class Presidente{
	Presidente elemento, esq, dir;

	public Presidente ( Presidente elemento ){
		this.elemento = elemento;
		this.esq = null;
		this.dir = null;
	}//end presidente No
      
	public Presidente( ){
    this.nome = this.localNascimento = this.localMorte = this.antecessor = this.sucessor = this.vice = this.pagina = "";
    this.id = this.idade = 0;
  }//Construtor


  public Presidente(LocalDateTime dataNascimento, LocalDateTime inicioMandato, LocalDateTime fimMandato,
      LocalDateTime dataMorte, int id, int idade, String nome, String localNascimento, String localMorte,
      String antecessor, String sucessor, String vice, String pagina, long paginaTam) {

    this.id = id;
    this.idade = idade;
    this.nome = nome;
    this.localNascimento = localNascimento;
    this.localMorte = localMorte;
    this.antecessor = antecessor;
    this.sucessor = sucessor;
    this.vice = vice;
    this.pagina = pagina;
    this.paginaTam = paginaTam;
    this.dataNascimento = dataNascimento;
    this.inicioMandato = inicioMandato;
    this.fimMandato = fimMandato;
    this.dataMorte = dataMorte;
  }

  private String nome,
	  localNascimento,
	  localMorte,
	  antecessor,
	  sucessor,
	  vice,
	  pagina;

  private int id,
	  idade;

  private LocalDateTime dataNascimento,
	 inicioMandato,
	 fimMandato,
	 dataMorte;

  private long paginaTam;

//Gets----------------------------------------------------------

  //Primeiro get int, apos get string e por ultimo get LocalDateTime

  //get int---------------------------

  public int getId( ){
    return id;
  }

  public int getIdade( ){
    return idade;
  }

  //get String-------------------------

  public String getNome( ){
    return nome;
  }

  public String getLocalNascimento( ){
    return localNascimento;
  }

  public String getLocalMorte( ){
    return localMorte;
  }

  public String getAntecessor( ){
    return antecessor;
  }

  public String getSucessor( ){
    return sucessor;
  }

  public String getVice( ){
    return vice;
  }

  public String getPagina( ){
    return pagina;
  }

  //get LocalDateTime-------------------

  public LocalDateTime getDataNascimento( ){
    return dataNascimento;
  }

  public LocalDateTime getInicioMandato( ){
    return inicioMandato;
  }

  public LocalDateTime getFimMandato( ){
    return fimMandato;
  }

  public LocalDateTime getDataMorte( ){
    return dataMorte;
  }

  public long getPaginaTam( ){
    return paginaTam;
  }

//END GET--------------------------------------------------------

//INICIO DO SET, INT, STRING, LOCALDATETIME----------------------

  //int-----------------

  public void setId( int id ){
    this.id = id;
  }

  public void setIdade( int idade ){
    this.idade = idade;
  }

  //String---------------

  public void setNome( String nome ){
    this.nome = nome;
  }

  public void setLocalNascimento( String localNascimento ){
    this.localNascimento = localNascimento;
  }

  public void setLocalMorte( String localMorte ){
    this.localMorte = localMorte;
  }

  public void setAntecessor( String antecessor ){
    this.antecessor = antecessor;
  }

  public void setSucessor( String sucessor ){
    this.sucessor = sucessor;
  }

  public void setVice( String vice ){
    this.vice = vice;
  }

  public void setPagina( String pagina ){
    this.pagina = pagina;
  }

  //LocalDateTime----------

  public void setDataNascimento( LocalDateTime dataNascimento ){
    this.dataNascimento = dataNascimento;
  }

  public void setInicioMandato( LocalDateTime inicioMandato ){
    this.inicioMandato = inicioMandato;
  }

  public void setFimMandato( LocalDateTime fimMandato ){
    this.fimMandato = fimMandato;
  }

  public void setDataMorte( LocalDateTime dataMorte ){
    this.dataMorte = dataMorte;
  }

  //Long------------------

  public void setPaginaTam( long paginaTam ){
    this.paginaTam = paginaTam;
  }

//end sets------------------------------------------------------



  //METODO limparDatas( )
  public String limparDatas( String linha ){

     String aux = "",temp = "";
     char c;
     int contBarras = 0, contChar = 0;

     if( linha.contains("de")){
        linha = linha.replace("de","/");
      }//end if

      if( linha.contains("janeiro") ){
	linha = linha.replace("janeiro","1");
      }//if janeiro

      if( linha.contains("fevereiro") ){
	linha = linha.replace("fevereiro","2");
      }//if fevereiro

      if( linha.contains("mar") ){
	linha = linha.replace("mar\u00E7o","3");
      }//if março

      if( linha.contains("abril") ){
	linha = linha.replace("abril","4");
      }//if abril

      if( linha.contains("maio") ){
	linha = linha.replace("maio","5");
      }//if maio

      if( linha.contains("junho") ){
	linha = linha.replace("junho","6");
      }//if junho

      if( linha.contains("julho") ){
	linha = linha.replace("julho","7");
      }//if julho

      if( linha.contains("agosto") ){
	linha = linha.replace("agosto","8");
      }//if agosto

      if( linha.contains("setembro") ){
	linha = linha.replace("setembro","9");
      }//if setembro

      if( linha.contains("outubro") ){
	linha = linha.replace("outubro","10");
      }//if outubro

      if( linha.contains("novembro") ){
	linha = linha.replace("novembro","11");
      }//if novembro

      if( linha.contains("zembro") ){
	linha = linha.replace("zembro","12");
      }//if dezembro

      for( int i = 0; i < linha.length( ); i++ ){
	if( ( linha.charAt( i ) >= '0'  &&
	      linha.charAt( i ) <= '9') ||
	      linha.charAt( i ) == '/'){
	      aux += linha.charAt( i );
          }//end if
      }//end for limpar a data

      if( aux.contains("//") ){
	aux = aux.replace( "//","/" );
      }//end if

      linha = aux;

     if( aux.length() > 10 ){
      linha = "";

      //separar data inicio de data fim
      for( int i = 0; i < aux.length( ); i++){
	c = aux.charAt( i );

	if( c == '/' ){
	  contBarras++;
	}//end if

	if( contBarras < 2 ){
	  linha += c;
	}else{
	if(contChar<=4){
	    temp += c;
	    contChar++;
	  }else{
	    temp+=" "+c;
	    linha+=temp;
	    contBarras-=4;
 	  }//end else
	}//else contBarras

	}//end for

      }//if duas datas

      return linha;
  }//end limparDatas( )


  //FUNCAO responsavel por remover as tags < >, e retornar a String tratada
  public String getInfo( String linha ){
    int beginTag = 0, endTag = 0;

    //(beginTag = primeira "<" Encontrado;
    //Enquanto houver tags;
    //beginTag = novoPrimeiro "<" Encontrado)
    for ( beginTag = linha.indexOf("<");
		     linha.contains("<") && linha.contains(">");
		     beginTag = linha.indexOf("<") ){

      endTag = linha.indexOf(">");

      //if remover &#32
      if( linha.contains("&#32")){
	linha = linha.replace("&#32","");
      }//end if

      //if remover&#160
      if( linha.contains("&#160")){
	linha = linha.replace("&#160","");
      }//end if

      linha = linha.replace( linha.substring(beginTag,endTag+1), "" );
    }//end for

      for( beginTag=linha.indexOf("["); linha.contains("[") && linha.contains("]"); beginTag = linha.indexOf("[")){

	endTag = linha.indexOf("]");
	linha = linha.replace( linha.substring(beginTag,endTag+1),"");

      }//end for [nota 1]

    return linha;
  }//end getInfo( )


  //METODO responsavel por ler do arquivo e "setar" os atributos de cada presidente;
  public static Presidente ler( String url ){
    Presidente presidente = new Presidente( );

    String leitura = "",x = "",aux ="";
    int ant = 0, suc = 0, vice = 0,j = 0, contId=0, contNome = 0, contInicioMandato = 0, contChar = 0, tamanho = 0;

    if( url == null ){
      MyIO.println("ERRO Url inacessivel");
    }else{

      //abertura de arquivo
      Arq.openRead( url, "UTF-8");
      tamanho = (int)Arq.length( );
     // MyIO.println(tamanho);
      presidente.setPaginaTam( tamanho );
      presidente.setPagina( url );
      //MyIO.println(presidente.getPagina( ) );

      //Leitura e teste de atributos Linha apos Linha.
      while( Arq.hasNext( ) ){
      	      leitura = Arq.readLine();
	      aux ="";

	    //if setId( )----------------------------------------------------------------
	    if( contId < 1 &&
	      (leitura.contains("<th")) &&
	      (leitura.contains("Lista de presidentes do Brasil")) ){

	     //metodo de tratamento de atributo id
		   contId++;
		   x = presidente.getInfo( leitura );

		   if( x.charAt(1) >= '0' &&
		       x.charAt(1) <= '9'){

		      x = x.substring( 0,2 );
		   }else{
		      x = x.substring( 0,1 );
		   }//end else

		   presidente.setId( Integer.parseInt(x) );
	    }//end if setId( )

	    //if setInicioeFimMandato()-------------------------------------------------
	    if( leitura.contains("\">Per") && contInicioMandato < 1 ){
		contInicioMandato++;

	       //informacao duas linhas abaixo
	       leitura = Arq.readLine( );
	       leitura = Arq.readLine( );

		//tratar os dados obtidos da linha html e retornarndo dd/mm/aaaa em String
		leitura = presidente.limparDatas( (presidente.getInfo(leitura)));

		//separar inicio do fim do mandato, apos separar em dia mes ano
		String[] periodo = leitura.split(" ");
		String[] data = periodo[0].split("/");
	        String[] dataFim = periodo[1].split("/");
		//objeto responsavel por criar o LocalDateTime com dia mes e ano, cast de String para Int.
		LocalDateTime date = LocalDateTime.of( Integer.parseInt(data[2]), Integer.parseInt(data[1]), Integer.parseInt(data[0]) ,0 ,0 );
	  	//setInicioMandato
		presidente.setInicioMandato( date );
		//setFimMandato
		date = LocalDateTime.of( Integer.parseInt(dataFim[2]), Integer.parseInt(dataFim[1]), Integer.parseInt(dataFim[0]) ,0 ,0 );
		presidente.setFimMandato( date );

	    }//end ifInicioMandato

	   //if setVice( )------------------------------------------------------
	    if( leitura.contains( ";\">Vice-presidente") && vice < 1){
	      vice++;
	      leitura = Arq.readLine( );
	      leitura = Arq.readLine( );
	      leitura = presidente.getInfo( leitura );
	      presidente.setVice( leitura );
	    }//end if setVice( )------------------------------------------------

	    //if setAntecessor()------------------------------------------------
	    if( ant < 1 &&leitura.contains(";\">Antecessor") ){
	      ant++;
	      leitura = Arq.readLine( );
	      leitura = Arq.readLine( );
	      leitura = presidente.getInfo( leitura );
	      presidente.setAntecessor( leitura );
	    }//end if setAntecessor()-------------------------------------------

	    //if setSucessor( )
	    if ( suc < 1 && leitura.contains(";\">Sucessor") ){
	      suc++;
	      leitura = Arq.readLine( );
	      leitura = Arq.readLine( );
	      leitura = presidente.getInfo( leitura );
	      presidente.setSucessor( leitura );
	    }//end if setSucessor( )--------------------------------------------

	    //if setLocalNascimento-------------------------------------------------
	    if( leitura.contains("left;\">Nascimento") ){
	      leitura = Arq.readLine( );
	      leitura = Arq.readLine( );
	      leitura = presidente.getInfo(leitura);
	      String linha = leitura;
	      int beginTag, endTag;

	       for( beginTag=linha.indexOf("("); linha.contains("(") && linha.contains(")"); beginTag = linha.indexOf("(")){

	endTag = linha.indexOf(")");
	linha = linha.replace( linha.substring(beginTag,endTag+1),"");

      }//end for ( )

	      leitura = linha;

	      //primeira posicao do for a seguir
	      int k = leitura.indexOf("d");
	      x ="";

	      for( int i = 0; i<k; i++ ){
		x+=leitura.charAt(i);
	      }//char antes do d

	      //for separar dataNascimento de localNascimento
	      for( int i = k; i < leitura.length( ); i++ ){
		if( leitura.charAt( i ) >= '0' && leitura.charAt( i ) <='9' ){
		  contChar++;
		}
		if( contChar == 4){
		  x += leitura.charAt( i );
		  leitura = leitura.substring( i+1, leitura.length( ));
		  i = leitura.length( );
		}else{
		  x += leitura.charAt( i );
		}//end else
	      }//end for
	      x = presidente.limparDatas(x);
              int dia, mes, ano;
	      String[] data = x.split("/");
	      dia = Integer.parseInt( data[0] );
	      mes = Integer.parseInt( data[1] );
	      ano = Integer.parseInt( data[2] );
	      //objeto responsavel por criar o LocalDateTime com dia mes e ano, cast de String para Int.
	      LocalDateTime date = LocalDateTime.of( ano, mes, dia, 0 ,0 );
	      //setIdade
	      presidente.setIdade( 2019 - date.getYear( ) );
	      //setInicioMandato
	      presidente.setDataNascimento( date );

	      //Limpar localNascimento
	       if( leitura.charAt(1) >= 'A' &&
		   leitura.charAt(1) <= 'Z'){

		      leitura = leitura.substring( 1, leitura.length( ) );
		   }else{
		      leitura = leitura.substring( 2, leitura.length( ) );
	       }//end else

	      presidente.setLocalNascimento( leitura );
	    }//end if setLocalNascimento( )--------------------------------------

	    //if  setNome( )------------------------------------------------------------
	    if( leitura.contains(";\">Nome completo") ){

		leitura = Arq.readLine( );
		leitura = Arq.readLine( );
		presidente.setNome( presidente.getInfo( leitura ) );
	    }//end if setNome( )--------------------------------------------------------

	  //if setLocalMorte e setDataMorte---------------------------------------------
	   if ( leitura.contains( ";\">Morte") ){
	    leitura = Arq.readLine( );
	    leitura = Arq.readLine( );
	    leitura = presidente.getInfo ( leitura );

	    String linha = leitura;
	    int beginTag, endTag;

	      for( beginTag=linha.indexOf("("); linha.contains("(") && linha.contains(")"); beginTag = linha.indexOf("(")){
      		endTag = linha.indexOf(")");
		linha = linha.replace( linha.substring(beginTag,endTag+1),"");
	      }//end for ( )

	      leitura = linha;

	      //primeira posicao do for a seguir
	      int k = leitura.indexOf("d");
	      x = "";//limpar variavel
	      contChar = 0;

	      for( int i = 0; i<k; i++ ){
		x+=leitura.charAt(i);
	      }//for armazenar o que vem antes da letra D do teste

	      //for separar dataNascimento de localNascimento atraves do ano que contem 4 char inteiros
	      for( int p = k; p < leitura.length( ); p++ ){

		if( leitura.charAt( p ) >= '0' && leitura.charAt( p ) <='9' ){
		  contChar++;
		}//if contInteiros /YYYY/

		if( contChar == 4){
		  x += leitura.charAt( p );
		  leitura = leitura.substring( p+1, leitura.length( ));
		  p = leitura.length( );
		}else{
		  x += leitura.charAt( p );
		}//end else

	      }//end

	      //x contem a data separada do local da morte
	      x = presidente.limparDatas(x);

              //  MyIO.println("pos limpar datas\n "+ x );
	      int dia, mes, ano;
	      String[] data = x.split("/");

	      dia = Integer.parseInt( data[0] );
	      mes = Integer.parseInt( data[1] );
	      ano = Integer.parseInt( data[2] );

	    //objeto responsavel por criar o LocalDateTime com dia mes e ano, cast de String para Int.
	      LocalDateTime date = LocalDateTime.of( ano, mes, dia, 0 ,0 );

	    //setInicioMandato
	      presidente.setDataMorte( date );

	      //Limpar localMorte
	       if( leitura.charAt(1) >= 'A' &&
		   leitura.charAt(1) <= 'Z'){

		      leitura = leitura.substring( 1, leitura.length( ) );
		   }else{
		      leitura = leitura.substring( 2, leitura.length( ) );
	       }//end else

	      presidente.setLocalMorte( leitura );
	   }//end if setLocalMorte e setDataMorte

      }//end while hasNext( )
    }//end else
    Arq.close( );
    return presidente;
  }//end ler( )

  public void imprimir( ){
    DateTimeFormatter formatter = DateTimeFormatter.ofPattern("d/M/yyy");
     if( this.getDataMorte( ) == null ){
      	
	MyIO.println( this.getId( ) + " ## "
		+ this.getNome( ) + " ## "
		+ this.getInicioMandato( ).format(formatter)+" (I) ## "
	        + this.getFimMandato( ).format(formatter)+" (F) ## "
		+ this.getDataNascimento( ).format(formatter)+" em "
		+ this.getLocalNascimento( )+" (N) ## "
		+ this.getIdade( )+ " ## "
	        + this.getPagina( )+ " ## "
	        + this.getPaginaTam( )+ " ## "
		+ this.getAntecessor( )+ " ## "
		+ this.getSucessor( )+ " ## "
		+ this.getVice( ));
    }else{
       MyIO.println( this.getId( ) + " ## "
		+ this.getNome( ) + " ## "
		+ this.getInicioMandato( ).format(formatter)+" (I) ## "
	        + this.getFimMandato( ).format(formatter)+" (F) ## "
		+ this.getDataNascimento( ).format(formatter)+" em "
		+ this.getLocalNascimento( )+" (N) ## "
		+ this.getIdade( )+ " ## "
		+ this.getDataMorte( ).format(formatter)+" em "
		+ this.getLocalMorte( )+" (M) ## "
	        + this.getPagina( )+ " ## "
	        + this.getPaginaTam( )+ " ## "
		+ this.getAntecessor( )+ " ## "
		+ this.getSucessor( )+ " ## "
		+ this.getVice( ));
    }
  }//end imprimir( );

  public static void main ( String [] args )throws Exception{
    
    PrintStream encodingChato = new PrintStream(System.out, true, "ISO-8859-1");
    
    ArvoreTrie av = new ArvoreTrie( );
    ArvoreTrie av2 = new ArvoreTrie( );
    MyIO.setCharset("UTF-8");
    String [ ] nomes = new String [150];    
    String input = MyIO.readLine( );
    int i = 0;

    while( !input.contains("FIM")){
      av.inserir( ler(input).getNome( ).trim( ) );
      input = MyIO.readLine( );
    }//end while

    input = MyIO.readLine( );

    while( !input.contains("FIM")){
      av2.inserir( ler(input).getNome( ).trim( ) );
      input = MyIO.readLine( );
    }//end while
    
    ArvoreTrie ar = av.merge( av2 );

    input = MyIO.readLine();
    long start = System.currentTimeMillis( );
	
    while( !input.contains("FIM") ){
	
	if( ar.pesquisar( input.trim( ) ) ){
		System.out.println("SIM");
	}else{
		encodingChato.println("N�O");
	}
	input = MyIO.readLine( );
     }//end while pesquisa()
    
	MyIO.println("");
    
    Arq log = new Arq( );
      log.openWrite ("608507_avinegra.txt");
      log.print("608507"+"\t"+ (( System.currentTimeMillis( ) - start ))+ "\t" + ar.getComp( ));
    log.close( );
	
  }//end MAIN

}//end class
