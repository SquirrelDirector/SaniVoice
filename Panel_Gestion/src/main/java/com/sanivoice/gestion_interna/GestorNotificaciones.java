package com.sanivoice.gestion_interna;

/**
 * Esta clase es invocada por el servlet NotificacionesServlet, de forma periódica.
 * La función de esta clase es leer las citas pendientes dentro de un rango, así
 * como las prescripciones, y conectarse a los servidores de Alexa para lanzar la
 * notificación
 * @author Equipo de SaniVoice
 *
 */
public class GestorNotificaciones {
	private static GestorNotificaciones gn;
	private GestorNotificaciones() {
		
	}
	
	public static GestorNotificaciones getGestorNotificaciones() {
		if (gn ==null){
			gn = new GestorNotificaciones();
		}
		return gn;
	}
	
	//TODO
	//MÉTODOS NECESARIOS PARA GESTIONAR LAS NOTIFICACIONES (conectarse a BD, 
	// leer citas, leer prescripciones, conectarse a Amazon...)
}
