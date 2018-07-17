



import java.io.IOException;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 * Servlet implementation class IndexController
 */
@WebServlet("/IndexController")
public class IndexController extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public IndexController() {
        super();
        // TODO Auto-generated constructor stub
    }

    /**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		try {
			this.verificaAction(request, response);
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		doGet(request, response);
	}
	protected void verificaAction(HttpServletRequest request, HttpServletResponse response) throws Exception {
		// TODO Auto-generated method stub
		String action = request.getParameter("action");
		 
        if (action == null) {
            throw new Exception("Erro 500 - erro na requisição");
        } else if (action.equals("criarConta")) {
            this.locationSignUpPage(request, response);
        } else if (action.equals("login")) {
            this.locationLoginPage(request, response);
        }
	}

	private void locationSignUpPage(HttpServletRequest request, HttpServletResponse response) throws Exception {
		// TODO Auto-generated method stub
	        String nomePagina = "criarConta.jsp";
	        this.redirect(request, response, nomePagina);
	}
	
	private void locationLoginPage(HttpServletRequest request, HttpServletResponse response) throws Exception {
		// TODO Auto-generated method stub
	        String nomePagina = "login.jsp";
	        this.redirect(request, response, nomePagina);
	}
	
	private void redirect(HttpServletRequest request, HttpServletResponse response,String nomePagina) throws Exception {
		// TODO Auto-generated method stub
		 RequestDispatcher rd = null;
	        try {
		        rd = request.getRequestDispatcher("view/"+nomePagina);
	            rd.forward(request, response);
	        } catch (Exception e) {
	        	throw new Exception("Erro 500 - erro na requisição");
	        }
	}

	

}
