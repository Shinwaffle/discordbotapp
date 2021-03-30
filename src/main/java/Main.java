import org.javacord.api.DiscordApi;
import org.javacord.api.DiscordApiBuilder;
import org.javacord.api.entity.message.Message;
import org.javacord.api.entity.message.MessageBuilder;

public class Main {
    private static final String token = Secret.token;
    public static void main(String[] args) throws Exception {
        
        DiscordApi api = new DiscordApiBuilder().setToken(token).login().join();
        HttpRequest s = new HttpRequest(); 
        
        api.addMessageCreateListener(event -> {
            Message message = event.getMessage();
            if (message.getContent().equalsIgnoreCase("!ping")) {
                new MessageBuilder()
                .append("Here's what I got: ")
                .appendCode("json", s.getResponse()).send(event.getChannel());
            }
            message.addReactionAddListener(msg -> {
                if(msg.getEmoji().equalsEmoji("💯")) {
                    event.deleteMessage("Dissapearing act");
                }
            });                   
        });
        
        System.out.println("Bot is running, "+api.createBotInvite());
    }
}
