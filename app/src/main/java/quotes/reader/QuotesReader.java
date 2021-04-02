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

    public Quote getRandomQuotation() {
        List<Quote> quotes;
        try {
            quotes = api.getRandomQuotation();
        } catch (Exception e) {
            quotes = cache.getRandomQuotation();
        }
        return quotes.get(0);
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