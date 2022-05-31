package com.satwik.taskrbotjava.discordbot.commands;

import org.springframework.stereotype.Component;

import javax.annotation.PostConstruct;
import java.util.HashMap;

@Component
public class CommandDict {
    private HashMap<String, Command> commandDict = null;

    @PostConstruct
    void init() {
        this.commandDict = new HashMap<>();
    }

    public void addCommand(Command comm){
        this.commandDict.put(comm.getName(), comm);
    }

    public Command getCommand(String commandName){
        return this.commandDict.get(commandName);
    }
}
