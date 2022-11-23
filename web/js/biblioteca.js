/*
 * biblioteca.js
 * 
 * Luis Gonzalo Cervantes Rivera | 00000228549
 * 
 * Contiene los menús de la aplicación web
 * videocentroWebAJAX_00000228549
 */

//Opciones del menú principal de la aplicación
let menuPrincipalItems = ["Catálogo de revistas", "Inventario de revistas"];

//Funciones que implementan las operaciones de las opciones
//del menú principal
let menuPrincipalFunciones = ["despliegaMenuRevistas()", "despliegaMenuInventario()"];

//Opciones del menú de las operaciones con las revistas
let menuRevistasItems = ["Agregar revista", "Actualizar revista", "Eliminar revistas", "Consultar revistas", "Regresar a menú principal"];

//Funciones que implementan las operaciones con las revistas
let menuRevistasFunciones = ["capturaIsbn()", "", "", "despliegaRevistas()", "despliegaMenuPrincipal()"];

//Opciones del menú con las operaciones con el inventario
let menuInventariosItems = ["Inventariar revista", "Desinventariar revista", "Consultar inventario revistas", "Regresar a menú principal"];

//Funciones que implementan las operaciones con el inventario
let menuInventarioFunciones = ["capturaRevistaInventariar()", "capturaRevistaDesinventariar()", "despliegaInventarioRevistas()",
    "despliegaMenuPrincipal()"];

/**
 * Función para desplegar el menú principal de la aplicación.
 */
function despliegaMenuPrincipal() {
    borraHijos("main");

    borraHijos("menu");

    despliegaMenu("menu", menuPrincipalItems, menuPrincipalFunciones);
}

/**
 * Función para desplegar el menú de las operaciones con las revistas.
 */
function despliegaMenuRevistas() {
    borraHijos("menu");

    despliegaMenu("menu", menuRevistasItems, menuRevistasFunciones);
}

/**
 * Función para desplegar el menú de las operaciones con el inventario.
 */
function despliegaMenuInventario() {
    borraHijos("menu");

    despliegaMenu("menu", menuInventariosItems, menuInventarioFunciones);
}