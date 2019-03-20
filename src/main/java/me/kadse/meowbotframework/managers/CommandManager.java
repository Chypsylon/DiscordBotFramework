package me.kadse.meowbotframework.managers;

import lombok.Getter;
import me.kadse.meowbotframework.commands.Command;

import java.util.HashMap;

@Getter
public class CommandManager {
    private HashMap<String, Command> commandsMap;

    public CommandManager() {
        this.commandsMap = new HashMap<>();
    }

    public void registerCommand(String command, Command c) {
        this.commandsMap.put(command, c);
    }
}
