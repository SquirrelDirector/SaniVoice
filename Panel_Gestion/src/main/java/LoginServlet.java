
import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.sanivoice.gestion_interna.GestorUsuarios;


/**
 * Servlet implementation class LoginServlet
 */
@WebServlet("/doLogin")
public class LoginServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;

	/**
	 * Default constructor.
	 */
	public LoginServlet() {
		// TODO Auto-generated constructor stub
	}

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse
	 *      response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		// TODO Auto-generated method stub
		// Rediriges con response.sendRedirect("");
		// Escribe texto con response.getWriter().append("Served at:
		// ").append(request.getContextPath());. Será util para implementar APIs
		response.getWriter().println("/doLogin");
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse
	 *      response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		// TODO Auto-generated method stub
		doGet(request, response);
		String email = request.getParameter("email");
		String clave = request.getParameter("clave");

		boolean isValid = GestorUsuarios.getGestorUsuarios().inicioSesion(email, clave);
		if (isValid) {
			// Crear una nueva cookie de inicio de sesión
			Cookie loginCookie = new Cookie("email", email);
			loginCookie.setMaxAge(30 * 60); // configurar la duración de la cookie en segundos
			response.addCookie(loginCookie);

			// Redirigir al usuario a la página de inicio después de iniciar sesión
			response.sendRedirect("login.jsp");
		} else {
			// Mostrar un mensaje de error si los valores de inicio de sesión son inválidos
			System.out.println("Nombre de usuario o contraseña inválidos.");
		}

	}
}
