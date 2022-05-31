package com.satwik.taskrbotjava;

import com.satwik.taskrbotjava.discordbot.DiscordService;
import com.satwik.taskrbotjava.discordbot.commands.Command;
import com.satwik.taskrbotjava.discordbot.commands.CommandBeans;
import com.satwik.taskrbotjava.discordbot.commands.CommandDispatcher;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.EnableAutoConfiguration;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.ApplicationContext;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;
import org.springframework.context.annotation.ComponentScan;

@SpringBootApplication
@EnableAutoConfiguration
public class TaskrBotJavaApplication implements CommandLineRunner {

	private final DiscordService discordService;

	private final CommandDispatcher commandDispatcher;
	private final CommandBeans commandBeans;

	@Autowired
	public TaskrBotJavaApplication(DiscordService discordService, CommandDispatcher commandDispatcher, CommandBeans commandBeans) {
		this.discordService = discordService;
		this.commandDispatcher = commandDispatcher;
		this.commandBeans = commandBeans;
	}

	public static void main(String[] args) {
		SpringApplication.run(TaskrBotJavaApplication.class, args);
	}

	@Override
	public void run(String... args) throws Exception {

		this.commandDispatcher.addCommands(new Command[] {
				commandBeans.showHub,
				commandBeans.addItem,
				commandBeans.show,
				commandBeans.all,
				commandBeans.myLists,
				commandBeans.newList,
				commandBeans.swap,
				commandBeans.swapId
		});

		this.discordService.addEventListener(this.commandDispatcher);
	}
}
