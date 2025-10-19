package org.example.bot.commands.solicitudes.solicitarBorrado;

import org.example.bot.clients.SolicitudesClient;
import org.example.bot.commands.BotCommand;
import org.telegram.telegrambots.meta.api.methods.send.SendMessage;
import org.telegram.telegrambots.meta.api.objects.Update;

import java.util.HashMap;
import java.util.Map;

public class SolicitarBorradoCommand implements BotCommand {

    private final SolicitudesClient solicitudesClient = new SolicitudesClient();
    private final Map<Long, ConversacionSolicitud> conversaciones = new HashMap<>();

    @Override
    public boolean matches(String text) {
        return text.startsWith("/solicitarborrado");
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

        // Si la conversación ya está en curso
        if (conversaciones.containsKey(chatId) && !text.startsWith("/solicitarborrado")) {
            return manejarConversacion(chatId, text);
        }

        // Inicio del flujo
        String[] parts = text.split(" ", 2);
        if (parts.length < 2) {
            msg.setText("Uso: /solicitarborrado <id_hecho>");
            return msg;
        }

        ConversacionSolicitud conv = new ConversacionSolicitud();
        conv.hechoId = parts[1];
        conv.pasoActual = ConversacionSolicitud.Paso.DESCRIPCION;
        conversaciones.put(chatId, conv);

        msg.setText("🧾 Creando solicitud de borrado para hecho *" + conv.hechoId + "*.\nPor favor, ingresá la descripción o motivo de la solicitud:");
        return msg;
    }

    private SendMessage manejarConversacion(Long chatId, String text) {
        ConversacionSolicitud conv = conversaciones.get(chatId);
        SendMessage msg = new SendMessage();
        msg.setChatId(chatId.toString());

        try {
            switch (conv.pasoActual) {
                case DESCRIPCION -> {
                    conv.descripcion = text;

                    // Construir JSON
                    String jsonBody = String.format("""
                        {
                          "descripcion": "%s",
                          "hecho_id": "%s"
                        }
                        """,
                            conv.descripcion,
                            conv.hechoId
                    );

                    String respuesta = solicitudesClient.crearSolicitud(jsonBody);
                    msg.setText("✅ Solicitud de borrado creada correctamente para el hecho *"
                            + conv.hechoId + "* ✅\n" + respuesta);
                    conversaciones.remove(chatId);
                }
            }
        } catch (Exception e) {
            e.printStackTrace();
            msg.setText("❌ Error al crear la solicitud. Intentá nuevamente.");
        }

        return msg;
    }
}
