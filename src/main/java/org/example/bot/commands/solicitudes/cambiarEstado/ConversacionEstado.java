package org.example.bot.commands.solicitudes.cambiarEstado;

public class ConversacionEstado {
    public enum Paso {
        ESTADO, DESCRIPCION, COMPLETO
    }

    public String idSolicitud;
    public String estado;
    public String descripcion;
    public Paso pasoActual;
}
