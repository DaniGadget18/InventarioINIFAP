package com.example.inventarioinifap;

public class Constantes {

    /**
     * Transición Home -> Detalle
     */
    public static final int CODIGO_DETALLE = 100;

    /**
     * Transición Detalle -> Actualización
     */
    public static final int CODIGO_ACTUALIZACION = 101;

    /**
     * Puerto que utilizas para la conexión.
     * Dejalo en blanco si no has configurado esta carácteristica.
     */
    private static final String PUERTO_HOST = "22";

    /**
     * Dirección IP de INIFAP NORTE CENTRO
     */
    private static final String IP = "http://192.168.1.104";
    /**
     * URLs del Web Service
     */
    public static final String IniciarSesion = IP + "/Inventario/ControladorAndroid/LoginUsuario.php";
    public static final String ObtenerArticulo = IP + "/Inventario/ControladorAndroid/Articulo_codigo.php";
    public static final String RegistrarArticulo = IP + "/Inventario/ControladorAndroid/RegistrarArticulo.php";
    public static final String ObtenerArticulos = IP + "/Inventario/ControladorAndroid/ObtenerArticulos.php";

    /**
     * Clave para el valor extra que representa al identificador de una meta
     */
    public static final String EXTRA_ID = "IDEXTRA";
}
