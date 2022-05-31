package com.satwik.taskrbotjava.discordbot;

import net.dv8tion.jda.api.JDA;
import net.dv8tion.jda.api.JDABuilder;
import net.dv8tion.jda.api.entities.ISnowflake;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

import javax.annotation.PostConstruct;
import javax.annotation.PreDestroy;
import javax.security.auth.login.LoginException;

@Service
public class DiscordService {

    private JDA jda;

    @Value("${jda.discord.token}")
    private String token;

    @PostConstruct
    void init() throws LoginException {
        this.jda = JDABuilder.createDefault(token).build();
    }

    @PreDestroy
    void preDestroy() {
        this.jda.shutdown();
    }

    public void addEventListener(Object listener) {
        this.jda.addEventListener(listener);
    }

    public JDA getJDA() {
        return this.jda;
    }
}
