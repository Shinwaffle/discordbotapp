package com.github.shinwaffle.discordbot;

/**
 * if you want testing stuff with junit
 * 
 * import static org.junit.Assert.*;    
 * 
 * import org.junit.Test;
 */

import org.javacord.api.DiscordApi;
import org.javacord.api.DiscordApiBuilder;

public class Main {
    
    public static void main(String[] args) {
        String token = hastoken.token;
        
        DiscordApi api = new DiscordApiBuilder().setToken(token).login().join();

        // Add a listener which answers with "Pong!" if someone writes "!ping"
        api.addMessageCreateListener(event -> {
            if (event.getMessageContent().equalsIgnoreCase("!ping")) {
                event.getChannel().sendMessage("Pong!");
            }
        });

        //to get invite: api.createBotInvite()
        System.out.println("Bot is running");
    }
}
