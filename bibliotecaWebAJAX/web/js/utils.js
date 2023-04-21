/*
 * utils.js
 * 
 * Luis Gonzalo Cervantes Rivera | 00000228549
 * 
 * Contiene funciones para eliminar los elementos hijos de
 * un elemento HTML, desplegar una barra de botones que
 * representan un menu, desplegar una tabla con los
 * valores de los miembros de un arreglo de objetos,
 * desplegar una lista de selección, desplegar una tabla
 * con los atributos de un objeto y desplegar una tabla
 * con la primera columna conteniendo casillas de verificación
 */

/**
 * Función que elimina todos los elementos hijos del elemento
 * del ID del parámetro.
 * 
 * @param {string} elementoId ID del elemento del que se
 * borrarán los hijos.
 */
function borraHijos(elementoId) {
    //Obtiene el elemento del que se va a eliminar los hijos
    let elemento = document.getElementById(elementoId);

    //Mientras el elemento tenga hijos estos irán siendo eliminados
    while (elemento.hasChildNodes()) {
        elemento.removeChild(elemento.firstChild);
    }
}

/**
 * Función para desplegar un menú consistente de botones.
 * 
 * @param {string} padreID ID del elemento dentro del que
 * se insertarán los botones del menú.
 * @param {arreglo de strings} items Arreglo con los textos
 * a desplegar en los botones del menú.
 * @param {arreglo de funciones} funciones Arreglo con los
 * nombres de las funciones que implementan las operaciones
 * del menú.
 */
function despliegaMenu(padreID, items, funciones) {
    //Obtiene el elemento dentro del que se insertarán los botones del menú.
    let padre = document.getElementById(padreID);

    //Para recorrer cada opción del menú
    for (let i = 0; i < items.length; i++) {
        //Se crea el botón
        let boton = document.createElement("button");

        //Se establece el atributo type para nuestro botón
        boton.setAttribute("type", "button");

        //Se establece el nombre de la función.
        boton.setAttribute("onclick", funciones[i]);

        //Se establece el texto del botón
        boton.innerHTML = items[i];

        //Se agrega el botón
        padre.appendChild(boton);
    }
}

/**
 * Función para desplegar una tabla llena de datos.
 * 
 * @param {string} padreID ID del elemento dentro del que
 * se desplegará la tabla.
 * @param {string} titulo Título de la tabla.
 * @param {arreglo de strings} encabezados Encabezados de las
 * columnas de la tabla.
 * @param {arreglo de objetos} datos Arreglo de objetos cuyos
 * miembros se desplegarán en la tabla.
 */
function despliegaTabla(padreID, titulo, encabezados, datos) {
    //Obtiene el elemento dentro del que se desplegará la tabla
    let padre = document.getElementById(padreID);

    //Crea la tabla
    let tabla = document.createElement("table");
    tabla.setAttribute("class", "centrada");

    //Agrega la tabla a su elemento padre
    padre.appendChild(tabla);

    //Crea el título de la tabla
    let tituloTabla = document.createElement("caption");

    //Le agrega el texto al título
    tituloTabla.innerHTML = titulo;

    //Agrega el título a la tabla
    tabla.appendChild(tituloTabla);

    //Crea el renglón para los encabezados
    let renglonEncabezados = document.createElement("tr");

    //Agrega el renglón de encabezados a la tabla
    tabla.appendChild(renglonEncabezados);

    //Ciclo for para llenar el renglón de encabezados
    for (let encabezado of encabezados) {
        //Crea una celda de encabezado
        let celdaEncabezado = document.createElement("th");

        //Establece el texto del encabezado
        celdaEncabezado.innerHTML = encabezado;

        //Agrega la celda al renglón
        renglonEncabezados.appendChild(celdaEncabezado);
    }

    //Ciclo for para llenar el resto de la tabla
    for (let dato of datos) {
        //Crea un renglón
        let renglon = document.createElement("tr");

        //Agrega el renglón a la tabla
        tabla.appendChild(renglon);

        //Ciclo for para cada miembro de cada objeto
        for (let llave in dato) {
            //Crea una celda
            let celda = document.createElement("td");

            //Agrega el texto de la celda
            celda.innerHTML = dato[llave];

            //Agrega la celda al renglón
            renglon.appendChild(celda);
        }
    }
}

/**
 * Función que despliega una lista de selección select
 * 
 * @param {string} padreID ID del elemento dentro del que
 * se desplegará la lista de selección.
 * @param {string} listaID Atributo ID que se le asignará a la lista.
 * @param {arreglo de strings} opciones Arreglo con los textos a
 * desplegar en cada opción.
 * @param {arreglo de strings} valores Arreglo con los valores de
 * los atributos value de cada uno de los elementos <option> de
 * la lista.
 * @param {string} sel Opción de la lista de selección seleccionada por
 * omisión.
 */
function despliegaListaSel(padreID, listaID, opciones, valores, sel) {
    //Obtiene el elemento dentro del que se desplegará la tabla
    let padre = document.getElementById(padreID);

    //Crea la lista
    let lista = document.createElement("select");

    //Establece el atributo value de la lista
    lista.setAttribute("id", listaID);

    //Agrega la lista al elemento padre
    padre.appendChild(lista);

    //Ciclo for para cada opción de la lista
    for (let i = 0; i < opciones.length; i++) {
        //Crea una opción
        let opcion = document.createElement("option");

        //Establece el texto de la opción
        opcion.innerHTML = opciones[i];

        //Establece el atributo value de la lista
        opcion.setAttribute("value", valores[i]);

        //Establece el atributo selected
        if ((sel !== null) && (sel === valores[i])) {
            opcion.setAttribute("selected", "selected");
        }

        //Agrega la opción a la lista
        lista.appendChild(opcion);
    }
}

/**
 * Función que despliega una tabla con los atributos de un objeto
 * 
 * @param {string} padreID ID del elemento dentro del que
 * se desplegará una tabla.
 * @param {string} titulo Título de la tabla.
 * @param {arreglo de strings} encabezados Encabezados de las
 * columnas de la tabla.
 * @param {objeto} objeto Objeto cuyos atributos se mostrarán en la tabla.
 */
function despliegaObjeto(padreID, titulo, encabezados, objeto) {
    //Obtiene el elemento dentro del que se desplegará la tabla.
    let padre = document.getElementById(padreID);

    //Crea la tabla
    let tabla = document.createElement("table");
    tabla.setAttribute("class", "centrada");

    //Agrega la tabla a su elemento padre
    padre.appendChild(tabla);

    //Crea el título de la tabla
    let tituloTabla = document.createElement("caption");

    //Le agrega el texo al título
    tituloTabla.innerHTML = titulo;

    //Agrega el título a la tabla
    tabla.appendChild(tituloTabla);

    //Ciclo for para cada objeto del arreglo de datos
    let i = 0;
    for (let miembro in objeto) {
        //Crea un renglón
        let renglon = document.createElement("tr");

        //Agrega el renglón a la tabla
        tabla.appendChild(renglon);

        //Crea una celda de encabezado
        let celdaEncabezado = document.createElement("th");

        //Agrega el texto del encabezado
        celdaEncabezado.innerHTML = encabezados[i];

        //Agrega la celda al renglón
        renglon.appendChild(celdaEncabezado);

        //Crea una celda
        let celdaMiembro = document.createElement("td");

        //Agrega el texto de la celda
        celdaMiembro.innerHTML = objeto[miembro];

        //Agrega la celda al renglón
        renglon.appendChild(celdaMiembro);

        i++;
    }
}