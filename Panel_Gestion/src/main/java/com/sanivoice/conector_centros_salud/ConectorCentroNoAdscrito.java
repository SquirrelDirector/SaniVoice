package com.sanivoice.conector_centros_salud;

import java.io.ByteArrayInputStream;
import java.io.InputStream;
import java.io.StringReader;
import java.net.URI;
import java.net.http.HttpClient;
import java.net.http.HttpClient.Version;
import java.net.http.HttpRequest;
import java.net.http.HttpResponse;
import java.net.http.HttpResponse.BodyHandlers;
import java.util.ArrayList;
import java.util.Iterator;

import javax.json.Json;
import javax.json.JsonArray;
import javax.json.JsonBuilderFactory;
import javax.json.JsonObject;
import javax.json.JsonReader;
import javax.json.JsonValue;
import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;

import org.w3c.dom.Document;
import org.w3c.dom.Node;
import org.w3c.dom.NodeList;
import org.xml.sax.InputSource;


import com.sanivoice.gestion_interna.Cita;
import com.sanivoice.gestion_interna.Facultativo;
import com.sanivoice.gestion_interna.Paciente;

/**
 * Conector para centro NO adscrito. Modificar según cómo funcione la web del centro.
 * @author Equipo de SaniVoice
 *
 */
public class ConectorCentroNoAdscrito extends Conector {
	

	public ConectorCentroNoAdscrito(String URL) {
		super(URL);
	}

	@Override
	public ArrayList<String> obtenerFechasDisponibles(Paciente p, Facultativo f) {
		return null;
	}
	
	@Override
	public ArrayList<String> obtenerHorasDisponibles(Paciente p, Facultativo f, String fecha) {
		return null;
	}
	
	@Override
	public boolean reservarCita(Cita c) {
		return false;
	}
	
	@Override
	public boolean eliminarCita(Cita c) {
		return false;
	}
	
	@Override
	public ArrayList<Facultativo> obtenerCuadroMedico(Paciente p){
		try {
			ArrayList<Facultativo> facu = new ArrayList<Facultativo>();
			ArrayList<String> especialidadesCod = obtenerCodigoEspecialidades();
			ArrayList<String> especialidadesNombres= obtenerEspecialidadesCentro();
			
			Iterator<String> itr = especialidadesCod.iterator();
			while(itr.hasNext()) {
				String espe = itr.next();
				HttpClient client = HttpClient.newHttpClient();
				HttpRequest request = HttpRequest.newBuilder()
									.uri(URI.create(URL+"/getFacultativos.php?especialidad="+espe))
									.GET()
									.build();
				HttpResponse response = client.send(request, BodyHandlers.ofString());
				String datos = response.body().toString();
				JsonReader jr = Json.createReader(new StringReader(datos));
				JsonObject ja = jr.readObject();
				jr.close();
				System.out.println("EH");
				/*Iterator<JsonValue> itr2 = ja.iterator();
				while(itr2.hasNext()) {
					JsonValue val = itr2.next();
					System.out.println("itr");
					System.out.println(val);
				}*/
				System.out.println("fin");
			}
			return facu;
		}catch(Exception ex) {
			ex.printStackTrace();
			return null;
		}
		
		
	}
	
	@Override
	public ArrayList<String> obtenerEspecialidadesCentro() {
		return obtenerNombreEspecialidades();
	}
	
	public ArrayList<String> obtenerNombreEspecialidades(){
		ArrayList<String> especialidades = new ArrayList<String>();
		try {
			HttpClient client = HttpClient.newHttpClient();
			HttpRequest request = HttpRequest.newBuilder()
								.uri(URI.create(URL))
								.GET()
								.build();
			HttpResponse response = client.send(request, BodyHandlers.ofString());
			String datos = response.body().toString();
			
			DocumentBuilderFactory factory = DocumentBuilderFactory.newInstance ( );
			Document documento = null;

			DocumentBuilder builder = factory.newDocumentBuilder();
			InputStream inputStream = new ByteArrayInputStream(datos.getBytes());
			documento = builder.parse( inputStream );
			
			NodeList lista = documento.getElementsByTagName("select");
			Node formulario = lista.item(0); 
			lista = formulario.getChildNodes();
			
			for (int i=0; i<lista.getLength(); i++) {
				Node elem = lista.item(i);
				if(i!=0) {
					especialidades.add(elem.getTextContent().trim());
				}
			}
			
		}catch(Exception e) {
			e.printStackTrace();
		}
		return especialidades;
	}
	
	public ArrayList<String> obtenerCodigoEspecialidades(){
		ArrayList<String> especialidades = new ArrayList<String>();
		try {
			HttpClient client = HttpClient.newHttpClient();
			HttpRequest request = HttpRequest.newBuilder()
								.uri(URI.create(URL))
								.GET()
								.build();
			HttpResponse response = client.send(request, BodyHandlers.ofString());
			String datos = response.body().toString();
			
			DocumentBuilderFactory factory = DocumentBuilderFactory.newInstance ( );
			Document documento = null;

			DocumentBuilder builder = factory.newDocumentBuilder();
			InputStream inputStream = new ByteArrayInputStream(datos.getBytes());
			documento = builder.parse( inputStream );
			
			NodeList lista = documento.getElementsByTagName("select");
			Node formulario = lista.item(0); 
			lista = formulario.getChildNodes();
			for (int i=0; i<lista.getLength(); i++) {
				Node option = lista.item(i);
				//System.out.println()
				if(i!=0) {
					if(option.getAttributes()!=null && !option.getAttributes().getNamedItem("value").getNodeValue().equals("nulo")) {
						especialidades.add(option.getAttributes().getNamedItem("value").getNodeValue());
					}
					
				}
			}
			
		}catch(Exception e) {
			e.printStackTrace();
		}
		return especialidades;
	}
}
