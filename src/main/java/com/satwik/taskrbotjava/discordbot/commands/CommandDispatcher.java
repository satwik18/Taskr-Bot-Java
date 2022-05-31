package com.satwik.taskrbotjava.discordbot.commands;

import com.satwik.taskrbotjava.dataaccess.queries.CommunityQueryController;
import com.satwik.taskrbotjava.dataaccess.queries.UserQueryController;
import net.dv8tion.jda.api.entities.Member;
import net.dv8tion.jda.api.events.message.MessageReceivedEvent;
import net.dv8tion.jda.api.hooks.ListenerAdapter;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Controller;

@Controller
public class CommandDispatcher extends ListenerAdapter {

    @Value("${jda.discord.prefix}")
    private String prefix;

    private CommandDict commandDict;
    private UserQueryController userQueryController;
    private CommunityQueryController communityQueryController;

    @Autowired
    public CommandDispatcher(CommandDict commandDict, UserQueryController userQueryController, CommunityQueryController communityQueryController) {
        this.commandDict = commandDict;
        this.userQueryController = userQueryController;
        this.communityQueryController = communityQueryController;
    }

    @Override
    public void onMessageReceived(MessageReceivedEvent event) {
        if (!event.isFromGuild()) return;

        Boolean isMentioned = false;
        for (Member m : event.getMessage().getMentions().getMembers()) {
            if (m.getUser().getId().equals(event.getJDA().getSelfUser().getId())) {
                isMentioned = true;
                break;
            }
        }

        if (isMentioned) {
            event.getChannel().sendMessage("TEST MESSAGE \n Testing Output").queue();
            return;
        }

        if(!event.getAuthor().isBot()){

        }

        String[] args = event.getMessage().getContentRaw().split(" ", 2);

        if (args[0].startsWith(prefix)) {
            communityQueryController.checkAndAddCommunity(event.getGuild().getIdLong());
            userQueryController.checkAndAddGuildUser(event.getGuild().getIdLong(), event.getAuthor().getIdLong(), event.getAuthor().getName());
            args[0] = args[0].substring(prefix.length());
            Command command = commandDict.getCommand(args[0]);
            if(command != null) command.trigger(args, event);
        }
    }

    public void addCommands(Command[] commands) {
        for (Command command : commands) {
            this.commandDict.addCommand(command);
        }
    }
}
