import org.javacord.api.DiscordApi;
import org.javacord.api.DiscordApiBuilder;
import org.javacord.api.entity.message.Message;
import org.javacord.api.entity.message.MessageBuilder;

public class Main {
    private static final String token = Secret.token;
    public static void main(String[] args) throws Exception {
        
        DiscordApi api = new DiscordApiBuilder().setToken(token).login().join();
        HttpRequest s = new HttpRequest(); 

        int[] indices = {0,1,2,3,4,5,6,7,8,9,10,11,12,13,14,15,16,17,18,19,20,21,22,23,24,25,26,27,28,29}; //there has to be a way better way of doing this lmao
        api.addMessageCreateListener(event -> {
            Message message = event.getMessage();
            System.out.println(message);
            if (message.getContent().equalsIgnoreCase("!ping")) {
                new MessageBuilder()
                .append("Here's what I got: ")
                .appendCode("json", s.getResponse(0)).send(event.getChannel());
            }
            
            for (int i = 0; i < 30; i++) {
                if (message.getContent().equalsIgnoreCase("!ping "+indices[i])) {
                    new MessageBuilder()
                    .append("Here's what I got: ")
                    .appendCode("json", s.getResponse(i)).send(event.getChannel());
                }
            }
            /**
             * The for loop works and everything but I highly doubt it's the best way. Making the array just feels meh
             */
            message.addReactionAddListener(msg -> {
                if(msg.getEmoji().equalsEmoji("💯")) {
                    event.deleteMessage("Dissapearing act");
                }
            });                   
        });
        
        System.out.println("Bot is running, "+api.createBotInvite());
    }
}
