package com.example.softcars.Model;

public class Usuarios {
    private int idUsuario;
    private String nombreUsuarios;
    private String contrasenia;
    private int estatus;

    public Usuarios() {
    }

    public Usuarios(String nombreUsuarios, int idUsuario, int estatus, String contrasenia) {
        this.nombreUsuarios = nombreUsuarios;
        this.idUsuario = idUsuario;
        this.estatus = estatus;
        this.contrasenia = contrasenia;
    }

    public int getIdUsuario() {
        return idUsuario;
    }

    public void setIdUsuario(int idUsuario) {
        this.idUsuario = idUsuario;
    }

    public String getNombreUsuarios() {
        return nombreUsuarios;
    }

    public void setNombreUsuarios(String nombreUsuarios) {
        this.nombreUsuarios = nombreUsuarios;
    }

    public String getContrasenia() {
        return contrasenia;
    }

    public void setContrasenia(String contrasenia) {
        this.contrasenia = contrasenia;
    }

    public int getEstatus() {
        return estatus;
    }

    public void setEstatus(int estatus) {
        this.estatus = estatus;
    }

    @Override
    public String toString() {
        return "AutoMoviles{" +
                "idUsuario=" + idUsuario +
                ", nombreUsuarios='" + nombreUsuarios + '\'' +
                ", contrasenia='" + contrasenia + '\'' +
                ", estatus=" + estatus +
                '}';
    }
}
