package me.kadse.meowbotframework.utils;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import lombok.Getter;

import java.io.File;
import java.lang.reflect.Field;
import java.lang.reflect.Modifier;

@Getter
public abstract class JsonConfig {

    private transient File file;
    private static Gson gson = new GsonBuilder().setPrettyPrinting().create();

    public JsonConfig(File file) {
        this.file = file;
    }

    public JsonConfig load() {
        try {

            if (!file.exists() || file.isDirectory()) {
                save();
                return this;
            }

            String content = FileUtils.readFile(file);
            JsonConfig config = gson.fromJson(content, this.getClass());

            Class<?> configClass = this.getClass();
            while (!configClass.equals(JsonConfig.class)) {
                for (Field field : configClass.getDeclaredFields()) {
                    if (Modifier.isTransient(field.getModifiers())) continue;
                    field.setAccessible(true);
                    try {
                        field.set(this, field.get(config));
                    } catch (IllegalAccessException e) {
                        e.printStackTrace();
                    }
                }
                configClass = configClass.getSuperclass();
            }
            return this;
        } catch (Exception e) {
            e.printStackTrace();
            return null;
        }
    }

    public JsonConfig save() {
        try {
            String content = gson.toJson(this, this.getClass());
            FileUtils.saveString(file, content);
            return this;
        } catch (Exception e) {
            e.printStackTrace();
            return null;
        }
    }

}
