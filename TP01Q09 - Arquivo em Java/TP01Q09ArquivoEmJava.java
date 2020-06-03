/**
* @author Mozart Junio Alves de Sousa
* @since 07/02/2019
* TP01Q09ArquivoEmJava.java
*/
import java.io.*;

class TP01Q09ArquivoEmJava{

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
  
  public static void fromFile(  )throws IOException{
    RandomAccessFile raf = new RandomAccessFile("reais.txt", "r" );
    long ponteiro = raf.length( ) / 8; 
    double valor;
    for( long i = ponteiro; i > 0; i-- ){
      
      raf.seek( (i - 1) * 8 );
      valor = raf.readDouble( );

      if( valor == (int)valor ){
	MyIO.println( (int)valor );
      }else{
	MyIO.println( valor );
      }
      
    }
    raf.close( );
  }//end fromFile

  //Funcao teste para entrada nula ou vazia.
  public static boolean eNulo ( String s ){

    if( s == null || s.length( ) <= 1 ){
      return true;
    }//end if nula
    else{
      return false;
    }//end else

  }//€nd eNulo

  public static void toFile ( int n ) throws IOException {
  RandomAccessFile raf = new RandomAccessFile("reais.txt","rw");
  double valor = 0;

    for( int i = 0; i < n; i++){
      valor = MyIO.readDouble( );
    //teste de leitura  MyIO.println("N|VALOR"+n+"|"+valor);
      raf.writeDouble( valor );
    }
    
    raf.close( );
     
  }//end toFile
  
  public static void main (String[] args)throws IOException{
    int n;
    
    n = MyIO.readInt( );
        
     toFile( n );
     fromFile( );
  
   

  }//end main
}//end class
