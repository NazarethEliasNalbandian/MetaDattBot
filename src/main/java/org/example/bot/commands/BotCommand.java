package org.example.bot.commands;

import org.telegram.telegrambots.meta.api.methods.send.SendMessage;
import org.telegram.telegrambots.meta.api.objects.Update;

public interface BotCommand {
    boolean matches(String messageText);             // Comando con barra
    default boolean hasConversation(Long chatId) {   // Indica si está en curso
        return false;
    }
    SendMessage handle(Update update) throws Exception;
}
