package com.github.shinwaffle.discordbot.util;

import com.github.shinwaffle.discordbot.handlers.CommandHandler;
import org.javacord.api.event.message.MessageCreateEvent;
import org.javacord.api.listener.message.MessageCreateListener;

import static com.github.shinwaffle.discordbot.main.Main.PREFIX;

public class Listener implements MessageCreateListener {

    private final long botclientid;

    public Listener(long botclientid) {
        this.botclientid = botclientid;
    }

    @Override
    public void onMessageCreate(MessageCreateEvent event) {
        var message = event.getMessage();
        System.out.println(message);
        var ch = new CommandHandler(botclientid);

        if (!message.getMentionedUsers().isEmpty() && message.getMentionedUsers().get(0).getId() == botclientid) {
            ch.execute(message);
        }

        if (event.getMessageContent().startsWith(PREFIX)) ch.execute(message);
        if (event.getMessageContent().equals("exit")) System.exit(0);
    }
}
