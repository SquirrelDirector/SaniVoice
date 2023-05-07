package com.sanivoice.gestion_interna;

import java.util.Date;

public class Preinscripcion {
	private String dni;
	private Date fechaInicio;
	private Date fechaFin;
	private String periodicidad;
	
	public Preinscripcion(String dni, Date fechaInicio, Date fechaFin, String periodicidad) {
		super();
		this.dni = dni;
		this.setFechaInicio(fechaInicio);
		this.setFechaFin(fechaFin);
		this.setPeriodicidad(periodicidad);
	}
	
	public String getDni() {
		return dni;
	}
	public void setDni(String dni) {
		this.dni = dni;
	}

	public Date getFechaInicio() {
		return fechaInicio;
	}

	public void setFechaInicio(Date fechaInicio) {
		this.fechaInicio = fechaInicio;
	}

	public String getPeriodicidad() {
		return periodicidad;
	}

	public void setPeriodicidad(String periodicidad) {
		this.periodicidad = periodicidad;
	}

	public Date getFechaFin() {
		return fechaFin;
	}

	public void setFechaFin(Date fechaFin) {
		this.fechaFin = fechaFin;
	}
}
