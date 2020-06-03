import java.util.Random;
/**
* @author Mozart Junio Alves de Sousa
* @since 11/02/2019
* TP01Q14AlteracaoAleatoria.java
*/

class TP01Q14AlteracaoAleatoriaRecursiva {

	static Random gerador = new Random( );

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

	public static boolean eNulo ( String s ){

		if( s == null || s.length( ) <= 1 ){
			return true;
		}//end if nula
		else{
			return false;
		}//end else

	}//€nd eNulo

	public static String eAleatorio ( String s ){

		char randomUm   = (char) ( 'a' + ( Math.abs( gerador.nextInt( ) ) % 26 ) );
		char randomDois = (char) ( 'a' + ( Math.abs( gerador.nextInt( ) ) % 26 ) );
		return eAleatorio(s, 0, randomUm, randomDois);
	}//end inicializador da funcao eAleatorio



	/**Funcao na qual a dois sorteios de char são feitos
	 *  apos onde na string original for igual ao primeiro char sorteado
	 *  substituimos pelo segundo sorteado
	 */
	public static String eAleatorio ( String s, int i, char randomUm, char randomDois){
		String newS = "";
		int sTamanho = s.length( );

		if ( eNulo( s ) ){
			return ( "Erro"  );
		}else{
			//comeco recursividade
			if(i < sTamanho ){
				if( s.charAt( i ) == randomUm ){
					newS += randomDois;
					newS += eAleatorio(s, i+1, randomUm, randomDois);
				}else{
					newS += s.charAt( i );
					newS += eAleatorio(s, i+1, randomUm, randomDois);
				}//end else

			}//end recursividade
		}//end else
		return newS;
	}//end eAleatorio


	public static void main (String[] args){
		String[] entrada = new String[1000];
		int numEntrada = 0;
		gerador.setSeed( 4 );

		//Leitura da entrada padrao
		do {
			entrada[numEntrada] = MyIO.readLine();
		} while ( equals("FIM",entrada[numEntrada++]) == false);
		numEntrada--;   //Desconsiderar ultima linha contendo a palavra FIM

		//Para cifrar todas as palavras do vetor na saida

		for(int i = 0; i < numEntrada; i++){
			MyIO.println( "" + eAleatorio( entrada[ i ] ) );
		}//end for

	}//end main
}//end class
