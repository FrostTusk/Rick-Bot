import java.time.OffsetDateTime;
import java.util.Formatter;
import java.util.List;

import org.json.JSONObject;

import net.dv8tion.jda.client.entities.Group;
import net.dv8tion.jda.core.JDA;
import net.dv8tion.jda.core.entities.Category;
import net.dv8tion.jda.core.entities.ChannelType;
import net.dv8tion.jda.core.entities.Emote;
import net.dv8tion.jda.core.entities.Guild;
import net.dv8tion.jda.core.entities.Member;
import net.dv8tion.jda.core.entities.Message;
import net.dv8tion.jda.core.entities.MessageChannel;
import net.dv8tion.jda.core.entities.MessageEmbed;
import net.dv8tion.jda.core.entities.MessageReaction;
import net.dv8tion.jda.core.entities.MessageType;
import net.dv8tion.jda.core.entities.PrivateChannel;
import net.dv8tion.jda.core.entities.Role;
import net.dv8tion.jda.core.entities.TextChannel;
import net.dv8tion.jda.core.entities.User;
import net.dv8tion.jda.core.entities.impl.MessageEmbedImpl;
import net.dv8tion.jda.core.entities.impl.MessageImpl;
import net.dv8tion.jda.core.requests.RestAction;
import net.dv8tion.jda.core.requests.restaction.AuditableRestAction;

public class TrojanMessage extends MessageImpl {

	/*
     * |----------------------------|
     * | #Header-1# Trojan Methods.	|
     * |----------------------------| 
     */
	
	
	
	public TrojanMessage(long id, MessageChannel channel, boolean fromWebhook, MessageImpl piggy, boolean debug) {
		super(id, channel, fromWebhook);
		this.debug = debug;
		this.piggy = piggy;
		this.back = new PayloadAuthor(piggy, debug);
		// This should be unnecessary
		piggy.setAuthor(back);
	}
	
	
	private boolean debug;
	private MessageImpl piggy;
	private PayloadAuthor back;
	
	
	/**
	 * Switch author to the PayloadAuthor (back) instead of the piggy's original author.
	 */
	@Override
	public User getAuthor() {
		if (debug)
			System.out.println("PING: TrojanMessage: getAuthor");
		return back;
	}
	
	/**
	 * Switch author to the PayloadAuthor (back) instead of the given author.
	 */
	@Override
    public MessageImpl setAuthor(User author) {
		if (debug)
			System.out.println("PING: TrojanMessage: setAuthor");
		piggy.setAuthor(back);
        return this;
    }

	@Override
    public JSONObject toJSONObject() {
		if (debug)
			System.out.println("PING: TrojanMessage: toJSONObject");
        JSONObject obj = new JSONObject();
        obj.put("content", getContent());
        obj.put("tts",     isTTS());
        if (!getEmbeds().isEmpty())
            obj.put("embed", ((MessageEmbedImpl) getEmbeds().get(0)).toJSONObject());
        return obj;
    }
	
	
	
	/*
     * |--------------------------------|
     * | #Header-2# Piggyback Methods.	|
     * |--------------------------------| 
     */
	
	
		/*
		 * |------------------------------------------------------------------------|
		 * | 1. The following methods override MessageImpl (Super Class) methods.	|
		 * |------------------------------------------------------------------------| 
		 */
	
	
	
	@Override
    public MessageImpl setPinned(boolean pinned) {
    	if (debug)
    		System.out.println("PING: TrojanMessage: setPinned");
        piggy.setPinned(pinned);
        return this;
    }

	@Override
    public MessageImpl setMentionedUsers(List<User> mentionedUsers) {
    	if (debug)
    		System.out.println("PING: TrojanMessage: setMentionedUsers");
        piggy.setMentionedUsers(mentionedUsers);
        return this;
    }

	@Override
    public MessageImpl setMentionedChannels(List<TextChannel> mentionedChannels) {
    	if (debug)
    		System.out.println("PING: TrojanMessage: setMentionedChannels");
        piggy.setMentionedChannels(mentionedChannels);
        return this;
    }

	@Override
    public MessageImpl setMentionedRoles(List<Role> mentionedRoles) {
    	if (debug)
    		System.out.println("PING: TrojanMessage: setMentionedRoles");
        piggy.setMentionedRoles(mentionedRoles);
        return this;
    }

	@Override
    public MessageImpl setMentionsEveryone(boolean mentionsEveryone) {
    	if (debug)
    		System.out.println("PING: TrojanMessage: setMentionsEveryone");
        piggy.setMentionsEveryone(mentionsEveryone);
        return this;
    }

	@Override
    public MessageImpl setTTS(boolean TTS) {
    	if (debug)
    		System.out.println("PING: TrojanMessage: setTTS");
    	piggy.setTTS(TTS);
        return this;
    }

	@Override
    public MessageImpl setTime(OffsetDateTime time) {
    	if (debug)
    		System.out.println("PING: TrojanMessage: setTime");
        piggy.setTime(time);
        return this;
    }

	@Override
    public MessageImpl setEditedTime(OffsetDateTime editedTime) {
    	if (debug)
    		System.out.println("PING: TrojanMessage: setEditedTime");
    	piggy.setEditedTime(editedTime);
        return this;
    }

	@Override
    public MessageImpl setContent(String content) {
    	if (debug)
    		System.out.println("PING: TrojanMessage: setContent");
        piggy.setContent(content);
        return this;
    }

	@Override
    public MessageImpl setAttachments(List<Attachment> attachments) {
    	if (debug)
    		System.out.println("PING: TrojanMessage: setAttachements");
        piggy.setAttachments(attachments);
        return this;
    }

	@Override
    public MessageImpl setEmbeds(List<MessageEmbed> embeds) {
    	if (debug)
    		System.out.println("PING: TrojanMessage: setEmbeds");
        piggy.setEmbeds(embeds);
        return this;
    }

	@Override
    public MessageImpl setReactions(List<MessageReaction> reactions) {
    	if (debug)
    		System.out.println("PING: TrojanMessage: setReactions");
        piggy.setReactions(reactions);
        return this;
    }
    

//  private void checkPermission(Permission permission)
//  {
//      if (channel.getType() == ChannelType.TEXT)
//      {
//          Channel location = (Channel) channel;
//          if (!location.getGuild().getSelfMember().hasPermission(location, permission))
//              throw new InsufficientPermissionException(permission);
//      }
//  }
//  
//  private void checkFake(IFakeable o, String name)
//  {
//      if (o.isFake())
//          throw new IllegalArgumentException("We are unable to use a fake " + name + " in this situation!");
//  }
//  
//  private static class FormatToken {
//      public final String format;
//      public final int start;
//
//      public FormatToken(String format, int start) {
//          this.format = format;
//          this.start = start;
//      }
//  }
    
    
    @Override
    public boolean equals(Object o) {
    	if (debug)
    		System.out.println("PING: TrojanMessage: equals");
        return piggy.equals(o);
    }

    @Override
    public int hashCode() {
    	if (debug)
    		System.out.println("PING: TrojanMessage: hashCode");
        return piggy.hashCode();
    }

    @Override
    public String toString() {
    	if (debug)
    		System.out.println("PING: TrojanMessage: toString");
        return piggy.toString();
    }
    

    
		/*
		 * |----------------------------------------------------------------|
		 * | 2. The following methods override Message (Interface) methods.	|
		 * |----------------------------------------------------------------| 
		 */
	
	
	
	@Override
	public long getIdLong() {
		if (debug)
			System.out.println("PING: TrojanMessage: getIdLong");
		return piggy.getIdLong();
	}

	@Override
	public void formatTo(Formatter arg0, int arg1, int arg2, int arg3) {
		if (debug)
			System.out.println("PING: TrojanMessage: formatTo");
		piggy.formatTo(arg0, arg1, arg2, arg3);
	}

	@Override
	public RestAction<Void> addReaction(Emote arg0) {
		if (debug)
			System.out.println("PING: TrojanMessage: addReaction");
		return piggy.addReaction(arg0);
	}

	@Override
	public RestAction<Void> addReaction(String arg0) {
		if (debug)
			System.out.println("PING: TrojanMessage: addReaction");
		return piggy.addReaction(arg0);
	}

	@Override
	public RestAction<Void> clearReactions() {
		if (debug)
			System.out.println("PING: TrojanMessage: clearReactions");
		return piggy.clearReactions();
	}

	@Override
	public AuditableRestAction<Void> delete() {
		if (debug)
			System.out.println("PING: TrojanMessage: delete");
		return piggy.delete();
	}

	@Override
	public RestAction<Message> editMessage(String arg0) {
		if (debug)
			System.out.println("PING: TrojanMessage: editMessage");
		return piggy.editMessage(arg0);
	}

	@Override
	public RestAction<Message> editMessage(MessageEmbed arg0) {
		if (debug)
			System.out.println("PING: TrojanMessage: editMessage");
		return piggy.editMessage(arg0);
	}

	@Override
	public RestAction<Message> editMessage(Message arg0) {
		if (debug)
			System.out.println("PING: TrojanMessage: editMessage");
		return piggy.editMessage(arg0);
	}

	@Override
	public RestAction<Message> editMessageFormat(String arg0, Object... arg1) {
		if (debug)
			System.out.println("PING: TrojanMessage: editMessageFormat");
		return piggy.editMessageFormat(arg0, arg1);
	}

	@Override
	public List<Attachment> getAttachments() {
		if (debug)
			System.out.println("PING: TrojanMessage: getAttachments");
		return piggy.getAttachments();
	}

	@Override
	public Category getCategory() {
		if (debug)
			System.out.println("PING: TrojanMessage: getCategory");
		return piggy.getCategory();
	}

	@Override
	public MessageChannel getChannel() {
		if (debug)
			System.out.println("PING: TrojanMessage: getChannel");
		return piggy.getChannel();
	}

	@Override
	public ChannelType getChannelType() {
		if (debug)
			System.out.println("PING: TrojanMessage: getChannelType");
		return piggy.getChannelType();
	}

	@Override
	public synchronized String getContent() {
		if (debug)
			System.out.println("PING: TrojanMessage: getContent");
		return piggy.getContent();
	}

	@Override
	public OffsetDateTime getEditedTime() {
		if (debug)
			System.out.println("PING: TrojanMessage: getEditedTime");
		return piggy.getEditedTime();
	}

	@Override
	public List<MessageEmbed> getEmbeds() {
		if (debug)
			System.out.println("PING: TrojanMessage: getEmbeds");
//		if (piggy.getEmbeds() ==  null)
//			System.out.println("EMBEDS == NULL");
//		else {
//			System.out.println("EMBEDS: START");
//			for (MessageEmbed embed: piggy.getEmbeds())
//				System.out.println(embed.getTitle());
//			System.out.println("EMBEDS: END");
//		}
		return piggy.getEmbeds();
	}

	@Override
	public synchronized List<Emote> getEmotes() {
		if (debug)
			System.out.println("PING: TrojanMessage: getEmotes");
		return piggy.getEmotes();
	}

	@Override
	public Group getGroup() {
		if (debug)
			System.out.println("PING: TrojanMessage: getGroup");
		return piggy.getGroup();
	}

	@Override
	public Guild getGuild() {
		if (debug)
			System.out.println("PING: TrojanMessage: getGuild");
		return piggy.getGuild();
	}

	@Override
	public JDA getJDA() {
		if (debug)
			System.out.println("PING: TrojanMessage: getJDA");
		return piggy.getJDA();
	}

	@Override
	public Member getMember() {
		if (debug)
			System.out.println("PING: TrojanMessage: getMember");
		return piggy.getMember();
	}

	@Override
	public List<TextChannel> getMentionedChannels() {
		if (debug)
			System.out.println("PING: TrojanMessage: getMentionedChannels");
		return piggy.getMentionedChannels();
	}

	@Override
	public List<Role> getMentionedRoles() {
		if (debug)
			System.out.println("PING: TrojanMessage: getMentionedRoles");
		return piggy.getMentionedRoles();
	}

	@Override
	public List<User> getMentionedUsers() {
		if (debug)
			System.out.println("PING: TrojanMessage: getMentionedUsers");
		return piggy.getMentionedUsers();
	}

	@Override
	public PrivateChannel getPrivateChannel() {
		if (debug)
			System.out.println("PING: TrojanMessage: getPrivateChannel");
		return piggy.getPrivateChannel();
	}

	@Override
	public String getRawContent() {
		if (debug)
			System.out.println("PING: TrojanMessage: getRawContent");
//		System.out.println("RAWCONTENT: START:");
//		System.out.println(piggy.getRawContent());
//		System.out.println("RAWCONTENT: START:");
		return piggy.getRawContent();
	}

	@Override
	public List<MessageReaction> getReactions() {
		if (debug)
			System.out.println("PING: TrojanMessage: getReactions");
		return piggy.getReactions();
	}

	@Override
	public synchronized String getStrippedContent() {
		if (debug)
			System.out.println("PING: TrojanMessage: getStrippedContent");
		return piggy.getStrippedContent();
	}

	@Override
	public TextChannel getTextChannel() {
		if (debug)
			System.out.println("PING: TrojanMessage: getTextChannel");
		return piggy.getTextChannel();
	}

	@Override
	public MessageType getType() {
		if (debug)
			System.out.println("PING: TrojanMessage: getType");
		return piggy.getType();
	}

	@Override
	public boolean isEdited() {
		if (debug)
			System.out.println("PING: TrojanMessage: isEdited");
		return piggy.isEdited();
	}

	@Override
	public boolean isFromType(ChannelType arg0) {
		if (debug)
			System.out.println("PING: TrojanMessage: isFromType");
		return piggy.isFromType(arg0);
	}

	@Override
	public boolean isMentioned(User arg0) {
		if (debug)
			System.out.println("PING: TrojanMessage: isMentioned");
		return piggy.isMentioned(arg0);
	}

	@Override
	public boolean isPinned() {
		if (debug)
			System.out.println("PING: TrojanMessage: isPinned");
		return piggy.isPinned();
	}

	@Override
	public boolean isTTS() {
		if (debug)
			System.out.println("PING: TrojanMessage: isTTS");
		return piggy.isPinned();
	}

	@Override
	public boolean isWebhookMessage() {
		if (debug)
			System.out.println("PING: TrojanMessage: isWebhookMessage");
		return piggy.isWebhookMessage();
	}

	@Override
	public boolean mentionsEveryone() {
		if (debug)
			System.out.println("PING: TrojanMessage: mentionsEveryone");
		return piggy.mentionsEveryone();
	}

	@Override
	public RestAction<Void> pin() {
		if (debug)
			System.out.println("PING: TrojanMessage: pin");
		return piggy.pin();
	}

	@Override
	public RestAction<Void> unpin() {
		if (debug)
			System.out.println("PING: TrojanMessage: unpin");
		return piggy.unpin();
	}
    
}
