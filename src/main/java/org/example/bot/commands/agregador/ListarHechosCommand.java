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
        String[] parts = update.getMessage().getText().split(" ", 2);
        String coleccion = parts.length > 1 ? parts[1] : "";
        String result = agregadorClient.listarHechos(coleccion);

        SendMessage msg = new SendMessage();
        msg.setChatId(update.getMessage().getChatId().toString());
        msg.setText(result);
        return msg;
    }
}
