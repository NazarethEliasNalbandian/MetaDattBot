package org.example.bot.clients;

import org.example.bot.utils.HttpUtils;

public class ProcesadorPdiClient {
    private static final String BASE_URL = "https://tp-dds-2025-procesadorpdi.onrender.com";

    public String obtenerPdisPorHecho(String hechoId) throws Exception {
        String url = BASE_URL + "/api/pdis?hecho=" + hechoId;
        return HttpUtils.get(url);
    }

    public String agregarPdi(String hechoId) throws Exception {
        String url = BASE_URL + "/pdis?hecho=" + hechoId;
        return HttpUtils.post(url, "{}");
    }
}
