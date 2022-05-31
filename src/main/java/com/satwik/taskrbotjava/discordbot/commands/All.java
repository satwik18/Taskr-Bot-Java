package com.satwik.taskrbotjava.discordbot.commands;

import com.satwik.taskrbotjava.dataaccess.document.Lists;
import com.satwik.taskrbotjava.dataaccess.queries.ListQueryController;
import com.satwik.taskrbotjava.discordbot.commands.util.MessageUtil;
import net.dv8tion.jda.api.events.message.MessageReceivedEvent;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.List;

@Component
public class All implements Command {

    private ListQueryController listQueryController;
    private MessageUtil messageUtil;

    @Autowired
    public All(ListQueryController listQueryController, MessageUtil messageUtil) {
        this.listQueryController = listQueryController;
        this.messageUtil = messageUtil;
    }

    @Override
    public String getName() { return "all"; }

    @Override
    public String getUsage() {
        return "Usage: \n" +
                getName() + "\n" +
                "Description: \n" +
                "Shows all lists.";
    }

    @Override
    public void trigger(String[] args, MessageReceivedEvent event) {
        List<Lists> allLists = listQueryController.getAllListsByCommunity(event.getGuild().getIdLong());
        messageUtil.dispatchMessage(event, messageUtil.formatListOfLists(allLists));
    }
}
