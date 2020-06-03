/**
* @author Mozart Junio Alves de Sousa
* @since 04/02/2019
* TP01Q02Palindromo.java
*/

class TP01Q02Palindromo {

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

  //Funcao responsavel por retornar um boolean do teste sobre um entrada aleatoria
  //se palindromo ou nao
  public static boolean ePalindromo( String s ){
    int sTamanho = s.length( );
    boolean resposta = true;
    if( eNulo( s ) ){
      return false;
    }//end if nula

    else{
      for ( int i = 0; i < sTamanho; i++ ){

        if ( s.charAt( i ) != s.charAt( sTamanho - i - 1 ) ){
          i = sTamanho;
          resposta = false;
        }//end if teste palindromo
        else{
          resposta = true;
        }//€nd else

      }//end for
    }//else se string nao nulo
    return resposta;
  }//end ePalindromo

  public static void main (String[] args){
    String[] entrada = new String[1000];
    int numEntrada = 0;

    //Leitura da entrada padrao
    do {
      entrada[numEntrada] = MyIO.readLine();
    } while ( equals("FIM",entrada[numEntrada++]) == false);
    numEntrada--;   //Desconsiderar ultima linha contendo a palavra FIM

    //Para cada linha de entrada, gerando uma de saida contendo o numero de letras maiusculas da entrada
    for(int i = 0; i < numEntrada; i++){
      if( ePalindromo( entrada[i] ) ){
        MyIO.println( "SIM" );
      }else{
        MyIO.println("NAO");
      }//end else
    }//end for
  }//end main
}//end class
