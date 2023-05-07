package com.sanivoice.conector_centros_salud;

import java.util.ArrayList;

import com.sanivoice.gestion_interna.Cita;
import com.sanivoice.gestion_interna.Facultativo;
import com.sanivoice.gestion_interna.Paciente;

/**
 * Clase base para realizar las tareas en el centro de salud externo.
 * @author Equipo de SaniVoice
 *
 */
public abstract class Conector {
	protected String URL;
	public Conector(String URL) {
		this.URL = URL;
	}
	public ArrayList<String> obtenerFechasDisponibles(Paciente p, Facultativo f) {
		return null;
	}
	
	public ArrayList<String> obtenerHorasDisponibles(Paciente p, Facultativo f, String fecha) {
		return null;
	}
	
	public boolean reservarCita(Cita c) {
		return false;
	}
	public boolean eliminarCita(Cita c) {
		return false;
	}
	
	public ArrayList<Facultativo> obtenerCuadroMedico(Paciente p){
		return null;
	}
	public ArrayList<String> obtenerEspecialidadesCentro(){
		return null;
	}
	
	
}
