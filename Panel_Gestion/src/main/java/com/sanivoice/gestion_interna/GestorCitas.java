package com.sanivoice.gestion_interna;

/**
 * Clase que gestiona las citas
 * 
 * @author Equipo de SaniVoice
 *
 */
public class GestorCitas {
	private static GestorCitas gc;

	private GestorCitas() {

	}

	public static GestorCitas getGestorCitas() {
		if (gc == null) {
			gc = new GestorCitas();
		}
		return gc;
	}

	/*
	 * TODO ESCRIBIR MÉTODOS AQUÍ PARA GESTIONAR LAS CITAS DE LOS PACIENTES (p.e.
	 * guardarCita(), modificarCita(), eliminarCita(), etc.) A través de este gestor
	 * llamar al gestorBD para realizar los cambios pertinentes, así como al gestor
	 * de centros de salud!
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
	

	


	public boolean compruebaUsuarioViaMail(String email) {
		Paciente p = null;
		boolean resp = false;
		p = GestorBD.getGestorBD().getPacientePorEmail(email);
		if (p != null) {
			resp = true;
		}
		return resp;

	}

	public String obtenEspecialidades(String url, String especialidad) {
		CentroSalud cs = GestorBD.getGestorBD().getURLParaEspecialidad(url);
		Facultativo fc = GestorBD.getGestorBD().getEspecialidad(especialidad);
		if (cs != null) {
			return fc.getEspecialidad();
		}
		return null;

	}

	public String obtenFecha(String url, String especialidad, String fecha) {
		CentroSalud cs = GestorBD.getGestorBD().getURLParaEspecialidad(url);
		Facultativo fc = GestorBD.getGestorBD().getEspecialidad(especialidad);
		Cita ct = GestorBD.getGestorBD().getFechaCita(fecha);
		if (cs != null && fc != null) {
			return ct.getFecha();
		}
		return null;
	}
	
	public String obtenHoras(String url, String especialidad, String fecha, String hora) {
		CentroSalud cs = GestorBD.getGestorBD().getURLParaEspecialidad(url);
		Facultativo fc = GestorBD.getGestorBD().getEspecialidad(especialidad);
		Cita ct = GestorBD.getGestorBD().getFechaCita(fecha);
		ct = GestorBD.getGestorBD().getHoraCita(hora);
		
		if (cs != null && fc != null) {
			return ct.getHora();
		}
		return null;
	}
	
	public boolean pideCita(String url, String especialidad, String fecha, String hora) {
		boolean resp = false;
		CentroSalud cs = GestorBD.getGestorBD().getURLParaEspecialidad(url);
		Facultativo fc = GestorBD.getGestorBD().getEspecialidad(especialidad);
		Cita ct = GestorBD.getGestorBD().getFechaCita(fecha);
		ct = GestorBD.getGestorBD().getHoraCita(hora);
		
		if (cs != null && fc != null && ct != null) {
			resp = true;
		}
		return resp;
	}
	
}
