package com.satwik.taskrbotjava.discordbot;

import com.satwik.taskrbotjava.dataaccess.queries.UserQueryController;
import com.satwik.taskrbotjava.discordbot.commands.Command;
import com.satwik.taskrbotjava.discordbot.commands.util.MessageUtil;
import net.dv8tion.jda.api.events.message.MessageReceivedEvent;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component
public class SwapId implements Command {

    private MessageUtil messageUtil;
    private UserQueryController userQueryController;

    @Autowired
    public SwapId(MessageUtil messageUtil, UserQueryController userQueryController) {
        this.messageUtil = messageUtil;
        this.userQueryController = userQueryController;
    }

    @Override
    public String getName() { return "swap-id"; }

    @Override
    public String getUsage() {
        return "Usage: \n" +
                getName() + " <ListId> \n" +
                "Description: \n" +
                "Switch active list to list specified as second argument.";
    }

    @Override
    public void trigger(String[] args, MessageReceivedEvent event) {
        String[] opts = args[1].split(" ", 0);

        Integer id = null;

        if (opts.length < 1){
            messageUtil.dispatchMessage(event, getUsage());
            return;
        }

        id = Integer.valueOf(opts[0]);

        String listName = userQueryController.swapUserCurrentActiveList(event.getAuthor().getIdLong(), id);
        messageUtil.dispatchMessage(event, event.getAuthor().getName() + " Swapped to list: " + listName);
    }
}
