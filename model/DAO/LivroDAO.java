package DAO;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import entity.Livro;
import entity.Usuario;

public class LivroDAO implements ILivroDAO{

	private String sql;
	private static final String url = "jdbc:postgresql://localhost/java";
	private static final String usuarioBD = "pj";
	private static final String senhaBD = "030392";
	
	static {
		try {
			Class.forName("org.postgresql.Driver");
		} catch (ClassNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	
	@Override
	public ResultSet recuperarTodosLivros() {
		// TODO Auto-generated method stub
		try (Connection conexao = DriverManager.getConnection(url,usuarioBD,senhaBD)) {
			this.setSql("Select * from livro");
			PreparedStatement pstmt = conexao.prepareStatement(this.getSql());
			ResultSet st = pstmt.executeQuery();
			return st;
		} catch (SQLException e) {
			throw new RuntimeException("Não foi possível acessar o banco de dados: " + e);
		}
	}
	
	@Override
	public ResultSet recuperarLivro(Livro livro) {
		// TODO Auto-generated method stub
		try (Connection conexao = DriverManager.getConnection(url,usuarioBD,senhaBD)) {
			this.setSql("Select * from livro where nome ?");
			PreparedStatement pstmt = conexao.prepareStatement(this.getSql());
			pstmt.setString(1, livro.getNome());
			ResultSet st = pstmt.executeQuery();
			return st;
		} catch (SQLException e) {
			throw new RuntimeException("Não foi possível acessar o banco de dados: " + e);
		}
	}
	
	
	public String getSql() {
		return sql;
	}
	public void setSql(String sql) {
		this.sql = sql;
	}
	
	
}
