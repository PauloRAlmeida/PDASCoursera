package DAO;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import entity.Usuario;

public class UsuarioDAO implements IUsuarioDAO {

	private String sql;
	private static final String url = "jdbc:postgresql://localhost/java";
	private static final String usuarioBD = "pj";
	private static final String senhaBD = "030392";

	static {
		try {
			Class.forName("org.postgresql.Driver");
		} catch (ClassNotFoundException e) {
			e.printStackTrace();
		}
	}

	@Override
	public void inserir(Usuario novoUsuario) {
		try (Connection conexao = DriverManager.getConnection(url,usuarioBD,senhaBD)) {
			this.setSql("INSERT INTO usuario(nome, email, senha, pontos) VALUES (?, ?, ?, ?)");
			PreparedStatement pstmt = conexao.prepareStatement(this.getSql());
			pstmt.setString(1, novoUsuario.getNome());
			pstmt.setString(2, novoUsuario.getEmail());
			pstmt.setString(3, novoUsuario.getSenha());
			pstmt.setInt(4, 0);
			pstmt.executeUpdate();
		} catch (SQLException e) {
			throw new RuntimeException("Não foi possível acessar o banco de dados: " + e);
		}
	}

	@Override
	public ResultSet recuperar(String email) {
		try (Connection conexao = DriverManager.getConnection(url,usuarioBD,senhaBD)) {
			String sql = "select * from usuario where email = ?";
			PreparedStatement stmt = conexao.prepareStatement(sql);
			stmt.setString(1, email);
			ResultSet rs = stmt.executeQuery();
			return rs;
		} catch (SQLException e) {
			throw new RuntimeException("Não foi possível executar o acesso: " + e);
		}

	}

	@Override
	public void adicionarPontos(Usuario usuario) {
		try (Connection conexao = DriverManager.getConnection(url,usuarioBD,senhaBD)) {
			this.setSql("UPDATE usuario SET pontos = pontos + ? WHERE email = ?");
			PreparedStatement pstmt = conexao.prepareStatement(this.getSql());
			pstmt.setInt(1, usuario.getPontos());
			pstmt.setString(2, usuario.getEmail());
			pstmt.executeUpdate();
		} catch (SQLException e) {
			throw new RuntimeException("Não foi possível acessar o banco de dados" + e);
		}
	}

	@Override
	public ResultSet ranking() {
		try (Connection conexao = DriverManager.getConnection(url,usuarioBD,senhaBD)){
			this.setSql("select * from usuario ORDER BY pontos DESC limit 10");
			PreparedStatement stmt = conexao.prepareStatement(this.getSql());
			ResultSet rs = stmt.executeQuery();
			return rs;
		} catch (SQLException e) {
			throw new RuntimeException("Não foi possível acessar o banco de dados: " + e);
		}
	}
	
	
	@Override
	public ResultSet listaLivros() {
		try (Connection conexao = DriverManager.getConnection(url,usuarioBD,senhaBD)){
			this.setSql("SELECT * from livro"); 
			PreparedStatement pstmt = conexao.prepareStatement(this.getSql());
			ResultSet rs = pstmt.executeQuery();
			return rs;
		} catch (SQLException e) {
			throw new RuntimeException("Não foi possível acessar o banco de dados: " + e);
		}
	}


	@Override
	public void adicionarLivro(String email, int id) {
		try (Connection conexao = DriverManager.getConnection(url,usuarioBD,senhaBD)){
			this.setSql("INSERT into usu_livro (email, livro_id) VALUES (?, ?)"); 
			PreparedStatement pstmt = conexao.prepareStatement(this.getSql());
			pstmt.setString(1, email);
			pstmt.setInt(2, id);
			pstmt.executeUpdate();
		} catch (SQLException e) {
			throw new RuntimeException("Não foi possível acessar o banco de dados: " + e);
			}	
	
	}
	
	@Override
	public ResultSet livrosLidos(String email) {
		try (Connection conexao = DriverManager.getConnection(url,usuarioBD,senhaBD)){
			this.setSql("select l.* from livro l, usu_livro ul  where ul.email = ? and l.id = ul.livro_id;"); 
			PreparedStatement pstmt = conexao.prepareStatement(this.getSql());
			pstmt.setString(1, email);
			ResultSet rs = pstmt.executeQuery();
			return rs;
		} catch (SQLException e) {
			throw new RuntimeException("Não foi possível acessar o banco de dados: " + e);
		}
	}

	
	private String getSql() {
		return sql;
	}

	private void setSql(String sql) {
		this.sql = sql;
	}

	public void calculaPontos(String email, int pontos) {
		try (Connection conexao = DriverManager.getConnection(url,usuarioBD,senhaBD)){
			this.setSql("update usuario set pontos = ? + pontos where email = ?"); 
			PreparedStatement pstmt = conexao.prepareStatement(this.getSql());
			pstmt.setInt(1, pontos);
			pstmt.setString(2, email);
			pstmt.executeUpdate();
		} catch (SQLException e) {
			throw new RuntimeException("Não foi possível acessar o banco de dados: " + e);
			}	
	
	}
	}

	
