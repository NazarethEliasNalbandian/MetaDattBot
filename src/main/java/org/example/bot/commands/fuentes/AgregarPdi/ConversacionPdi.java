package org.example.bot.commands.fuentes.AgregarPdi;

import java.util.HashMap;
import java.util.Map;

public class ConversacionPdi {
    public enum Paso {
        DESCRIPCION, LUGAR, MOMENTO, CONTENIDO, IMAGE_URL, COMPLETO
    }

    public String hechoId;
    public Paso pasoActual;
    public Map<String, String> datos = new HashMap<>();
}
