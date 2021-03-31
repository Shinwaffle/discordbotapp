package com.github.shinwaffle.discordbot;

import kong.unirest.Unirest;

public class TopHackerStory {

    String url = "https://hacker-news.firebaseio.com/v0/topstories.json?print=pretty";
    public String getTopStoryLink(int topStory) {

        String topStoryId = Unirest.get(url)
        .asJson()
        .getBody()
        .getArray()
        .get(topStory)
        .toString();
        
        //https://hacker-news.firebaseio.com/v0/item/8863.json?print=pretty
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