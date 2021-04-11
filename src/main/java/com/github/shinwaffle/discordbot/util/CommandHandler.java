package com.github.shinwaffle.discordbot.util;

import java.util.Map;
import java.util.Set;
import java.util.HashMap;
import org.reflections.*;

public class CommandHandler {
    
    private static final Map<String, Command> allcommands = new HashMap<>();

    /**
     * todo list:
     * initialize commands
     * put them into hashmap
     * String being the command to invake the command
     * Command being the command to invoke
     * 
     */

     /**
      * Initializes commands 
      */
     private void initializeCommands() {
        Reflections reflections = new Reflections("com.github.shinwaffle.discordbot.commands");
        Set<Class<? extends Command>> classes = reflections.getSubTypesOf(Command.class);
        
     }
}
