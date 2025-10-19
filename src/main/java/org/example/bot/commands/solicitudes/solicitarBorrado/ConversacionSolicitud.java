package org.example.bot.commands.solicitudes.solicitarBorrado;

public class ConversacionSolicitud {
    public enum Paso {
        DESCRIPCION
    }

    public String hechoId;
    public String descripcion;
    public Paso pasoActual;
}
