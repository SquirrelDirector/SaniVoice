package com.sanivoice.gestion_interna;

public class GestorPreinscripcion {
private static GestorPreinscripcion gp;
	
	private GestorPreinscripcion() {
		
	}
	
	public static GestorPreinscripcion getGestorPreinscripcion() {
		if(gp==null) {
			gp = new GestorPreinscripcion();
		}
		return gp;
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
