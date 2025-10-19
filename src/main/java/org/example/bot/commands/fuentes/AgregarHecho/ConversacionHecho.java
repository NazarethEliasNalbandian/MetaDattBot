package org.example.bot.commands.fuentes.AgregarHecho;

import java.util.HashMap;
import java.util.Map;

public class ConversacionHecho {
    public enum Paso {
        ID, TITULO, CATEGORIA, UBICACION, FECHA, ORIGEN, COMPLETO
    }

    public String coleccion;
    public Paso pasoActual;
    public Map<String, String> datos = new HashMap<>();
}
