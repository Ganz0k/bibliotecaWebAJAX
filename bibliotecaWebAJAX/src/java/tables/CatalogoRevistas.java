/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package tables;

import java.util.Objects;

/**
 *
 * @author luisg
 */
public class CatalogoRevistas {
    
    private String isbn;
    private String titulo;
    private String editorial;
    private String clasificacion;
    private String periodicidad;
    private String fecha;

    public CatalogoRevistas(String isbn, String titulo, String editorial, String clasificacion, String periodicidad, String fecha) {
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

    @Override
    public int hashCode() {
        int hash = 3;
        hash = 67 * hash + Objects.hashCode(this.isbn);
        return hash;
    }

    @Override
    public boolean equals(Object obj) {
        if (this == obj) {
            return true;
        }
        if (obj == null) {
            return false;
        }
        if (getClass() != obj.getClass()) {
            return false;
        }
        final CatalogoRevistas other = (CatalogoRevistas) obj;
        return Objects.equals(this.isbn, other.isbn);
    }

    @Override
    public String toString() {
        return "CatalogoRevistas{" + "isbn=" + isbn + ", titulo=" + titulo + ", editorial=" + editorial + ", clasificacion=" + clasificacion + ", periodicidad=" + periodicidad + ", fecha=" + fecha + '}';
    }
}
