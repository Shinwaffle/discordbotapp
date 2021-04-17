package com.github.shinwaffle.discordbot.util;

import com.github.shinwaffle.discordbot.handlers.CommandHandler;
import org.javacord.api.entity.message.Message;
import org.javacord.api.event.message.MessageCreateEvent;
import org.javacord.api.listener.message.MessageCreateListener;

import static com.github.shinwaffle.discordbot.main.Main.PREFIX;

public class Listener implements MessageCreateListener {

    private long botclientid = 0;

    public Listener(long botclientid) {
        this.botclientid = botclientid;
    }

    @Override
    public void onMessageCreate(MessageCreateEvent event) {
        Message message = event.getMessage();
        System.out.println(message);
        CommandHandler ch = new CommandHandler(botclientid);

        if (!message.getMentionedUsers().isEmpty() && message.getMentionedUsers().get(0).getId() == botclientid) {
            ch.execute(message);
        }

        if (event.getMessageContent().startsWith(PREFIX)) ch.execute(message);
        if (event.getMessageContent().equals("exit")) System.exit(0);
    }
}
