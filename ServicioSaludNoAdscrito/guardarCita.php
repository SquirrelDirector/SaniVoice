<?php
include("Facultativo.php");
include("Cita.php");
include("TuplaFechaHora.php");
include("GestorBD.php");
$nombre = $_POST["nombre"];
$apellidos = $_POST["apellidos"];
$telContacto = $_POST["tel_contacto"];
$email = $_POST["email"];
$fechaHora = $_POST["fecha_hora"];
$idFac = $_POST["doctor"];

$fechaVar = new DateTime($fechaHora);

$gestorBD = new DBControl();
$facultativo = $gestorBD->obtenerFacultativo($idFac);

$c = new Cita($nombre,$apellidos, $telContacto, $email, $fechaVar->format("Y-m-d"), $fechaVar->format("H:i"), $facultativo);
$resultado = $gestorBD->insertarCita($c);
echo json_encode($resultado);
?>