<?php

//ini_set("display_errors", "on"); //Muestra errores del parser PHP -BORRAR EN PRODUCCIÓN//
//ini_set('display_errors', 1);
//ini_set('display_startup_errors', 1);

include("Facultativo.php");
include("Cita.php");
include("TuplaFechaHora.php");
include("GestorBD.php");

$gestorBD = new DBControl();

$fechas = $gestorBD->obtenerCitasPorFacultativo($_GET["idFacultativo"]);

//echo json_encode($fechas, JSON_UNESCAPED_UNICODE);
//$currentDate = date('Y-m-d');
$fechaHoy = new DateTime(date('Y-m-d'));
$fechaIndex = new DateTime(date('Y-m-d'));
$fechaUnMes = new DateTime(date('Y-m-d'));
$fechaUnMes->add(new DateInterval("P30D"));
//$fechaUnMesDespues = $fechaUnMes->format('Y-m-d');


$horaActual = date('H:i:s');
$horas= array(date("10:00:00"), 
         date("10:15:00"),
         date("10:30:00"),
         date("10:45:00"),
         date("11:00:00"),
         date("11:15:00"),
         date("11:30:00"),
         date("11:45:00"),
         date("12:00:00"),
         date("12:15:00"),
         date("12:30:00"),
         date("12:45:00"),
         date("13:00:00"));
$citasDisponibles=array();
while($fechaIndex<=$fechaUnMes){
    foreach($horas as $horaIdx){
        $existe = false;
        foreach($fechas as $citaIdx){
            $fechaCita = new DateTime($citaIdx->getFecha());
            $horaCita = date($citaIdx->getHora());
            if($fechaCita==$fechaIndex){
                if($horaIdx==$horaCita){
                    $existe = true;
                }
            }
        }
        if($existe==false && (($fechaIndex == $fechaHoy && $horaIdx>$horaActual) || $fechaIndex>$fechaHoy)){
            //echo "<br>FECHA: ".$fechaIndex->format("Y-m-d").". HORA: ".$horaIdx;
            $cita = new TuplaFechaHora($fechaIndex->format("Y-m-d"), $horaIdx);
            array_push($citasDisponibles, $cita);
        }
    }
    $fechaIndex->add(new DateInterval("P1D"));
}

echo json_encode($citasDisponibles);


//$param=$_GET["tipo_parametro"];


/*if($param=="fechas"){


}else if ($param=="horas"){
    $fecha = $_GET["fechas"];
}*/


?>