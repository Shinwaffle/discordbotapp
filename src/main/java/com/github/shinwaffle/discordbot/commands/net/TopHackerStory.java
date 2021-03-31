package com.github.shinwaffle.discordbot.commands.net;

import kong.unirest.Unirest;

public class TopHackerStory {

    private static final String URL = "https://hacker-news.firebaseio.com/v0/topstories.json?print=pretty";

    public String getTopStoryLink(int topStory) {

        /**
         * First line gets the ID of the top story.
         * The second line gets the 
         */
        String topStoryId = Unirest.get(URL)
        .asJson()
        .getBody()
        .getArray()
        .get(topStory)
        .toString();
        
        String topStoryLink = Unirest.get("https://hacker-news.firebaseio.com/v0/item/{id}.json?print=pretty")
        .routeParam("id", topStoryId)
        .asJson()
        .getBody()
        .getObject()
        .getString("url")
        .toString();

        return topStoryLink;
    }
}