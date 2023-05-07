

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.sanivoice.gestion_interna.GestorUsuarios;

/**
 * Servlet implementation class ClinicasUsuarioServlet
 */
@WebServlet("/get_clinicas_usuario")
public class ClinicasUsuarioServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public ClinicasUsuarioServlet() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		//Rediriges con response.sendRedirect("");
		//Escribe texto con response.getWriter().append("Served at: ").append(request.getContextPath());. Será util para implementar APIs
		response.getWriter().println("/get_clinicas_usuario");
		response.getWriter().println(GestorUsuarios.getGestorUsuarios().obtenClinicaUsuario(request.getParameter("nombreCentro")));
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		doGet(request, response);
	}

}
