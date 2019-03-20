package me.kadse.meowbotframework.commands;

import lombok.Getter;

@Getter
public enum PermissionLevel {
    EVERYONE(0),
    MOD(3),
    ADMIN(10);

    private int level;

    PermissionLevel(int level) {
        this.level = level;
    }
}
