package com.github.shinwaffle.discordbot.util;

import java.util.Map;
import java.util.Set;
import java.util.HashMap;
import org.reflections.*;

public class CommandHandler {
    
    private static final Map<String, Command> allcommands = new HashMap<>();

     /**
      * Initializes commands 
      */

     //todo: finish initializeCommands()

     private void initializeCommands() {
        Reflections reflections = new Reflections("com.github.shinwaffle.discordbot.commands");
        Set<Class<? extends Command>> classes = reflections.getSubTypesOf(Command.class);
        
     }
}
