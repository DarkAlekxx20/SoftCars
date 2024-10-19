package com.example.softcars.model;

public class AutoMoviles extends Usuarios{
    private int idAutomovil;
    private String marca;
    private float precioCompra;
    private float precioVenta;
    private float gastos;
    private Usuarios datosUsuarios;

    public AutoMoviles() {
    }

    public AutoMoviles(int idAutomovil, String marca, float precioCompra, float precioVenta, float gastos, Usuarios datosUsuarios) {
        this.idAutomovil = idAutomovil;
        this.marca = marca;
        this.precioCompra = precioCompra;
        this.precioVenta = precioVenta;
        this.gastos = gastos;
        this.datosUsuarios = datosUsuarios;
    }

    public int getIdAutomovil() {
        return idAutomovil;
    }

    public void setIdAutomovil(int idAutomovil) {
        this.idAutomovil = idAutomovil;
    }

    public String getMarca() {
        return marca;
    }

    public void setMarca(String marca) {
        this.marca = marca;
    }

    public float getPrecioCompra() {
        return precioCompra;
    }

    public void setPrecioCompra(float precioCompra) {
        this.precioCompra = precioCompra;
    }

    public float getPrecioVenta() {
        return precioVenta;
    }

    public void setPrecioVenta(float precioVenta) {
        this.precioVenta = precioVenta;
    }

    public float getGastos() {
        return gastos;
    }

    public void setGastos(float gastos) {
        this.gastos = gastos;
    }

    public Usuarios getDatosUsuarios() {
        return datosUsuarios;
    }

    public void setDatosUsuarios(Usuarios datosUsuarios) {
        this.datosUsuarios = datosUsuarios;
    }

    @Override
    public String toString() {
        return "AutoMoviles{" +
                "idAutomovil=" + idAutomovil +
                ", marca='" + marca + '\'' +
                ", precioCompra=" + precioCompra +
                ", precioVenta=" + precioVenta +
                ", gastos=" + gastos +
                ", datosUsuarios=" + datosUsuarios +
                '}';
    }
}