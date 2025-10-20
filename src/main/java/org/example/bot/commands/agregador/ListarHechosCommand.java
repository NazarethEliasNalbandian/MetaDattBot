package org.example.bot.commands.agregador;

import org.example.bot.clients.AgregadorClient;
import org.example.bot.commands.BotCommand;
import org.telegram.telegrambots.meta.api.methods.send.SendMessage;
import org.telegram.telegrambots.meta.api.objects.Update;

public class ListarHechosCommand implements BotCommand {

    private final AgregadorClient agregadorClient = new AgregadorClient();

    @Override
    public boolean matches(String text) {
        return text.startsWith("/listar");
    }

    @Override
    public SendMessage handle(Update update) throws Exception {
        Long chatId = update.getMessage().getChatId();
        String text = update.getMessage().getText().trim();

        SendMessage msg = new SendMessage();
        msg.setChatId(chatId.toString());

        String[] parts = text.split(" ", 2);
        if (parts.length < 2) {
            msg.setText("Uso: /listar <nombre_coleccion>");
            return msg;
        }

        String coleccion = parts[1];
        String respuesta = agregadorClient.listarHechos(coleccion);

        msg.setText(respuesta);
        return msg;
    }
}
