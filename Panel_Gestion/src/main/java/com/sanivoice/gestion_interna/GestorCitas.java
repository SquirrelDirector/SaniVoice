package com.sanivoice.gestion_interna;

/**
 * Clase que gestiona las citas
 * @author Equipo de SaniVoice
 *
 */
public class GestorCitas {
	private static GestorCitas gc;
	
	private GestorCitas() {
		
	}
	
	public static GestorCitas getGestorCitas() {
		if(gc==null) {
			gc = new GestorCitas();
		}
		return gc;
	}
	
	/*
	 * TODO
	 * ESCRIBIR MÉTODOS AQUÍ PARA GESTIONAR LAS CITAS DE LOS PACIENTES (p.e. 
	 * guardarCita(), modificarCita(), 
	 * eliminarCita(), etc.)
	 * A través de este gestor llamar al gestorBD para realizar los cambios
	 * pertinentes, así como al gestor de centros de salud!
	 */
}
