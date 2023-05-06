<?php
class Facultativo implements JsonSerializable{
    private $id;
    private $nombre;
    private $apellidos;
    private $especialidad;

    public function __construct($id, $nombre, $apellidos, $especialidad){
        $this->id=$id;
        $this->nombre=$nombre;
        $this->apellidos=$apellidos;
        $this->especialidad=$especialidad;
    }

    public function jsonSerialize() {
        return (object) get_object_vars($this);
    }

    public function getId(){
        return $this->id;
    }

    public function getNombre(){
        return $this->nombre;
    }

    public function getApellidos(){
        return $this->apellidos;
    }

    public function getEspecialidad(){
        return $this->especialidad;
    }
}



?>