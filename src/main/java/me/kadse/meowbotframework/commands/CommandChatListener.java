package me.kadse.meowbotframework.commands;

import me.kadse.meowbotframework.DiscordBot;
import me.kadse.meowbotframework.managers.CommandManager;
import org.javacord.api.event.message.MessageCreateEvent;
import org.javacord.api.listener.message.MessageCreateListener;

public class CommandChatListener implements MessageCreateListener {
    private DiscordBot discordBot;
    private CommandManager commandManager;

    public CommandChatListener(DiscordBot discordBot, CommandManager commandManager) {
        this.discordBot = discordBot;
        this.commandManager = commandManager;
    }

    @Override
    public void onMessageCreate(MessageCreateEvent messageCreateEvent) {
        String message = messageCreateEvent.getMessage().getContent();

        if(message.length() < 1)
            return;

        if(message.charAt(0) == discordBot.getCommandPrefix()) {
            String[] messageParts = message.split(" ");

            String command = messageParts[0].substring(1);

            String[] args;
            if(message.split(" ").length > 1) {
                args = message.replace(messageParts[0] + " ", "").split(" ");
            } else {
                args = new String[0];
            }

            if(commandManager.getCommandsMap().containsKey(command)) {
                    commandManager.getCommandsMap().get(command).execute(
                            messageCreateEvent.getChannel(),
                            messageCreateEvent.getMessageAuthor(),
                            args,
                            messageCreateEvent.getMessage().getMentionedUsers());
            }
        }
    }
}
