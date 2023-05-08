package com.sanivoice.conector_centros_salud;

import java.io.ByteArrayInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.StringReader;
import java.net.URI;
import java.net.http.HttpClient;
import java.net.http.HttpClient.Version;
import java.net.http.HttpRequest;
import java.net.http.HttpRequest.BodyPublishers;
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
		ArrayList<String> fechas = new ArrayList<String>();
		HttpClient client = HttpClient.newHttpClient();
		HttpRequest request = HttpRequest.newBuilder()
							.uri(URI.create(URL+"/getFechasYHorasDisponibles.php?idFacultativo="+f.getNumColegiado()))
							.GET()
							.build();
		HttpResponse response;
		try {
			response = client.send(request, BodyHandlers.ofString());
			String datos = response.body().toString();
			JsonReader jr = Json.createReader(new StringReader(datos));
			JsonArray ja = jr.readArray();
			jr.close();
			Iterator<JsonValue> itr2 = ja.iterator();
			while(itr2.hasNext()) {
				JsonValue val = itr2.next();
				JsonObject jo = val.asJsonObject();
				String fecha = jo.getString("fecha");
				if(!fechas.contains(fecha)) {
					fechas.add(fecha);
				}
				
				
			}
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		
		}
		
		return fechas;
	}
	
	@Override
	public ArrayList<String> obtenerHorasDisponibles(Paciente p, Facultativo f, String fecha) {
		ArrayList<String> horas = new ArrayList<String>();
		ArrayList<String> fechas = new ArrayList<String>();
		HttpClient client = HttpClient.newHttpClient();
		HttpRequest request = HttpRequest.newBuilder()
							.uri(URI.create(URL+"/getFechasYHorasDisponibles.php?idFacultativo="+f.getNumColegiado()))
							.GET()
							.build();
		HttpResponse response;
		try {
			response = client.send(request, BodyHandlers.ofString());
			String datos = response.body().toString();
			JsonReader jr = Json.createReader(new StringReader(datos));
			JsonArray ja = jr.readArray();
			jr.close();
			Iterator<JsonValue> itr2 = ja.iterator();
			while(itr2.hasNext()) {
				JsonValue val = itr2.next();
				JsonObject jo = val.asJsonObject();
				String fechaJSON = jo.getString("fecha");
				String horaJSON = jo.getString("hora");
				if(fecha.equals(fechaJSON)) {
					horas.add(horaJSON);
				}
				
				
			}
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		
		}
		return horas;
	}
	
	@Override
	public boolean reservarCita(Cita c) {
		String idFac = c.getFacultativo().getNumColegiado();
		String nombreUs = c.getPaciente().getNombre();
		String apeUs = c.getPaciente().getApellidos();
		String tel = c.getPaciente().getTelContacto();
		String email = c.getPaciente().getEmail();
		String fecha = c.getFecha();
		String hora = c.getHora();
		
		try {
			HttpClient client = HttpClient.newHttpClient();
			String cabecera="nombre="+nombreUs+"&apellidos="+apeUs+"&tel_contacto="+tel+"&email="+email+"&fecha_hora="+fecha+"T"+hora+"&doctor="+idFac;
			System.out.println(cabecera);
			HttpRequest request = HttpRequest.newBuilder()
								.uri(URI.create(URL+"/guardarCita.php"))
								.header("Content-Type", "application/x-www-form-urlencoded")
								.POST(BodyPublishers.ofString(cabecera))
								.build();
			HttpResponse response = client.send(request, BodyHandlers.ofString());
			
		}catch(Exception ex) {
			ex.printStackTrace();
		}
		
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
			int idxEspec=0;
			while(itr.hasNext()) {
				String espe = itr.next();
				String espeNombre = especialidadesNombres.get(idxEspec);
				HttpClient client = HttpClient.newHttpClient();
				HttpRequest request = HttpRequest.newBuilder()
									.uri(URI.create(URL+"/getFacultativos.php?especialidad="+espe))
									.GET()
									.build();
				HttpResponse response = client.send(request, BodyHandlers.ofString());
				String datos = response.body().toString();
				JsonReader jr = Json.createReader(new StringReader(datos));
				JsonArray ja = jr.readArray();
				jr.close();
				Iterator<JsonValue> itr2 = ja.iterator();
				while(itr2.hasNext()) {
					JsonValue val = itr2.next();
					JsonObject jo = val.asJsonObject();
					Facultativo f = new Facultativo(jo.getString("nombre"), jo.getString("apellidos"), jo.getInt("id")+"", especialidadesNombres.get(idxEspec), null, null);
					facu.add(f);
				}
				idxEspec++;
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
			System.out.println("URL: "+URL);
			HttpClient client = HttpClient.newHttpClient();
			HttpRequest request = HttpRequest.newBuilder()
								.uri(URI.create(URL))
								.GET()
								.build();
			HttpResponse response = client.send(request, BodyHandlers.ofString());
			String datos = response.body().toString();
			System.out.println(datos);
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
