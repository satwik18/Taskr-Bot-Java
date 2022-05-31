package com.satwik.taskrbotjava.discordbot.commands.util;

import com.github.freva.asciitable.AsciiTable;
import com.satwik.taskrbotjava.dataaccess.document.Items;
import com.satwik.taskrbotjava.dataaccess.document.Lists;
import com.satwik.taskrbotjava.dataaccess.repository.UsersRepository;
import net.dv8tion.jda.api.events.message.MessageReceivedEvent;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.text.SimpleDateFormat;
import java.util.List;

@Component
public class MessageUtil {
    private final static String[] ITEMS_HEADER = {"ID", "User", "DueDate", "Description", "Status"};
    private final static String[] LISTS_HEADER = {"ID", "Name", "Owner", "Sharing"};

    private UsersRepository usersRepository;

    @Autowired
    public MessageUtil(UsersRepository usersRepository) {
        this.usersRepository = usersRepository;
    }

    public void dispatchMessage(MessageReceivedEvent event, String message){
        event.getChannel().sendMessage(message).queue();
    }

    public String formatList(String listName, List<Items> items){
        String[][] data = (String[][]) items.stream().map(this::getStringArrayFromItem).toArray();
        return listName + "\n" +
                AsciiTable.getTable(ITEMS_HEADER, data);
    }

    public String formatListOfLists(List<Lists> lists){
        String[][] data = (String[][]) lists.stream().map(this::getStringArrayFromLists).toArray();
        return AsciiTable.getTable(LISTS_HEADER, data);
    }

    private String[] getStringArrayFromItem(Items item){
        SimpleDateFormat strDateFormat=new SimpleDateFormat("dd/MM/yyyy");
        return new String[]{
                item.getItemID().toString(),
                usersRepository.findById(item.getUser()).get(0).getName(),
                strDateFormat.format(item.getDueDate()),
                item.getDescription(),
                item.getStatus().name()
        };
    }
    private String[] getStringArrayFromLists(Lists list){
        return new String[]{
                list.getListID().toString(),
                list.getName(),
                usersRepository.findById(list.getOwner()).get(0).getName(),
                String.valueOf(list.isOpen())
        };
    }
}
