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
        return "Retrieves top stories from Hacker News!";
    }

    @Override
    public EmbedBuilder getDescriptionEmbed() {
        return new EmbedBuilder()
                .setAuthor("shinBot", "https://www.github.com/Shinwaffle/discordbot", "https://cdn.discordapp.com/attachments/813108197615337505/832976357050810398/20210309_092734.png")
                .setThumbnail("https://cdn.discordapp.com/attachments/813108197615337505/832986607282815026/ycombinator-logo-b603b0a270e12b1d42b7cca9d4527a9b206adf8293a.png")
                .setColor(Color.RED)
                .setTitle("Top Hacker Stories Usage")
                .setDescription("Returns top stories from https://news.ycombinator.com/news")
                .addField("Exceptions", "Word arguments such as \"one\" will cause the function to return the top story")
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

        int noOfStories = 1;
        int startIndex = 0;
        var indices = new int[2];
        Map<String, String> stories = new HashMap<>();

        if (args.length == 2 &&
                (args[1].equalsIgnoreCase("help")
                        || args[1].equalsIgnoreCase("?"))) {
            channel.sendMessage(getDescriptionEmbed());
            return;
        } else {
            indices = processArgs(args);
            noOfStories = indices[0];
            startIndex = indices[1];
        }

        for (var i = 0; i < noOfStories; i++) {
            var topStoryId = Unirest.get(URL)
                    .asJson()
                    .getBody()
                    .getArray()
                    .get(startIndex + i)
                    .toString();

            var topStoryTitle = Unirest.get("https://hacker-news.firebaseio.com/v0/item/{id}.json?print=pretty")
                    .routeParam("id", topStoryId)
                    .asJson()
                    .getBody()
                    .getObject()
                    .getString("title");

            var topStoryLink = Unirest.get("https://hacker-news.firebaseio.com/v0/item/{id}.json?print=pretty")
                    .routeParam("id", topStoryId)
                    .asJson()
                    .getBody()
                    .getObject()
                    .getString("url");

            stories.put(topStoryTitle, topStoryLink);
        }

        var msg = new MessageBuilder();
        for (Map.Entry<String, String> s : stories.entrySet()) {
            msg.append(s.getKey() + " " + s.getValue() + "\n\n");
        }
        msg.send(channel);
    }

    /**
     * Execute helper function
     *
     * @param args arguments used in execute function
     * @return returnArgs processed arguments
     */
    private int[] processArgs(String[] args) {
        var returnArgs = new int[2];

        try {
            returnArgs[0] = Integer.parseInt(args[1]);
        } catch (Exception e) {
            returnArgs[0] = 1;
            return returnArgs;
        }

        if (returnArgs[0] > 5) returnArgs[0] = 5;
        if (returnArgs[0] == 0) returnArgs[0] = 1;

        if (args.length > 2) {
            returnArgs[1] = Integer.parseInt(args[2]);
            if (returnArgs[1] != 0) returnArgs[1]--;
        }

        return returnArgs;
    }
}