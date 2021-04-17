package com.github.shinwaffle.discordbot.main;

import com.github.shinwaffle.discordbot.util.Listener;
import org.javacord.api.DiscordApi;
import org.javacord.api.DiscordApiBuilder;

public class Main {

    public static final String PREFIX = "!"; //dynamically change this later

    public static void main(String[] args) {

        String token = args[0];
        DiscordApi api = new DiscordApiBuilder().setToken(token).login().join();
        final long botclientid = api.getClientId();
        api.addListener(new Listener(botclientid));
        System.out.println("Bot is running, " + api.createBotInvite());
    }
}
