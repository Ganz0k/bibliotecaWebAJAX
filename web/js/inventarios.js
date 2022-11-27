/*
 * inventarios.js
 * 
 * Luis Gonzalo Cervantes Rivera | 00000228549
 * 
 * Contiene las funciones que implementan las operaciones
 * con el inventario
 */
let encabezadosInventario = ["ID", "Cantidad", "Revista"];
var revistaCapturada;
var cantidad;

/**
 * Función que despliega un formulario para inventariar
 * una revista.
 */
function capturaRevistaInventariar() {
    borraHijos("main");

    let padre = document.getElementById("main");

    let titulo = document.createElement("h1");
    titulo.innerHTML = "Selecciona revista a inventariar";
    padre.appendChild(titulo);

    let formulario = document.createElement("form");
    padre.appendChild(formulario);

    let contenedorFormulario = document.createElement("div");
    contenedorFormulario.setAttribute("class", "formulario");
    formulario.appendChild(contenedorFormulario);

    let celdaEitquetaRevista = document.createElement("div");
    celdaEitquetaRevista.setAttribute("class", "derecha");
    contenedorFormulario.appendChild(celdaEitquetaRevista);

    let etiquetaRevista = document.createElement("label");
    etiquetaRevista.setAttribute("for", "revistaId");
    etiquetaRevista.innerHTML = "Revista*";
    celdaEitquetaRevista.appendChild(etiquetaRevista);

    let celdaRevista = document.createElement("div");
    celdaRevista.setAttribute("id", "celdaRevistaId");
    contenedorFormulario.appendChild(celdaRevista);

    let celdaVacia = document.createElement("div");
    celdaVacia.setAttribute("class", "span centrada");
    celdaVacia.innerHTML = "&nbsp;";
    contenedorFormulario.appendChild(celdaVacia);

    let celdaEtiquetaCantidad = document.createElement("div");
    celdaEtiquetaCantidad.setAttribute("class", "derecha");
    contenedorFormulario.appendChild(celdaEtiquetaCantidad);

    let etiquetaCantidad = document.createElement("label");
    etiquetaCantidad.setAttribute("for", "cantidadId");
    etiquetaCantidad.innerHTML = "Cantidad*";
    celdaEtiquetaCantidad.appendChild(etiquetaCantidad);

    let celdaCantidad = document.createElement("div");
    celdaCantidad.setAttribute("id", "celdaCantidadId");
    contenedorFormulario.appendChild(celdaCantidad);

    let campoCantidad = document.createElement("input");
    campoCantidad.setAttribute("type", "text");
    campoCantidad.setAttribute("id", "cantidadId");
    campoCantidad.setAttribute("name", "Cantidad");
    campoCantidad.setAttribute("size", "10");
    campoCantidad.setAttribute("pattern", "^[0-9]+$");
    campoCantidad.setAttribute("title", "Solo dígitos");
    campoCantidad.setAttribute("placeholder", "Cantidad");
    campoCantidad.setAttribute("required", "required");
    celdaCantidad.appendChild(campoCantidad);

    let celdaVacia2 = document.createElement("div");
    celdaVacia2.setAttribute("class", "span centrada");
    celdaVacia2.innerHTML = "&nbsp;";
    contenedorFormulario.appendChild(celdaVacia2);

    let celdaBoton = document.createElement("div");
    celdaBoton.setAttribute("class", "span centrada");
    contenedorFormulario.appendChild(celdaBoton);

    let boton = document.createElement("button");
    boton.setAttribute("type", "button");
    boton.setAttribute("onclick", "inventariarRevista()");
    boton.innerHTML = "Continuar";
    celdaBoton.appendChild(boton);

    let celdaVacia3 = document.createElement("div");
    celdaVacia3.setAttribute("class", "span centrada");
    celdaVacia3.innerHTML = "&nbsp;";
    contenedorFormulario.appendChild(celdaVacia3);

    let resultados = document.createElement("div");
    resultados.setAttribute("id", "resultadosId");
    padre.appendChild(resultados);
    
    obtenRevistasSel();
}

/**
 * Función que obtiene la lista de revistas de la base de datos.
 */
function obtenRevistasSel() {
    fetch('ObtenRevistas').then(procesaRespuestaObtenRevistas).catch(muestraError);
}

/**
 * Procesa la respuesta de la función obtenRevistasSel.
 * @param {type} response
 */
function procesaRespuestaObtenRevistas(response) {
    console.log('response.ok: ', response.ok);
    if (response.ok) {
        response.json().then(llenarRevistasSel);
    } else {
        muestraError('Código de status: ' + response.status + ' Descripción: ' + response.statusText);
    }
}

/**
 * Invoca a la función despliegaListaSel para llenar el campo de selección.
 * @param {type} revistas
 */
function llenarRevistasSel(revistas) {
    let tituloRevistas = [];
    
    for (i = 0; i < revistas.length; i++) {
        tituloRevistas[i] = revistas[i].titulo;
    }
    
    despliegaListaSel("celdaRevistaId", "revistaId", tituloRevistas, tituloRevistas, "-Seleccionar-");
}

/**
 * Función para inventariar una revista.
 */
function inventariarRevista() {
    revista = document.getElementById("revistaId").value;
    cantidad = document.getElementById("cantidadId").value;
    
    fetch('InventariarRevista?revista=' + revista + '&cantidad=' + cantidad).then(procesaRespuestaInventariarRevista).catch(mensajeError);
}

/**
 * Procesa la respuesta de la función inventariarRevista.
 * @param {type} response
 */
function procesaRespuestaInventariarRevista(response) {
    console.log('response.ok: ' + response.ok);
    
    if (response.ok) {
        response.json().then(despliegaInventarioRevistas);
    } else {
        muestraError('Código de status: ' + response.status + ' Descripción: ' + response.statusText);
    }
}

/**
 * Obtiene la lista del inventario del servidor.
 */
function obtenInventario() {    
    fetch('ObtenInventarioRevistas').then(procesaRespuestaObtenInventario).catch(mensajeError);
}

/**
 * Procesa la respuesta de la función obtenInventario.
 * @param {type} response
 */
function procesaRespuestaObtenInventario(response) {
    console.log('response.ok: ' + response.ok);
    
    if (response.ok) {
        response.json().then(despliegaInventarioRevistas);
    } else {
        muestraError('Código de status: ' + response.status + ' Descripción: ' + response.statusText);
    }
}

/**
 * Función que despliega una tabla con los datos de todo el inventario.
 * @param {type} inventario
 */
function despliegaInventarioRevistas(inventario) {
    borraHijos("main");

    despliegaTabla("main", "Inventario de revistas", encabezadosInventario, inventario);
}