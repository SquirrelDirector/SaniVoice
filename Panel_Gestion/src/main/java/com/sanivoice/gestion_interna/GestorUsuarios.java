package com.sanivoice.gestion_interna;

/**
 * Clase que gestiona los diversos usuarios, sean facultativos o pacientes
 * 
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
	 * TODO ESCRIBIR MÉTODOS AQUÍ PARA GESTIONAR LOS USUARIOS (p.e.
	 * guardarUsuario(usuario U), modificarDatosUsuario(usuario U),
	 * validarInicioSesion(), obtenerFacultativosDePaciente(usuario U) etc.) A
	 * través de este gestor llamar al gestorBD para realizar los cambios
	 * pertinentes, así como al gestor de centros de salud!
	 */

	public void registroCorrecto(Paciente p) {
		GestorBD.getGestorBD().getRegistroCorrecto(p);
	}
}
