package com.github.shinwaffle.discordbot.commands.net;

import com.github.shinwaffle.discordbot.util.Command;

import org.javacord.api.entity.channel.TextChannel;
import org.javacord.api.entity.message.MessageBuilder;
import org.javacord.api.entity.message.embed.EmbedBuilder;
import org.javacord.api.entity.user.User;

import kong.unirest.Unirest;

public class StackExAnswers extends Command {

    /**
     * when fleshing out this function, make sure to set the url to static and
     * replace the original query with {query} to insert parameters.
     */
    private static final String URL = "https://api.stackexchange.com/2.2/search/advanced?order=desc&sort=relevance&q=how%20to%20center%20div&answers=2&site=stackoverflow&filter=!)rTkr_OQen1do4oxw7QI";

    @Override
    public EmbedBuilder getDescriptionEmbed() {
        return new EmbedBuilder().
                setTitle("Not implemented");
    }

    @Override
    public String getDescription() {
        return "returns query";
    }

    @Override
    public String getCommand() {
        return "stackex";
    }

    @Override
    public void execute(String[] args, TextChannel channel, User author) {


        try {
            if (args[1].equalsIgnoreCase("help") || args[1].equalsIgnoreCase("?")) {
                channel.sendMessage(getDescriptionEmbed());
            }
        } catch (ArrayIndexOutOfBoundsException ignored) {
        }

        String answer;
        try {
            var index = Integer.parseInt(args[2]);

            answer = Unirest.get(URL)
                    .asJson()
                    .getBody()
                    .getObject()
                    .getJSONArray("items")
                    .getJSONObject(0)
                    .getJSONArray("answers")
                    .getJSONObject(index)
                    .getString("body_markdown");

        } catch (NumberFormatException e) {
            return;
        }

        MessageBuilder msg = new MessageBuilder()
                .appendCode("md", answer);
        msg.send(channel);
    }

    private String parseQuery(String s) {
        return s.replace(" ", "%20");
    }

}
