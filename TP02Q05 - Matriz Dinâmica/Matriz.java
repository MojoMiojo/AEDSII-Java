class Celula {
   public int elemento;
   public Celula inf, sup, esq, dir;

   public Celula(){
      this(0, null, null, null, null);
   }

   public Celula(int elemento){
      this(elemento, null, null, null, null);

   }

   public Celula(int elemento, Celula inf, Celula sup, Celula esq, Celula dir){
      this.elemento = elemento;
      this.inf = inf;
      this.sup = sup;
      this.esq = esq;
      this.dir = dir;
   }
}//end class celula-------------------------------------------------------------

public class Matriz {
   private int linha, coluna;
   public Celula inicio = new Celula( );
   public Celula ultimo = new Celula( );

   public Matriz (){
      this(3, 3);
   }

   public Matriz (int linha, int coluna){
      this.linha = linha;
      this.coluna = coluna;
      ultimo = inicio = null;
                
      //alocar a matriz com this.linha linhas e this.coluna colunas
   }//end Matriz

    public void coluna( int x ){       
    
	    if( inicio.inf == null ){
	      Celula tmp = new Celula( x );
	      inicio.inf = tmp;
	      tmp.sup = inicio;
	      ultimo = tmp;
	    }else{
	      Celula tmp = new Celula( x );
	      ultimo.dir = tmp;
	      tmp.esq = ultimo;
	      ultimo = tmp;
	    }

	}//end inserir

    public void linha( int x ){
      while( inicio.inf != null ){
	inicio = inicio.inf;
      }
      Celula tmp;
      Celula i = new Celula( x );
      i.sup = tmp;
      tmp.inf = i;
    }
    
    public void mostrar( ){
        MyIO.println("\nMatriz");
        for( Celula i = inicio; i != null; i = i.dir ){
            MyIO.print( i.elemento + "\t" );

        }//end for
    }//end mostrar

   /*public Matriz soma (Matriz m) {
      Matriz resp = null;

      if(this.linha == m.linha && this.coluna == m.coluna){
         //...
      }

      return resp;
   }//end soma

   public Matriz multiplicacao (Matriz m) {
      Matriz resp = null;

      if(){
         //...
      }

      return resp;
   }//end multiplicacao

   public boolean isQuadrada(){
      boolean (this.linha == this.coluna);
   }//end isQuadrada

   public void mostrarDiagonalPrincipal (){
      if(isQuadrada() == true){
         for(Celula i = inicio; i != null; i = i.inf.dir){
            Sop(i.elemento);
         }
      }
   }//end mostrarDiagonalPrincipal

   public void mostrarDiagonalSecundaria (){
      if(isQuadrada() == true){

      }
   }//end mostrarDiagonal

   public void mostrar(){
      for(Celula i = inicio; i != null; i = i.prox){
         for(Celula j = 
   }//end mostrar */

    public static void main(String[] args){
      int casos = MyIO.readLine( );
      int linhas, colunas, elemento;

        linhas  = MyIO.readInt( "Linhas: " );
        colunas = MyIO.readInt( "Colunas: ");
        int posCol = 0;

        Matriz m1 = new Matriz( linhas, colunas );
        for( int i = 0; i < m1.linha; i++ ){
            for ( int j = 0; j < m1.coluna; j++){
                elemento = MyIO.readInt("Elemento: "); 
                m1.coluna( elemento.charAt(posCol) ); 
		posCol += 2;
            }
        }
        m1.mostrar( );
   }//end main
    
}
