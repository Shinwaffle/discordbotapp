import org.javacord.api.DiscordApi;
import org.javacord.api.DiscordApiBuilder;
import org.javacord.api.entity.message.Message;

public class Main {
    private static final String token = Secret.token;
    public static void main(String[] args) throws Exception {
        
        DiscordApi api = new DiscordApiBuilder().setToken(token).login().join();
        //HttpStatusRequest s = new HttpStatusRequest(); 
        
        api.addMessageCreateListener(event -> {
            Message message = event.getMessage();
            if (message.getContent().equalsIgnoreCase("!ping")) {
                event.getChannel().sendMessage("React to me and I'll dissapear");
            }
            message.addReactionAddListener(msg -> {
                if(msg.getEmoji().equalsEmoji("😆")) {
                    event.deleteMessage("Dissapearing act");
                }
            });
        });
        
        //to get invite: api.createBotInvite()
        System.out.println("Bot is running, "+api.createBotInvite());
    }
}
