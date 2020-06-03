import java.util.Random;
/**
* @author Mozart Junio Alves de Sousa
* @since 04/02/2019
* TP01Q05AlgebraBoolena.java
*/

class TP01Q05AlgebraBoolena {
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

  //Funcao para criptografar uma string de acordo com o ciframento de cesar
  public static String eCiframento ( String s ){
    String newS = "";
    int sTamanho = s.length( );

    if ( eNulo( s ) ){
      return ( "Erro"  );
    }else{

      for ( int i = 0; i < sTamanho; i++ ){
        newS += (char) (s.charAt( i ) + 3);
      }//end for

    }//end else
    return newS;
  }//end eCiframento

  /**Funcao na qual a dois sorteios de char são feitos
  *  apos onde na string original for igual ao primeiro char sorteado
  *  substituimos pelo segundo sorteado
  */
  public static String eAleatorio ( String s ){
    String newS = "";
    int sTamanho = s.length( );
    char randomUm,randomDois;

    if ( eNulo( s ) ){
      return ( "Erro"  );
    }else{

      randomUm   = (char) ( 'a' + ( Math.abs( gerador.nextInt( ) ) % 26 ) );
      randomDois = (char) ( 'a' + ( Math.abs( gerador.nextInt( ) ) % 26 ) );
      //MyIO.println("TESTE|a|b|" + randomUm + randomDois );

      for ( int i = 0; i < sTamanho; i++ ){

        if( s.charAt( i ) == randomUm ){
          newS += randomDois;
        }else{
          newS += s.charAt( i );
        }//end else
      }//end for

    }//end else
    return newS;
  }//end eAleatorio

  public static boolean algebraBool( String s ){
    boolean resposta = true,
    a,
    b,
    c = false;

    char newDir = '',
    newEsq = '';

    String newS = s, simplificada;

    int cont = 0,
    sTamanho = newS.length( );

    if( eNulo( newS ) ){
      resposta = false;
    }else{
      for(int i = 0; i < sTamanho; i++){
        if( .charAt( i ) == '(' ){ cont++; }
        MyIO.println( "I|" + i +"|"+ newS.charAt( i ) );
      }//end for

      if( newS.chart( 0 ) == '2' ){
        a = (newS.charAt( 2 ) != '0' );
        b = (newS.charAt( 4 ) != '0' );

        //caminhar pela String
        for(int i = sTamanho; i > 6; i++){

          //IF achar expressao
          if(newEsq == ''){

            if ( s.charAt( i ) == ')' ){
              newDir = i;
            }
            if ( s.charAt( i ) == '(' ){
              newEsq = i;
            }
          }
          else{

            //TESTE Operador NOT
            if( newEsq - 1 == 't' ){
              if(newDir - 1 == 'A'){
                if ( a ){ a = false; } else { a = true; }
              }//Operacao NOT variavel A
              else{
                if( b ){ n = false; } else { b = true; }
              }//Operacano NOT variavel B;

              //reescrever string
              for ( int j = 6; i < newEsq-3; i++ ){
                simplificada += newS.charAt( i );
              }
              

            }//Operacao NOT |A|B|

          }//ELSE expressao encontrada

        //end IF achar expressao

      }//end for

      newS =+
    }//end if 2 variaveis

    //variavel C se existente
    if (s.charAt( 6 ) == '0' || s.charAt( 6 ) == '1' ){
      c = (s.charAt( 6 ) != '0');
    }//end if C

    MyIO.println("TESTE|A|B|C|"+a+"|"+b+"|"+c );

  }
  return resposta;
}//end algebraBool

static Random gerador = new Random( );

public static void main (String[] args){
  String[] entrada = new String[1000];
  int numEntrada = 0;
  gerador.setSeed( 4 );

  //Leitura da entrada padrao
  do {
    entrada[numEntrada] = MyIO.readLine();
  } while (entrada[numEntrada++].equals("FIM") == false);
  numEntrada--;   //Desconsiderar ultima linha contendo a palavra FIM

  //Para cifrar todas as palavras do vetor na saida

  for(int i = 0; i < numEntrada; i++){
    MyIO.println( "" + algebraBool( entrada[ i ] ) );
  }//end for

}//end main
}//end class
