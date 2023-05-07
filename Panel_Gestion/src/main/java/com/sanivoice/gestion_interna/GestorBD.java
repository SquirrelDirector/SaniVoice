package com.sanivoice.gestion_interna;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

/**
 * Clase que gestiona la conexión a la base de datos
 * 
 * @author Equipo de SaniVoice
 *
 */
public class GestorBD {
	private Connection conexion;
	private static GestorBD gbd;

	private GestorBD() {
		try {
			Class.forName("com.mysql.cj.jdbc.Driver");
		} catch (Exception ex) {
			System.err.println("ERROR BUSCANDO LA CLASE");
			ex.printStackTrace();
		}
	}

	public static GestorBD getGestorBD() {
		if (gbd == null) {
			gbd = new GestorBD();
		}
		return gbd;
	}

	private void conectar() {
		try {
			String usuario = "TU_USUARIO";
			String clave = "CLAVE";
			String puerto = "8889";
			String nombreDB = "database";
			conexion = DriverManager.getConnection("jdbc:mysql://localhost:" + puerto + "/" + nombreDB, "admin",
					"test");
		} catch (SQLException ex) {
			ex.printStackTrace();
		}
	}

	private void desconectar() {
		try {
			conexion.close();
		} catch (Exception ex) {
			ex.getStackTrace();
		}
	}

	/**
	 * TODO ESCRIBIR MÉTODOS PARA ALMACENAR LOS DATOS NECESARIOS EN LA BASE DE DATOS
	 * DE SANIVOICE GESTIÓN (EL .SQL QUE ESTÁ CON EL RESTO DE DOCUMENTOS) Por
	 * ejemplo, guardarUsuario(Usuario u) y dentro realizar la consulta.
	 */

	/**
	 * Método para almacenar datos
	 */
	public void metodoEjemploEscritura() {
		conectar();
		int regAfectados;
		String dml = "";
		try {
			dml = "insert into Municipio(id, nombre, habitantes) values " + "(?, ?, ?)";
			PreparedStatement sentenciaPreparada = conexion.prepareStatement(dml);
			sentenciaPreparada.setInt(1, 9);
			sentenciaPreparada.setString(2, "Amorebieta");
			sentenciaPreparada.setInt(3, 120);
			regAfectados = sentenciaPreparada.executeUpdate();
			sentenciaPreparada.close();

		} catch (SQLException ex) {
			ex.printStackTrace();
		}
		desconectar();
	}

	/**
	 * Método para leer datos
	 */
	public void metodoEjemploLectura() {
		conectar();
		try {
			Statement sentencia = conexion.createStatement();
			String dql = "select * from Municipio";
			ResultSet resultado = sentencia.executeQuery(dql);
			while (resultado.next()) {

				int id = resultado.getInt("id");
				String nombre = resultado.getString("nombre");
				int numHab = resultado.getInt("habitantes");
				System.out.println("ID: " + id + ". Nombre: " + nombre + ". NumHab: " + numHab);
			}
			resultado.close();
			sentencia.close();
		} catch (Exception ex) {
			ex.printStackTrace();
		}

		desconectar();
	}

	public Paciente getPacientePorEmail(String emailAlexa) {
		Paciente p = null;
		conectar();
		try {
			Statement sentencia = conexion.createStatement();
			String dql = "select * from Paciente where correo_electronico = '"+emailAlexa+"'";
			ResultSet resultado = sentencia.executeQuery(dql);
			while (resultado.next()) {

				int id = resultado.getInt("id");
				String nombre = resultado.getString("nombre");
				String email = resultado.getString("correo_electronico");
				System.out.println("ID: " + id + ". Nombre: " + nombre + ". correo_electronico: " + email);
				p=new Paciente(nombre, null, null, null, null, null, email, null);
			}
			resultado.close();
			sentencia.close();
		} catch (Exception ex) {
			ex.printStackTrace();
		}

		desconectar();
		return p;
	}
	
	public CentroSalud getURLParaEspecialidad(String url) {
		CentroSalud cs = null;
		conectar();
		try {
			Statement sentencia = conexion.createStatement();
			String dql = "select * from CentroSalud where URL = '"+url+"'";
			ResultSet resultado = sentencia.executeQuery(dql);
			while (resultado.next()) {

				String urlcentro = resultado.getString("URL");
				cs = new CentroSalud(null, urlcentro, true);
			}
			resultado.close();
			sentencia.close();
		} catch (Exception ex) {
			ex.printStackTrace();
		}

		desconectar();
		return cs;
	}
	

	public Facultativo getEspecialidad(String especialidad) {
		Facultativo fc = null;
		conectar();
		try {
			Statement sentencia = conexion.createStatement();
			String dql = "select * from CentroSalud where especialidad = '"+especialidad+"'";
			ResultSet resultado = sentencia.executeQuery(dql);
			while (resultado.next()) {

				String especialidad1 = resultado.getString("especialidad");
				fc = new Facultativo(null, null, null, especialidad1, null, null);
			}
			resultado.close();
			sentencia.close();
		} catch (Exception ex) {
			ex.printStackTrace();
		}

		desconectar();
		return fc;
	}
	
	public Cita getFechaCita(String fecha) {
		Cita ct = null;
		conectar();
		try {
			Statement sentencia = conexion.createStatement();
			String dql = "select * from CentroSalud where fecha = '"+fecha+"'";
			ResultSet resultado = sentencia.executeQuery(dql);
			while (resultado.next()) {

				String fechaobtener = resultado.getString("fecha");
				ct = new Cita(null, fechaobtener, null, null, null, null);
			}
			resultado.close();
			sentencia.close();
		} catch (Exception ex) {
			ex.printStackTrace();
		}

		desconectar();
		return ct;
	}
	
	public Cita getHoraCita(String hora) {
		Cita ct = null;
		conectar();
		try {
			Statement sentencia = conexion.createStatement();
			String dql = "select * from CentroSalud where hora = '"+hora+"'";
			ResultSet resultado = sentencia.executeQuery(dql);
			while (resultado.next()) {

				String horaobtener = resultado.getString("fecha");
				ct = new Cita(null, null, horaobtener, null, null, null);
			}
			resultado.close();
			sentencia.close();
		} catch (Exception ex) {
			ex.printStackTrace();
		}

		desconectar();
		return ct;
	}
	
	public CentroSalud getCentroSalud(String centroSalud) {
		CentroSalud cs = null;
		conectar();
		try {
			Statement sentencia = conexion.createStatement();
			String dql = "select * from CentroSalud where nombreCentro = '"+centroSalud+"'";
			ResultSet resultado = sentencia.executeQuery(dql);
			while (resultado.next()) {

				String nombreCentro = resultado.getString("nombreCentro");
				cs = new CentroSalud(nombreCentro, null, true);
			}
			resultado.close();
			sentencia.close();
		} catch (Exception ex) {
			ex.printStackTrace();
		}

		desconectar();
		return cs;
	}
	
	public CentroSalud getURLParaEspecialidad(String url) {
		CentroSalud cs = null;
		conectar();
		try {
			Statement sentencia = conexion.createStatement();
			String dql = "select * from CentroSalud where URL = '"+url+"'";
			ResultSet resultado = sentencia.executeQuery(dql);
			while (resultado.next()) {

				String urlcentro = resultado.getString("URL");
				cs = new CentroSalud(null, urlcentro, true);
			}
			resultado.close();
			sentencia.close();
		} catch (Exception ex) {
			ex.printStackTrace();
		}

		desconectar();
		return cs;
	}
	
	public Facultativo getEspecialidad(String especialidad) {
		Facultativo fc = null;
		conectar();
		try {
			Statement sentencia = conexion.createStatement();
			String dql = "select * from CentroSalud where especialidad = '"+especialidad+"'";
			ResultSet resultado = sentencia.executeQuery(dql);
			while (resultado.next()) {

				String especialidad1 = resultado.getString("especialidad");
				fc = new Facultativo(null, null, null, especialidad1, null, null);
			}
			resultado.close();
			sentencia.close();
		} catch (Exception ex) {
			ex.printStackTrace();
		}

		desconectar();
		return fc;
	}
	
	public Cita getFechaCita(String fecha) {
		Cita ct = null;
		conectar();
		try {
			Statement sentencia = conexion.createStatement();
			String dql = "select * from CentroSalud where fecha = '"+fecha+"'";
			ResultSet resultado = sentencia.executeQuery(dql);
			while (resultado.next()) {

				String fechaobtener = resultado.getString("fecha");
				ct = new Cita(null, fechaobtener, null, null, null, null);
			}
			resultado.close();
			sentencia.close();
		} catch (Exception ex) {
			ex.printStackTrace();
		}

		desconectar();
		return ct;
	}
	
	public Cita getHoraCita(String hora) {
		Cita ct = null;
		conectar();
		try {
			Statement sentencia = conexion.createStatement();
			String dql = "select * from CentroSalud where hora = '"+hora+"'";
			ResultSet resultado = sentencia.executeQuery(dql);
			while (resultado.next()) {

				String horaobtener = resultado.getString("fecha");
				ct = new Cita(null, null, horaobtener, null, null, null);
			}
			resultado.close();
			sentencia.close();
		} catch (Exception ex) {
			ex.printStackTrace();
		}

		desconectar();
		return ct;
	}
	
	public void setCita(String url, String especialidad, String fecha, String hora, String nombre) {
		conectar();
		int reserva;
		String dml = "";
		try {
			dml = "insert into Cita(GUID, fecha, hora, p, f, cs) values " + "(gUID, fecha, hora, nombre, especialidad ,url)";
			PreparedStatement sentenciaPreparada = conexion.prepareStatement(dml);
			sentenciaPreparada.setInt(1, 9);
			sentenciaPreparada.setString(2, fecha);
			sentenciaPreparada.setString(3, hora);
			sentenciaPreparada.setString(4, nombre);
			sentenciaPreparada.setString(5, especialidad);
			sentenciaPreparada.setString(6, url);
			reserva = sentenciaPreparada.executeUpdate();
			sentenciaPreparada.close();

		} catch (SQLException ex) {
			ex.printStackTrace();
		}
		desconectar();
	}


	public Paciente getPacientePorEmail(String emailAlexa) {
		Paciente p = null;
		conectar();
		try {
			Statement sentencia = conexion.createStatement();
			String dql = "select * from Paciente where correo_electronico = '"+emailAlexa+"'";
			ResultSet resultado = sentencia.executeQuery(dql);
			while (resultado.next()) {

				int id = resultado.getInt("id");
				String nombre = resultado.getString("nombre");
				String email = resultado.getString("correo_electronico");
				System.out.println("ID: " + id + ". Nombre: " + nombre + ". correo_electronico: " + email);
				p=new Paciente(nombre, null, null, null, null, null, email, null);
			}
			resultado.close();
			sentencia.close();
		} catch (Exception ex) {
			ex.printStackTrace();
		}

		desconectar();
		return p;
	}
	
	public Cita getFechaCita(String fecha) {
		Cita ct = null;
		conectar();
		try {
			Statement sentencia = conexion.createStatement();
			String dql = "select * from CentroSalud where fecha = '"+fecha+"'";
			ResultSet resultado = sentencia.executeQuery(dql);
			while (resultado.next()) {

				String fechaobtener = resultado.getString("fecha");
				ct = new Cita(null, fechaobtener, null, null, null, null);
			}
			resultado.close();
			sentencia.close();
		} catch (Exception ex) {
			ex.printStackTrace();
		}

		desconectar();
		return ct;
	}
	
	public Cita getHoraCita(String hora) {
		Cita ct = null;
		conectar();
		try {
			Statement sentencia = conexion.createStatement();
			String dql = "select * from CentroSalud where hora = '"+hora+"'";
			ResultSet resultado = sentencia.executeQuery(dql);
			while (resultado.next()) {

				String horaobtener = resultado.getString("fecha");
				ct = new Cita(null, null, horaobtener, null, null, null);
			}
			resultado.close();
			sentencia.close();
		} catch (Exception ex) {
			ex.printStackTrace();
		}

		desconectar();
		return ct;
	}
	
	public void setEliminaCita(String mail, String fecha, String hora) {
		conectar();
		int reserva;
		String dml = "";
		try {
			dml = "insert into Cita(mail, fecha, hora) values " + "(mail, fecha, hora)";
			PreparedStatement sentenciaPreparada = conexion.prepareStatement(dml);
			sentenciaPreparada.setString(1, mail);
			sentenciaPreparada.setString(2, fecha);
			sentenciaPreparada.setString(3, hora);
			reserva = sentenciaPreparada.clearParameters();//¿Elimina?
			sentenciaPreparada.close();

		} catch (SQLException ex) {
			ex.printStackTrace();
		}
		desconectar();
	}
	
	
	
}
