package org.example.bot.commands.help;

import org.telegram.telegrambots.meta.api.methods.send.SendMessage;
import org.telegram.telegrambots.meta.api.objects.Update;
import org.example.bot.commands.BotCommand;

public class HelpCommand implements BotCommand {

    @Override
    public boolean matches(String text) {
        return text.equalsIgnoreCase("/help");
    }

    @Override
    public SendMessage handle(Update update) {
        Long chatId = update.getMessage().getChatId();
        SendMessage msg = new SendMessage();
        msg.setChatId(chatId.toString());
        msg.enableHtml(true);

        msg.setText("""
                <b>Comandos disponibles</b>

                🔹 <b>Fuentes</b>
                /ver &lt;id_hecho&gt; — Visualiza un hecho y sus PDIs asociados.
                /agregarhecho &lt;coleccion&gt; — Crea un nuevo hecho en la colección indicada.
                /agregarpdi &lt;id_hecho&gt; — Agrega un nuevo PDI al hecho especificado.

                🔹 <b>Agregador</b>
                /listar &lt;coleccion&gt; — Lista los hechos de una colección.

                🔹 <b>Solicitudes</b>
                /solicitarborrado &lt;id_hecho&gt; — Crea una solicitud de borrado para un hecho.
                /cambiarestado &lt;id_solicitud&gt; — Modifica el estado de una solicitud existente.

                """);

        return msg;
    }
}
