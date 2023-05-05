

import java.io.ByteArrayInputStream;
import java.io.IOException;
import java.io.InputStream;

import javax.json.Json;
import javax.json.JsonObject;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;

import org.w3c.dom.Document;
import org.w3c.dom.Node;

import com.sanivoice.gestion_interna.GestorBD;

/**
 * Esta clase es para implementar el estándar propuesto.
 * Simplemente interpretará un fichero XML que llegue y devolverá un XML 
 * por respuesta
 * Servlet implementation class XMLGatewayServlet
 */
@WebServlet("/XMLDocumentExchange")
public class XMLGatewayServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public XMLGatewayServlet() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		GestorBD gbd = GestorBD.getGestorBD();
		//gbd.metodoEjemploEscritura();
		//gbd.metodoEjemploLectura();
		
		JsonObject jo = Json.createObjectBuilder()
							.add("nombre", "Eufrasio")
							.build();
		
		
		DocumentBuilderFactory factory = DocumentBuilderFactory.newInstance ( );
		Document documento = null;

		try
		{
		   DocumentBuilder builder = factory.newDocumentBuilder();
		   InputStream inputStream = new    ByteArrayInputStream("<nombre>Eufrasio</nombre>".getBytes());
		   documento = builder.parse( inputStream );
		   Node nodoRaiz = documento.getFirstChild();
		   response.getWriter().append("Served at: ").append(request.getContextPath()).append(jo.toString()).append(nodoRaiz.getTextContent());
		}
		catch (Exception spe)
		{
			spe.printStackTrace();
		   // Algún tipo de error: fichero no accesible, formato de XML incorrecto, etc.
		}
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		doGet(request, response);
	}

}
