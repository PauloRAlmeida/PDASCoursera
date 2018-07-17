package DAO;

import java.sql.ResultSet;

import entity.Livro;

public interface ILivroDAO {

	public ResultSet recuperarTodosLivros();
	
	public ResultSet recuperarLivro(Livro livro);

}
