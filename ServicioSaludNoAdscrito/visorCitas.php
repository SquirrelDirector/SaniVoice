<?php

ini_set("display_errors", "on"); //Muestra errores del parser PHP -BORRAR EN PRODUCCIÓN
ini_set('display_errors', 1);
ini_set('display_startup_errors', 1);

?>


<!DOCTYPE html>
<html>
<head>
    <title>VisorCitas</title>
</head>
<?php

include("Facultativo.php");
include("Cita.php");
include("GestorBD.php");

$gestorBD = new DBControl();
$citas = $gestorBD->obtenerCitasExistentes();

foreach($citas as $cita){
    $fac = $cita->getFacultativo();
?>
<div class="cita">
    <h5>Datos del paciente a ser atendido:</h5>
    <p>Nombre: <?=$cita->getNombre()?></p>
    <p>Apellidos: <?=$cita->getApellidos()?></p>
    <p>Teléfono: <?=$cita->getTelContacto()?></p>
    <p>Fecha y hora: <?=$cita->getFecha()?> a las <?=$cita->getHora()?></p>

    <h5>Datos del facultativo que atiende:</h5>
    <p>Nombre: <?=$fac->getNombre()?></p>
    <p>Apellidos: <?=$fac->getApellidos()?></p>
    <p>Especialidad: <?=$fac->getEspecialidad()?></p>

</div>
<hr/>
<?php
}
?>