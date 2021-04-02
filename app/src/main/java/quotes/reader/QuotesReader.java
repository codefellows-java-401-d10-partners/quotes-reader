package quotes.reader;

import com.google.gson.Gson;

import java.io.*;
import java.util.*;

public class QuotesReader {
    BufferedReader br;
    Random rand = new Random();
    QuotesAPI api = new QuotesAPI();
    QuotesCache cache;

    public QuotesReader(String localCachePath) throws IOException {
        cache = new QuotesCache(localCachePath);
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

    public Quote getRandomQuotationByAuthor(String author) {
        List<Quote> quotes;
        try {
            quotes = api.getQuotationsByAuthor(author);
        } catch (Exception e) {
            quotes = cache.getQuotationsByAuthor(author);
        }
        int idx = rand.nextInt(quotes.size());
        return quotes.get(idx);
    }

    public Quote getRandomQuotationByTag(String tag) {
        List<Quote> quotes;
        try {
            quotes = api.getQuotationsByTag(tag);
        } catch (Exception e) {
            quotes = cache.getQuotationsByTag(tag);
        }
        int idx = rand.nextInt(quotes.size());
        return quotes.get(idx);
    }

    public Quote getRandomQuotationByWord(String word) {
        List<Quote> quotes;
        try {
            quotes = api.getQuotationsByWord(word);
        } catch (Exception e) {
            quotes = cache.getQuotationsByWord(word);
        }
        int idx = rand.nextInt(quotes.size());
        return quotes.get(idx);
    }
}