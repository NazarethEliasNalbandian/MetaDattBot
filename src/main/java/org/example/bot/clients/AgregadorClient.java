package org.example.bot.clients;

import org.example.bot.utils.HttpUtils;

public class AgregadorClient {
    private static final String BASE_URL = "https://two025-tp-entrega-2-lgo1980.onrender.com";

    public String listarHechos(String coleccion) throws Exception {
        if (coleccion == null || coleccion.isBlank()) {
            return "⚠️ Debes indicar una colección. Ejemplo: /listar incendios";
        }
        String url = BASE_URL + "/api/colecciones/" + coleccion + "/hechos";
        return HttpUtils.get(url);
    }
}
