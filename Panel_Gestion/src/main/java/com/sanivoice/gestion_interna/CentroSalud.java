package com.sanivoice.gestion_interna;

public class CentroSalud {

	private String nombreCentro,
				   URL;
	private boolean isAdscrito;
	public CentroSalud(String nombreCentro, String uRL, boolean isAdscrito) {
		this.nombreCentro = nombreCentro;
		this.URL = uRL;
		this.isAdscrito = isAdscrito;
	}
	public String getNombreCentro() {
		return nombreCentro;
	}
	public void setNombreCentro(String nombreCentro) {
		this.nombreCentro = nombreCentro;
	}
	public String getURL() {
		return URL;
	}
	public void setURL(String uRL) {
		URL = uRL;
	}
	public boolean isAdscrito() {
		return isAdscrito;
	}
	public void setAdscrito(boolean isAdscrito) {
		this.isAdscrito = isAdscrito;
	}
	
	
}
