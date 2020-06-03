//Mozart Junio Alves de Sousa
//608507     AedsII    2019/1

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.io.*;

class No {
	public Presidente elemento; // Conteudo do no.
	public No esq; // No da esquerda.
	public No dir; // No da direita.
	public int nivel; //Numero de niveis abaixo do no

	/**
	* Construtor da classe.
	* @param elemento Conteudo do no.
	*/
	No(Presidente elemento) {
		this(elemento, null, null, 1);
	}

	/**
	* Construtor da classe.
	* @param elemento Conteudo do no.
	* @param esq No da esquerda.
	* @param dir No da direita.
	*/
	No(Presidente elemento, No esq, No dir, int nivel) {
		this.elemento = elemento;
		this.esq = esq;
		this.dir = dir;
		this.nivel = nivel;
	}

	// Cálculo do número de níveis a partir de um vértice
	public No setNivel() {
		this.nivel = 1 + Math.max(getNivel(esq),getNivel(dir));
		return this;
	}

	// Retorna o número de níveis a partir de um vértice
	public static int getNivel(No no) {
		return (no == null) ? 0 : no.nivel;
	}
}

class AVL {
	private No raiz; // Raiz da arvore.
	public int comp;
	/**
	* Construtor da classe.
	*/
	public AVL() {
		raiz = null;
	}

	public int getComp( ){
		return comp;
	}

	public int getAltura() {
		return No.getNivel(raiz) - 1;
	}

	/**
	* Metodo publico iterativo para pesquisar elemento.
	* @param elemento Elemento que sera procurado.
	* @return <code>true</code> se o elemento existir,
	* <code>false</code> em caso contrario.
	*/
	public boolean pesquisar( String elemento ) {
		return pesquisar(raiz, elemento);
	}

	/**
	* Metodo privado recursivo para pesquisar elemento.
	* @param no No em analise.
	* @param x Elemento que sera procurado.
	* @return <code>true</code> se o elemento existir,
	* <code>false</code> em caso contrario.
	*/
	private boolean pesquisar(No no, String x) {
		boolean resp;
		if (no == null) {
			resp = false;
			comp++;
		} else if (x.equals( no.elemento.getNome( ))) {
			resp = true;
			comp++;
		} else if ( x.compareTo( no.elemento.getNome( ) ) < 0 ) {
			MyIO.print("esq ");
			resp = pesquisar(no.esq, x);
			comp++;
		} else {
			MyIO.print("dir ");
			resp = pesquisar(no.dir, x);
		}
		return resp;
	}

	/**
	* Metodo publico iterativo para exibir elementos.
	*/
	public void mostrarCentral() {
		System.out.print("[ ");
		mostrarCentral(raiz);
		System.out.println("]");
	}

	/**
	* Metodo privado recursivo para exibir elementos.
	* @param no No em analise.
	*/
	private void mostrarCentral(No no) {
		if (no != null) {
			mostrarCentral(no.esq); // Elementos da esquerda.
			System.out.print(no.elemento + " "); // Conteudo do no.
			mostrarCentral(no.dir); // Elementos da direita.
		}
	}

	/**
	* Metodo publico iterativo para exibir elementos.
	*/
	public void mostrarPre() {
		System.out.print("[ ");
		mostrarPre(raiz);
		System.out.println("]");
	}

	/**
	* Metodo privado recursivo para exibir elementos.
	* @param no No em analise.
	*/
	private void mostrarPre(No no) {
		if (no != null) {
			System.out.print(no.elemento + "(fator " + (No.getNivel(no.dir) - no.getNivel(no.esq)) + ") "); // Conteudo do no.
			mostrarPre(no.esq); // Elementos da esquerda.
			mostrarPre(no.dir); // Elementos da direita.
		}
	}

	/**
	* Metodo publico iterativo para exibir elementos.
	*/
	public void mostrarPos() {
		System.out.print("[ ");
		mostrarPos(raiz);
		System.out.println("]");
	}

	/**
	* Metodo privado recursivo para exibir elementos.
	* @param no No em analise.
	*/
	private void mostrarPos(No no) {
		if (no != null) {
			mostrarPos(no.esq); // Elementos da esquerda.
			mostrarPos(no.dir); // Elementos da direita.
			System.out.print(no.elemento + " "); // Conteudo do no.
		}
	}


	/**
	* Metodo publico iterativo para inserir elemento.
	* @param x Elemento a ser inserido.
	* @throws Exception Se o elemento existir.
	*/
	public void inserir(Presidente x) throws Exception {
		raiz = inserir(raiz, x);
	}

	/**
	* Metodo privado recursivo para inserir elemento.
	* @param no No em analise.
	* @param x Elemento a ser inserido.
	* @return No em analise, alterado ou nao.
	* @throws Exception Se o elemento existir.
	*/
	private No inserir(No no, Presidente x) throws Exception {
		if (no == null) {
			//System.out.println("Inserindo o " + x);
			no = new No(x);
			comp++;
		} else if (x.getNome( ).compareTo( no.elemento.getNome( ) ) < 0 ) {
			//System.out.println("-> esq");
			no.esq = inserir(no.esq, x);
			comp++;
		} else if (x.getNome( ).compareTo( no.elemento.getNome( ) ) > 0 ) {
			//System.out.println("-> dir");
			no.dir = inserir(no.dir, x);
			comp++;
		} else {
			throw new Exception("Erro ao inserir elemento (" + x + ")! ");
		}

		no = balancear(no);
		return no;
	}

	private No balancear(No no) throws Exception {
		if(no != null){
			int fator = No.getNivel(no.dir) - no.getNivel(no.esq);
			comp++;
			//Se balanceada
			if (Math.abs(fator) <= 1){
				no = no.setNivel();
				comp++;
				//Se desbalanceada para a direita
			}else if (fator == 2){
				comp++;
				int fatorFilhoDir = No.getNivel(no.dir.dir) - No.getNivel(no.dir.esq);

				//Se o filho a direita tambem estiver desbalanceado
				if (fatorFilhoDir == -1) {
					no.dir = rotacionarDir(no.dir);
					comp++;
				}
				no = rotacionarEsq(no);

				//Se desbalanceada para a esquerda
			}else if (fator == -2){
				comp++;
				int fatorFilhoEsq = No.getNivel(no.esq.dir) - No.getNivel(no.esq.esq);

				//Se o filho a esquerda tambem estiver desbalanceado
				if (fatorFilhoEsq == 1) {
					no.esq = rotacionarEsq(no.esq);
					comp++;
				}
				no = rotacionarDir(no);

			}else{
				throw new Exception("Erro fator de balanceamento (" + fator + ") invalido!");
			}
		}

		return no;
	}

	private No rotacionarDir(No no) {
		//System.out.println("Rotacionar DIR(" + no.elemento + ")");
		No noEsq = no.esq;
		No noEsqDir = noEsq.dir;

		noEsq.dir = no;
		no.esq = noEsqDir;

		no.setNivel();
		noEsq.setNivel();

		return noEsq;
	}

	private No rotacionarEsq(No no) {
		//System.out.println("Rotacionar ESQ(" + no.elemento + ")");
		No noDir = no.dir;
		No noDirEsq = noDir.esq;

		noDir.esq = no;
		no.dir = noDirEsq;

		no.setNivel();
		noDir.setNivel();
		return noDir;
	}

	/**
	* Metodo publico iterativo para remover elemento.
	* @param elemento Elemento a ser removido.
	* @throws Exception Se nao encontrar elemento.
	*/
	public void remover(Presidente x) throws Exception {
		raiz = remover(raiz, x);
	}

	/**
	* Metodo privado recursivo para remover elemento.
	* @param no No em analise.
	* @param x Elemento a ser removido.
	* @return No em analise, alterado ou nao.
	* @throws Exception Se nao encontrar elemento.
	*/
	private No remover(No no, Presidente x) throws Exception {

		if (no == null) {
			throw new Exception("Erro ao remover!");

		} else if (x.getNome().compareTo(no.elemento.getNome()) < 0) {
			no.esq = remover(no.esq, x);

		} else if (x.getNome().compareTo(no.elemento.getNome()) > 0){
			no.dir = remover(no.dir, x);

			// Sem no a direita.
		} else if (no.dir == null) {
			no = no.esq;

			// Sem no a esquerda.
		} else if (no.esq == null) {
			no = no.dir;

			// No a esquerda e no a direita.
		} else {
			no.esq = antecessor(no, no.esq);
		}

		no = balancear(no);
		return no;
	}

	/**
	* Metodo para trocar no removido pelo antecessor.
	* @param n1 No que teve o elemento removido.
	* @param n2 No da subarvore esquerda.
	* @return No em analise, alterado ou nao.
	*/
	private No antecessor(No n1, No n2) {

		// Existe no a direita.
		if (n2.dir != null) {
			// Caminha para direita.
			n2.dir = antecessor(n1, n2.dir);

			// Encontrou o maximo da subarvore esquerda.
		} else {
			n1.elemento = n2.elemento; // Substitui N1 por N2.
			n2 = n2.esq; // Substitui N2 por N2.ESQ.
		}
		return n2;
	}
}

class ArvoreBinaria{
	private Presidente raiz; // Raiz da arvore.
	public int comp = 0, mov = 0;

	public int getComp( ){
		return comp;
	}

	public int getMov( ){
		return mov;
	}

	public ArvoreBinaria (){
		raiz = null;
	}//end ArvoreBinaria()

	public void inserir( Presidente elemento )throws Exception{
		raiz = inserir( elemento, raiz );
	}//end inserir( )

	public Presidente inserir ( Presidente elemento, Presidente No ) throws Exception{
		if ( No == null ){
			comp++;
			No = new Presidente( elemento );
		}else if( elemento.getNome().compareTo( No.elemento.getNome()  ) < 0 ){
			comp++;
			No.esq = inserir( elemento, No.esq );
		}else if( elemento.getNome().compareTo( No.elemento.getNome( ) ) > 0 ){
			comp++;
			No.dir = inserir( elemento, No.dir );
		}else{
			throw new Exception("Erro ao inserir");
		}
		return No;
	}//end inserir

	public boolean pesquisar( String x ){
		return pesquisar( x, raiz );
	}//end pesquisa( )

	public boolean pesquisar( String x, Presidente i ){
		boolean resp;

		if ( i == null ){
			comp++;
			resp = false;
		}else if( x.equals( i.elemento.getNome( ) ) ){
			comp++;
			resp = true;
		}else if( x.compareTo( i.elemento.getNome( ) ) < 0 ){
			comp++;
			MyIO.print("esq ");
			resp = pesquisar ( x, i.esq );
		}else{
			MyIO.print("dir ");
			resp = pesquisar ( x, i.dir );
		}
		return resp;

	}//end pesquisar( )

	public void mostraPre( String nome ){
		mostrarPre( raiz );
	}//end mostrarPre( )

	public void mostrarPre( Presidente i ){
		if( i != null ){
			i.elemento.imprimir( );
			mostrarPre( i.esq );
			mostrarPre( i.dir );
		}
	}//end mostrarPreRecursiva

}//end class

public class Presidente{
	Presidente elemento, esq, dir;

	public Presidente ( Presidente elemento ){
		this.elemento = elemento;
		this.esq = null;
		this.dir = null;
	}//end presidente No

	public Presidente( ){
		this.nome = this.localNascimento = this.localMorte = this.antecessor = this.sucessor = this.vice = this.pagina = "";
		this.id = this.idade = 0;
	}//Construtor


	public Presidente(LocalDateTime dataNascimento, LocalDateTime inicioMandato, LocalDateTime fimMandato,
	LocalDateTime dataMorte, int id, int idade, String nome, String localNascimento, String localMorte,
	String antecessor, String sucessor, String vice, String pagina, long paginaTam) {

		this.id = id;
		this.idade = idade;
		this.nome = nome;
		this.localNascimento = localNascimento;
		this.localMorte = localMorte;
		this.antecessor = antecessor;
		this.sucessor = sucessor;
		this.vice = vice;
		this.pagina = pagina;
		this.paginaTam = paginaTam;
		this.dataNascimento = dataNascimento;
		this.inicioMandato = inicioMandato;
		this.fimMandato = fimMandato;
		this.dataMorte = dataMorte;
	}

	private String nome,
	localNascimento,
	localMorte,
	antecessor,
	sucessor,
	vice,
	pagina;

	private int id,
	idade;

	private LocalDateTime dataNascimento,
	inicioMandato,
	fimMandato,
	dataMorte;

	private long paginaTam;

	//Gets----------------------------------------------------------

	//Primeiro get int, apos get string e por ultimo get LocalDateTime

	//get int---------------------------

	public int getId( ){
		return id;
	}

	public int getIdade( ){
		return idade;
	}

	//get String-------------------------

	public String getNome( ){
		return nome;
	}

	public String getLocalNascimento( ){
		return localNascimento;
	}

	public String getLocalMorte( ){
		return localMorte;
	}

	public String getAntecessor( ){
		return antecessor;
	}

	public String getSucessor( ){
		return sucessor;
	}

	public String getVice( ){
		return vice;
	}

	public String getPagina( ){
		return pagina;
	}

	//get LocalDateTime-------------------

	public LocalDateTime getDataNascimento( ){
		return dataNascimento;
	}

	public LocalDateTime getInicioMandato( ){
		return inicioMandato;
	}

	public LocalDateTime getFimMandato( ){
		return fimMandato;
	}

	public LocalDateTime getDataMorte( ){
		return dataMorte;
	}

	public long getPaginaTam( ){
		return paginaTam;
	}

	//END GET--------------------------------------------------------

	//INICIO DO SET, INT, STRING, LOCALDATETIME----------------------

	//int-----------------

	public void setId( int id ){
		this.id = id;
	}

	public void setIdade( int idade ){
		this.idade = idade;
	}

	//String---------------

	public void setNome( String nome ){
		this.nome = nome;
	}

	public void setLocalNascimento( String localNascimento ){
		this.localNascimento = localNascimento;
	}

	public void setLocalMorte( String localMorte ){
		this.localMorte = localMorte;
	}

	public void setAntecessor( String antecessor ){
		this.antecessor = antecessor;
	}

	public void setSucessor( String sucessor ){
		this.sucessor = sucessor;
	}

	public void setVice( String vice ){
		this.vice = vice;
	}

	public void setPagina( String pagina ){
		this.pagina = pagina;
	}

	//LocalDateTime----------

	public void setDataNascimento( LocalDateTime dataNascimento ){
		this.dataNascimento = dataNascimento;
	}

	public void setInicioMandato( LocalDateTime inicioMandato ){
		this.inicioMandato = inicioMandato;
	}

	public void setFimMandato( LocalDateTime fimMandato ){
		this.fimMandato = fimMandato;
	}

	public void setDataMorte( LocalDateTime dataMorte ){
		this.dataMorte = dataMorte;
	}

	//Long------------------

	public void setPaginaTam( long paginaTam ){
		this.paginaTam = paginaTam;
	}

	//end sets------------------------------------------------------



	//METODO limparDatas( )
	public String limparDatas( String linha ){

		String aux = "",temp = "";
		char c;
		int contBarras = 0, contChar = 0;

		if( linha.contains("de")){
			linha = linha.replace("de","/");
		}//end if

		if( linha.contains("janeiro") ){
			linha = linha.replace("janeiro","1");
		}//if janeiro

		if( linha.contains("fevereiro") ){
			linha = linha.replace("fevereiro","2");
		}//if fevereiro

		if( linha.contains("mar") ){
			linha = linha.replace("mar\u00E7o","3");
		}//if março

		if( linha.contains("abril") ){
			linha = linha.replace("abril","4");
		}//if abril

		if( linha.contains("maio") ){
			linha = linha.replace("maio","5");
		}//if maio

		if( linha.contains("junho") ){
			linha = linha.replace("junho","6");
		}//if junho

		if( linha.contains("julho") ){
			linha = linha.replace("julho","7");
		}//if julho

		if( linha.contains("agosto") ){
			linha = linha.replace("agosto","8");
		}//if agosto

		if( linha.contains("setembro") ){
			linha = linha.replace("setembro","9");
		}//if setembro

		if( linha.contains("outubro") ){
			linha = linha.replace("outubro","10");
		}//if outubro

		if( linha.contains("novembro") ){
			linha = linha.replace("novembro","11");
		}//if novembro

		if( linha.contains("zembro") ){
			linha = linha.replace("zembro","12");
		}//if dezembro

		for( int i = 0; i < linha.length( ); i++ ){
			if( ( linha.charAt( i ) >= '0'  &&
			linha.charAt( i ) <= '9') ||
			linha.charAt( i ) == '/'){
				aux += linha.charAt( i );
			}//end if
		}//end for limpar a data

		if( aux.contains("//") ){
			aux = aux.replace( "//","/" );
		}//end if

		linha = aux;

		if( aux.length() > 10 ){
			linha = "";

			//separar data inicio de data fim
			for( int i = 0; i < aux.length( ); i++){
				c = aux.charAt( i );

				if( c == '/' ){
					contBarras++;
				}//end if

				if( contBarras < 2 ){
					linha += c;
				}else{
					if(contChar<=4){
						temp += c;
						contChar++;
					}else{
						temp+=" "+c;
						linha+=temp;
						contBarras-=4;
					}//end else
				}//else contBarras

			}//end for

		}//if duas datas

		return linha;
	}//end limparDatas( )


	//FUNCAO responsavel por remover as tags < >, e retornar a String tratada
	public String getInfo( String linha ){
		int beginTag = 0, endTag = 0;

		//(beginTag = primeira "<" Encontrado;
		//Enquanto houver tags;
		//beginTag = novoPrimeiro "<" Encontrado)
		for ( beginTag = linha.indexOf("<");
		linha.contains("<") && linha.contains(">");
		beginTag = linha.indexOf("<") ){

			endTag = linha.indexOf(">");

			//if remover &#32
			if( linha.contains("&#32")){
				linha = linha.replace("&#32","");
			}//end if

			//if remover&#160
			if( linha.contains("&#160")){
				linha = linha.replace("&#160","");
			}//end if

			linha = linha.replace( linha.substring(beginTag,endTag+1), "" );
		}//end for

		for( beginTag=linha.indexOf("["); linha.contains("[") && linha.contains("]"); beginTag = linha.indexOf("[")){

			endTag = linha.indexOf("]");
			linha = linha.replace( linha.substring(beginTag,endTag+1),"");

		}//end for [nota 1]

		return linha;
	}//end getInfo( )


	//METODO responsavel por ler do arquivo e "setar" os atributos de cada presidente;
	public static Presidente ler( String url ){
		Presidente presidente = new Presidente( );

		String leitura = "",x = "",aux ="";
		int ant = 0, suc = 0, vice = 0,j = 0, contId=0, contNome = 0, contInicioMandato = 0, contChar = 0, tamanho = 0;

		if( url == null ){
			MyIO.println("ERRO Url inacessivel");
		}else{

			//abertura de arquivo
			Arq.openRead( url, "UTF-8");
			tamanho = (int)Arq.length( );
			// MyIO.println(tamanho);
			presidente.setPaginaTam( tamanho );
			presidente.setPagina( url );
			//MyIO.println(presidente.getPagina( ) );

			//Leitura e teste de atributos Linha apos Linha.
			while( Arq.hasNext( ) ){
				leitura = Arq.readLine();
				aux ="";

				//if setId( )----------------------------------------------------------------
				if( contId < 1 &&
				(leitura.contains("<th")) &&
				(leitura.contains("Lista de presidentes do Brasil")) ){

					//metodo de tratamento de atributo id
					contId++;
					x = presidente.getInfo( leitura );

					if( x.charAt(1) >= '0' &&
					x.charAt(1) <= '9'){

						x = x.substring( 0,2 );
					}else{
						x = x.substring( 0,1 );
					}//end else

					presidente.setId( Integer.parseInt(x) );
				}//end if setId( )

				//if setInicioeFimMandato()-------------------------------------------------
				if( leitura.contains("\">Per") && contInicioMandato < 1 ){
					contInicioMandato++;

					//informacao duas linhas abaixo
					leitura = Arq.readLine( );
					leitura = Arq.readLine( );

					//tratar os dados obtidos da linha html e retornarndo dd/mm/aaaa em String
					leitura = presidente.limparDatas( (presidente.getInfo(leitura)));

					//separar inicio do fim do mandato, apos separar em dia mes ano
					String[] periodo = leitura.split(" ");
					String[] data = periodo[0].split("/");
					String[] dataFim = periodo[1].split("/");
					//objeto responsavel por criar o LocalDateTime com dia mes e ano, cast de String para Int.
					LocalDateTime date = LocalDateTime.of( Integer.parseInt(data[2]), Integer.parseInt(data[1]), Integer.parseInt(data[0]) ,0 ,0 );
					//setInicioMandato
					presidente.setInicioMandato( date );
					//setFimMandato
					date = LocalDateTime.of( Integer.parseInt(dataFim[2]), Integer.parseInt(dataFim[1]), Integer.parseInt(dataFim[0]) ,0 ,0 );
					presidente.setFimMandato( date );

				}//end ifInicioMandato

				//if setVice( )------------------------------------------------------
				if( leitura.contains( ";\">Vice-presidente") && vice < 1){
					vice++;
					leitura = Arq.readLine( );
					leitura = Arq.readLine( );
					leitura = presidente.getInfo( leitura );
					presidente.setVice( leitura );
				}//end if setVice( )------------------------------------------------

				//if setAntecessor()------------------------------------------------
				if( ant < 1 &&leitura.contains(";\">Antecessor") ){
					ant++;
					leitura = Arq.readLine( );
					leitura = Arq.readLine( );
					leitura = presidente.getInfo( leitura );
					presidente.setAntecessor( leitura );
				}//end if setAntecessor()-------------------------------------------

				//if setSucessor( )
				if ( suc < 1 && leitura.contains(";\">Sucessor") ){
					suc++;
					leitura = Arq.readLine( );
					leitura = Arq.readLine( );
					leitura = presidente.getInfo( leitura );
					presidente.setSucessor( leitura );
				}//end if setSucessor( )--------------------------------------------

				//if setLocalNascimento-------------------------------------------------
				if( leitura.contains("left;\">Nascimento") ){
					leitura = Arq.readLine( );
					leitura = Arq.readLine( );
					leitura = presidente.getInfo(leitura);
					String linha = leitura;
					int beginTag, endTag;

					for( beginTag=linha.indexOf("("); linha.contains("(") && linha.contains(")"); beginTag = linha.indexOf("(")){

						endTag = linha.indexOf(")");
						linha = linha.replace( linha.substring(beginTag,endTag+1),"");

					}//end for ( )

					leitura = linha;

					//primeira posicao do for a seguir
					int k = leitura.indexOf("d");
					x ="";

					for( int i = 0; i<k; i++ ){
						x+=leitura.charAt(i);
					}//char antes do d

					//for separar dataNascimento de localNascimento
					for( int i = k; i < leitura.length( ); i++ ){
						if( leitura.charAt( i ) >= '0' && leitura.charAt( i ) <='9' ){
							contChar++;
						}
						if( contChar == 4){
							x += leitura.charAt( i );
							leitura = leitura.substring( i+1, leitura.length( ));
							i = leitura.length( );
						}else{
							x += leitura.charAt( i );
						}//end else
					}//end for
					x = presidente.limparDatas(x);
					int dia, mes, ano;
					String[] data = x.split("/");
					dia = Integer.parseInt( data[0] );
					mes = Integer.parseInt( data[1] );
					ano = Integer.parseInt( data[2] );
					//objeto responsavel por criar o LocalDateTime com dia mes e ano, cast de String para Int.
					LocalDateTime date = LocalDateTime.of( ano, mes, dia, 0 ,0 );
					//setIdade
					presidente.setIdade( 2019 - date.getYear( ) );
					//setInicioMandato
					presidente.setDataNascimento( date );

					//Limpar localNascimento
					if( leitura.charAt(1) >= 'A' &&
					leitura.charAt(1) <= 'Z'){

						leitura = leitura.substring( 1, leitura.length( ) );
					}else{
						leitura = leitura.substring( 2, leitura.length( ) );
					}//end else

					presidente.setLocalNascimento( leitura );
				}//end if setLocalNascimento( )--------------------------------------

				//if  setNome( )------------------------------------------------------------
				if( leitura.contains(";\">Nome completo") ){

					leitura = Arq.readLine( );
					leitura = Arq.readLine( );
					presidente.setNome( presidente.getInfo( leitura ) );
				}//end if setNome( )--------------------------------------------------------

				//if setLocalMorte e setDataMorte---------------------------------------------
				if ( leitura.contains( ";\">Morte") ){
					leitura = Arq.readLine( );
					leitura = Arq.readLine( );
					leitura = presidente.getInfo ( leitura );

					String linha = leitura;
					int beginTag, endTag;

					for( beginTag=linha.indexOf("("); linha.contains("(") && linha.contains(")"); beginTag = linha.indexOf("(")){
						endTag = linha.indexOf(")");
						linha = linha.replace( linha.substring(beginTag,endTag+1),"");
					}//end for ( )

					leitura = linha;

					//primeira posicao do for a seguir
					int k = leitura.indexOf("d");
					x = "";//limpar variavel
					contChar = 0;

					for( int i = 0; i<k; i++ ){
						x+=leitura.charAt(i);
					}//for armazenar o que vem antes da letra D do teste

					//for separar dataNascimento de localNascimento atraves do ano que contem 4 char inteiros
					for( int p = k; p < leitura.length( ); p++ ){

						if( leitura.charAt( p ) >= '0' && leitura.charAt( p ) <='9' ){
							contChar++;
						}//if contInteiros /YYYY/

						if( contChar == 4){
							x += leitura.charAt( p );
							leitura = leitura.substring( p+1, leitura.length( ));
							p = leitura.length( );
						}else{
							x += leitura.charAt( p );
						}//end else

					}//end

					//x contem a data separada do local da morte
					x = presidente.limparDatas(x);

					//  MyIO.println("pos limpar datas\n "+ x );
					int dia, mes, ano;
					String[] data = x.split("/");

					dia = Integer.parseInt( data[0] );
					mes = Integer.parseInt( data[1] );
					ano = Integer.parseInt( data[2] );

					//objeto responsavel por criar o LocalDateTime com dia mes e ano, cast de String para Int.
					LocalDateTime date = LocalDateTime.of( ano, mes, dia, 0 ,0 );

					//setInicioMandato
					presidente.setDataMorte( date );

					//Limpar localMorte
					if( leitura.charAt(1) >= 'A' &&
					leitura.charAt(1) <= 'Z'){

						leitura = leitura.substring( 1, leitura.length( ) );
					}else{
						leitura = leitura.substring( 2, leitura.length( ) );
					}//end else

					presidente.setLocalMorte( leitura );
				}//end if setLocalMorte e setDataMorte

			}//end while hasNext( )
		}//end else
		Arq.close( );
		return presidente;
	}//end ler( )

	public void imprimir( ){
		DateTimeFormatter formatter = DateTimeFormatter.ofPattern("d/M/yyy");
		if( this.getDataMorte( ) == null ){

			MyIO.println( this.getId( ) + " ## "
			+ this.getNome( ) + " ## "
			+ this.getInicioMandato( ).format(formatter)+" (I) ## "
			+ this.getFimMandato( ).format(formatter)+" (F) ## "
			+ this.getDataNascimento( ).format(formatter)+" em "
			+ this.getLocalNascimento( )+" (N) ## "
			+ this.getIdade( )+ " ## "
			+ this.getPagina( )+ " ## "
			+ this.getPaginaTam( )+ " ## "
			+ this.getAntecessor( )+ " ## "
			+ this.getSucessor( )+ " ## "
			+ this.getVice( ));
		}else{
			MyIO.println( this.getId( ) + " ## "
			+ this.getNome( ) + " ## "
			+ this.getInicioMandato( ).format(formatter)+" (I) ## "
			+ this.getFimMandato( ).format(formatter)+" (F) ## "
			+ this.getDataNascimento( ).format(formatter)+" em "
			+ this.getLocalNascimento( )+" (N) ## "
			+ this.getIdade( )+ " ## "
			+ this.getDataMorte( ).format(formatter)+" em "
			+ this.getLocalMorte( )+" (M) ## "
			+ this.getPagina( )+ " ## "
			+ this.getPaginaTam( )+ " ## "
			+ this.getAntecessor( )+ " ## "
			+ this.getSucessor( )+ " ## "
			+ this.getVice( ));
		}
	}//end imprimir( );

	public static void main ( String [] args )throws Exception{
		AVL av = new AVL( );
		MyIO.setCharset("UTF-8");
		String [ ] nomes = new String [150];
		String input = MyIO.readLine( );
		int i = 0;

		while( !input.contains("FIM")){
			av.inserir( ler(input) );
			input = MyIO.readLine( );
		}//end while

		input = MyIO.readLine();
		long start = System.currentTimeMillis( );

		while( !input.contains("FIM") ){
			MyIO.print( input + " raiz " );
			if( av.pesquisar( input ) ){
				System.out.println("SIM");
			}else{
				MyIO.println("N"+ ((char)195) +"O");
			}
			input = MyIO.readLine( );
		}//end while pesquisa()


		Arq log = new Arq( );
		log.openWrite ("608507_avl.txt");
		log.print("608507"+"\t"+ (( System.currentTimeMillis( ) - start ))+ "\t" + av.getComp( ));
		log.close( );

	}//end MAIN

}//end class
