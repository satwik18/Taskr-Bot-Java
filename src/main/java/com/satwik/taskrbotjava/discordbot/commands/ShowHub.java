package com.satwik.taskrbotjava.discordbot.commands;

import com.satwik.taskrbotjava.dataaccess.Views.PopulatedList;
import com.satwik.taskrbotjava.dataaccess.queries.PopulatedListQueryController;
import com.satwik.taskrbotjava.discordbot.commands.util.ItemFilter;
import com.satwik.taskrbotjava.discordbot.commands.util.MessageUtil;
import net.dv8tion.jda.api.events.message.MessageReceivedEvent;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.List;

@Component
public class ShowHub implements Command {

    private PopulatedListQueryController populatedListQueryController;
    private MessageUtil messageUtil;

    @Autowired
    public ShowHub(PopulatedListQueryController populatedListQueryController, MessageUtil messageUtil) {
        this.populatedListQueryController = populatedListQueryController;
        this.messageUtil = messageUtil;
    }

    @Override
    public String getName() { return "show-hub"; }

    @Override
    public String getUsage() {
        return "Usage: \n" +
                getName() + " <(Optional) ShowDone> \n" +
                "Description: \n" +
                "Shows all hub/default lists of Community Members. " +
                "Pass 'ShowDone' as second parameter to show completed items. Hidden by default.";
    }

    @Override
    public void trigger(String[] args, MessageReceivedEvent event) {
        List<PopulatedList> hub = populatedListQueryController.getHubList(event.getGuild().getIdLong());
        boolean showDone = args.length > 1 && args[1].equals("ShowDone") ? true : false;
        String output = new String();

        for (PopulatedList list: hub) {
            if (!showDone) {
                list.setItems(ItemFilter.filterDone(list.getItems()));
            }
            output += messageUtil.formatList(list.getName(), list.getItems()) + "\n\n";
        }

        messageUtil.dispatchMessage(event, output);
    }
}
