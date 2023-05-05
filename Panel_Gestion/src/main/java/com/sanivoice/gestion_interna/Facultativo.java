package com.sanivoice.gestion_interna;

public class Facultativo {
	private String nombre,
				   apellidos,
				   numColegiado,
				   especialidad,
				   nombreUsuario,
				   clave;

	public Facultativo(String nombre, String apellidos, String numColegiado, String especialidad, String nombreUsuario,
			String clave) {
		super();
		this.nombre = nombre;
		this.apellidos = apellidos;
		this.numColegiado = numColegiado;
		this.especialidad = especialidad;
		this.nombreUsuario = nombreUsuario;
		this.clave = clave;
	}

	public String getNombre() {
		return nombre;
	}

	public void setNombre(String nombre) {
		this.nombre = nombre;
	}

	public String getApellidos() {
		return apellidos;
	}

	public void setApellidos(String apellidos) {
		this.apellidos = apellidos;
	}

	public String getNumColegiado() {
		return numColegiado;
	}

	public void setNumColegiado(String numColegiado) {
		this.numColegiado = numColegiado;
	}

	public String getEspecialidad() {
		return especialidad;
	}

	public void setEspecialidad(String especialidad) {
		this.especialidad = especialidad;
	}

	public String getNombreUsuario() {
		return nombreUsuario;
	}

	public void setNombreUsuario(String nombreUsuario) {
		this.nombreUsuario = nombreUsuario;
	}

	public String getClave() {
		return clave;
	}

	public void setClave(String clave) {
		this.clave = clave;
	}
	
	
}
