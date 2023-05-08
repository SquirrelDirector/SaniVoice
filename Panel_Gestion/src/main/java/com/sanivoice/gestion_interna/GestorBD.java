package com.sanivoice.gestion_interna;

import java.sql.Connection;
import java.sql.Date;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;


import com.mysql.cj.x.protobuf.MysqlxDatatypes.Array;


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
			String urlJDBC = "jdbc:mysql://localhost:" + puerto + "/" + nombreDB;
			System.out.println("URL JDBC: "+urlJDBC);
			conexion = DriverManager.getConnection(urlJDBC, "admin","test");
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
	
	public CentroSalud getCentroSalud(String mailUsuario) {

		CentroSalud cs = null;
		conectar();
		try {
			Statement sentencia = conexion.createStatement();
			System.out.println("MAIL: "+mailUsuario);
			String dql = "select * from `Centro_salud` where idCentro_salud = (Select Centro_salud_idCentro_salud FROM Paciente WHERE correo_electronico='"+mailUsuario+"')";
			System.out.println("SQL: "+dql);
			ResultSet resultado = sentencia.executeQuery(dql);
			while (resultado.next()) {
				
				String nombreCentro = resultado.getString("nombre_centro");
				String url = resultado.getString("url");
				int adscrito = resultado.getInt("adscrito");
				
				System.out.println("OK! nC: "+nombreCentro+". url: "+url+". ads: "+adscrito);
				boolean isAdscrito = false;
				if(adscrito==1) {
					isAdscrito = true;
				}
				cs = new CentroSalud(nombreCentro, url, isAdscrito);
			}
			resultado.close();
			sentencia.close();
		} catch (Exception ex) {
			ex.printStackTrace();
		}

		desconectar();
		return cs;
	}
	
	public CentroSalud getCentroSaludPorId(int idCentro) {
		CentroSalud cs = null;
		conectar();
		try {
			Statement sentencia = conexion.createStatement();
			String dql = "select * from Centro_salud where idCentro_salud="+idCentro;
			System.out.println(dql);
			ResultSet resultado = sentencia.executeQuery(dql);
			while (resultado.next()) {

				String nombreCentro = resultado.getString("nombre_centro");
				String url = resultado.getString("url");
				int adscrito = resultado.getInt("adscrito");
				boolean isAdscrito = false;
				if(adscrito==1) {
					isAdscrito = true;
				}
				System.out.println("OK! nC: "+nombreCentro+". url: "+url+". ads: "+adscrito);
				cs = new CentroSalud(nombreCentro, url, isAdscrito);
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
			System.out.println("PACIENTE POR MAIL: "+emailAlexa);
			Statement sentencia = conexion.createStatement();
			String dql = "select * from Paciente where correo_electronico = '"+emailAlexa+"'";
			ResultSet resultado = sentencia.executeQuery(dql);
			while (resultado.next()) {

				int id = resultado.getInt("idPaciente");
				String nombre = resultado.getString("nombre");
				String apellidos = resultado.getString("apellidos");
				String dni = resultado.getString("dni");
				String tSan = resultado.getString("tarjeta_sanitaria");
				String email = resultado.getString("correo_electronico");
				String domicilio = resultado.getString("domicilio");
				String clave = resultado.getString("contraseña");
				String telContacto = resultado.getString("telefono");
				int idCentro = resultado.getInt("Centro_salud_idCentro_salud");
				CentroSalud cs = getCentroSaludPorId(idCentro);
				p=new Paciente(nombre, apellidos, dni, tSan,telContacto, domicilio, email, clave, cs);
			}
			resultado.close();
			sentencia.close();
		} catch (Exception ex) {
			ex.printStackTrace();
		}

		desconectar();
		return p;
	}
	
	

	
	/*public void setEliminaCita(String mail, String fecha, String hora) {
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


	public void getPreinscripcion(Preinscripcion p) {
		conectar();
		int regAfectados;
		String dml = "";
		try {
			dml = "insert into Preinscripcion(dni, fechaInicio, fechaFin, periodicidad) values " + "(?, ?, ?, ?)";
			PreparedStatement sentenciaPreparada = conexion.prepareStatement(dml);
			sentenciaPreparada.setString(1, p.getDni());
			sentenciaPreparada.setDate(2, (Date) p.getFechaInicio());
			sentenciaPreparada.setDate(3, (Date) p.getFechaFin());
			sentenciaPreparada.setString(4, p.getPeriodicidad());
			
			regAfectados = sentenciaPreparada.executeUpdate();
			sentenciaPreparada.close();

		} catch (SQLException ex) {
			ex.printStackTrace();
		}
		desconectar();
	}


	public List<String> obtenerCentro(String dni) {
		
		 CentroSalud cs = null;
	        conectar();
	        try {
	            Statement sentencia = conexion.createStatement();
	            String dql = "select * from Paciente where dni = '"+dni+"'";
	            ResultSet resultado = sentencia.executeQuery(dql);
	            List<String> nombreCentros = new ArrayList<>();
	            int i = 0;
	            while (resultado.next()) {
	                nombreCentros[i] = resultado.getString("nombreCentro");
	            }
	            resultado.close();
	            sentencia.close();
	        } catch (Exception ex) {
	            ex.printStackTrace();
	        }

	        desconectar();
	        return ;
	}*/


	public void getRegistroCorrecto(Paciente p) {
		conectar();
		int regAfectados;
		String dml = "";
		try {
			dml = "insert into Paciente(id, nombre, apellidos, dni, tarjeta_sanitaria, telefono, domicilio, correo_electronico, contraseña) values " + "(?, ?, ?, ?, ?, ?, ?, ?, ?)";
			PreparedStatement sentenciaPreparada = conexion.prepareStatement(dml);
			sentenciaPreparada.setString(1, p.getNombre());
			sentenciaPreparada.setString(2, p.getApellidos());
			sentenciaPreparada.setString(3, p.getDNI());
			sentenciaPreparada.setString(4, p.getTarjetaSanitaria());
			sentenciaPreparada.setString(5, p.getTelContacto());
			sentenciaPreparada.setString(6, p.getDomicilio());
			sentenciaPreparada.setString(7, p.getEmail());
			sentenciaPreparada.setString(8, p.getClave());
			regAfectados = sentenciaPreparada.executeUpdate();
			sentenciaPreparada.close();

		} catch (SQLException ex) {
			ex.printStackTrace();
		}
		desconectar();
	}


}
