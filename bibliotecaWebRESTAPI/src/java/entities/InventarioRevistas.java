/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package entities;

import java.io.Serializable;
import javax.persistence.Basic;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;
import javax.persistence.Table;
import javax.validation.constraints.NotNull;
import javax.xml.bind.annotation.XmlRootElement;

/**
 *
 * @author luisg
 */
@Entity
@Table(name = "INVENTARIO_REVISTAS")
@XmlRootElement
@NamedQueries({
    @NamedQuery(name = "InventarioRevistas.findAll", query = "SELECT i FROM InventarioRevistas i"),
    @NamedQuery(name = "InventarioRevistas.findByIdInventario", query = "SELECT i FROM InventarioRevistas i WHERE i.idInventario = :idInventario"),
    @NamedQuery(name = "InventarioRevistas.findByCantidad", query = "SELECT i FROM InventarioRevistas i WHERE i.cantidad = :cantidad")})
public class InventarioRevistas implements Serializable {

    private static final long serialVersionUID = 1L;
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Basic(optional = false)
    @Column(name = "ID_INVENTARIO")
    private Long idInventario;
    @Basic(optional = false)
    @NotNull
    @Column(name = "CANTIDAD")
    private int cantidad;
    @JoinColumn(name = "ISBN_REVISTA", referencedColumnName = "ISBN")
    @ManyToOne(optional = false)
    private CatalogoRevistas isbnRevista;

    public InventarioRevistas() {
    }

    public InventarioRevistas(Long idInventario) {
        this.idInventario = idInventario;
    }

    public InventarioRevistas(Long idInventario, int cantidad) {
        this.idInventario = idInventario;
        this.cantidad = cantidad;
    }

    public Long getIdInventario() {
        return idInventario;
    }

    public void setIdInventario(Long idInventario) {
        this.idInventario = idInventario;
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
        hash += (idInventario != null ? idInventario.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof InventarioRevistas)) {
            return false;
        }
        InventarioRevistas other = (InventarioRevistas) object;
        if ((this.idInventario == null && other.idInventario != null) || (this.idInventario != null && !this.idInventario.equals(other.idInventario))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "entities.InventarioRevistas[ idInventario=" + idInventario + " ]";
    }
    
}
