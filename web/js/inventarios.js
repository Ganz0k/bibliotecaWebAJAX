/*
 * inventarios.js
 * 
 * Luis Gonzalo Cervantes Rivera | 00000228549
 * 
 * Contiene las funciones que implementan las operaciones
 * con el inventario
 */
let inventario = [{ revista: "Revista 1", cantidad: 40 }, { revista: "Revista 3", cantidad: 45 }, { revista: "Food for Thought", cantidad: 20 }];
let encabezadosInventario = ["Revista", "Cantidad"];
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

    let tituloRevistas = [];
    for (let i = 0; i < revistas.length; i++) {
        tituloRevistas[i] = revistas[i].titulo;
    }

    let sel = "- Seleccionar -";
    despliegaListaSel("celdaRevistaId", "revistaId", tituloRevistas, tituloRevistas, sel);

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
    boton.setAttribute("onclick", "despliegaRevistaInventariada()");
    boton.innerHTML = "Continuar";
    celdaBoton.appendChild(boton);

    let celdaVacia3 = document.createElement("div");
    celdaVacia3.setAttribute("class", "span centrada");
    celdaVacia3.innerHTML = "&nbsp;";
    contenedorFormulario.appendChild(celdaVacia3);

    let resultados = document.createElement("div");
    resultados.setAttribute("id", "resultadosId");
    padre.appendChild(resultados);
}

/**
 * Función que despliega un formulario para desinventariar
 * una revista.
 */
function capturaRevistaDesinventariar() {
    borraHijos("main");

    let padre = document.getElementById("main");

    let titulo = document.createElement("h1");
    titulo.innerHTML = "Selecciona revista a desinventariar";
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

    let tituloRevistas = [];
    for (let i = 0; i < inventario.length; i++) {
        tituloRevistas[i] = inventario[i].revista;
    }

    let sel = "- Seleccionar -";
    despliegaListaSel("celdaRevistaId", "revistaId", tituloRevistas, tituloRevistas, sel);

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
    boton.setAttribute("onclick", "despliegaRevistaDesinventariada()");
    boton.innerHTML = "Continuar";
    celdaBoton.appendChild(boton);

    let celdaVacia3 = document.createElement("div");
    celdaVacia3.setAttribute("class", "span centrada");
    celdaVacia3.innerHTML = "&nbsp;";
    contenedorFormulario.appendChild(celdaVacia3);

    let resultados = document.createElement("div");
    resultados.setAttribute("id", "resultadosId");
    padre.appendChild(resultados);
}

/**
 * Función que despliega la revista inventariada
 * en una tabla.
 */
function despliegaRevistaInventariada() {
    revistaCapturada = document.getElementById("revistaId").value;
    cantidad = parseInt(document.getElementById("cantidadId").value);
    var objeto;

    const indice = inventario.findIndex(object => {
        return object.revista === revistaCapturada;
    });

    if (indice !== -1) {
        var c = inventario[indice].cantidad
        inventario[indice].cantidad = cantidad + c;
        objeto = inventario[indice];
        console.log(objeto)
    } else {
        inventario.push({ revista: revistaCapturada, cantidad: cantidad });
        objeto = inventario[inventario.length - 1];
    }

    borraHijos("resultadosId");

    despliegaObjeto("resultadosId", "Revista inventariada", encabezadosInventario, objeto);
}

/**
 * Función que despliega la revista desinventariada
 * en una tabla.
 */
function despliegaRevistaDesinventariada() {
    revistaCapturada = document.getElementById("revistaId").value;
    cantidad = parseInt(document.getElementById("cantidadId").value);
    var objeto;

    const indice = inventario.findIndex(object => {
        return object.revista === revistaCapturada;
    });

    var c = inventario[indice].cantidad
    inventario[indice].cantidad = c - cantidad;
    objeto = inventario[indice];
    console.log(objeto)

    borraHijos("resultadosId");

    despliegaObjeto("resultadosId", "Revista desinventariada", encabezadosInventario, objeto);
}

/**
 * Función que despliega una tabla con los datos de
 * todo el inventario
 */
function despliegaInventarioRevistas() {
    borraHijos("main");

    despliegaTabla("main", "Inventario de revistas", encabezadosInventario, inventario);
}