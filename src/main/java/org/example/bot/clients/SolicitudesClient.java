package org.example.bot.clients;

import org.example.bot.utils.HttpUtils;

public class SolicitudesClient {
    private static final String BASE_URL = "https://dds-tp-anual-solicitudes.onrender.com";

    public String crearSolicitud(String jsonBody) throws Exception {
        String url = BASE_URL + "/solicitudes";
        return HttpUtils.post(url, jsonBody);
    }

    public String cambiarEstado(String jsonBody) throws Exception {
        String url = BASE_URL + "/solicitudes";
        return HttpUtils.patch(url, jsonBody);
    }
}
