package com.github.shinwaffle.discordbot.util;

import org.javacord.api.entity.channel.Channel;
import org.javacord.api.entity.message.MessageBuilder;
import org.javacord.api.entity.user.User;

public abstract class Command {

    /**
     * return description of what the command does
     */
    public abstract String getDescription();
    
    /**
     * return command to call this class
     */
    public abstract String getCommand();

    /**
     * return the message or nothing
     */
    public abstract MessageBuilder execute(String[] args, Channel channel, User author);
}
