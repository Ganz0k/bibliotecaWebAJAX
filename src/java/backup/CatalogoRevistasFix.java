/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package backup;

/**
 *
 * @author luisg
 */
public class CatalogoRevistasFix {
    
    private String isbn;
    private String titulo;
    private String editorial;
    private String clasificacion;
    private String periodicidad;
    private String fecha;

    public CatalogoRevistasFix(String isbn, String titulo, String editorial, String clasificacion, String periodicidad, String fecha) {
        this.isbn = isbn;
        this.titulo = titulo;
        this.editorial = editorial;
        this.clasificacion = clasificacion;
        this.periodicidad = periodicidad;
        this.fecha = fecha;
    }

    public String getIsbn() {
        return isbn;
    }

    public void setIsbn(String isbn) {
        this.isbn = isbn;
    }

    public String getTitulo() {
        return titulo;
    }

    public void setTitulo(String titulo) {
        this.titulo = titulo;
    }

    public String getEditorial() {
        return editorial;
    }

    public void setEditorial(String editorial) {
        this.editorial = editorial;
    }

    public String getClasificacion() {
        return clasificacion;
    }

    public void setClasificacion(String clasificacion) {
        this.clasificacion = clasificacion;
    }

    public String getPeriodicidad() {
        return periodicidad;
    }

    public void setPeriodicidad(String periodicidad) {
        this.periodicidad = periodicidad;
    }

    public String getFecha() {
        return fecha;
    }

    public void setFecha(String fecha) {
        this.fecha = fecha;
    }
}
