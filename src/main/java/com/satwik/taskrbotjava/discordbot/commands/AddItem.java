package com.satwik.taskrbotjava.discordbot.commands;

import com.satwik.taskrbotjava.dataaccess.queries.ItemQueryController;
import com.satwik.taskrbotjava.discordbot.commands.util.MessageUtil;
import net.dv8tion.jda.api.events.message.MessageReceivedEvent;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;

@Component
public class AddItem implements Command {
    private static final Logger logger = LoggerFactory.getLogger(AddItem.class);
    private static final SimpleDateFormat strDateFormat=new SimpleDateFormat("dd/MM/yyyy");

    private ItemQueryController itemQueryController;
    private MessageUtil messageUtil;

    @Autowired
    public AddItem(ItemQueryController itemQueryController, MessageUtil messageUtil) {
        this.itemQueryController = itemQueryController;
        this.messageUtil = messageUtil;
    }

    @Override
    public String getName() { return "add-item"; }

    @Override
    public String getUsage() {
        return "Usage: \n" +
                getName() + " \"<Description>\" \"<Date as dd/mm/yyy>\" \n" +
                "Description: \n" +
                "Add new list item to currently checked-out list. Sets status to \"TODO\"";
    }

    @Override
    public void trigger(String[] args, MessageReceivedEvent event) {
        String[] opts = args[1].split("\"", 0);

        String itemDescription = null;
        Date dueDate = null;

        if (opts.length < 2){
            messageUtil.dispatchMessage(event, getUsage());
            return;
        }

        if (opts.length >= 2){
            itemDescription = opts[1];
        }

        if (opts.length >= 4){
            try {
                dueDate = strDateFormat.parse(opts[3]);
            } catch (ParseException e) {}
        }

        itemQueryController.createNewItem(event.getAuthor().getIdLong(), dueDate,itemDescription);
    }
}