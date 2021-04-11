package com.github.shinwaffle.discordbot.main;

import java.io.File;
import java.io.FileInputStream;
import java.util.Map;
import java.util.Properties;

import com.github.shinwaffle.discordbot.commands.net.StackExAnswers;
import com.github.shinwaffle.discordbot.commands.net.TopHackerStory;
import com.github.shinwaffle.discordbot.util.CommandHandler;

import org.javacord.api.DiscordApi;
import org.javacord.api.DiscordApiBuilder;
import org.javacord.api.entity.message.Message;
import org.javacord.api.entity.message.MessageBuilder;

public class Main {
    
    public static void main(String[] args) throws Exception {

        Properties prop = new Properties();
        FileInputStream ip = new FileInputStream("config.properties");
        prop.load(ip);

        //String prefix = prop.getProperty("prefix"); not used
        String token = "";
        
        DiscordApi api = new DiscordApiBuilder().setToken(token).login().join();

        //temporary for functionality, move to commandhandler
        CommandHandler cum = new CommandHandler();
        TopHackerStory d = new TopHackerStory();
        StackExAnswers s = new StackExAnswers();

        int[] indices = {0,1,2,3,4,5,6,7,8,9,10,11,12,13,14,15,16,17,18,19,20,21,22,23,24,25,26,27,28,29}; //there has to be a way better way of doing this lmao

        api.addMessageCreateListener(event -> {
            Message message = event.getMessage();
            
            /**
             * move to commandhandler after you finally figure out
             * how to do one
             */
            System.out.println(message);
            if (message.getContent().startsWith("s")) {  
                event.getChannel().sendMessage(cum.read());
                /*        
                for(Map.Entry<String, String> values : d.getTopStoryLink(message).entrySet()) {
                    event.getChannel().sendMessage(values.getKey()+ "\n" +values.getValue());
                }
                */
            }
            
            for (int i = 0; i < 30; i++) {
                if (message.getContent().equalsIgnoreCase("!ping "+indices[i])) {
                    new MessageBuilder()
                    .append("Here's what I got: ")
                    .appendCode("html", s.getResponse(i)).send(event.getChannel());
                }
            }
            
            message.addReactionAddListener(msg -> {
                if(msg.getEmoji().equalsEmoji("💯")) {
                    event.deleteMessage("Dissapearing act");
                }
            });                   
        });
        
        System.out.println("Bot is running, "+api.createBotInvite());
    }
}
