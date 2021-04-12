package com.github.shinwaffle.discordbot.handlers;

import java.util.Map;
import java.util.Set;
import java.util.HashMap;

import com.github.shinwaffle.discordbot.main.Main;
import com.github.shinwaffle.discordbot.util.Command;
import org.javacord.api.entity.message.Message;
import org.reflections.Reflections;


public class CommandHandler {
    
    private static final Map<String, Command> allcommands = new HashMap<>();
    private final Long botClientID;
    private static final String PREFIX = Main.PREFIX; //probs a better way to do this, seems wack.

    /**
     * For now this exception will stay here,
     * but in the future I'll probs have an exceptions folder once I find a need for more exceptions
     */
    public static class ClientIDMissingException extends RuntimeException {
        public ClientIDMissingException(String errorMessage) {
            super(errorMessage);
        }
    }

    public CommandHandler(){
        throw new ClientIDMissingException("Client ID is missing!");
    }

    public CommandHandler(Long botClientID) {
        this.botClientID = botClientID;
        initializeCommands();
    }

    /**
     * Finds the correct command for the message.
     */
    //todo instead of getting description, make it actually call the command.
    public void execute(Message message) {
        String[] args = parse(message);
        if (allcommands.containsKey(args[0])) {
            System.out.println(allcommands.get(args[0]).getDescription());
        }
    }

    /**
     * Processes a message and returns the arguments
     * @param message to be parsed
     * @return returns a String array where first element is command and afterwards arguments
     */
    private String[] parse(Message message) {
        String msg = message.getContent();

        if (msg.startsWith(PREFIX)) {
            return msg.substring(PREFIX.length()).split(" ");
        }

        //mentions are formatted as <![numbers]>
        if (msg.startsWith("<@!"+botClientID+">")) {
            /*
            using replaceFirst because split has problem with replacing characters at the beginning of strings
            \\s* is a reluctant regex
             */
            return msg.substring(msg.indexOf(">")+1).replaceFirst("\\s*", "").split(" ");
        }
        return new String[0];
    }

     /**
      * Initializes commands 
      */
     private void initializeCommands() {
         Reflections reflections = new Reflections("com.github.shinwaffle.discordbot.commands");
         Set<Class<? extends Command>> classes = reflections.getSubTypesOf(Command.class);

         try {
             for (Class<? extends Command> c : classes) {
                 Command cmd = c.getDeclaredConstructor().newInstance();
                 if (!allcommands.containsKey(cmd.getCommand())) allcommands.put(cmd.getCommand(), cmd);
             }
         } catch (Exception e) {
             e.printStackTrace();
         }
     }
}
