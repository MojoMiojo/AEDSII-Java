import java.net.*;
import java.io.*;
/**
* @author Mozart Junio Alves de Sousa
* @since 06/02/2019
* TP0Q08CodigoHTML.java
*/

class TP01Q08CodigoHTML {

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

  //Funcao teste para entrada nula ou vazia.
  public static boolean eNulo ( String s ){

    if( s == null || s.length( ) < 1 ){
      return true;
    }//end if nula
    else{
      return false;
    }//end else

  }//€nd eNulo

  public static String readCode( String url )throws IOException{
    String resposta = "";

    URL access = new URL (url);
    URLConnection connect = access.openConnection( );
    BufferedReader read = new BufferedReader( new InputStreamReader ( connect.getInputStream( )) );
  
    String linha;
    
    while( (linha = read.readLine()) != null ){
      resposta+=linha;
    }//end while

    return resposta;
  }//end readCode

  public static int consoantes ( String str ){
    char c;
    int resposta = 0;
    for ( int i = 1; i < str.length( ); i++){

      c = str.charAt( i );
      if ( c != 'a'&&
      c != 'e'&&
      c != 'i'&&
      c != 'o'&&
      c != 'u'&&
      c != 'A'&&
      c != 'E'&&
      c != 'I'&&
      c != 'O'&&
      c != 'U'){

        if ( c >='a' && c <='z'){
          resposta += 1;
        }//if soma
      }
    }//end for
    return ( resposta );
  }//end consoantes

  public static void contar( String s, String nome ){
    int tamanho = s.length( );
    int a = 0, e = 0, i = 0, o = 0, u = 0; //vogais
    int ah = 0, eh = 0, ih = 0, oh = 0, uh = 0; //vogais com acento '
    int ca = 0, ce = 0, ci = 0, co = 0, cu = 0; //vogais Crazeadas `aeiou`
    int ac = 0, oc = 0; //vogais crazeadas till~
    int a1 = 0, e1 = 0, i1 = 0, o1 = 0, u1 = 0; //vogais circunflexos
    char c;
    int consoantes = consoantes( s ), br = 0, table = 0;

    for( int j = 0; j < tamanho; j++ ){

      c = s.charAt( j );

      switch ( c ){

        case 'a': a++; break; case 'á': ah++; break;
        case 'e': e++; break; case 'é': eh++; break;
        case 'i': i++; break; case 'í': ih++; break;
        case 'o': o++; break; case 'ó': oh++; break;
        case 'u': u++; break; case 'ú': uh++; break;
        case 'ã': ac++;break; case 'â': a1++; break;
        case 'õ': oc++;break; case 'ê': e1++; break;
        case 'î': i1++;break; case 'ô': o1++; break;
        case 'û': u1++;break; case 'à': ca++; break;
        case 'è': ce++;break; case 'ì': ci++; break;
        case 'ò': co++;break; case 'ù': cu++; break;

      }//end switch

      if ( c == '<' ){
        if ( s.charAt( j+1 ) == 'b' ){
          if ( s.charAt( j+2 ) == 'r' ){
            if ( s.charAt( j+3 ) == '>' ){
              br++;
            }//end '>'
          }//end 'r'
        }//if 'b'
      }//if '<'

      if ( c == '<' ){
        if ( s.charAt( j+1 ) == 't' ){
          if ( s.charAt( j+2 ) == 'a' ){
            if ( s.charAt( j+3 ) == 'b' ){
              if ( s.charAt( j+4 ) == 'l' ){
                if ( s.charAt( j+5 ) == 'e'){
                  if ( s.charAt( j+6 ) == '>' ){
                    table++; a--; e--; consoantes = consoantes - 3;
                  }//if '>'
                }//end 'e'
              }//if 'l'
            }//if 'b'
          }// if 'a'
        }//if 't'
      }//if '<'

    }//end for


    MyIO.print( "a("+a+") e("+e+") i("+i+") o("+o+") u("+u+")");
    MyIO.print( " á("+ah+") é("+eh+") í("+ih+") ó("+oh+") ú("+uh+")");
    MyIO.print( " à("+ca+") è("+ce+") ì("+ci+") ò("+co+") ù("+cu+")");
    MyIO.print( " ã("+ac+") õ("+oc+")");
    MyIO.print( " â("+a1+") ê("+e1+") î("+i1+") ô("+o1+") û("+u1+")");
    MyIO.print( " consoante("+consoantes+") <br>("+br+") <table>("+table+") " );
    MyIO.println( ""+nome );
  }//end contar

  public static void main (String[] args){
    String[] entrada = new String[1000];
    int numEntrada = 0;
    //gerador.setSeed( 4 );
    
    //Leitura da entrada padrao
    do {
      entrada[numEntrada] = MyIO.readLine();
    } while ( equals("FIM",entrada[numEntrada++]) == false);
    numEntrada--;   //Desconsiderar ultima linha contendo a palavra FIM
	    MyIO.setCharset("UTF-8");
    try{
      for( int i = 0; i < numEntrada; i+=2 ){
        contar( readCode( entrada[i+1]), entrada[i] );
      }//end
    }catch(IOException erro){
      erro.printStackTrace( );
    }
  }//end main
}//end class
