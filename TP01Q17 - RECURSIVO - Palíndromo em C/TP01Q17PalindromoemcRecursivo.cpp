#include <stdio.h>
#include <string.h>
#include <stdbool.h>
#include <stdlib.h>
bool ePalindromos ( char *s , int i );

/*
 *Mozart Junio Alves de Sousa
 */

bool ePalindromo ( char *s ){
  return ePalindromos ( s, 0 );
}//end inicializador

bool ePalindromos ( char *s , int i ){
  bool resposta = true;
  int tamanho = strlen(s);
  if( tamanho < 1 ){
     resposta = false;
  }else{
    if( i < tamanho ){
      if( s[i] == s[tamanho-i-1]){
	resposta = ePalindromos( s, i+1 );
	i = tamanho;
      }else{
	      resposta = false;
      }
    }//end IF RECURSIVO
  }//end else

  return resposta;
}//end ePalindromos


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
