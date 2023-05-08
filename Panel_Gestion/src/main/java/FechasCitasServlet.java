

import java.io.IOException;
import java.util.ArrayList;
import java.util.Iterator;

import javax.json.Json;
import javax.json.JsonArrayBuilder;
import javax.json.JsonObject;
import javax.json.JsonValue;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.mysql.cj.xdevapi.JsonArray;
import com.sanivoice.gestion_interna.GestorCitas;

/**
 * Servlet implementation class FechasCitasServlet
 */
@WebServlet("/get_fechas")
public class FechasCitasServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public FechasCitasServlet() {
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
		ArrayList<String> fechas = GestorCitas.getGestorCitas().obtenerFechas(request.getParameter("mail_usuario"),request.getParameter("orden_especialidad"), request.getParameter("orden_medico"));
		//JsonArrayBuilder jarr = Json.createArrayBuilder();
		//Iterator<String> itr = fechas.iterator();
		String dato="{fechas:[";
		for (int i=0; i<fechas.size(); i++) {
			if(i< 5) {
				dato+="'"+fechas.get(i)+"',";
			}else {
				dato+="'"+fechas.get(i)+"'";
			}
		}
		dato+="]}";
		response.getWriter().print(dato);
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		doGet(request, response);
	}

}
