
import java.io.IOException;
import java.sql.SQLException;
import java.util.LinkedList;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import entity.Livro;
import entity.Usuario;


@WebServlet(name = "controllerUsuario", urlPatterns = {"/controllerUsuario"})
public class ControllerUsuario extends HttpServlet {
	
	private static final long serialVersionUID = 1L;
	private BusinessUsuario businessUsuario = new BusinessUsuario();   
    
	public ControllerUsuario() {
        super();
    }

	
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		String acaoFormulario = request.getParameter("formAction");
		if(acaoFormulario.equals("Cadastrar usuario")){
			inserirUsuario(request, response);
		}
		else if(acaoFormulario.equals("Entrar")){
			login(request, response);
		}
		else if (acaoFormulario.equals("Adicionar Livro")) {
			adicionaLivro(request, response);
		}
	}
	
	public void login(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		String email = request.getParameter("email");
		String senha = request.getParameter("senha");
		try{
			if(senha.equals(businessUsuario.recuperarUsuario(email).getSenha())){
				request.setAttribute("msg", "Olá "+ businessUsuario.recuperarUsuario(email).getNome());
				HttpSession sessao = request.getSession();
				sessao.setAttribute("login", email);	
				List<Livro> livros = businessUsuario.livorsLidos(email);
				if (livros!=null) {
					List<String> trofeus = trofeuLivros(livros);
					request.setAttribute("trofeus", trofeus);
				}
			}
				request.setAttribute("pontos", businessUsuario.recuperarUsuario(email).getPontos());
				request.getRequestDispatcher("view/profile.jsp").forward(request, response);
		}catch(Exception e){
			request.setAttribute("msg", "Não foi possivel realizar o Login: <br>"+e.getMessage());
			request.getRequestDispatcher("view/erroLogin.jsp").forward(request, response);
		}
	}
	
	public void adicionaLivro(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		{
		String paginasLidas = request.getParameter("paginas");
		Integer paginas = Integer.parseInt(paginasLidas);
		int pontos = 0;
		if (paginas<=100) { 
			pontos = 1;
		} else pontos =  Math.floorDiv(paginas, 100);
		String id = request.getParameter("idLivro");
		String email = request.getParameter("email");
		int livroId = Integer.parseInt(id);
		try {
			businessUsuario.insereLivro(email, livroId);
			businessUsuario.calculaPontos(email,pontos);
			request.setAttribute("msg", "Livro Adicionado com sucesso");
			request.getRequestDispatcher("view/livroAdicionado.jsp").forward(request, response);
		}catch(Exception e){
			request.setAttribute("msg", "Erro ao adicionar o livro: <br>"+ e.getMessage());
			request.getRequestDispatcher("view/erroLivro.jsp").forward(request, response);
			}
		}
	}
	
	private void inserirUsuario(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		String nome = request.getParameter("nome");
		String email = request.getParameter("email");
		String senha = request.getParameter("senha");
		try{
			this.businessUsuario.insereUsuario(email,nome,senha);
			request.setAttribute("msg", "Cadastro realizado com sucesso, <br> entre com os seus dados");
			request.getRequestDispatcher("view/login.jsp").forward(request, response);
		}catch(Exception e){
			request.setAttribute("msg", "Nï¿½o foi possï¿½vel realizar o cadastro: <br>"+e.getMessage());
			request.getRequestDispatcher("view/erroCadastro.jsp").forward(request, response);
		}
	}
	
	
	private void redirecionaParaRanking(HttpServletRequest request, HttpServletResponse response) {
		try {
			request.setAttribute("usuarios", businessUsuario.ranking());
			for (Usuario u: businessUsuario.ranking())
				System.out.println(u.getPontos());
			request.getRequestDispatcher("view/ranking.jsp").forward(request, response);
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
	
		protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
			String acao = request.getParameter("action");
			if(acao.equals("ranking")) this.redirecionaParaRanking(request, response);
			if(acao.equals("listaDeLivros")) this.redirecionaParaListaLivros(request, response);
			if(acao.equals("livrosLidos")) this.redirecionaParaLivrosLidos(request, response);
			if(acao.equals("profile")) this.redirecionaParaProfile(request, response);


		}
		
		private void redirecionaParaProfile(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
			HttpSession sessao = request.getSession();
			String email = (String) sessao.getAttribute("login");	
			try{
					List<Livro> livros = businessUsuario.livorsLidos(email);
					if (livros!=null) {
						List<String> trofeus = trofeuLivros(livros);
						request.setAttribute("trofeus", trofeus);
					}
					request.setAttribute("pontos", businessUsuario.recuperarUsuario(email).getPontos());
					request.getRequestDispatcher("view/profile.jsp").forward(request, response);
			}catch(Exception e){
				request.setAttribute("msg", "Não foi possivel realizar o Login: <br>"+e.getMessage());
				request.getRequestDispatcher("view/erroLogin.jsp").forward(request, response);
			}
		}


		private void redirecionaParaListaLivros(HttpServletRequest request, HttpServletResponse response) {
			try {
				request.setAttribute("livros", businessUsuario.listaLivros());
				request.getRequestDispatcher("view/livros.jsp").forward(request, response);
			} catch (Exception e) {
				e.printStackTrace();
			}
	}
		
		private void redirecionaParaLivrosLidos(HttpServletRequest request, HttpServletResponse response) {
			HttpSession sessao = request.getSession();
			String email = (String) sessao.getAttribute("login");
			try {
				request.setAttribute("livrosLidos", businessUsuario.livorsLidos(email));
				request.getRequestDispatcher("view/livrosLidos.jsp").forward(request, response);
			} catch (Exception e) {
				e.printStackTrace();
			}
			
			
	}
		
		private List<String> trofeuLivros(List<Livro> livros){
			List<String> trofeus = new LinkedList<>();
			String estilo = "";
			String anterior = "";
			int vezes;
			for (Livro livro: livros)  {
				vezes = 0;
				estilo = livro.getEstilo();
				if (estilo!=(anterior)) {
				for (int i = 0; i < livros.size(); i++) {
					if (livros.get(i).getEstilo().equals(estilo)) {
						vezes += 1;
						if (vezes >= 5) {
							if (trofeus.contains(estilo)) continue;
							trofeus.add(estilo);
						}
						}		
					}
				}
				anterior = livro.getEstilo();
			}
			
			return trofeus;
		}

	

}
