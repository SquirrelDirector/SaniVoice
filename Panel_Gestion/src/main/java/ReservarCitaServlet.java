

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.sanivoice.gestion_interna.Cita;
import com.sanivoice.gestion_interna.GestorCitas;

/**
 * Servlet implementation class ReservarCitaServlet
 */
@WebServlet("/reservar_cita")
public class ReservarCitaServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public ReservarCitaServlet() {
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
		
		//response.getWriter().println("/reservar_cita");
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		doGet(request, response);
		response.getWriter().println(GestorCitas.getGestorCitas().pideCita(request.getParameter("mail_usuario"), request.getParameter("orden_especialidad"),request.getParameter("orden_medico"), request.getParameter("fecha"), request.getParameter("hora")));
	}

}
