/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package src;

/**
 *
 * @author sebas
 */
public class Producto {

    private String codigo;
    private String nombre;
    private double precio;
    private int cantida;
    private String categoria;
    private String descripcion;
    private String cedulaPersona;

    public Producto(String codigo, String nombre, double precio, int cantida, String categoria, String descripcion, String cedulaPersona) {
        this.codigo = codigo;
        this.nombre = nombre;
        this.precio = precio;
        this.cantida = cantida;
        this.categoria = categoria;
        this.descripcion = descripcion;
        this.cedulaPersona = cedulaPersona;
    }

    public String getCodigo() {
        return codigo;
    }

    public void setCodigo(String codigo) {
        this.codigo = codigo;
    }

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public double getPrecio() {
        return precio;
    }

    public void setPrecio(double precio) {
        this.precio = precio;
    }

    public int getCantida() {
        return cantida;
    }

    public void setCantida(int cantida) {
        this.cantida = cantida;
    }

    public String getCategoria() {
        return categoria;
    }

    public void setCategoria(String categoria) {
        this.categoria = categoria;
    }

    public String getDescripcion() {
        return descripcion;
    }

    public void setDescripcion(String descripcion) {
        this.descripcion = descripcion;
    }

    public String getCedulaPersona() {
        return cedulaPersona;
    }

    public void setCedulaPersona(String cedulaPersona) {
        this.cedulaPersona = cedulaPersona;
    }

    
    
}
