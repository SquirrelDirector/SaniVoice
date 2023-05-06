<?php
class DBControl{

    private $user="admin";
    private $pwd="test";
    private $dbName="database";
    private $hostname="db";

    /**
     * Constructor
     */
    public function __construct(){

    }


    public function obtenerCitasPorFacultativo($idFac){
        $enlace=mysqli_connect($this->hostname,$this->user,$this->pwd,$this->dbName);

        $citas=array();
        if(!$enlace){
            die("Fallo de conexion:" . mysqli_connect_error());
        }

        //WHERE FECHA>HOY
        $stmt =$enlace->prepare("SELECT nombrePac, apellidosPac, telContactoPac, emailPac, fechaCita, horaCita, idFacultativo FROM Cita WHERE idFacultativo=?");
        $stmt->bind_param("i", $idFac);
        $res=$stmt->execute();
        $resultado=$stmt->get_result();
        $stmt->close();
        
        if($resultado!=false){
            if ($resultado->num_rows > 0) {
                // output data of each row
                while($row = $resultado->fetch_assoc()) {
                    $stmt_2 =$enlace->prepare("SELECT idFacultativo, nombreFacultativo, apellidosFacultativo, especialidad FROM Facultativo WHERE idFacultativo = ?");
                    $stmt_2->bind_param("i", $row["idFacultativo"]);

                    $res=$stmt_2->execute();
                    $resultado_fac=$stmt_2->get_result();
                    $stmt_2->close();
                    $facultativo = null;
                    if($resultado_fac!=false){
                        if($resultado_fac->num_rows > 0){
                            while($fila = $resultado_fac->fetch_assoc()){
                                $facultativo = new Facultativo($fila["idFacultativo"], $fila["nombreFacultativo"], $fila["apellidosFacultativo"], $fila["especialidad"]);
                            }
                        }
                    }
                    $c = new Cita($row["nombrePac"],$row["apellidosPac"], $row["telContactoPac"], $row["emailPac"], $row["fechaCita"], $row["horaCita"], $facultativo);
                    array_push($citas, $c);
                }
            }

        }
        mysqli_close ($enlace);
        return $citas;
    }

    public function obtenerCitasExistentes(){
        $enlace=mysqli_connect($this->hostname,$this->user,$this->pwd,$this->dbName);

        $citas=array();
        if(!$enlace){
            die("Fallo de conexion:" . mysqli_connect_error());
        }

        //WHERE FECHA>HOY
        $stmt =$enlace->prepare("SELECT nombrePac, apellidosPac, telContactoPac, emailPac, fechaCita, horaCita, idFacultativo FROM Cita ");
        //$stmt->bind_param("s", $tipo);
        $res=$stmt->execute();
        $resultado=$stmt->get_result();
        $stmt->close();

        if($resultado!=false){
            if ($resultado->num_rows > 0) {
                // output data of each row
                while($row = $resultado->fetch_assoc()) {

                    $stmt_2 =$enlace->prepare("SELECT idFacultativo, nombreFacultativo, apellidosFacultativo, especialidad FROM Facultativo WHERE idFacultativo = ?");
                    $stmt_2->bind_param("i", $row["idFacultativo"]);

                    $res=$stmt_2->execute();
                    $resultado_fac=$stmt_2->get_result();
                    $stmt_2->close();
                    $facultativo = null;
                    if($resultado_fac!=false){
                        if($resultado_fac->num_rows > 0){
                            while($fila = $resultado_fac->fetch_assoc()){
                                $facultativo = new Facultativo($fila["idFacultativo"], $fila["nombreFacultativo"], $fila["apellidosFacultativo"], $fila["especialidad"]);
                            }
                        }
                    }
                    $c = new Cita($row["nombrePac"],$row["apellidosPac"], $row["telContactoPac"], $row["emailPac"], $row["fechaCita"], $row["horaCita"], $facultativo);
                    array_push($citas, $c);
                }
            }

        }
        mysqli_close ($enlace);
        return $citas;
    }

    public function obtenerFacultativo($id){
        $enlace=mysqli_connect($this->hostname,$this->user,$this->pwd,$this->dbName);
        
        $fac =null;
        if(!$enlace){
            die("Fallo de conexion:" . mysqli_connect_error());
        }

        $stmt_2 =$enlace->prepare("SELECT nombreFacultativo, apellidosFacultativo, especialidad FROM Facultativo WHERE idFacultativo = ?");
        $stmt_2->bind_param("i", $id);

        $res=$stmt_2->execute();
        $resultado_fac=$stmt_2->get_result();
        $stmt_2->close();
        $facultativo = null;
        if($resultado_fac!=false){
            if($resultado_fac->num_rows > 0){
                while($fila = $resultado_fac->fetch_assoc()){
                    
                    $fac = new Facultativo($id, $row["nombreFacultativo"], $row["apellidosFacultativo"], $row["especialidad"]);
                }
            }
        }
        mysqli_close ($enlace);
        return $fac;
    }

    public function obtenerFacultativoPorEspecialidad($especialidad){
        $enlace=mysqli_connect($this->hostname,$this->user,$this->pwd,$this->dbName);

        $fac =array();
        if(!$enlace){
            die("Fallo de conexion:" . mysqli_connect_error());
        }

        $stmt_2 =$enlace->prepare("SELECT idFacultativo, nombreFacultativo, apellidosFacultativo, especialidad FROM Facultativo WHERE especialidad = ?");
        $stmt_2->bind_param("s", $especialidad);

        $res=$stmt_2->execute();
        $resultado_fac=$stmt_2->get_result();
        $stmt_2->close();
        //$facultativo = null;
        if($resultado_fac!=false){
            if($resultado_fac->num_rows > 0){
                while($row = $resultado_fac->fetch_assoc()){
                    $facu = new Facultativo($row["idFacultativo"], $row["nombreFacultativo"], $row["apellidosFacultativo"], $row["especialidad"]);
                    array_push($fac, $facu);
                }
            }
        }
        mysqli_close ($enlace);
        return $fac;
    }

    public function insertarCita($cita){
        $fac = $cita->getFacultativo();
        $enlace=mysqli_connect(($this->hostname),($this->user),($this->pwd),($this->dbName));
        if(!$enlace){
            die("Fallo de conexion:" . mysqli_connect_error());
        }
        $nomPac = $cita->getNombre();
        $apePac = $cita->getApellidos();
        $telCon = $cita->getTelContacto();
        $email = $cita->getEmail();
        $fecha = $cita->getFecha();
        $hora = $cita->getHora();

        $stmt=$enlace->prepare("INSERT INTO Cita (nombrePac, apellidosPac, telContactoPac, emailPac, fechaCita, horaCita, idFacultativo) VALUES (?,?,?,?,?,?,?)");
        $stmt->bind_param("ssssssi",$nomPac, $apePac, $telCon, $email, $fecha, $hora, $fac->getId());
        $estado = $stmt->execute();
        $stmt->close();
        return $estado;
    }
}


?>