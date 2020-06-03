/**
* @author Mozart Junio Alves de Sousa
* @since 04/02/2019
* TP01Q02Palindromo.java
*/

class TP01Q12PalindromoRecursivo {

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
  }//end isMaiuscula

  public static int contarLetrasMaiusculas (String s){
    int resp = 0;

    for(int i = 0; i < s.length(); i++){
      if(isMaiuscula(s.charAt(i)) == true){
        resp ++;
      }//end if
    }//end for

    return resp;
  }
  //Funcao teste para entrada nula ou vazia.
  public static boolean eNulo ( String s ){

    if( s == null || s.length( ) <= 1 ){
      return true;
    }//end if nula
    else{
      return false;
    }//end else

  }//€nd eNulo

  public static boolean ePalindromo (String s){
    return ePalindromo ( s, 0 );
  }
  
  //Funcao responsavel por retornar um boolean do teste sobre um entrada aleatoria
  //se palindromo ou nao

  public static boolean ePalindromo( String s, int i ){
    boolean resposta = true;
    int tamanho = s.length( );

        if ( i < tamanho ){
      	  if ( s.charAt(i) == s.charAt( tamanho-1-i) ){ 
		return ePalindromo(s,i+=1);
          }else{
	    return false;
	  }
	}
      return resposta;

  }//end ePalindromo

  public static void main (String[] args){
    String[] entrada = new String[1000];
    int numEntrada = 0;
    String input;

    //Leitura da entrada padrao
    do {
      entrada[numEntrada] = MyIO.readLine();
    } while ( !equals("FIM",entrada[numEntrada++]));
    numEntrada--;   //Desconsiderar ultima linha contendo a palavra FIM


    //Para cada linha de entrada, gerando uma de saida contendo o numero de letras maiusculas da entrada
    for(int i = 0; i < numEntrada; i++){
      input = entrada[i];
      
      if( ePalindromo( input ) ){
        MyIO.println( "SIM" );
      }else{
        MyIO.println("NAO");
      }//end else
    }//end for
  }//end main
}//end class
