package quotes.reader;

import java.io.PrintStream;
import java.util.Arrays;
import java.util.LinkedList;

public class AnimalWriter {
    int width;
    PrintStream out;

    public AnimalWriter(PrintStream out) { this(out, 45); }

    public AnimalWriter(PrintStream out, int width) {
        this.out = out;
        this.width = width;
    }

    public static void main(String[] args) {
        AnimalWriter aw = new AnimalWriter(System.out, 10);
        String s = "Lorem ipsum dolor sit amet";
        aw.fishSay(s);
    }

    private String spaces(int n) {
        if (n <= 0) return "";
        char[] chars = new char[n];
        Arrays.fill(chars, ' ');
        return new String(chars);
    }

    private LinkedList<String> wrap(String text) {
        LinkedList<String> lines = new LinkedList<>();
        int start = 0; // Start is the first index of the next line
        int cur = 0;
        while(start + width < text.length()) {
            int next = text.indexOf(" ", cur);
            if (next == -1) next = text.length();
            if (next - cur > width) {
                cur = start + width;
                lines.add(text.substring(start, cur));
                start = cur;
            } else {
                if (next - start >= width) {
                    lines.add(text.substring(start, cur) + spaces(width - cur + start));
                    start = cur;
                }
                cur = next + 1;
            }
        }
        lines.add(text.substring(start) + spaces(width - text.length() + start));
        return lines;
    }

    private void printSpeechBubble(Iterable<String> lines) {
        String border = " " + "-".repeat(width + 2) + " ";
        out.println(border);
        for (String line : lines) out.printf("| %s |\n", line);
        out.println(border);
    }

    public void fishSay(String text) {
        LinkedList<String> lines = wrap(text);
        printSpeechBubble(lines);
        String fish =
                "      |\\   \\\\\\\\__     o\n" +
                "      | \\_/    o \\    o \n" +
                "      > _   (( <_  oo  \n" +
                "      | / \\__+___/      \n" +
                "      |/     |/";
        out.println(fish);
    }
}
