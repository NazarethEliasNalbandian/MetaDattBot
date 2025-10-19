package org.example.bot.commands.solicitudes.cambiarEstado;

import org.example.bot.clients.SolicitudesClient;
import org.example.bot.commands.BotCommand;
import org.telegram.telegrambots.meta.api.methods.send.SendMessage;
import org.telegram.telegrambots.meta.api.objects.Update;

import java.util.HashMap;
import java.util.Map;

public class CambiarEstadoCommand implements BotCommand {

    private final SolicitudesClient solicitudesClient = new SolicitudesClient();
    private final Map<Long, ConversacionEstado> conversaciones = new HashMap<>();

    @Override
    public boolean matches(String text) {
        return text.startsWith("/cambiarestado");
    }

    @Override
    public boolean hasConversation(Long chatId) {
        return conversaciones.containsKey(chatId);
    }

    @Override
    public SendMessage handle(Update update) throws Exception {
        Long chatId = update.getMessage().getChatId();
        String text = update.getMessage().getText().trim();
        SendMessage msg = new SendMessage();
        msg.setChatId(chatId.toString());

        // Si ya hay conversación en curso
        if (conversaciones.containsKey(chatId) && !text.startsWith("/cambiarestado")) {
            return manejarConversacion(chatId, text);
        }

        // Inicio del flujo
        String[] parts = text.split(" ", 2);
        if (parts.length < 2) {
            msg.setText("Uso: /cambiarestado <id_solicitud>");
            return msg;
        }

        ConversacionEstado conv = new ConversacionEstado();
        conv.idSolicitud = parts[1];
        conv.pasoActual = ConversacionEstado.Paso.ESTADO;
        conversaciones.put(chatId, conv);

        msg.setText("⚙️ Cambiando estado de la solicitud *" + conv.idSolicitud + "*.\nPor favor, ingresá el nuevo estado (por ejemplo: ACEPTADA, RECHAZADA, PENDIENTE):");
        return msg;
    }

    private SendMessage manejarConversacion(Long chatId, String text) {
        ConversacionEstado conv = conversaciones.get(chatId);
        SendMessage msg = new SendMessage();
        msg.setChatId(chatId.toString());

        try {
            switch (conv.pasoActual) {
                case ESTADO -> {
                    conv.estado = text.toUpperCase();
                    conv.pasoActual = ConversacionEstado.Paso.DESCRIPCION;
                    msg.setText("✏️ Ingresá una descripción o motivo del cambio de estado:");
                }
                case DESCRIPCION -> {
                    conv.descripcion = text;
                    conv.pasoActual = ConversacionEstado.Paso.COMPLETO;

                    String jsonBody = String.format("""
                        {
                          "id": "%s",
                          "estado": "%s",
                          "descripcion": "%s"
                        }
                        """,
                            conv.idSolicitud,
                            conv.estado,
                            conv.descripcion
                    );

                    String respuesta = solicitudesClient.cambiarEstado(jsonBody);
                    msg.setText("✅ Estado actualizado correctamente:\n🆔 ID: " + conv.idSolicitud + "\n🪪 Nuevo estado: " + conv.estado + "\n" + respuesta);
                    conversaciones.remove(chatId);
                }
            }
        } catch (Exception e) {
            e.printStackTrace();
            msg.setText("❌ Error al actualizar el estado. Intentá nuevamente.");
        }

        return msg;
    }
}
