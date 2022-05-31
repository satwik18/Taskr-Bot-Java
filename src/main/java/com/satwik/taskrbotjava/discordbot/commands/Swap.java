package com.satwik.taskrbotjava.discordbot.commands;

import com.satwik.taskrbotjava.dataaccess.queries.UserQueryController;
import com.satwik.taskrbotjava.discordbot.commands.util.MessageUtil;
import net.dv8tion.jda.api.events.message.MessageReceivedEvent;
import org.springframework.stereotype.Component;

@Component
public class Swap implements Command {


    private MessageUtil messageUtil;
    private UserQueryController userQueryController;

    public Swap(MessageUtil messageUtil, UserQueryController userQueryController) {
        this.messageUtil = messageUtil;
        this.userQueryController = userQueryController;
    }

    @Override
    public String getName() { return "swap"; }

    @Override
    public String getUsage() {
        return "Usage: \n" +
                getName() + " \"<List Name>\" \n" +
                "Description: \n" +
                "Switch active list to list specified as second argument. " +
                "If multiple lists with same name exist, it is recommended to use swap-id command instead.";
    }

    @Override
    public void trigger(String[] args, MessageReceivedEvent event) {
        String[] opts = args[1].split("\"", 0);

        String name = null;

        if (opts.length < 2){
            messageUtil.dispatchMessage(event, getUsage());
            return;
        }

        if (opts.length >= 2){
            name = opts[1];
        }

        Integer listID = userQueryController.swapUserCurrentActiveList(event.getAuthor().getIdLong(), name);
        messageUtil.dispatchMessage(event, event.getAuthor().getName() + " Swapped to listId: " + listID.toString());
    }
}
