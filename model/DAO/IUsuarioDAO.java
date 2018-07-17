package DAO;
import java.sql.ResultSet;
import java.util.List;

import entity.Livro;
import entity.Usuario;
public interface IUsuarioDAO {
	//insere um novo usu�rio no banco de dados
	   public void inserir(Usuario usuario);
	   
	   //recupera o usu�rio pelo seu login
	   public ResultSet recuperar(String email);
	   
	   //adiciona os pontos para o usu�rio no banco
	   public void adicionarPontos(Usuario usuario);
	   
	   //retorna a lista de usu�rios ordenada por pontos (maior primeiro)
	   public ResultSet ranking();
	   
	   //Adiciona o livro no banco de livros lidos do usuario
	   public void adicionarLivro(String email,int livro_id);
	   
	   //retorna a lista de livros lidos do usuario
	   public ResultSet livrosLidos(String email);

	   public ResultSet listaLivros();



	  
}
