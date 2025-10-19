package org.example.bot.clients;

import org.example.bot.utils.HttpUtils;

public class SolicitudesClient {
    private static final String BASE_URL = "https://dds-tp-anual-solicitudes.onrender.com";

    public String crearSolicitud(String jsonBody) throws Exception {
        String url = BASE_URL + "/solicitudes";
        return HttpUtils.post(url, jsonBody);
    }

    public String cambiarEstado(String solicitudId, String estado) throws Exception {
        String json = String.format("{\"estado\": \"%s\"}", estado);
        String url = BASE_URL + "/solicitudes/" + solicitudId;
        return HttpUtils.patch(url, json);
    }

    public String cambiarEstado(String jsonBody) throws Exception {
        String url = BASE_URL + "/solicitudes";
        return HttpUtils.patch(url, jsonBody);
    }

}
