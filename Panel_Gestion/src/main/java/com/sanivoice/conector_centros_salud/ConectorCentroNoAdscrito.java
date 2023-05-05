package com.sanivoice.conector_centros_salud;

import java.util.ArrayList;

import com.sanivoice.gestion_interna.Cita;
import com.sanivoice.gestion_interna.Facultativo;
import com.sanivoice.gestion_interna.Paciente;

/**
 * Conector para centro NO adscrito. Modificar según cómo funcione la web del centro.
 * @author Equipo de SaniVoice
 *
 */
public class ConectorCentroNoAdscrito extends Conector {
	public ConectorCentroNoAdscrito(String URL) {
		super(URL);
	}

	private String URL;
	@Override
	public ArrayList<String> obtenerFechasDisponibles(Paciente p, Facultativo f) {
		return null;
	}
	
	@Override
	public ArrayList<String> obtenerHorasDisponibles(Paciente p, Facultativo f, String fecha) {
		return null;
	}
	
	@Override
	public boolean reservarCita(Cita c) {
		return false;
	}
	
	@Override
	public boolean eliminarCita(Cita c) {
		return false;
	}
	
	@Override
	public ArrayList<Facultativo> obtenerCuadroMedico(Paciente p){
		return null;
	}
}
