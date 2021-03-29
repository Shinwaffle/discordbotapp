import org.javacord.api.DiscordApi;
import org.javacord.api.DiscordApiBuilder;

public class Main {
    private static final String token = Secret.token;
    public static void main(String[] args) throws Exception {
        
        DiscordApi api = new DiscordApiBuilder().setToken(token).login().join();
        HttpStatusRequest s = new HttpStatusRequest(); 
        
        api.addMessageCreateListener(event -> {
            if (event.getMessageContent().equalsIgnoreCase("!ping")) {
                event.getChannel().sendMessage("```json\n"+s.getResponse()+"\n```");
            }
        });
        
        //to get invite: api.createBotInvite()
        System.out.println("Bot is running, "+api.createBotInvite());
    }
}
