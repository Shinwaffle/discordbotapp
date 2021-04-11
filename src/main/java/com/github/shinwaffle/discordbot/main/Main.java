package com.github.shinwaffle.discordbot.main;

import com.github.shinwaffle.discordbot.commands.net.StackExAnswers;

import com.github.shinwaffle.discordbot.commands.net.TopHackerStory;
import com.github.shinwaffle.discordbot.util.CommandHandler;
import org.javacord.api.DiscordApi;
import org.javacord.api.DiscordApiBuilder;
import org.javacord.api.entity.message.Message;
import org.javacord.api.entity.message.MessageBuilder;

import java.util.Collection;
import java.util.Map;

public class Main {
    
    public static void main(String[] args) {

        String token = "ODIzNTUwNzAxMTk5ODE4NzYz.YFidWw.x9i3BLPma2g8BkRW69bNaNrIYw4";
        
        DiscordApi api = new DiscordApiBuilder().setToken(token).login().join();
        StackExAnswers s = new StackExAnswers();
        TopHackerStory top = new TopHackerStory();
        CommandHandler ch = new CommandHandler();

        int[] indices = {0,1,2,3,4,5,6,7,8,9,10,11,12,13,14,15,16,17,18,19,20,21,22,23,24,25,26,27,28,29}; //there has to be a way better way of doing this lmao



        api.addMessageCreateListener(event -> {
            Message message = event.getMessage();
            System.out.println(message);
            
            for (int i = 0; i < 30; i++) {
                if (message.getContent().equalsIgnoreCase("!ping "+indices[i])) {
                    new MessageBuilder()
                    .append("Here's what I got: ")
                    .appendCode("html", s.getResponse(i)).send(event.getChannel());
                }
            }
            
            message.addReactionAddListener(msg -> {
                if(msg.getEmoji().equalsEmoji("💯")) {
                    event.deleteMessage("Disappearing act");
                }
            });                   
        });
        
        System.out.println("Bot is running, "+api.createBotInvite());
    }
}
