/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package tables;

/**
 *
 * @author luisg
 */
public class InventarioRevistas {
    
    private long idInventario;
    private int cantidad;
    private CatalogoRevistas isbnRevista;

    public InventarioRevistas(long idInventario, int cantidad, CatalogoRevistas isbnRevista) {
        this.idInventario = idInventario;
        this.cantidad = cantidad;
        this.isbnRevista = isbnRevista;
    }

    public InventarioRevistas(int cantidad, CatalogoRevistas isbnRevista) {
        this.cantidad = cantidad;
        this.isbnRevista = isbnRevista;
    }

    public long getIdInventario() {
        return idInventario;
    }

    public void setIdInventario(long idInventario) {
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
        int hash = 7;
        hash = 31 * hash + (int) (this.idInventario ^ (this.idInventario >>> 32));
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
        final InventarioRevistas other = (InventarioRevistas) obj;
        return this.idInventario == other.idInventario;
    }

    @Override
    public String toString() {
        return "InventarioRevistas{" + "idInventario=" + idInventario + ", cantidad=" + cantidad + ", isbnRevista=" + isbnRevista + '}';
    }
}
