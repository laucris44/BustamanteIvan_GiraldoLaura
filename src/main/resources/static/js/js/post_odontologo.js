window.addEventListener('load', function () {

     //Al cargar la pagina buscamos y obtenemos el formulario donde estarán
     //los datos que el usuario cargará del nuevo odontologo
    const formulario = document.querySelector('#add_new_odontologo');

    //Ante un submit del formulario se ejecutará la siguiente funcionfunction mostrarFormulario() {

                                                                       // Declaramos las constantes pacienteSelect y odontologoSelect
                                                                       const pacienteSelect = document.querySelector('#paciente_id');
                                                                       const odontologoSelect = document.querySelector('#odontologo_id');

                                                                       if (!mostrarFormulario.pacientesSet) {
                                                                         mostrarFormulario.pacientesSet = new Set();
                                                                       }
                                                                       if (!mostrarFormulario.odontologosSet) {
                                                                         mostrarFormulario.odontologosSet = new Set();
                                                                       }
                                                                       let pacientes = [];
                                                                       let odontologos = [];

                                                                       fetch('/paciente/listar')
                                                                         .then(response => response.json())
                                                                         .then(data => {
                                                                           pacientes = data;
                                                                           pacientes.forEach(paciente => {
                                                                             if (!mostrarFormulario.pacientesSet.has(paciente.id)) {
                                                                               const option = document.createElement('option');
                                                                               option.value = paciente.id;
                                                                               option.textContent = `${paciente.nombre} ${paciente.apellido}`;
                                                                               pacienteSelect.appendChild(option);
                                                                               mostrarFormulario.pacientesSet.add(paciente.id);
                                                                             }
                                                                           });
                                                                           return fetch('/odontologo/listar')
                                                                         })
                                                                         .then(response => response.json())
                                                                         .then(data => {
                                                                           odontologos = data;
                                                                           odontologos.forEach(odontologo => {
                                                                             if (!mostrarFormulario.odontologosSet.has(odontologo.id)) {
                                                                               const option = document.createElement('option');
                                                                               option.value = odontologo.id;
                                                                               option.textContent = `${odontologo.nombre} ${odontologo.apellido}`;
                                                                               odontologoSelect.appendChild(option);
                                                                               mostrarFormulario.odontologosSet.add(odontologo.id);
                                                                             }
                                                                           });
                                                                           const formulario = document.querySelector('#update_turno_form');
                                                                           formulario.style.display = "block";
                                                                         })
                                                                         .catch(error => {
                                                                           console.error('Error al obtener los datos:', error);
                                                                         })
                                                                     };
                                                                     // En la vista, llamamos al controlador cuando se carga la página
                                                                     window.onload = function() {
                                                                       mostrarFormulario();

                                                                       const formulario = document.querySelector('#update_turno_form');

                                                                       formulario.addEventListener('submit', function(event) {
                                                                         event.preventDefault();

                                                                         const idTurno = document.querySelector('#turno_id').value;
                                                                         const odontologoId = document.querySelector('#odontologo_id').value;
                                                                         const fecha = document.querySelector('#fecha').value;
                                                                         const hora = document.querySelector('#hora').value;
                                                                         const pacienteId = document.querySelector('#paciente_id').value;

                                                                         if (!idTurno || !odontologoId || !fecha || !hora || !pacienteId) {
                                                                           alert('Por favor, complete todos los campos');
                                                                           return;
                                                                         }

                                                                         const turno = {
                                                                           id: idTurno,
                                                                           odontologo: {
                                                                             id: odontologoId
                                                                           },
                                                                           paciente: {
                                                                             id: pacienteId
                                                                           },
                                                                           fecha: fecha,
                                                                           hora: hora
                                                                         };

                                                                         const url = '/turnos/modificar';
                                                                         const settings = {
                                                                           method: 'PUT',
                                                                           headers: {
                                                                             'Content-Type': 'application/json',
                                                                           },
                                                                           body: JSON.stringify(turno)
                                                                         }
                                                                         fetch(url, settings)
                                                                           .then(response => response.json())
                                                                          .then(data => {
                                                                             console.log('El turno ha sido modificado correctamente');
                                                                             location.reload();
                                                                           })
                                                                           .catch(error => {
                                                                             alert('Error al modificar el turno');
                                                                           });
                                                                       });
                                                                     }

                                                                      //Es la función que se invoca
                                                                      //cuando se hace clic sobre el ID de un turno del listado
                                                                      //se encarga de llenar el formulario con los datos del turno
                                                                     //que se desea modificar
                                                                     function findBy(id) {
                                                                       const url = '/turnos/' + id;
                                                                       const settings = {
                                                                         method: 'GET',
                                                                         headers: {
                                                                           'Content-Type': 'application/json',
                                                                         },
                                                                       };
                                                                       return fetch(url, settings)
                                                                         .then(response => response.json())
                                                                         .then(turno => {
                                                                           document.querySelector('#turno_id').value = turno.id;
                                                                           document.querySelector('#odontologo_id').value = turno.odontologo.id;
                                                                           document.querySelector('#paciente_id').value = turno.paciente.id;
                                                                           document.querySelector('#fecha').value = turno.fecha;
                                                                           document.querySelector('#hora').value = turno.hora;

                                                                           mostrarFormulario(); // Llama a la función mostrarFormulario para cargar los datos del turno en el formulario

                                                                           //el formulario por default esta oculto y al editar se habilita
                                                                           document.querySelector('#div_turno_updating').style.display = "block";
                                                                         })
                                                                         .catch(error => {
                                                                           alert("Error: " + error);
                                                                         });
                                                                     }
    formulario.addEventListener('submit', function (event) {

        //creamos un JSON que tendrá los datos del nuevo odontologo
        const formData = {
            nombre: document.querySelector('#nombre').value,
            apellido: document.querySelector('#apellido').value,
            matricula: document.querySelector('#matricula').value,
        };

        //invocamos utilizando la función fetch la API odontologos con el método POST
        //que guardará al odontologo que enviaremos en formato JSON
        const url = '/odontologo';
        const settings = {
            method: 'POST',
            headers: {
                'Content-Type': 'application/json',
            },
            body: JSON.stringify(formData)
        }

        fetch(url, settings)
            .then(response => response.json())
            .then(data => {
               //Si no hay ningun error se muestra un mensaje diciendo que el odontologo
               //se agrego bien
                 let successAlert = '<div class="alert alert-success alert-dismissible">' +
                     '<button type="button" class="close" data-dismiss="alert">&times;</button>' +
                     '<strong> Odontólogo agregado </strong> </div>'

                 document.querySelector('#response').innerHTML = successAlert;
                 document.querySelector('#response').style.display = "block";
                 //se dejan todos los campos vacíos por si se quiere ingresar otro odontologo
                 resetUploadForm();

            })
            .catch(error => {
                 //Si hay algun error se muestra un mensaje diciendo que el odontologo
                 //no se pudo guardar y se intente nuevamente
                  let errorAlert = '<div class="alert alert-danger alert-dismissible">' +
                                     '<button type="button" class="close" data-dismiss="alert">&times;</button>' +
                                     '<strong> Error intente nuevamente</strong> </div>'

                   document.querySelector('#response').innerHTML = errorAlert;
                   document.querySelector('#response').style.display = "block";

                   //se dejan todos los campos vacíos por si se quiere ingresar otro odontologo
                   resetUploadForm();})
    });

    function resetUploadForm(){
    const form = document.querySelector('#add_new_odontologo');
      const elements = form.elements;

      for (let i = 0; i < elements.length; i++) {
        const element = elements[i];

        // Solo establecer el valor predeterminado para los elementos que tienen un valor
        if (element.value) {
          element.value = "";
        }
      }
    }

    (function(){
        let pathname = window.location.pathname;
        if(pathname === "/"){
            document.querySelector(".nav .nav-item a:first").addClass("active");
        } else if (pathname == "/odontologoList.html") {
            document.querySelector(".nav .nav-item a:last").addClass("active");
        }
    })();
});