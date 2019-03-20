package me.kadse.meowbotframework.utils;

import java.io.*;

public class FileUtils {
    public static void saveString(File file, String string) {
        try(PrintWriter out = new PrintWriter(file.getPath())){
            out.println(string);
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        }
    }

    public static String readFile(File file) {
        try(BufferedReader br = new BufferedReader(new FileReader(file))) {
            StringBuilder sb = new StringBuilder();
            String line = br.readLine();

            while (line != null) {
                sb.append(line).append(System.lineSeparator());
                line = br.readLine();
            }

            return sb.toString();
        } catch (IOException e) {
            e.printStackTrace();
            return null;
        }
    }
}
