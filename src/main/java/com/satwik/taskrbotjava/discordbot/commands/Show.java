package com.satwik.taskrbotjava.discordbot.commands;

import com.satwik.taskrbotjava.dataaccess.document.Items;
import com.satwik.taskrbotjava.dataaccess.queries.ItemQueryController;
import com.satwik.taskrbotjava.dataaccess.queries.ListQueryController;
import com.satwik.taskrbotjava.discordbot.commands.util.ItemFilter;
import com.satwik.taskrbotjava.discordbot.commands.util.MessageUtil;
import net.dv8tion.jda.api.events.message.MessageReceivedEvent;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.List;

@Component
public class Show implements Command  {

    private ItemQueryController itemQueryController;
    private ListQueryController listQueryController;
    private MessageUtil messageUtil;

    @Autowired
    public Show(ItemQueryController itemQueryController, ListQueryController listQueryController, MessageUtil messageUtil) {
        this.itemQueryController = itemQueryController;
        this.listQueryController = listQueryController;
        this.messageUtil = messageUtil;
    }

    @Override
    public String getName() { return "show"; }

    @Override
    public String getUsage() {
        return "Usage: \n" +
                getName() + " <(Optional) ShowDone> \n" +
                "Description: \n" +
                "Shows all items on currently checked-out list. " +
                "Pass 'ShowDone' as second parameter to show completed items. Hidden by default.";
    }

    @Override
    public void trigger(String[] args, MessageReceivedEvent event) {
        List<Items> listItems = itemQueryController.getCurrentUserListItems(event.getAuthor().getIdLong());
        String listName = listQueryController.getCurrentUsersActiveList(event.getAuthor().getIdLong()).getName();
        boolean showDone = args.length > 1 && args[1].equals("ShowDone") ? true : false;

        if (!showDone) {
            listItems = ItemFilter.filterDone(listItems);
        }

        messageUtil.dispatchMessage(event, messageUtil.formatList(listName, listItems));
    }
}
