package com.sanivoice.gestion_interna;


/**
 * Clase que gestiona los diversos usuarios, sean facultativos o pacientes 
 * @author Equipo de SaniVoice
 *
 */
public class GestorUsuarios {
	private static GestorUsuarios gu;
	
	private GestorUsuarios() {
		
	}
	
	public static GestorUsuarios getGestorUsuarios() {
		if (gu == null) {
			gu = new GestorUsuarios();
		}
		return gu;
	}
	
	/*
	 * TODO
	 * ESCRIBIR MÉTODOS AQUÍ PARA GESTIONAR LOS USUARIOS (p.e. 
	 * guardarUsuario(usuario U), modificarDatosUsuario(usuario U), 
	 * validarInicioSesion(), obtenerFacultativosDePaciente(usuario U) etc.)
	 * A través de este gestor llamar al gestorBD para realizar los cambios
	 * pertinentes, así como al gestor de centros de salud!
	 */
	
	public String obtenClinicaUsuario(String nombreCentro) {
		CentroSalud cs = GestorBD.getGestorBD().getCentroSalud(nombreCentro);
	    if (cs != null) {
	        return cs.getNombreCentro();
	    }
	    return null;
	}
	
	
}
