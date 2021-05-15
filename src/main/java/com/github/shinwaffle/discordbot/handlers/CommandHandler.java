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


    public CommandHandler() {
        throw new IllegalArgumentException("Client ID is missing!");
    }

    public CommandHandler(Long botClientID) {
        this.botClientID = botClientID;
        initializeCommands();
    }

    /**
     * Finds the correct command for the message.
     *
     * @param message, presumably a command to execute with corresponding class
     */
    public void execute(Message message) {
        String[] args = parse(message);
        if (allcommands.containsKey(args[0])) {
            allcommands.get(args[0]).execute(args, message.getChannel(), message.getUserAuthor().get());
        }
    }

    /**
     * Processes a message and returns the arguments
     *
     * @param message to be parsed
     * @return a String array where first element is command and afterwards arguments
     */
    private String[] parse(Message message) {
        String msg = message.getContent();

        if (msg.startsWith(PREFIX)) {
            return msg.substring(PREFIX.length()).split(" ");
        }

        if (msg.startsWith("<@!" + botClientID + ">")) {
            return msg.substring(msg.indexOf(">") + 1).replaceFirst("\\s*", "").split(" ");
        }
        return new String[0];
    }

    /**
     * Initializes commands
     */
    private void initializeCommands() {
        var reflections = new Reflections("com.github.shinwaffle.discordbot.commands");
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
