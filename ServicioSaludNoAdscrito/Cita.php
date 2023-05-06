<?php
class Cita implements JsonSerializable{
    private $nombre;
    private $apellidos;
    private $telContacto;
    private $email;
    private $fecha;
    private $hora;
    private $fac;

    public function __construct($nombre, $apellidos, $telefono, $email, $fecha, $hora, $fac){
        $this->nombre=$nombre;
        $this->apellidos=$apellidos;
        $this->telContacto=$telefono;
        $this->email=$email;
        $this->hora=$hora;
        $this->fecha=$fecha;
        $this->fac=$fac;
    }

    public function jsonSerialize() {
        return (object) get_object_vars($this);
    }

    public function getNombre(){
        return $this->nombre;
    }

    public function getApellidos(){
        return $this->apellidos;
    }

    public function getTelContacto(){
        return $this->telContacto;
    }

    public function getEmail(){
        return $this->email;
    }
    
    public function getFecha(){
        return $this->fecha;
    }

    public function getHora(){
        return $this->hora;
    }

    public function getFacultativo(){
        return $this->fac;
    }
}