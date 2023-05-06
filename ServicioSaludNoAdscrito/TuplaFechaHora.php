<?php

class TuplaFechaHora implements JsonSerializable{

    private $fecha;
    private $hora;

    public function __construct($fecha, $hora){
        $this->fecha = $fecha;
        $this->hora = $hora;
    }

    public function jsonSerialize() {
        return (object) get_object_vars($this);
    }

    public function getFecha(){
        return $fecha;
    }

    public function getHora(){
        return $hora;
    }

    public function setFecha($fecha){
        $this->fecha = $fecha;
    }

    public function setHora($hora){
        $this->hora = $hora;
    }
}


?>