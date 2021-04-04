package com.github.shinwaffle.discordbot.commands.net;

import java.util.HashMap;
import java.util.Map;

import com.github.shinwaffle.discordbot.commands.Command;

import org.javacord.api.entity.message.Message;

import kong.unirest.Unirest;

public class TopHackerStory extends Command {

    private static final String URL = "https://hacker-news.firebaseio.com/v0/topstories.json?print=pretty";

    public String test = "test";
    
    public Map<String, String> getTopStoryLink(Message message) {

      int noOfStories;
      int startIndex;
      Map<String, String> stories = new HashMap<>();
      String[] args = message.getContent().substring(17).trim().split(" ");
      
      try {
        noOfStories = Integer.valueOf(args[0]);
      } catch (Exception e) {
        noOfStories = 1;
      }
        
      try {
        startIndex = Integer.valueOf(args[1]);
        if (startIndex != 0) {
          startIndex--;
        }
      } catch (Exception e) {
        startIndex = 0;
      }
        
        /**
         * First line gets the ID of the top story.
         * The second line gets the url
         * maps title to key and url to value
         */
        for (int i  = 0; i < noOfStories; i++) {
        String topStoryId = Unirest.get(URL)
        .asJson()
        .getBody()
        .getArray()
        .get(startIndex+i)
        .toString();
 
        String topStoryTitle = Unirest.get("https://hacker-news.firebaseio.com/v0/item/{id}.json?print=pretty")
        .routeParam("id", topStoryId)
        .asJson()
        .getBody()
        .getObject()
        .getString("title")
        .toString();

        String topStoryLink = Unirest.get("https://hacker-news.firebaseio.com/v0/item/{id}.json?print=pretty")
        .routeParam("id", topStoryId)
        .asJson()
        .getBody()
        .getObject()
        .getString("url")
        .toString();

        stories.put(topStoryTitle, topStoryLink);
        }
        return stories;
    }
    @Override
  public String getDescription() {
    return "tophackerstories test";
  }
}