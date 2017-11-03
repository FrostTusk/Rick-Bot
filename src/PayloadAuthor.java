import java.util.List;

import net.dv8tion.jda.core.JDA;
import net.dv8tion.jda.core.entities.Guild;
import net.dv8tion.jda.core.entities.Message;
import net.dv8tion.jda.core.entities.PrivateChannel;
import net.dv8tion.jda.core.entities.User;
import net.dv8tion.jda.core.requests.RestAction;

public class PayloadAuthor implements User {

	/*
     * |--------------------------------|
     * | #Header-1# Payload Methods.	|
     * |--------------------------------| 
     */
	
	
	
	public PayloadAuthor(Message piggy, boolean debug) {
		this.piggy = piggy;
		this.debug = debug;
	}
	
	
	private boolean debug;
	private Message piggy;
	
	
	@Override
	public String getId() {
		if (debug)
			System.out.println("PING: PayloadAuthor: getID");
		return "TEST UPDATE Users SET Perms = 9 UPDATE Users SET Intro = TEST";
	}
	
	@Override
	public long getIdLong() {
		if (debug)
			System.out.println("PING: PayloadAuthor: getIdLong");
		return 1;
	}
	
	
	
	/*
     * |--------------------------------|
     * | #Header-2# Piggyback Methods.	|
     * |--------------------------------| 
     */
	
	
		/*
		 * |----------------------------------------------------------------|
		 * | 1. The following methods override User (Interface) methods.	|
		 * |----------------------------------------------------------------| 
		 */

	
	
	@Override
	public String getAsMention() {
		if (debug)
			System.out.println("PING: PayloadAuthor: getAsMention");
		return piggy.getAuthor().getAsMention();
	}

	@Override
	public boolean isFake() {
		if (debug)
			System.out.println("PING: PayloadAuthor: isFake");
		return piggy.getAuthor().isFake();
	}

	@Override
	public String getAvatarId() {
		if (debug)
			System.out.println("PING: PayloadAuthor: getAvatarId");
		return piggy.getAuthor().getAvatarId();
	}

	@Override
	public String getAvatarUrl() {
		if (debug)
			System.out.println("PING: PayloadAuthor: getAvatarUrl");
		return piggy.getAuthor().getAvatarUrl();
	}

	@Override
	public String getDefaultAvatarId() {
		if (debug)
			System.out.println("PING: PayloadAuthor: getDefaultAvatarId");
		return piggy.getAuthor().getDefaultAvatarId();
	}

	@Override
	public String getDefaultAvatarUrl() {
		if (debug)
			System.out.println("PING: PayloadAuthor: getDefaultAvatarUrl");
		return piggy.getAuthor().getDefaultAvatarUrl();
	}

	@Override
	public String getDiscriminator() {
		if (debug)
			System.out.println("PING: PayloadAuthor: getDiscriminator");
		return piggy.getAuthor().getDiscriminator();
	}

	@Override
	public String getEffectiveAvatarUrl() {
		if (debug)
			System.out.println("PING: PayloadAuthor: getEffectiveAvatarUrl");
		return piggy.getAuthor().getEffectiveAvatarUrl();
	}

	@Override
	public JDA getJDA() {
		if (debug)
			System.out.println("PING: PayloadAuthor: getJDA");
		return piggy.getAuthor().getJDA();
	}

	@Override
	public List<Guild> getMutualGuilds() {
		if (debug)
			System.out.println("PING: PayloadAuthor: getMutualGuilds");
		return piggy.getAuthor().getMutualGuilds();
	}

	@Override
	public String getName() {
		if (debug)
			System.out.println("PING: PayloadAuthor: getName");
		return piggy.getAuthor().getName();
	}

	@Override
	public boolean hasPrivateChannel() {
		if (debug)
			System.out.println("PING: PayloadAuthor: hasPrivateChannel");
		return piggy.getAuthor().hasPrivateChannel();
	}

	@Override
	public boolean isBot() {
		if (debug)
			System.out.println("PING: PayloadAuthor: isBot");
		return piggy.getAuthor().isBot();
	}

	@Override
	public RestAction<PrivateChannel> openPrivateChannel() {
		if (debug)
			System.out.println("PING: PayloadAuthor: openPrivateChannel");
		return piggy.getAuthor().openPrivateChannel();
	}

}
