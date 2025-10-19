package org.example.bot.clients;

import org.example.bot.utils.HttpUtils;

public class AgregadorClient {
    private static final String BASE_URL = "https://two025-tp-entrega-2-lgo1980.onrender.com";

    public String listarHechos(String coleccion) throws Exception {
        if (coleccion.isEmpty()) {
            return "Debes indicar una colección. Ejemplo: /listar incendios2025";
        }
        String url = BASE_URL + "/coleccion/" + coleccion + "/hechos";
        return HttpUtils.get(url);
    }
}
