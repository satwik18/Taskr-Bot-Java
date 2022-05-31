package com.satwik.taskrbotjava.discordbot.commands;

import com.satwik.taskrbotjava.dataaccess.queries.ListQueryController;
import com.satwik.taskrbotjava.discordbot.commands.util.MessageUtil;
import net.dv8tion.jda.api.events.message.MessageReceivedEvent;
import org.springframework.stereotype.Component;

@Component
public class NewList implements Command {

    private final static String OPEN = "Open";
    private final static String LOCKED = "Locked";

    private MessageUtil messageUtil;
    private ListQueryController listQueryController;

    public NewList(MessageUtil messageUtil, ListQueryController listQueryController) {
        this.messageUtil = messageUtil;
        this.listQueryController = listQueryController;
    }

    @Override
    public String getName() { return "new-list"; }

    @Override
    public String getUsage() {
        return "Usage: \n" +
                getName() + " \"<Name>\" \"<(Optional) " + OPEN + "/" + LOCKED + ">\"\n" +
                "Description: \n" +
                "Create new list. Must pass a Name for new list and optionally can pass " +
                "Open or Closed to indicate if others can add tasks to your list. Default is Locked.";
    }

    @Override
    public void trigger(String[] args, MessageReceivedEvent event) {
        String[] opts = args[1].split("\"", 0);

        String listName = null;
        boolean isOpen = false;

        if (opts.length < 2){
            messageUtil.dispatchMessage(event, getUsage());
            return;
        }

        if (opts.length >= 2){
            listName = opts[1];
        }

        if (opts.length >= 4){
            isOpen = OPEN.equalsIgnoreCase(opts[3]);
        }

        listQueryController.createNewList(event.getGuild().getIdLong(), listName, event.getAuthor().getIdLong(), isOpen);
    }
}
