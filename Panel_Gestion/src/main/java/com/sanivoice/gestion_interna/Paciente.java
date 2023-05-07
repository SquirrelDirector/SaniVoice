package com.sanivoice.gestion_interna;

public class Paciente {
	private String nombre, 
				   apellidos, 
				   DNI,
				   tarjetaSanitaria,
				   telContacto,
				   domicilio,
				   email,
				   clave;
	private CentroSalud cs;

	public Paciente(String nombre, String apellidos, String dNI, String tarjetaSanitaria, String telContacto,
			String domicilio, String email, String clave, CentroSalud cs) {
		super();
		this.nombre = nombre;
		this.apellidos = apellidos;
		DNI = dNI;
		this.tarjetaSanitaria = tarjetaSanitaria;
		this.telContacto = telContacto;
		this.domicilio = domicilio;
		this.email = email;
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

	public String getDNI() {
		return DNI;
	}

	public void setDNI(String dNI) {
		DNI = dNI;
	}

	public String getTarjetaSanitaria() {
		return tarjetaSanitaria;
	}

	public void setTarjetaSanitaria(String tarjetaSanitaria) {
		this.tarjetaSanitaria = tarjetaSanitaria;
	}

	public String getTelContacto() {
		return telContacto;
	}

	public void setTelContacto(String telContacto) {
		this.telContacto = telContacto;
	}

	public String getDomicilio() {
		return domicilio;
	}

	public void setDomicilio(String domicilio) {
		this.domicilio = domicilio;
	}

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	public String getClave() {
		return clave;
	}

	public void setClave(String clave) {
		this.clave = clave;
	}

	public CentroSalud getCs() {
		return cs;
	}

	public void setCs(CentroSalud cs) {
		this.cs = cs;
	}
	
	
}
