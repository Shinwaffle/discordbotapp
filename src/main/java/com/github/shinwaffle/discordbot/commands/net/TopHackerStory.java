package com.github.shinwaffle.discordbot.commands.net;

import java.awt.*;
import java.util.HashMap;
import java.util.Map;

import com.github.shinwaffle.discordbot.util.Command;

import org.javacord.api.entity.channel.TextChannel;
import org.javacord.api.entity.message.MessageBuilder;
import org.javacord.api.entity.message.embed.EmbedBuilder;
import org.javacord.api.entity.user.User;

import kong.unirest.Unirest;

public class TopHackerStory extends Command {

    private static final String URL = "https://hacker-news.firebaseio.com/v0/topstories.json?print=pretty";

    @Override
    public String getDescription() {
        return null;
    }

    @Override
    public EmbedBuilder getDescriptionEmbed() {
        return new EmbedBuilder()
                .setAuthor("shinBot", "https://www.github.com/Shinwaffle/discordbot", "https://cdn.discordapp.com/attachments/813108197615337505/832976357050810398/20210309_092734.png")
                .setThumbnail("https://cdn.discordapp.com/attachments/813108197615337505/832986607282815026/ycombinator-logo-b603b0a270e12b1d42b7cca9d4527a9b206adf8293a.png")
                .setColor(Color.RED)
                .setTitle("Top Hacker Stories Usage")
                .setDescription("Returns top stories from https://news.ycombinator.com/news")
                .addField("Amount of stories", "The first argument returns how many top stories you want. The bot will return a max of 5")
                .addField("Where to start", "The second argument will start returning top stories from given index. See example usage")
                .addField("Example Usage", """
                        !tophackerstories
                        Will return the top story
                                            
                        !tophackerstories 3 5
                        Returns the 5th, 6th, and 7th top story.
                                            
                        !tophackerstories 0 5
                        Returns the 5th top story""");

    }

    @Override
    public String getCommand() {
        return "tophackerstories";
    }

    @Override
    public void execute(String[] args, TextChannel channel, User author) {

        int noOfStories;
        int startIndex;
        Map<String, String> stories = new HashMap<>();
        try {
            if (args[1].equalsIgnoreCase("help") || args[1].equalsIgnoreCase("?")) {
                channel.sendMessage(getDescriptionEmbed());
            }
        } catch (ArrayIndexOutOfBoundsException ignored) {
        }

        try {
            noOfStories = Integer.parseInt(args[1]);
            if (noOfStories > 5) noOfStories = 5;
            if (noOfStories == 0) noOfStories = 1;
        } catch (ArrayIndexOutOfBoundsException e) {
            noOfStories = 1;
        } catch (NumberFormatException e) {
            return;
        }

        try {
            startIndex = Integer.parseInt(args[2]);
            if (startIndex != 0) startIndex--;
        } catch (ArrayIndexOutOfBoundsException e) {
            startIndex = 0;
        }

        for (int i = 0; i < noOfStories; i++) {
            String topStoryId = Unirest.get(URL)
                    .asJson()
                    .getBody()
                    .getArray()
                    .get(startIndex + i)
                    .toString();

            String topStoryTitle = Unirest.get("https://hacker-news.firebaseio.com/v0/item/{id}.json?print=pretty")
                    .routeParam("id", topStoryId)
                    .asJson()
                    .getBody()
                    .getObject()
                    .getString("title");

            String topStoryLink = Unirest.get("https://hacker-news.firebaseio.com/v0/item/{id}.json?print=pretty")
                    .routeParam("id", topStoryId)
                    .asJson()
                    .getBody()
                    .getObject()
                    .getString("url");

            stories.put(topStoryTitle, topStoryLink);
        }

        MessageBuilder msg = new MessageBuilder();
        for (Map.Entry<String, String> s : stories.entrySet()) {
            msg.append(s.getKey() + " " + s.getValue() + "\n\n");
        }
        msg.send(channel);

    }
}