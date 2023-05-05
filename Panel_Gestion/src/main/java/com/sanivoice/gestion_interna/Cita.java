package com.sanivoice.gestion_interna;

public class Cita {
	private String GUID; //Se usa para identificar la cita en el centro de salud adscrito
	private String fecha; //TODO Revisar clase Date para implementar la fecha con un tipo de variable coherente
	private String hora;//TODO Revisar clase Date para implementar la fecha con un tipo de variable coherente
	
	private Paciente p;
	private Facultativo f;
	private CentroSalud cs;
	public Cita(String gUID, String fecha, String hora, Paciente p, Facultativo f, CentroSalud cs) {
		super();
		GUID = gUID;
		this.fecha = fecha;
		this.hora = hora;
		this.p = p;
		this.f = f;
		this.cs = cs;
	}
	public String getGUID() {
		return GUID;
	}
	public void setGUID(String gUID) {
		GUID = gUID;
	}
	public String getFecha() {
		return fecha;
	}
	public void setFecha(String fecha) {
		this.fecha = fecha;
	}
	public String getHora() {
		return hora;
	}
	public void setHora(String hora) {
		this.hora = hora;
	}
	public Paciente getPaciente() {
		return p;
	}
	public void setPaciente(Paciente p) {
		this.p = p;
	}
	public Facultativo getFacultativo() {
		return f;
	}
	public void setFacultativo(Facultativo f) {
		this.f = f;
	}
	public CentroSalud getCentroSalud() {
		return cs;
	}
	public void setCentroSalud(CentroSalud cs) {
		this.cs = cs;
	}
	
	
}
