package quotes.reader;

import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;

public class AnimalWriter {
    static final int WIDTH = 45;

    public static LinkedList<String> wrap(String text) {
        String[] words = text.split(" ");
        LinkedList<String> lines = new LinkedList<>();
        int curLength = 0;
        String curLine = "";
        for( String word : words ) {
            if (word.length() + curLength >= WIDTH) {
                // Add the old line to the lines list
                curLine += " ".repeat(WIDTH - curLine.length());
                lines.add(curLine);
                // Start a new line
                curLine = "";
                curLength = 0;
            }
            // Add the word to the current line
            curLine += " " + word;
            curLength += 1 + word.length();
            curLine += " ".repeat(WIDTH - curLine.length());
        }
        // Add the last line to the lines list
        lines.add(curLine);
        return lines;
    }

    public static String join(List<String> lines) {
        return String.join("\n", lines);
    }

    public static void speechBubble(LinkedList<String> lines) {
        for (int i = 0; i < lines.size(); i++) {
            String line = lines.get(i);
            String newLine = (String) ("| " + line + " |");
            lines.set(i, newLine);
        }

        String border = "  " + "-".repeat(WIDTH) + "  ";
        lines.addFirst(border);
        lines.addLast(border);
    }

    public static String fishSay(String text) {
        LinkedList<String> lines = wrap(text);
        speechBubble(lines);
        /*
        String fish = "|\\   \\\\\\\\__     o\n" +
                "| \\_/    o \\    o \n" +
                "> _   (( <_  oo  \n" +
                "| / \\__+___/      \n" +
                "|/     |/";
        lines.add(fish);

         */
        return join(lines);
    }
}
