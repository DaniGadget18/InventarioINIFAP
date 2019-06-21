package com.example.inventarioinifap;

import java.util.Date;

public class Articulos {

    private String id;
    private String No_sini;
    private String No_sinaso;
    private String Centro;
    private String Descripcion;
    private String Costo_del_bien;
    private String Cambs;
    private Date Fecha_de_facturacion;
    private String Cuenta;
    private String Subcuenta;
    private String Estatus_del_bien;
    private String Recurso;
    private String Rfc_empleado;
    private String Empleado;
    private String Adscripcion;


    public Articulos(String id, String no_sini, String no_sinaso, String centro, String descripcion, String costo_del_bien, String cambs, Date fecha_de_facturacion, String cuenta, String subcuenta, String estatus_del_bien, String recurso, String rfc_empleado, String empleado, String adscripcion) {
        this.id = id;
        No_sini = no_sini;
        No_sinaso = no_sinaso;
        Centro = centro;
        Descripcion = descripcion;
        Costo_del_bien = costo_del_bien;
        Cambs = cambs;
        Fecha_de_facturacion = fecha_de_facturacion;
        Cuenta = cuenta;
        Subcuenta = subcuenta;
        Estatus_del_bien = estatus_del_bien;
        Recurso = recurso;
        Rfc_empleado = rfc_empleado;
        Empleado = empleado;
        Adscripcion = adscripcion;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getNo_sini() {
        return No_sini;
    }

    public void setNo_sini(String no_sini) {
        No_sini = no_sini;
    }

    public String getNo_sinaso() {
        return No_sinaso;
    }

    public void setNo_sinaso(String no_sinaso) {
        No_sinaso = no_sinaso;
    }

    public String getCentro() {
        return Centro;
    }

    public void setCentro(String centro) {
        Centro = centro;
    }

    public String getDescripcion() {
        return Descripcion;
    }

    public void setDescripcion(String descripcion) {
        Descripcion = descripcion;
    }

    public String getCosto_del_bien() {
        return Costo_del_bien;
    }

    public void setCosto_del_bien(String costo_del_bien) {
        Costo_del_bien = costo_del_bien;
    }

    public String getCambs() {
        return Cambs;
    }

    public void setCambs(String cambs) {
        Cambs = cambs;
    }

    public Date getFecha_de_facturacion() {
        return Fecha_de_facturacion;
    }

    public void setFecha_de_facturacion(Date fecha_de_facturacion) {
        Fecha_de_facturacion = fecha_de_facturacion;
    }

    public String getCuenta() {
        return Cuenta;
    }

    public void setCuenta(String cuenta) {
        Cuenta = cuenta;
    }

    public String getSubcuenta() {
        return Subcuenta;
    }

    public void setSubcuenta(String subcuenta) {
        Subcuenta = subcuenta;
    }

    public String getEstatus_del_bien() {
        return Estatus_del_bien;
    }

    public void setEstatus_del_bien(String estatus_del_bien) {
        Estatus_del_bien = estatus_del_bien;
    }

    public String getRecurso() {
        return Recurso;
    }

    public void setRecurso(String recurso) {
        Recurso = recurso;
    }

    public String getRfc_empleado() {
        return Rfc_empleado;
    }

    public void setRfc_empleado(String rfc_empleado) {
        Rfc_empleado = rfc_empleado;
    }

    public String getEmpleado() {
        return Empleado;
    }

    public void setEmpleado(String empleado) {
        Empleado = empleado;
    }

    public String getAdscripcion() {
        return Adscripcion;
    }

    public void setAdscripcion(String adscripcion) {
        Adscripcion = adscripcion;
    }
}
