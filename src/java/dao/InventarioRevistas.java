/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package dao;

import java.io.Serializable;
import javax.persistence.Basic;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;
import javax.persistence.OneToOne;
import javax.persistence.Table;
import javax.xml.bind.annotation.XmlRootElement;

/**
 *
 * @author luisg
 */
@Entity
@Table(name = "inventario_revistas")
@XmlRootElement
@NamedQueries({
    @NamedQuery(name = "InventarioRevistas.findAll", query = "SELECT i FROM InventarioRevistas i"),
    @NamedQuery(name = "InventarioRevistas.findById", query = "SELECT i FROM InventarioRevistas i WHERE i.id = :id"),
    @NamedQuery(name = "InventarioRevistas.findByCantidad", query = "SELECT i FROM InventarioRevistas i WHERE i.cantidad = :cantidad")})
public class InventarioRevistas implements Serializable {

    private static final long serialVersionUID = 1L;
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Basic(optional = false)
    @Column(name = "id")
    private Long id;
    @Basic(optional = false)
    @Column(name = "cantidad")
    private int cantidad;
    @JoinColumn(name = "isbn_revista", referencedColumnName = "isbn")
    @OneToOne(optional = false)
    private CatalogoRevistas isbnRevista;

    public InventarioRevistas() {
    }

    public InventarioRevistas(Long id) {
        this.id = id;
    }

    public InventarioRevistas(Long id, int cantidad) {
        this.id = id;
        this.cantidad = cantidad;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public int getCantidad() {
        return cantidad;
    }

    public void setCantidad(int cantidad) {
        this.cantidad = cantidad;
    }

    public CatalogoRevistas getIsbnRevista() {
        return isbnRevista;
    }

    public void setIsbnRevista(CatalogoRevistas isbnRevista) {
        this.isbnRevista = isbnRevista;
    }

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (id != null ? id.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof InventarioRevistas)) {
            return false;
        }
        InventarioRevistas other = (InventarioRevistas) object;
        if ((this.id == null && other.id != null) || (this.id != null && !this.id.equals(other.id))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "dao.InventarioRevistas[ id=" + id + " ]";
    }
    
}
