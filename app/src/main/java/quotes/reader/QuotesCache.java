package quotes.reader;

import com.google.gson.Gson;

import java.io.*;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class QuotesCache {
    String localCachePath;
    Map<Integer, Quote> quotesMap = new HashMap<>();
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
                System.out.println("Adding quote to quotes map");
                quotesMap.put(q.id, q);
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
                    if (hasEntries) writer.append(",\n");
                    else writer.append("\n");
                    gson.toJson(q, Quote.class, writer);
                }
            }
            writer.append("\n]");
        }
    }

    public static ArrayList<Quote> getRandomQuotation() {
    }

    public ArrayList<Quote> getQuotationsByAuthor(String author) {

    }

    public ArrayList<Quote> getQuotationsByTag(String tag) {

    }

    public ArrayList<Quote> getQuotationsByWord(String word) {

    }
}
