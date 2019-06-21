package com.example.inventarioinifap;

public class Usuarios {

    private String correo;
    private String password;
    private int id;

    public Usuarios(String correo, String password, int id) {
        this.correo = correo;
        this.password = password;
        this.id = id;
    }


    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }


    public String getCorreo() {
        return correo;
    }

    public void setCorreo(String correo) {
        this.correo = correo;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }
}
