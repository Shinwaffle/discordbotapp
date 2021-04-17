package com.github.shinwaffle.discordbot.util;

import org.javacord.api.entity.channel.TextChannel;
import org.javacord.api.entity.message.embed.EmbedBuilder;
import org.javacord.api.entity.user.User;

public abstract class Command {

    /**
     * return description of what the command does in embed form
     */
    public abstract EmbedBuilder getDescriptionEmbed();

    /**
     * return description of what the command does in String form
     * Should be short and sweet so it can be viewed in a big list of commands
     */
    public abstract String getDescription();

    /**
     * return command to call this class
     */
    public abstract String getCommand();

    /**
     * return the message or nothing
     */
    public abstract void execute(String[] args, TextChannel channel, User author);
}
