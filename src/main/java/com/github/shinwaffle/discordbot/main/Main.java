package com.github.shinwaffle.discordbot.main;

import com.github.shinwaffle.discordbot.util.Listener;
import org.javacord.api.DiscordApiBuilder;
import org.javacord.api.entity.user.UserStatus;

public class Main {

    public static final String PREFIX = "!"; //dynamically change this later

    public static void main(String[] args) {

        var token = args[0];
        var api = new DiscordApiBuilder().setToken(token).login().join();
        api.updateStatus(UserStatus.DO_NOT_DISTURB);
        api.updateActivity("Remember to use exit");
        final long botclientid = api.getClientId();
        api.addListener(new Listener(botclientid));
        System.out.println("Bot is running, " + api.createBotInvite());
    }
}
