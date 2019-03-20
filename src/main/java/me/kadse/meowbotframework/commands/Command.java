package me.kadse.meowbotframework.commands;

import lombok.Getter;
import me.kadse.meowbotframework.DiscordBot;
import org.javacord.api.entity.channel.TextChannel;
import org.javacord.api.entity.message.MessageAuthor;
import org.javacord.api.entity.user.User;

import java.util.List;

@Getter
public abstract class Command {
    private String command;
    private String[] aliases;
    private String description;
    private PermissionLevel permissionLevel;

    public Command(String command, String[] aliases, String description, PermissionLevel permissionLevel) {
        this.command = command;
        this.aliases = aliases;
        this.description = description;
        this.permissionLevel = permissionLevel;

        DiscordBot.instance.getCommandManager().registerCommand(command, this);
        for (String alias : aliases) {
            DiscordBot.instance.getCommandManager().registerCommand(alias, this);
        }
    }

    public Command(String command, String[] aliases, String description) {
        this(command, aliases, description, PermissionLevel.EVERYONE);
    }

    public abstract void execute(TextChannel channel, MessageAuthor sender, String[] args, List<User> mentionedUsers);

    public void reply(TextChannel channel, String message) {
        channel.sendMessage(message);
    }
}
