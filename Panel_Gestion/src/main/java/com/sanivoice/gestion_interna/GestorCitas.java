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
	public Cita consultarCita(String url, String especialidad, String fecha, String hora) {
		CentroSalud cs = GestorBD.getGestorBD().getURLParaEspecialidad(url);
		Facultativo fc = GestorBD.getGestorBD().getEspecialidad(especialidad);
		Cita ct = GestorBD.getGestorBD().getFechaCita(fecha);
		ct = GestorBD.getGestorBD().getHoraCita(hora);
		if (cs != null && cs != null && fc != null) {
			return ct;
		}
		return null;

	}
	

	
	
}
