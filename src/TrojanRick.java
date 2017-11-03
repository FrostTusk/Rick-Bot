import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.Map;
import net.dv8tion.jda.core.AccountType;
import net.dv8tion.jda.core.JDA;
import net.dv8tion.jda.core.JDABuilder;
import net.dv8tion.jda.core.entities.Message;
import net.dv8tion.jda.core.entities.TextChannel;

public class TrojanRick {
	
	public static JDA jda = null;
	public static String RICK_TOKEN;

	
	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new FileReader("token.txt"));
		RICK_TOKEN = br.readLine();
		br.close();
		jda = run();
	}
	
	
	public static JDA run() {
		JDA jda = null;
		try {
			jda = new JDABuilder(AccountType.BOT).setToken(RICK_TOKEN).buildBlocking();
			jda.addEventListener(new HackedListener());
		} catch (Exception e) {
			System.out.println("[!] Initialization Error [!]");
		}
		return jda;
	}
	
	
	public static void cleanUp(int sentCounter, Map<String, TextChannel> sentMessages, boolean show_output) {
		System.out.println("");
		if (sentCounter != sentMessages.size())
			System.out.println("[!] SENT MESSAGES COUNTER != SELF RECEIVED [!]");
		
		purgeMessages(sentMessages, show_output);
		jda.shutdown();
		System.exit(0);
	}
	
	
	public static void purgeMessages(Map<String, TextChannel> sentMessages, boolean show_output) {
		if (show_output)
			System.out.println("Showing Deleted Messages in no particular order:");
		
		int counter = 1;
		for (String messageID: sentMessages.keySet()) {	
			// Catches exception if message was already deleted.
			try {
				if (show_output) {
					Message message = sentMessages.get(messageID).getMessageById(messageID).complete();
					System.out.format("Message %d: Message String id = %S\n", counter, message.getId());
					System.out.format("Message %d: Contents = %S\n", counter, message.getContent());
				}
				sentMessages.get(messageID).deleteMessageById(messageID).complete();
			} catch (Exception e) {
				if (show_output)
					System.out.format("Message %d: Already deleted\n", counter);
			} 
			counter += 1;
		}
	}
	
}
