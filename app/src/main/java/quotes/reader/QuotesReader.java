package quotes.reader;

import com.google.gson.Gson;
import org.checkerframework.checker.units.qual.A;

import java.io.*;
import java.util.*;

public class QuotesReader {
    BufferedReader br;
    Gson gson = new Gson();
    Random rand = new Random();
    List<Quote> quotes;
    Map<String, ArrayList<Quote>> authors = new HashMap<>();

    /**
     * @param inputFilePath
     */
    public QuotesReader(String inputFilePath) throws FileNotFoundException {
        br = new BufferedReader(new FileReader(inputFilePath));
        Quote[] quotesArray = gson.fromJson(br, Quote[].class);
        quotes = Arrays.asList(quotesArray);
    }

    public Quote getQuotation(String author, String tag, String word) throws NoSuchElementException {
        ArrayList<Quote> holder = new ArrayList<>();

        for(Quote q : quotes){
            if (author != null && !q.author.equals(author))
                continue;
            if (word != null
                    && !q.text.toLowerCase(Locale.ROOT).contains(word.toLowerCase(Locale.ROOT)))
                continue;
            if (tag != null && !q.tags.contains(tag))
                continue;
            holder.add(q);
        }

        if (holder.size() == 0) throw new NoSuchElementException("No quotes found");

        int idx = rand.nextInt(holder.size());
        return holder.get(idx);
    }

    public Quote getQuotation() {
        int index = rand.nextInt(quotes.size());
        return quotes.get(index);
    }
}