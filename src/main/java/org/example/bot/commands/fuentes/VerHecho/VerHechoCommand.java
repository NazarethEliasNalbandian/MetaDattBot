package org.example.bot.commands.fuentes.VerHecho;

import org.example.bot.clients.FuentesClient;
import org.example.bot.commands.BotCommand;
import org.telegram.telegrambots.meta.api.methods.send.SendMessage;
import org.telegram.telegrambots.meta.api.objects.Update;

public class VerHechoCommand implements BotCommand {

    private final FuentesClient fuentesClient = new FuentesClient();

    @Override
    public boolean matches(String text) {
        return text.startsWith("/ver");
    }

    @Override
    public SendMessage handle(Update update) throws Exception {
        String[] parts = update.getMessage().getText().split(" ", 2);
        SendMessage msg = new SendMessage();
        msg.setChatId(update.getMessage().getChatId().toString());

        if (parts.length < 2) {
            msg.setText("Uso: /ver <id_hecho>");
        } else {
            String hechoId = parts[1];
            String hechoData = fuentesClient.verHecho(hechoId);
            msg.setText("🧾 Detalles del hecho:\n" + hechoData);
        }

        return msg;
    }
}
