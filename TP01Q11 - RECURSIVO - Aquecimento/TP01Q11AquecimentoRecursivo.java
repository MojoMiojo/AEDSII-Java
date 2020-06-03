class TP01Q11AquecimentoRecursivo {

  //Funcao teste para entrada nula ou vazia.
  public static boolean eNulo ( String s ){

    if( s == null || s.length( ) <= 1 ){
      return true;
    }//end if nula
    else{
      return false;
    }//end else

  }//€nd eNulo

  public static boolean equals ( String str, String str2 ){
    boolean resposta = true;

    if( ! ( eNulo( str ) && eNulo( str2 ) ) ){
      char c;
      for ( int i = 0; i < str.length( ); i++ ){
        c = str.charAt( i );

        if ( c != str2.charAt( i ) ){
          resposta = false;
          i = str.length( );
        }//end if

      }//end for
    }//end if eVazio

    return ( resposta );
  }//end equals

   public static boolean isMaiuscula (char c){
      return (c >= 'A' && c <= 'Z');
   }

   public static int contarLetrasMaiusculas(String s){
    return contarLetrasMaiusculas(s, 0);
   }

   public static int contarLetrasMaiusculas (String s , int i){
      int tamanho = s.length( );
      int resp = 0;
      //  MyIO.println("DDD");
      if( i < tamanho ){

        if(isMaiuscula(s.charAt(i))){
	      resp ++;
	}

        resp += contarLetrasMaiusculas( s, i+=1 );
      }//end if recursivo

      return resp;
   }//end contarLetrasMaiuscula

   public static void main (String[] args){
      String[] entrada = new String[1000];
      String linha;
      int numEntrada = 0;
     
      //Leitura da entrada padrao
      do {

        entrada[numEntrada] = MyIO.readLine();
	 
      } while ( ! equals("FIM" , entrada[numEntrada++] ));
      numEntrada--;   //Desconsiderar ultima linha contendo a palavra FIM

      //Para cada linha de entrada, gerando uma de saida contendo o numero de letras maiusculas da entrada
      for(int i = 0; i < numEntrada; i++){
         MyIO.println(contarLetrasMaiusculas(entrada[i]));
      }
   }
}
