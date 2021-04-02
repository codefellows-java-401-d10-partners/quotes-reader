package quotes.reader;

import com.google.gson.Gson;

import java.io.*;
import java.util.*;

public class QuotesCache {
    String localCachePath;
    Map<Integer, Quote> quotesMap = new HashMap<>();
    List<Integer> quotesIds = new ArrayList<>();
    Random rand = new Random();
    Gson gson = new Gson();

    public QuotesCache(String localCachePath) throws IOException {
        this.localCachePath = localCachePath;
        File localCacheFile = new File(localCachePath);
        if (localCacheFile.exists()) {
            BufferedReader br = new BufferedReader(new FileReader(localCachePath));
            // load file into quotesArray
            System.out.println("Quotes cache exist.");
            Quote[] quotesArray = gson.fromJson(br, Quote[].class);
            // insert quotes into our hashArray
            for (Quote q : quotesArray) {
                quotesMap.put(q.id, q);
                quotesIds.add(q.id);
            }
        } else {
            System.out.println("File does not exist.");
            // Create a file initialized as an empty JSON array
            BufferedWriter bw = new BufferedWriter(new FileWriter(localCacheFile));
            bw.append("[\n]");
            bw.close();
        }
    }

    public void cacheLocal(List<Quote> quotes) throws IOException {
        boolean hasEntries = false;
        // Delete the last two characters (a newline and a square bracket)
        try (RandomAccessFile f = new RandomAccessFile(localCachePath, "rw")) {
            if (f.length() > 4) hasEntries = true;
            f.setLength(f.length() - 2);
        }

        try (FileWriter writer = new FileWriter(localCachePath, true)) {
            for (Quote q : quotes) {
                // Check to see if our quote exists in our localCache list
                // If it doesn't then save the quote to our localCachePath file
                if (!quotesMap.containsKey(q.id)) {
                    quotesMap.put(q.id, q);
                    quotesIds.add(q.id);
                    if (hasEntries) writer.append(",\n");
                    else writer.append("\n");
                    gson.toJson(q, Quote.class, writer);
                }
            }
            writer.append("\n]");
        }
    }

    public ArrayList<Quote> getRandomQuotation() {
        int idx = rand.nextInt(quotesIds.size());
        ArrayList<Quote> result = new ArrayList<>();
        int randomId = quotesIds.get(idx);
        result.add(quotesMap.get(randomId));
        return result;
    }

    public ArrayList<Quote> getQuotationsByAuthor(String author) {
        ArrayList<Quote> result = new ArrayList<>();
        for (Map.Entry<Integer, Quote> e : quotesMap.entrySet()) {
            Quote q = e.getValue();
            if (q.author.equals(author)) result.add(q);
        }
        return result;
    }

    public ArrayList<Quote> getQuotationsByTag(String tag) {
        ArrayList<Quote> result = new ArrayList<>();
        for (Map.Entry<Integer, Quote> e : quotesMap.entrySet()) {
            Quote q = e.getValue();
            if (q.tags.contains(tag.toLowerCase(Locale.ROOT))) result.add(q);
        }
        return result;
    }

    public ArrayList<Quote> getQuotationsByWord(String word) {
        ArrayList<Quote> result = new ArrayList<>();
        String lowercaseWord = word.toLowerCase(Locale.ROOT);
        String capitalized = word.substring(0, 1).toUpperCase() + word.substring(1);
        for (Map.Entry<Integer, Quote> e : quotesMap.entrySet()) {
            Quote q = e.getValue();
            if (q.body.contains(word.toLowerCase(Locale.ROOT))) result.add(q);
        }
        return result;
    }
}
