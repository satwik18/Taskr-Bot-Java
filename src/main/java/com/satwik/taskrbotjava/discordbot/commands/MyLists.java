package com.satwik.taskrbotjava.discordbot.commands;

import com.satwik.taskrbotjava.dataaccess.document.Lists;
import com.satwik.taskrbotjava.dataaccess.queries.ListQueryController;
import com.satwik.taskrbotjava.discordbot.commands.util.MessageUtil;
import net.dv8tion.jda.api.events.message.MessageReceivedEvent;
import org.springframework.stereotype.Component;

import java.util.List;

@Component
public class MyLists implements Command{

    private ListQueryController listQueryController;
    private MessageUtil messageUtil;

    public MyLists(ListQueryController listQueryController, MessageUtil messageUtil) {
        this.listQueryController = listQueryController;
        this.messageUtil = messageUtil;
    }

    @Override
    public String getName() { return  "my-lists"; }

    @Override
    public String getUsage() {
        return "Usage: \n" +
                getName() + "\n" +
                "Description: \n" +
                "Shows all lists owned by current user.";
    }

    @Override
    public void trigger(String[] args, MessageReceivedEvent event) {
        List<Lists> allLists = listQueryController.getAllListsByUser(event.getAuthor().getIdLong());
        messageUtil.dispatchMessage(event, messageUtil.formatListOfLists(allLists));
    }
}
