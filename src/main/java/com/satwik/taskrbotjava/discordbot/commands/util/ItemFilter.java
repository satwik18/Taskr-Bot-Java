package com.satwik.taskrbotjava.discordbot.commands.util;

import com.satwik.taskrbotjava.dataaccess.document.Items;
import com.satwik.taskrbotjava.dataaccess.document.util.TaskStatus;

import java.util.List;
import java.util.stream.Collectors;

public class ItemFilter {
    public static List<Items> filterDone(List<Items> items){
        return items.stream().filter(item -> item.getStatus() != TaskStatus.DONE).collect(Collectors.toList());
    }
}
