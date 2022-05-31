package com.satwik.taskrbotjava.discordbot.commands;

import com.satwik.taskrbotjava.discordbot.SwapId;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component
public class CommandBeans {

    public ShowHub showHub;
    public AddItem addItem;
    public Show show;
    public All all;
    public MyLists myLists;
    public NewList newList;
    public Swap swap;
    public SwapId swapId;

    @Autowired
    public CommandBeans(ShowHub showHub,
                        AddItem addItem,
                        Show show,
                        All all,
                        MyLists myLists,
                        NewList newList,
                        Swap swap,
                        SwapId swapId){
        this.showHub = showHub;
        this.addItem = addItem;
        this.show = show;
        this.all = all;
        this.myLists = myLists;
        this.newList = newList;
        this.swap = swap;
        this.swapId = swapId;
    }
}
