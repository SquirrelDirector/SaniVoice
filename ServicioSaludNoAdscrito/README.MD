# Centro de salud no adscrito
## Objetivo del Software
Simular un centro de salud fuera de la red de SaniVoice, para lo cual hará falta usar ciertas técnicas para comunicarse y registrar citas en este sitio.

## Acciones posibles a través de Alexa:
- Consultar las especialidades del centro médico.
- Consultar el personal por especialidad (en este caso se asume que un facultativo solo puede estar en una especialidad).
- Consultar fechas y horas disponibles para un facultativo en concreto.
- Reservar una cita para un facultativo con una fecha y hora concreta.

## Enlaces de acceso para explotación con SaniVoice Gestión.
- Acceso a especialidades: index.html (árbol DOM > elemento Select con ID "especialidad" -cuidado con leer el valor "Seleccione una especialidad..."-).
    | Método | Enlace |
    | --- | --- |
    | GET | index.html |
    Respuesta HTML:
    ```
    <!DOCTYPE html>
    <html>

    <head>
        <style>
            * {
                margin: 0;
                padding: 0;
                box-sizing: border-box;
            }

            body {
                font-family: "Inter", Arial, Helvetica, sans-serif;
            }

            .formbold-mb-5 {
                margin-bottom: 20px;
            }

            .formbold-pt-3 {
                padding-top: 12px;
            }

            .formbold-main-wrapper {
                display: flex;
                align-items: center;
                justify-content: center;
                padding: 48px;
            }

            .formbold-form-wrapper {
                margin: 0 auto;
                max-width: 550px;
                width: 100%;
                background: white;
            }

            .formbold-form-label {
                display: block;
                font-weight: 500;
                font-size: 16px;
                color: #07074d;
                margin-bottom: 12px;
            }

            .formbold-form-label-2 {
                font-weight: 600;
                font-size: 20px;
                margin-bottom: 20px;
            }

            .formbold-form-input {
                width: 100%;
                padding: 12px 24px;
                border-radius: 6px;
                border: 1px solid #e0e0e0;
                background: white;
                font-weight: 500;
                font-size: 16px;
                color: #6b7280;
                outline: none;
                resize: none;
            }

            .formbold-form-input:focus {
                border-color: #6a64f1;
                box-shadow: 0px 3px 8px rgba(0, 0, 0, 0.05);
            }

            .formbold-btn {
                text-align: center;
                font-size: 16px;
                border-radius: 6px;
                padding: 14px 32px;
                border: none;
                font-weight: 600;
                background-color: #6a64f1;
                color: white;
                width: 100%;
                cursor: pointer;
            }

            .formbold-btn:hover {
                box-shadow: 0px 3px 8px rgba(0, 0, 0, 0.05);
            }

            .formbold--mx-3 {
                margin-left: -12px;
                margin-right: -12px;
            }

            .formbold-px-3 {
                padding-left: 12px;
                padding-right: 12px;
            }

            .flex {
                display: flex;
            }

            .flex-wrap {
                flex-wrap: wrap;
            }

            .w-full {
                width: 100%;
            }

            @media (min-width: 540px) {
                .sm\:w-half {
                    width: 50%;
                }
            }
        </style>

    </head>

    <body>
        <h1>Petición cita - Ejemplo centro no adscrito</h1>
        <div class="formbold-main-wrapper">
            <!-- Author: FormBold Team -->
            <!-- Learn More: https://formbold.com -->
            <div class="formbold-form-wrapper">
                <form action="guardarCita.php" method="POST">
                    <div class="formbold-mb-5">
                        <label for="name" class="formbold-form-label"> Nombre </label>
                        <input
            type="text"
            name="nombre"
            id="name"
            placeholder="Nombre"
            class="formbold-form-input"
            />
                        <label for="apellidos" class="formbold-form-label"> Apellidos </label>
                        <input
            type="text"
            name="apellidos"
            id="apellidos"
            placeholder="Apellidos"
            class="formbold-form-input"
            />
        </div>
                        <div class="formbold-mb-5">
                            <label for="phone" class="formbold-form-label"> Teléfono de Contacto </label>
                            <input
            type="text"
            name="tel_contacto"
            id="phone"
            placeholder="Nº de teléfono"
            class="formbold-form-input"
            />
        </div>
                            <div class="formbold-mb-5">
                                <label for="email" class="formbold-form-label"> Correo Electrónico </label>
                                <input
            type="email"
            name="email"
            id="email"
            placeholder="Correo electrónico"
            class="formbold-form-input"
            />
        </div>
                                <div class="formbold-mb-5">
                                    <label for="especialidad" class="formbold-form-label"> Especialidad </label>
                                    <select
            name="especialidad"
            id="especialidad"
            class="formbold-form-input"
            >
                <option value="nulo">Elija opción...</option>
                <option value="nutricion">Nutrición</option>
                <option value="urologia">Urología</option>
                <option value="medicina-general">Medicina General</option>
                <option value="oftalmologia">Oftalmología</option>
                <option value="otorrinolaringologia">Otorrinolaringología</option>
                <option value="pediatria">Pediatría</option>
                <option value="dermatologia">Dermatología</option>
                <option value="endocrinologia">Endocrinología</option>
                <option value="traumatologia">Traumatología</option>
            </select>
                                </div>
                                <div class="formbold-mb-5">
                                    <label for="doctor" class="formbold-form-label"> Doctor/a </label>
                                    <select
            name="doctor"
            id="doctor"
            class="formbold-form-input"
            >
            <option value="nulo">Elija opción...</option>
            </select>
                                </div>
                                <div class="formbold-mb-5">
                                    <label for="fecha_hora" class="formbold-form-label"> Fechas y horas disponibles </label>
                                    <select
            name="fecha_hora"
            id="fecha_hora"
            class="formbold-form-input"
            ><option value="nulo">Elija opción...</option>
            </select>
                                </div>

                                <div>
                                    <button class="formbold-btn">Reservar cita</button>
                                </div>
                </form>
            </div>
        </div>
        <script>
            document.getElementById("especialidad").addEventListener("change", function(e){
                    if(e.target.value!="nulo"){
                    fetch("getFacultativos.php?especialidad="+e.target.value).then((response)=>{
                    return response.json();
                    }).then((response)=>{
                        selectElement = document.getElementById("doctor");
                        var i, L = selectElement.options.length - 1;
                        for(i = L; i >= 0; i--) {
                        if(selectElement.options[i].value!="nulo"){
                            selectElement.remove(i);
                        }
                        }
                        
                        for(var i=0; i<response.length; i++){
                        var opt = document.createElement("option");
                        opt.value= response[i].id;
                        opt.innerHTML = response[i].nombre+" "+response[i].apellidos; // whatever property it has
                        document.getElementById("doctor").appendChild(opt);
                        }
                    }
                    );
                    }
                    
                });



                document.getElementById("doctor").addEventListener("change", function(e){
                    
                    var idFac = e.target.value;
                    if(idFac!="nulo"){

                    
                    fetch("getFechasYHorasDisponibles.php?idFacultativo="+idFac).then((response)=>{
                        return response.json();
                    }).then((response)=>{
                        selectElement = document.getElementById("fecha_hora");
                        var i, L = selectElement.options.length - 1;
                        for(i = L; i >= 0; i--) {
                        if(selectElement.options[i].value!="nulo"){
                            selectElement.remove(i);
                        }
                        
                        }
                        for(var i=0; i<response.length; i++){
                        console.log(response[i]);
                        var opt = document.createElement("option");
                        opt.value= response[i].fecha+"T"+response[i].hora;
                        var fecha = new Date(response[i].fecha);
                        opt.innerHTML = fecha.toLocaleDateString()+" a las "+response[i].hora; // whatever property it has
                        document.getElementById("fecha_hora").appendChild(opt);
                        }
                    });
                    }
                });
        </script>
    </body>

    </html>
    ```

- Acceso a facultativos: getFacultativos.php
    | Método | Enlace |
    | --- | --- |
    | GET | getFacultativos.php |
    
    | Nombre de parámetro | Descripción |
    | --- | --- |
    | especialidad | Nombre de la especialidad (tal como aparece en index.html en el apartado "Value" de cada especialidad, como por ejemplo "medicina-general") |
    Ejemplo Respuesta JSON:
    ```
    [
    {
        "id": 1,
        "nombre": "Fernando",
        "apellidos": "Muguruza Galán",
        "especialidad": "medicina-general"
    },
    {
        "id": 2,
        "nombre": "Tomás",
        "apellidos": "Vega Santamaría",
        "especialidad": "medicina-general"
    }
    ]
    ```

- Obtención de las citas disponibles por especialista: getFechasYHorasDisponibles.php. *NOTA: DEVUELVE CITAS DISPONIBLES SOLO HASTA 1 MES DESPUÉS DE LA FECHA ACTUAL.*
    | Método | Enlace |
    | --- | --- |
    | GET | getFechasYHorasDisponibles.php |

    | Nombre de parámetro | Descripción |
    | --- | --- |
    | idFacultativo | El identificador del doctor (se puede obtener a través de getFacultativos.php) |

    Respuesta JSON de ejemplo:

    ```
    [
    {
        "fecha": "2023-05-06",
        "hora": "13:00:00"
    },
    {
        "fecha": "2023-05-07",
        "hora": "10:15:00"
    }
    ]
    ```


- Concertar cita en centro de salud
    | Método | Enlace |
    | --- | --- |
    | POST | guardarCita.php |

    | Nombre de parámetro | Descripción |
    | --- | --- |
    | idFacultativo | El identificador del doctor (se puede obtener a través de getFacultativos.php) |
    | nombre | Nombre del paciente (alojado en base de datos de SaniVoice Gestión)|
    | apellidos | Apellidos del paciente (alojado en base de datos de SaniVoice Gestión) |
    | tel_contacto | Teléfono de contacto del paciente (alojado en base de datos de SaniVoice Gestión) |
    | email | Email del paciente (alojado en base de datos de SaniVoice Gestión) |
    | fecha_hora | Fecha y hora de la cita (***¡en formato YYYY-MM-DDTHH:MM:SS!***) |
    | doctor | Identificador del facultativo |
    
    Respuesta que se obtiene: JSON, booleano, True si se ha concertado la cita, False en caso contrario.
    ```
    true
    ```