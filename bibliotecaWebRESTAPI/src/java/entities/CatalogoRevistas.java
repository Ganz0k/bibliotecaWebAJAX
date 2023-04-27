/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package entities;

import java.io.Serializable;
import java.util.Date;
import javax.persistence.Basic;
import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;
import javax.persistence.OneToOne;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;
import javax.xml.bind.annotation.XmlRootElement;
import javax.xml.bind.annotation.XmlTransient;

/**
 *
 * @author luisg
 */
@Entity
@Table(name = "CATALOGO_REVISTAS")
@XmlRootElement
@NamedQueries({
    @NamedQuery(name = "CatalogoRevistas.findAll", query = "SELECT c FROM CatalogoRevistas c"),
    @NamedQuery(name = "CatalogoRevistas.findByIsbn", query = "SELECT c FROM CatalogoRevistas c WHERE c.isbn = :isbn"),
    @NamedQuery(name = "CatalogoRevistas.findByTitulo", query = "SELECT c FROM CatalogoRevistas c WHERE c.titulo = :titulo"),
    @NamedQuery(name = "CatalogoRevistas.findByEditorial", query = "SELECT c FROM CatalogoRevistas c WHERE c.editorial = :editorial"),
    @NamedQuery(name = "CatalogoRevistas.findByClasificacion", query = "SELECT c FROM CatalogoRevistas c WHERE c.clasificacion = :clasificacion"),
    @NamedQuery(name = "CatalogoRevistas.findByPeriodicidad", query = "SELECT c FROM CatalogoRevistas c WHERE c.periodicidad = :periodicidad"),
    @NamedQuery(name = "CatalogoRevistas.findByFecha", query = "SELECT c FROM CatalogoRevistas c WHERE c.fecha = :fecha")})
public class CatalogoRevistas implements Serializable {

    private static final long serialVersionUID = 1L;
    @Id
    @Basic(optional = false)
    @NotNull
    @Size(min = 1, max = 13)
    @Column(name = "ISBN")
    private String isbn;
    @Basic(optional = false)
    @NotNull
    @Size(min = 1, max = 50)
    @Column(name = "TITULO")
    private String titulo;
    @Basic(optional = false)
    @NotNull
    @Size(min = 1, max = 35)
    @Column(name = "EDITORIAL")
    private String editorial;
    @Basic(optional = false)
    @NotNull
    @Size(min = 1, max = 20)
    @Column(name = "CLASIFICACION")
    private String clasificacion;
    @Size(max = 20)
    @Column(name = "PERIODICIDAD")
    private String periodicidad;
    @Column(name = "FECHA")
    @Temporal(TemporalType.DATE)
    private Date fecha;
    @OneToOne(cascade = CascadeType.ALL, mappedBy = "isbnRevista")
    private InventarioRevistas inventarioRevistas;

    public CatalogoRevistas() {
    }

    public CatalogoRevistas(String isbn) {
        this.isbn = isbn;
    }

    public CatalogoRevistas(String isbn, String titulo, String editorial, String clasificacion) {
        this.isbn = isbn;
        this.titulo = titulo;
        this.editorial = editorial;
        this.clasificacion = clasificacion;
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

    public Date getFecha() {
        return fecha;
    }

    public void setFecha(Date fecha) {
        this.fecha = fecha;
    }

    @XmlTransient
    public InventarioRevistas getInventarioRevistas() {
        return inventarioRevistas;
    }

    public void setInventarioRevistas(InventarioRevistas inventarioRevistas) {
        this.inventarioRevistas = inventarioRevistas;
    }

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (isbn != null ? isbn.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof CatalogoRevistas)) {
            return false;
        }
        CatalogoRevistas other = (CatalogoRevistas) object;
        if ((this.isbn == null && other.isbn != null) || (this.isbn != null && !this.isbn.equals(other.isbn))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "entities.CatalogoRevistas[ isbn=" + isbn + " ]";
    }
    
}
