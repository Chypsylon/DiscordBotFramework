package me.kadse.meowbotframework;

import lombok.Getter;
import me.kadse.meowbotframework.commands.CommandChatListener;
import me.kadse.meowbotframework.managers.CommandManager;
import org.javacord.api.DiscordApi;
import org.javacord.api.DiscordApiBuilder;

@Getter
public class DiscordBot {
    public static DiscordBot instance;

    private String token;
    private char commandPrefix;
    private DiscordApi discordApi;

    private CommandManager commandManager;

    public DiscordBot(String token, char commandPrefix) {
        this.token = token;
        this.commandPrefix = commandPrefix;

        this.discordApi = new DiscordApiBuilder().setToken(token).login().join();
        this.commandManager = new CommandManager();

        discordApi.addMessageCreateListener(new CommandChatListener(this, this.commandManager));

        DiscordBot.instance = this;
    }
}
