package com.sanivoice.conector_centros_salud;
import com.sanivoice.gestion_interna.CentroSalud;


/**
 * Clase que genera las diversas conexiones a los distintos centros de salud
 * @author Equipo de SaniVoice
 *
 */
public class ConectorCentroSaludFactory {
	private static ConectorCentroSaludFactory ccsf;
	private ConectorCentroSaludFactory() {
		
	}
	
	public static ConectorCentroSaludFactory getConectorCentroSaludFactory() {
		if(ccsf == null) {
			ccsf = new ConectorCentroSaludFactory();
		}
		return ccsf;
	}
	
	public Conector getConectorCentroSalud(CentroSalud cs) {
		if(cs.getURL().contains("164.92.236.0")) {
			return new ConectorCentroNoAdscrito(cs.getURL());
		}else {
			return new ConectorCentroAdscrito(cs.getURL());
		}
	}

}
