	package entity;

import java.util.LinkedList;
import java.util.List;

public class Usuario {
	
	private int id;
	private String nome;
	private String email;
	private String senha;
	private List<Livro> livrosLidos = new LinkedList<>();
	private List<String> trofeus = new LinkedList<>();
	private int pontos;
	
	public Usuario() {}
		
	public Usuario(String nome, String email, String senha, int pontos) {
		this.nome = nome;
		this.email = email;
		this.senha = senha;
		this.pontos = pontos;
	}


	//Calcula os trofeus do usuario
	private void trofeuLivros(){
		String estilo = "";
		String anterior = "";
		int vezes;
		for (Livro livro: livrosLidos)  {
			vezes = 0;
			estilo = livro.getEstilo();
			if (estilo!=(anterior)) {
			for (int i = 0; i < livrosLidos.size(); i++) {
				if (livrosLidos.get(i).getEstilo().equals(estilo)) {
					vezes += 1;
					if (vezes >= 5) trofeus.add(estilo);
					}		
				}
			}
			anterior = livro.getEstilo();
		}
	}
	
	//Adiciona livro a Lista Livros Lidos
	public void addLivro(Livro livro) {
		this.livrosLidos.add(livro);
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
	public String getEmail() {
		return email;
	}
	public void setEmail(String email) {
		this.email = email;
	}
	public String getSenha() {
		return senha;
	}
	public void setSenha(String senha) {
		this.senha = senha;
	}
	public List<Livro> getLivrosLidos() {
		return livrosLidos;
	}
	public void setLivrosLidos(List<Livro> livrosLidos) {
		this.livrosLidos = livrosLidos;
	}
	
	public int getPontos() {
		return pontos;
	}
	
	public List<String> getTrofeus() {
		trofeuLivros();
		return trofeus;
	}
	
	//Metodos privados pois não podem ser setados de forma manual, apenas utilizano os metodos da classe.
	public void setPontos(int pontos) {
		this.pontos = pontos;
	}
	private void setTrofeus(List<String> trofeus) {
		this.trofeus = trofeus;
	}


	
}