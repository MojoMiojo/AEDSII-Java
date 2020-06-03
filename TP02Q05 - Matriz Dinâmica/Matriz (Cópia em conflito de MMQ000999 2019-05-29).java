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
      ultimo.esq = inicio;
          
      //alocar a matriz com this.linha linhas e this.coluna colunas
   }//end Matriz

    public void inserir( int x, int coluna ){       
        Celula tmp = new Celula( x );

        	if( inicio.dir == null){
        		inicio.dir = ultimo;
        	}else{
        		tmp.esq = ultimo;
        		tmp.dir = ultimo;
        		ultimo.esq = tmp;
        		tmp = null; 
     		}

    }//end inserir
    
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
      
      int linhas, colunas, elemento;
        linhas  = MyIO.readInt( "Linhas: " );
        colunas = MyIO.readInt( "Colunas: "); 
        Matriz m1 = new Matriz( linhas, colunas );
        for( int i = 0; i < m1.linha; i++ ){
            for ( int j = 0; j < m1.coluna; j++){
                elemento = MyIO.readInt("Elemento: "); 
                m1.inserir( elemento , m1.coluna);   
            }
        }
        m1.mostrar( );
   }//end main
    
}
