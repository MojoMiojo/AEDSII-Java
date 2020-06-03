/**
* @author Mozart Junio Alves de Sousa
* @since 04/02/2019
* TP01Q13CiframentoRecursivo.java
*/

class TP01Q13CiframentoRecursivo {

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

  }//€nd eNul

  public static String eCiframento( String s ){
    return eCiframento( s, 0 );
  }
  
  //Funcao para criptografar uma string de acordo com o ciframento de cesar
  public static String eCiframento ( String s, int i ){
    String newS = "";
    int sTamanho = s.length( );

    if ( eNulo( s ) ){
      newS = "Erro String nao cifrada";
    }else{

      if ( i < sTamanho ){
	  newS += (char) (s.charAt( i ) + 3);
	  newS += eCiframento ( s, i+=1);
      }//end for

    }//end else
    return newS;
  }//end eCiframento

  public static void main (String[] args){
    String[] entrada = new String[1000];
    int numEntrada = 0;

    //Leitura da entrada padrao
    do {
      entrada[numEntrada] = MyIO.readLine();
    } while ( equals("FIM",entrada[numEntrada++]) == false);
    numEntrada--;   //Desconsiderar ultima linha contendo a palavra FIM

    //Para cifrar todas as palavras do vetor na saida

    for(int i = 0; i < numEntrada; i++){
      MyIO.println( "" + eCiframento( entrada[ i ] ) );
    }//end for

  }//end main
}//end class
