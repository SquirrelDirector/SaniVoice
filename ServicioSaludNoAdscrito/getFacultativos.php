<?php
//ini_set("display_errors", "on"); //Muestra errores del parser PHP -BORRAR EN PRODUCCIÓN
//ini_set('display_errors', 1);
//ini_set('display_startup_errors', 1);

include("Facultativo.php");
include("Cita.php");
include("GestorBD.php");

$especialidad = $_GET["especialidad"];
$gestorBD = new DBControl();

$facultativos = $gestorBD->obtenerFacultativoPorEspecialidad($especialidad);

echo json_encode($facultativos, JSON_UNESCAPED_UNICODE);
?>