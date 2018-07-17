import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;

import DAO.UsuarioDAO;
import entity.Livro;
import entity.Usuario;

public class BusinessUsuario {
	
	
	UsuarioDAO usuarioDAO = new UsuarioDAO();
	
	public List<Livro> livorsLidos(String email) throws Exception{
		List<Livro> listaLivros = new LinkedList<>();
		Livro livro = new Livro();
		try {
			ResultSet rs = usuarioDAO.livrosLidos(email);
			while (rs.next()) {
				livro = new Livro(rs.getInt("id"), rs.getString("nome"), rs.getString("estilo"), rs.getInt("paginas"));
				listaLivros.add(livro);
			}
			return listaLivros;
		} catch (Exception e) {
			throw new Exception("Não foi possível recuperar" + e.getMessage());
		}

	}
	
	public boolean insereUsuario(String email, String nome, String senha) throws Exception {
		Usuario usuario = new Usuario();
		usuario.setEmail(email);
		usuario.setNome(nome);
		usuario.setSenha(senha);
		try {
			this.usuarioDAO.inserir(usuario);
			return true;
		} catch (Exception e) {
			throw new Exception("Não foi possível fazer o cadastro" + e.getMessage());
		}
	}

	public Usuario recuperarUsuario(String email) throws Exception {
		Usuario usuario = new Usuario();
		try {
			ResultSet resultado = usuarioDAO.recuperar(email);
			if (resultado.next()) {
				usuario = montaUsuario(resultado);
			}
			return usuario;
		} catch (Exception e) {
			throw new Exception("Não foi possível recuperar" + e.getMessage());
		}

	}

	public List<Usuario> ranking() throws Exception {

		ResultSet resultado = null;
		List<Usuario> listaUsuarios = new ArrayList<>();
		try {
			resultado = usuarioDAO.ranking();
			while (resultado.next()) {
				Usuario usuario = montaUsuario(resultado);
				listaUsuarios.add(usuario);
			}
			return listaUsuarios;
		} catch (Exception e) {
			throw new Exception("Não foi possível recuperar" + e.getMessage());
		}

	}
	
	public List<Livro> listaLivros() throws Exception{
		ResultSet rs = null;
		List<Livro> listaLivros = new ArrayList<>();
		try {
			rs = usuarioDAO.listaLivros();
			while (rs.next()) {
				Livro livro = new Livro(rs.getInt("id"), rs.getString("nome"), rs.getString("estilo"), rs.getInt("paginas"));
				listaLivros.add(livro);
			}
			return listaLivros;
		
		} catch (Exception e) {
			throw new Exception("Não foi possível recuperar" + e.getMessage());
		}

	}
	
	public Usuario montaUsuario(ResultSet rs) throws SQLException {
		Usuario usuarioMontado = new Usuario(rs.getString("nome"),rs.getString("email"),rs.getString("senha"),rs.getInt("pontos"));
		return usuarioMontado;
	}

	public boolean insereLivro(String email, int id) throws Exception {
	try {
		usuarioDAO.adicionarLivro(email, id);
		return true;
	} catch (Exception e) {
		throw new Exception("Não foi possível Realizar a operação" + e.getMessage());
	}
	}

	public boolean calculaPontos(String email, int pontos) throws Exception{
		try {
		usuarioDAO.calculaPontos(email, pontos);
		return true;
	} catch (Exception e) {
		throw new Exception("Não foi possível Realizar a operação" + e.getMessage());
	}
	}
}

		
	
