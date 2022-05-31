package com.satwik.taskrbotjava.discordbot.commands;

import net.dv8tion.jda.api.events.message.MessageReceivedEvent;

public interface Command {
    String getName();
    default String getUsage() {
        return "Usage: \n" +
                getName();
    }
    void trigger(String[] args, MessageReceivedEvent event);
}
