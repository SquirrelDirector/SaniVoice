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
