package com.github.shinwaffle.discordbot.main;

import com.github.shinwaffle.discordbot.handlers.CommandHandler;
import org.javacord.api.DiscordApi;
import org.javacord.api.DiscordApiBuilder;
import org.javacord.api.entity.message.Message;
public class Main {

    public static final String PREFIX = "!"; //dynamically change this later
    public static Long botclientid;

    public static void main(String[] args) {

        /*
         *
         * Delete this token after using
         *
         */
        String token = "";
        /*
         *
         * Or else you're gonna get a message from Safety Jim
         *
         */

        DiscordApi api = new DiscordApiBuilder().setToken(token).login().join();
        botclientid = api.getClientId();
        CommandHandler ch = new CommandHandler(botclientid);
        System.out.println(api.getClientId());

        api.addMessageCreateListener(event -> {
            Message message = event.getMessage();
            System.out.println(message);

             
                    if (!message.getMentionedUsers().isEmpty() && message.getMentionedUsers().get(0).getId() == botclientid) {
                        ch.execute(message);
                    }
                
                if (message.getContent().startsWith("!")) {
                    ch.execute(message);
                }


        });
        
        System.out.println("Bot is running, "+api.createBotInvite());
    }
}
