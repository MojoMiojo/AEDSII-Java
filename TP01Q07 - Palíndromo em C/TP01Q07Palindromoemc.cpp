#include <stdio.h>
#include <string.h>
#include <stdbool.h>
#include <stdlib.h>

/*
 *Mozart Junio Alves de Sousa
 */

bool ePalindromo ( char *s ){
  bool resposta = true;

  if( strlen(s) < 1 ){
     resposta = false;
  }else{
    for( int i = 0; i < strlen(s); i++ ){
      if( s[i] != s[strlen(s)-i-1]){
	resposta = false;
	i = strlen(s);
      } 
    }//end for
  }//end else

  return resposta;
}//end ePalindromo


int main( ){
    char s[1000];
    int resp = 0;
    scanf( "%[^\n]s", s );

    while( strcmp( s,"FIM" ) ){
           
      if( ePalindromo( s ) ){
	printf( "SIM\n" );
      }else{
	printf( "NAO\n" );
      }

     scanf( " %[^\n]s",s );

    }//end while

    return 0;
}//end class
