package com.github.shinwaffle.discordbot.util;

import java.util.Map;
import java.util.Set;
import java.util.HashMap;
import org.reflections.*;

public class CommandHandler {
    
    private static final Map<String, Command> allcommands = new HashMap<>();


    public CommandHandler() {
        initializeCommands();
    }

     /**
      * Initializes commands 
      */

     public void initializeCommands() {
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
    /**
     * Finds the correct command for the message.
     */

    //todo: first finish initializeCommands()
    //todo: match command with strings on the map and call accordingly
    private void findCommand() {

     }
}
