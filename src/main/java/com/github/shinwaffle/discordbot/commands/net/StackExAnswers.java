package com.github.shinwaffle.discordbot.commands.net;

import com.github.shinwaffle.discordbot.util.Command;

import org.javacord.api.entity.channel.Channel;
import org.javacord.api.entity.message.MessageBuilder;
import org.javacord.api.entity.user.User;

import kong.unirest.Unirest;

public class StackExAnswers extends Command {

  @Override
  public String getDescription() {
    return "stackex test";
  }

  @Override 
  public String getCommand() {
    return "stackex";
  }
  
  @Override
  public MessageBuilder execute(String[] args, Channel channel, User author) {
    return null;
  }
 /**
  * when fleshing out this function, make sure to set the url to static and
  * replace the original query with {query} to insert parameters.
  */
 final String url = "https://api.stackexchange.com/2.2/search/advanced?order=desc&sort=relevance&q=how%20to%20center%20div&answers=2&site=stackoverflow&filter=!)rTkr_OQen1do4oxw7QI";

  /**
   * currently I have it set to retrieve the JSON of the first answer.
   */
    public String getResponse(int index) {
     
     Unirest.config().cookieSpec("standard");

      return Unirest.get(url)
      .asJson()
      .getBody()
      .getObject()
      .getJSONArray("items")
      .getJSONObject(0)
      .getJSONArray("answers")
      .getJSONObject(index)
      .getString("body_markdown");
    }
  
}
