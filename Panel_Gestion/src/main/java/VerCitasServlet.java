

import java.io.IOException;
import java.util.ArrayList;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.sanivoice.gestion_interna.Cita;
import com.sanivoice.gestion_interna.GestorCitas;

/**
 * Servlet implementation class VerCitasServlet
 */
@WebServlet("/ver_citas")
public class VerCitasServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public VerCitasServlet() {
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
		//response.getWriter().println("/ver_citas");
		ArrayList<Cita> citas = GestorCitas.getGestorCitas().consultarCitas(request.getParameter("mail_usuario"));
		String respuesta="{ citas: [";
		for (int i=0; i<citas.size(); i++) {
			respuesta+="{ fecha: '"+ citas.get(i).getFecha()+"', hora: '"+citas.get(i).getHora()+"'}";
			if(i<citas.size()-1) {
				respuesta+=",";
			}
			
		}
		respuesta+="] }";
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
