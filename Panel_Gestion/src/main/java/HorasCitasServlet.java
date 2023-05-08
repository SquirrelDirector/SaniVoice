

import java.io.IOException;
import java.util.ArrayList;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.sanivoice.gestion_interna.GestorCitas;

/**
 * Servlet implementation class HorasCitasServlet
 */
@WebServlet("/get_horas")
public class HorasCitasServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public HorasCitasServlet() {
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
		ArrayList<String> horas =  GestorCitas.getGestorCitas().obtenHoras(request.getParameter("mail_usuario"), request.getParameter("orden_especialidad"), request.getParameter("orden_medico"), request.getParameter("fecha"));
		String respuesta = "{ horas: [";
		for (int i=0; i<horas.size(); i++) {
			if(i<horas.size()-1) {
				respuesta+="'"+horas.get(i)+"',";
			}else {
				respuesta+="'"+horas.get(i)+"'";
			}
		}
		respuesta+="]}";
		response.getWriter().println(respuesta);
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		doGet(request, response);
	}

}
