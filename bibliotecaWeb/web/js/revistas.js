/*
 * revistas.js
 * 
 * Hugo Rivera Vazquez | 00000205540
 * Luis Gonzalo Cervantes Rivera | 00000228549
 * Jesus Ricardo Ortega Sanchez | 00000216703
 * 
 * Contiene las funciones que implementan las operaciones
 * con las revistas
 */
var isbn;
var titulo;
var editorial;
var clasificacion;
var periodicidad;
var fecha;
let encabezados = ["ISBN", "Título", "Editorial", "Clasificación", "Periodicidad", "Fecha"];

/**
 * Función que despliega un formulario para capturar el
 * isbn de una revista a guardar.
 */
function capturaIsbn() {
    borraHijos("main");

    let padre = document.getElementById("main");
    let titulo = document.createElement("h1");

    titulo.innerHTML = "Captura ISBN";
    padre.appendChild(titulo);

    let formulario = document.createElement("form");
    padre.appendChild(formulario);

    let contenedorFormulario = document.createElement("div");
    contenedorFormulario.setAttribute("class", "formulario");
    formulario.appendChild(contenedorFormulario);

    let celdaEqtiquetaIsbn = document.createElement("div");
    celdaEqtiquetaIsbn.setAttribute("class", "derecha");
    contenedorFormulario.appendChild(celdaEqtiquetaIsbn);

    let etiquetaIsbn = document.createElement("label");
    etiquetaIsbn.setAttribute("for", "campoIsbnId");
    etiquetaIsbn.innerHTML = "ISBN*";
    celdaEqtiquetaIsbn.appendChild(etiquetaIsbn);

    let celdaIsbn = document.createElement("div");
    celdaIsbn.setAttribute("id", "isbnId");
    contenedorFormulario.appendChild(celdaIsbn);

    let campoIsbn = document.createElement("input");
    campoIsbn.setAttribute("type", "text");
    campoIsbn.setAttribute("id", "campoIsbnId");
    campoIsbn.setAttribute("size", "13");
    campoIsbn.setAttribute("title", "13 dígitos");
    campoIsbn.setAttribute("maxlength", "13");
    campoIsbn.setAttribute("placeholder", "Código ISBN");
    campoIsbn.setAttribute("pattern", "^[0-9]{13}");
    campoIsbn.setAttribute("required", "required");
    celdaIsbn.appendChild(campoIsbn);

    let celdaVacia = document.createElement("div");
    celdaVacia.setAttribute("class", "span centrada");
    celdaVacia.innerHTML = "&nbsp;";
    contenedorFormulario.appendChild(celdaVacia);

    let celdaBoton = document.createElement("div");
    celdaBoton.setAttribute("class", "span centrada");
    contenedorFormulario.appendChild(celdaBoton);

    let boton = document.createElement("button");
    boton.setAttribute("type", "button");
    boton.setAttribute("onclick", "obtenRevista()");
    boton.innerHTML = "Continuar";
    celdaBoton.appendChild(boton);

    let celdaVacia2 = document.createElement("div");
    celdaVacia2.setAttribute("class", "span centrada");
    celdaVacia2.innerHTML = "&nbsp;";
    contenedorFormulario.appendChild(celdaVacia2);

    let resultados = document.createElement("div");
    resultados.setAttribute("id", "resultadosId");
    padre.appendChild(resultados);
}

/**
 * Esta función realiza una solicitud asíncrona al servidor para obtener una
 * revista de la base de datos.
 * El servlet que atiende esta solicitud es ObtenRevista.
 */
function obtenRevista() {
    isbn = document.getElementById("campoIsbnId").value;
    xhttp = new XMLHttpRequest();
    xhttp.onreadystatechange = obtenRevistaRespuesta;
    
    if (xhttp) {
        xhttp.open('GET', 'ObtenRevista?isbn=' + isbn, true);
        xhttp.send(null);
    }
}

/**
 * Función de respuesta para procesar la respuesta a la solicitud asíncrona al
 * servidor para obtener una revista de la base de datos. Si la revista existe,
 * despliega sus atributos indicando que ya existe. En caso contrario invoca a
 * la función capturaRevista() que despliega un formulario para capturar los
 * datos de una revista a agregar a la base de datos.
 */
function obtenRevistaRespuesta() {
    borraHijos("resultadosId");
    
    if (xhttp.readyState === 4 && xhttp.status === 200) {
        let respuesta = xhttp.responseText;
        
        if (!respuesta.startsWith('{}')) {
            let revista = JSON.parse(respuesta);
            
            despliegaObjeto("resultadosId", "Revista repetida", encabezados, revista);
        }
        else capturaRevista();
    }
}

/**
 * Función que despliega un formulario para capturar
 * una revista.
 */
function capturaRevista() {
    isbn = document.getElementById("campoIsbnId").value;

    borraHijos("main");

    let padre = document.getElementById("main");

    let titulo = document.createElement("h1");
    titulo.innerHTML = "Captura revista";
    padre.appendChild(titulo);

    let formulario = document.createElement("form");
    padre.appendChild(formulario);

    let contenedorFormulario = document.createElement("div");
    contenedorFormulario.setAttribute("class", "formulario");
    formulario.appendChild(contenedorFormulario);

    let celdaEtiquetaIsbn = document.createElement("div");
    celdaEtiquetaIsbn.setAttribute("class", "derecha");
    contenedorFormulario.appendChild(celdaEtiquetaIsbn);

    let etiquetaIsbn = document.createElement("label");
    etiquetaIsbn.setAttribute("for", "campoIsbnId");
    etiquetaIsbn.innerHTML = "ISBN*";
    celdaEtiquetaIsbn.appendChild(etiquetaIsbn);

    let celdaIsbn = document.createElement("div");
    celdaIsbn.setAttribute("id", "isbnId");
    contenedorFormulario.appendChild(celdaIsbn);

    let campoIsbn = document.createElement("input");
    campoIsbn.setAttribute("type", "text");
    campoIsbn.setAttribute("id", "campoIsbnId");
    campoIsbn.setAttribute("size", "13");
    campoIsbn.setAttribute("readonly", "readonly");
    campoIsbn.setAttribute("value", isbn);
    celdaIsbn.appendChild(campoIsbn);

    let celdaVacia = document.createElement("div");
    celdaVacia.innerHTML = "&nbsp;";
    contenedorFormulario.appendChild(celdaVacia);

    let celdaEtiquetaTitulo = document.createElement("div");
    celdaEtiquetaTitulo.setAttribute("class", "derecha");
    contenedorFormulario.appendChild(celdaEtiquetaTitulo);

    let etiquetaTitulo = document.createElement("label");
    etiquetaTitulo.setAttribute("for", "campoTituloId");
    etiquetaTitulo.innerHTML = "Título*";
    celdaEtiquetaTitulo.appendChild(etiquetaTitulo);

    let celdaTitulo = document.createElement("div");
    celdaTitulo.setAttribute("id", "tituloId");
    contenedorFormulario.appendChild(celdaTitulo);

    let campoTitulo = document.createElement("input");
    campoTitulo.setAttribute("type", "text");
    campoTitulo.setAttribute("id", "campoTituloId");
    campoTitulo.setAttribute("size", "20");
    campoTitulo.setAttribute("maxlength", "50");
    campoTitulo.setAttribute("placeholder", "Título de la revista");
    campoTitulo.setAttribute("required", "required");
    celdaTitulo.appendChild(campoTitulo);

    let celdaVacia2 = document.createElement("div");
    celdaVacia2.innerHTML = "&nbsp;";
    contenedorFormulario.appendChild(celdaVacia2);

    let celdaEtiquetaEditorial = document.createElement("div");
    celdaEtiquetaEditorial.setAttribute("class", "derecha");
    contenedorFormulario.appendChild(celdaEtiquetaEditorial);

    let etiquetaEditorial = document.createElement("label");
    etiquetaEditorial.setAttribute("for", "campoEditorialId");
    etiquetaEditorial.innerHTML = "Editorial*";
    celdaEtiquetaEditorial.appendChild(etiquetaEditorial);

    let celdaEditorial = document.createElement("div");
    celdaEditorial.setAttribute("id", "editorialId");
    contenedorFormulario.appendChild(celdaEditorial);

    let campoEditorial = document.createElement("input");
    campoEditorial.setAttribute("type", "text");
    campoEditorial.setAttribute("id", "campoEditorialId");
    campoEditorial.setAttribute("size", "20");
    campoEditorial.setAttribute("maxlength", "35");
    campoEditorial.setAttribute("placeholder", "Editorial de la revista");
    campoEditorial.setAttribute("required", "required");
    celdaEditorial.appendChild(campoEditorial);

    let celdaVacia3 = document.createElement("div");
    celdaVacia3.innerHTML = "&nbsp;";
    contenedorFormulario.appendChild(celdaVacia3);

    let celdaEtiquetaClasificacion = document.createElement("div");
    celdaEtiquetaClasificacion.setAttribute("class", "derecha");
    contenedorFormulario.appendChild(celdaEtiquetaClasificacion);

    let etiquetaClasificacion = document.createElement("label");
    etiquetaClasificacion.setAttribute("for", "campoClasificacionId");
    etiquetaClasificacion.innerHTML = "Clasificacion*";
    celdaEtiquetaClasificacion.appendChild(etiquetaClasificacion);

    let celdaClasificacion = document.createElement("div");
    celdaClasificacion.setAttribute("id", "clasificacionId");
    contenedorFormulario.appendChild(celdaClasificacion);

    let campoClasificacion = document.createElement("input");
    campoClasificacion.setAttribute("type", "text");
    campoClasificacion.setAttribute("id", "campoClasificacionId");
    campoClasificacion.setAttribute("size", "20");
    campoClasificacion.setAttribute("maxlength", "20");
    campoClasificacion.setAttribute("placeholder", "Clasificación de la revista");
    campoClasificacion.setAttribute("required", "required");
    celdaClasificacion.appendChild(campoClasificacion);

    let celdaVacia4 = document.createElement("div");
    celdaVacia4.innerHTML = "&nbsp;";
    contenedorFormulario.appendChild(celdaVacia4);

    let celdaEtiquetaPeriodicidad = document.createElement("div");
    celdaEtiquetaPeriodicidad.setAttribute("class", "derecha");
    contenedorFormulario.appendChild(celdaEtiquetaPeriodicidad);

    let etiquetaPeriodicidad = document.createElement("label");
    etiquetaPeriodicidad.setAttribute("for", "campoPeriodicidadId");
    etiquetaPeriodicidad.innerHTML = "Periodicidad";
    celdaEtiquetaPeriodicidad.appendChild(etiquetaPeriodicidad);

    let celdaPeriodicidad = document.createElement("div");
    celdaPeriodicidad.setAttribute("id", "periodicidadId");
    contenedorFormulario.appendChild(celdaPeriodicidad);

    let campoPeriodicidad = document.createElement("input");
    campoPeriodicidad.setAttribute("type", "text");
    campoPeriodicidad.setAttribute("id", "campoPeriodicidadId");
    campoPeriodicidad.setAttribute("size", "20");
    campoPeriodicidad.setAttribute("maxlength", "20");
    campoPeriodicidad.setAttribute("placeholder", "Periodicidad de la revista");
    celdaPeriodicidad.appendChild(campoPeriodicidad);

    let celdaVacia5 = document.createElement("div");
    celdaVacia5.innerHTML = "&nbsp;";
    contenedorFormulario.appendChild(celdaVacia5);

    let celdaEtiquetaFecha = document.createElement("div");
    celdaEtiquetaFecha.setAttribute("class", "derecha");
    contenedorFormulario.appendChild(celdaEtiquetaFecha);

    let etiquetaFecha = document.createElement("label");
    etiquetaFecha.setAttribute("for", "campoFechaId");
    etiquetaFecha.innerHTML = "Fecha";
    celdaEtiquetaFecha.appendChild(etiquetaFecha);

    let celdaFecha = document.createElement("div");
    celdaFecha.setAttribute("id", "fechaId");
    contenedorFormulario.appendChild(celdaFecha);

    let campoFecha = document.createElement("input");
    campoFecha.setAttribute("type", "text");
    campoFecha.setAttribute("id", "campoFechaId");
    campoFecha.setAttribute("size", "10");
    campoFecha.setAttribute("placeholder", "aaaa-mm-dd");
    campoFecha.setAttribute("pattern", "^([12]\d{3}-(0[1-9]|1[0-2])-(0[1-9]|[12]\d|3[01]))");
    celdaFecha.appendChild(campoFecha);

    let celdaVacia6 = document.createElement("div");
    celdaVacia6.setAttribute("class", "span centrada");
    celdaVacia6.innerHTML = "&nbsp;";
    contenedorFormulario.appendChild(celdaVacia6);

    let celdaBoton = document.createElement("div");
    celdaBoton.setAttribute("class", "span centrada");
    contenedorFormulario.appendChild(celdaBoton);

    let boton = document.createElement("button");
    boton.setAttribute("type", "button");
    boton.setAttribute("onclick", "agregarRevista()");
    boton.innerHTML = "Continuar";
    celdaBoton.appendChild(boton);

    let celdaVacia7 = document.createElement("div");
    celdaVacia7.setAttribute("class", "span centrada");
    celdaVacia7.innerHTML = "&nbsp;";
    contenedorFormulario.appendChild(celdaVacia7);

    let resultados = document.createElement("div");
    resultados.setAttribute("id", "resultadosId");
    padre.appendChild(resultados);
}

/**
 * Función que agrega una revista a la base de datos.
 * El servlet que atiende esta función es AgregarRevista()
 */
function agregarRevista() {
    isbn = document.getElementById("campoIsbnId").value;
    titulo = document.getElementById("campoTituloId").value;
    editorial = document.getElementById("campoEditorialId").value;
    clasificacion = document.getElementById("campoClasificacionId").value;
    periodicidad = document.getElementById("campoPeriodicidadId").value;
    fecha = document.getElementById("campoFechaId").value;
    
    xhttp = new XMLHttpRequest();
    xhttp.onreadystatechange = despliegaRevistas;
    
    if (xhttp) {
        xhttp.open('GET', 'AgregaRevista?isbn=' + isbn + '&titulo=' + titulo + '&editorial=' + editorial + '&clasificacion=' + clasificacion + '&periodicidad=' + periodicidad + '&fecha=' + fecha, true);
        xhttp.send(null);
    }
}

/**
 * Esta función realiza una solicitud asíncrona al servidor para obtener la
 * lista de revistas.
 */
function obtenRevistas() {
    xhttp = new XMLHttpRequest();
    
    xhttp.onreadystatechange = despliegaRevistas;
    
    if (xhttp) {
        xhttp.open('GET', 'ObtenRevistas', true);
        xhttp.send(null);
    }
}

/**
 * Función que despliega una tabla con los datos de
 * todas las revistas guardadas
 */
function despliegaRevistas() {
    borraHijos("main");
    
    if (xhttp.readyState === 4 && xhttp.status === 200) {
        let revistas = JSON.parse(xhttp.responseText);
        
        despliegaTabla("main", "Catálogo de revistas", encabezados, revistas);
    }
}