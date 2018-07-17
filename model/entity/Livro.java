package entity;

public class Livro {
	
	public Livro() {}
	public Livro(int id, String nome, String estilo, int paginas) {
		this.id = id;
		this.nome = nome;
		this.estilo = estilo;
		this.paginas = paginas;
	}
	
	private int id;
	private String nome;
	private String estilo;
	private int paginas;
	
	
	//Metodo que calcula pontos por pagina
	public int pontosPorPaginas() {
		if (paginas<=100) return 1;
		else { return Math.floorDiv(paginas, 100);
		}
	}
	
	//Getters e Setters
	public int getId() {
		return id;
	}
	public String getNome() {
		return nome;
	}
	public void setNome(String nome) {
		this.nome = nome;
	}
	public String getEstilo() {
		return estilo;
	}
	public void setEstilo(String estilo) {
		this.estilo = estilo;
	}
	public int getPaginas() {
		return paginas;
	}
	public void setPaginas(int paginas) {
		this.paginas = paginas;
	}


	

}

