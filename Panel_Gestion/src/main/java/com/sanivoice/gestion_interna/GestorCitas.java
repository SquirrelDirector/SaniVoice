package com.sanivoice.gestion_interna;

import java.util.ArrayList;
import java.util.Iterator;

import com.sanivoice.conector_centros_salud.Conector;
import com.sanivoice.conector_centros_salud.ConectorCentroSaludFactory;

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


	public ArrayList<Cita> consultarCitas(String mailUsuario) {
		/*CentroSalud cs = GestorBD.getGestorBD().getURLParaEspecialidad(url);
		Facultativo fc = GestorBD.getGestorBD().getEspecialidad(especialidad);
		Cita ct = GestorBD.getGestorBD().getFechaCita(fecha);
		ct = GestorBD.getGestorBD().getHoraCita(hora);
		if (cs != null && cs != null && fc != null) {
			return ct;
		}*/
		Paciente p = GestorBD.getGestorBD().getPacientePorEmail(mailUsuario);
		ArrayList<Cita> citas = GestorBD.getGestorBD().getCitasPaciente(p);
		return citas;

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

	public ArrayList<String> obtenEspecialidades(String mailUsuario) {
		CentroSalud cs = GestorBD.getGestorBD().getCentroSalud(mailUsuario);
		
		/*Facultativo fc = GestorBD.getGestorBD().getEspecialidad(especialidad);
		if (cs != null) {
			return fc.getEspecialidad();
		}
		return null;*/
		Conector c = ConectorCentroSaludFactory.getConectorCentroSaludFactory().getConectorCentroSalud(cs);
		ArrayList<String> especialidades = c.obtenerEspecialidadesCentro(); 
		return especialidades;
	}
	

	public ArrayList<Facultativo> obtenerCuadroMedicoPorEspecialidad(String mailUsuario, String especialidad){
		Paciente p = GestorBD.getGestorBD().getPacientePorEmail(mailUsuario);
		CentroSalud cs = p.getCs();
		Conector c = ConectorCentroSaludFactory.getConectorCentroSaludFactory().getConectorCentroSalud(cs);
		ArrayList<Facultativo> medicosIterando = c.obtenerCuadroMedico(GestorBD.getGestorBD().getPacientePorEmail(mailUsuario));
		ArrayList<Facultativo> medicos = c.obtenerCuadroMedico(GestorBD.getGestorBD().getPacientePorEmail(mailUsuario));
		Iterator<Facultativo> itr = medicosIterando.iterator();
		while(itr.hasNext()) {
			Facultativo f = itr.next();
			if(!f.getEspecialidad().equals(especialidad)) {
				medicos.remove(f);
			}
		}
		
		return medicos;
	}

	public ArrayList<String> obtenerFechas(String mailUsuario, String ordenEspecialidad, String ordenMedico) {
		ArrayList<String> especialidades = obtenEspecialidades(mailUsuario);
		ArrayList<Facultativo> medicos = obtenerCuadroMedicoPorEspecialidad(mailUsuario, especialidades.get(Integer.parseInt(ordenEspecialidad)));
		String especialidadSeleccionada = especialidades.get(Integer.parseInt(ordenEspecialidad)); 
		String idFacultativoSeleccionado = medicos.get(Integer.parseInt(ordenMedico)).getNumColegiado();
		Paciente p = GestorBD.getGestorBD().getPacientePorEmail(mailUsuario);
		CentroSalud cs = p.getCs();
		Conector c = ConectorCentroSaludFactory.getConectorCentroSaludFactory().getConectorCentroSalud(cs);
		ArrayList<String> fechas = c.obtenerFechasDisponibles(GestorBD.getGestorBD().getPacientePorEmail(mailUsuario), medicos.get(Integer.parseInt(ordenMedico)));
		return fechas;
	}
	
	public ArrayList<String> obtenHoras(String mailUsuario, String ordenEspecialidad, String ordenMedico, String fecha) {
		Paciente p = GestorBD.getGestorBD().getPacientePorEmail(mailUsuario);
		CentroSalud cs = p.getCs();
		Conector c = ConectorCentroSaludFactory.getConectorCentroSaludFactory().getConectorCentroSalud(cs);
		Facultativo f = obtenerCuadroMedicoPorEspecialidad(mailUsuario, ordenEspecialidad).get(Integer.parseInt(ordenMedico));
		ArrayList<String> horas = c.obtenerHorasDisponibles(p, f, fecha);
		return horas;
	}
	
	public boolean pideCita(String mailUsuario, String orden_especialidad, String orden_medico, String fecha, String hora) {
		boolean resp = false;
		/*CentroSalud cs = GestorBD.getGestorBD().getURLParaEspecialidad(url);
		Facultativo fc = GestorBD.getGestorBD().getEspecialidad(especialidad);
		Cita ct = GestorBD.getGestorBD().getFechaCita(fecha);
		ct = GestorBD.getGestorBD().getHoraCita(hora);
		
		if (cs != null && fc != null && ct != null) {
			resp = true;
		}
		return resp;*/
		Paciente p = GestorBD.getGestorBD().getPacientePorEmail(mailUsuario);
		CentroSalud cs = p.getCs();
		Facultativo f = obtenerCuadroMedicoPorEspecialidad(mailUsuario, orden_especialidad).get(Integer.parseInt(orden_medico));
		Cita c = new Cita("", fecha, hora, p, f, cs);
		ConectorCentroSaludFactory.getConectorCentroSaludFactory().getConectorCentroSalud(cs).reservarCita(c);
		GestorBD.getGestorBD().setCita(c);
		
		return true;
	}

	
	
	public boolean eliminaCita(String mail, String fecha, String hora) {
		GestorBD.getGestorBD().eliminarCita(mail, fecha, hora);
		return true;
	}
	
}
