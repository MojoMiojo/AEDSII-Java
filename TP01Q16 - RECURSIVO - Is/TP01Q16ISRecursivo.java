import java.util.Random;
/**
* @author Mozart Junio Alves de Sousa
* @since 11/02/2019
* TP0Q16ISRecursivo.java
*/

class TP01Q16ISRecursivo{
	 static int contPontos = 0;
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

    if( s == null ){
      return true;
    }//end if nula
    else{
      return false;
    }//end else

  }//€nd eNulo

  
  
  
  public static boolean isVogal ( String s ){
	 return isVogal( s, 0 );
  }//end inicializador recursivo
   
  public static boolean isVogal ( String s, int i ){
    boolean resposta = true;
	 //MyIO.println("TESTE|isVogal|"+s.charAt(i)+"|"+i);
    if( eNulo( s ) ){
      resposta = false;
    }else{
      if( i < s.length( ) && resposta ){
	if ( s.charAt( i ) == 'a'||
	     s.charAt( i ) == 'e'||
	     s.charAt( i ) == 'i'||
	     s.charAt( i ) == 'o'||
	     s.charAt( i ) == 'u'||
	     s.charAt( i ) == 'A'||
	     s.charAt( i ) == 'E'||
	     s.charAt( i ) == 'I'||
	     s.charAt( i ) == 'O'||
	     s.charAt( i ) == 'U'){
	   resposta = isVogal( s, i+1 );
	  	}else{
		resposta = false;
		i = s.length( );
	}//end else
	 
      }//end IF RECURSIVO
   
    }
    return resposta;
  }//end isVogal

  public static boolean isConsoante ( String s ){
	 return isConsoante ( s, 0 );
  }//end inicializador isConsoante

  public static boolean isConsoante ( String s, int i ){
    boolean resposta = false;

    if( eNulo( s ) ){
      resposta = false;
    }else{
		//Inicio isConsoante Recursivo
      if( i < s.length( ) ){
	if( (s.charAt(i) >= 'a' && s.charAt(i) <= 'z' ||
	     s.charAt(i) >= 'A' && s.charAt(i) <= 'Z') && (
	     s.charAt( i ) != 'a'&&
	     s.charAt( i ) != 'e'&&
	     s.charAt( i ) != 'i'&&
	     s.charAt( i ) != 'o'&&
	     s.charAt( i ) != 'u'&&
	     s.charAt( i ) != 'A'&&
	     s.charAt( i ) != 'E'&&
	     s.charAt( i ) != 'I'&&
	     s.charAt( i ) != 'O'&&
	     s.charAt( i ) != 'U')){

	     resposta = isConsoante( s, i+1);
	}else{
	      resposta = false;
	      i = s.length( );
	}//end else
      }//end if RECURSIVO
    }//end else
    return resposta;
  }//end isConsoante

  public static boolean isInt (String s){
	 return isInt( s, 0);
  }//end inicializador isInt
  
  public static boolean isInt (String s, int i){
    boolean resposta = true;
    if( eNulo(s) ){
      resposta = false;
    }else{
      if( i < s.length() ){
	if( s.charAt(i) >= '0' &&
	    s.charAt(i) <= '9'){
	  resposta = isInt( s, i+1);
        }else{
	  resposta = false;
	  i = s.length( );
	}//end else
      }//end if RECURSIVO
    }//end else
    return resposta;
  }//end isInt

  public static boolean isReal (String s){
	 return isReal( s, 0 );
  }//end inicializador isReal
  
  public static boolean isReal (String s, int i){
    boolean resposta = true;

    if( eNulo(s) ){
      resposta = false;
    }else{

      //variavel responsavel por indicar se deixa de ser real
      
		
      if( i < s.length() ){
	      if( ( (s.charAt(i) >='0' && s.charAt(i) <='9') || (s.charAt(i) == '.' || s.charAt(i) == ',') ) && (contPontos <= 1) ){
	        if( s.charAt(i) == ',' || s.charAt(i) == '.' ){ contPontos++;  }
			 		
		  resposta = isReal( s, i+1);
      	}else{
				resposta = false;
				i = s.length( );
			}//end else
      }//end IF RECURSIVO
    }//€nd else

    return resposta;
  }//end isReal

  public static void main (String[] args){
    String[] entrada = new String[1000];
    int numEntrada = 0;
	 
    //gerador.setSeed( 4 );

    //Leitura da entrada padrao
    do {
      entrada[numEntrada] = MyIO.readLine();
    } while ( equals("FIM",entrada[numEntrada++]) == false);
    numEntrada--;   //Desconsiderar ultima linha contendo a palavra FIM

    //Para registrar as saidas esperadas

    for(int i = 0; i < numEntrada; i++){
		contPontos = 0;
      if( isVogal( entrada[i] ) ){
	MyIO.print("SIM ");
      }else{
	MyIO.print("NAO ");
      }//end isVogal

      if ( isConsoante( entrada[i] ) ){
	MyIO.print("SIM ");
      }else{
	MyIO.print("NAO ");
      }//end isConsoante

      if( isInt( entrada[i] ) ){
	MyIO.print("SIM ");
      }else{
	MyIO.print("NAO ");
      }//end isInt

      if( isReal( entrada[i] ) ){
	MyIO.print("SIM");
      }else{
	MyIO.print("NAO");
      }//end isReal

      MyIO.println("");

    }//end for

  }//end main
}//end class
