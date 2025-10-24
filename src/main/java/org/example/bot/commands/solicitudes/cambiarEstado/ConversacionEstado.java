package org.example.bot.commands.solicitudes.cambiarEstado;

public class ConversacionEstado {
    public enum Paso {
        ESTADO, COMPLETO
    }

    public String idSolicitud;
    public String estado;
    public Paso pasoActual;
}
