

import java.io.IOException;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.sanivoice.gestion_interna.GestorBD;
import com.sanivoice.gestion_interna.GestorUsuarios;
import com.sanivoice.gestion_interna.Paciente;

/**
 * Servlet implementation class RegistroServlet
 */
@WebServlet("/doRegistro")
public class RegistroServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;

    /**
     * Default constructor. 
     */
    public RegistroServlet() {
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		//Rediriges con response.sendRedirect("");
		//Escribe texto con response.getWriter().append("Served at: ").append(request.getContextPath());. Será util para implementar APIs
		response.getWriter().println("Texto en doRegistro");
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		String nombre = request.getParameter("nombre");
		String apellidos = request.getParameter("apellidos");
		String dni = request.getParameter("dni");
		String tarjeta_sanitaria = request.getParameter("tarjeta_sanitaria");
		String telefono = request.getParameter("telefono");
		String domicilio = request.getParameter("domicilio");
		String correo_electronico = request.getParameter("correo_electronico");
		String contraseña = request.getParameter("contraseña");
		int identificador = Integer.parseInt(request.getParameter("centro_salud"));
		Paciente paciente = new Paciente(nombre, apellidos, dni, tarjeta_sanitaria, telefono, domicilio, correo_electronico, contraseña, GestorBD.getGestorBD().getCentroSaludPorId(identificador));
		GestorUsuarios.getGestorUsuarios().registroCorrecto(paciente);		
		
		doGet(request, response);
	}

}
