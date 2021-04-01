package quotes.reader;

import java.util.ArrayList;

public class Quote {
    public String author;
    public String text;
    public String likes;
    public ArrayList<String> tags;

    public Quote(String author, String text, String likes, ArrayList<String> tags) {
        this.author = author;
        this.text = text;
        this.likes = likes;
        this.tags = tags;
    }

    @Override
    public String toString() {
        return "Quote{" +
                "author='" + author + '\'' +
                ", text='" + text + '\'' +
                ", likes='" + likes + '\'' +
                ", tags=" + tags +
                '}';
    }

    public String prettyPrint() {
        return String.format("%s - %s", text, author);
        //AnimalWriter.speechBubble(lines);
        //return AnimalWriter.join(lines);
    }
}
