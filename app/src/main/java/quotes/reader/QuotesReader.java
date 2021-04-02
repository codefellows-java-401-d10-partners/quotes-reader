package quotes.reader;

import com.google.gson.Gson;

import java.io.*;
import java.util.*;

public class QuotesReader {
    String localCachePath;
    BufferedReader br;
    Gson gson = new Gson();
    Random rand = new Random();
    List<Quote> quotes;
    QuotesAPI api = new QuotesAPI();
    Map<Integer, Quote> quotesMap = new HashMap<>();
    Map<String, ArrayList<Quote>> authors = new HashMap<>();

    /**
     * @param localCachePath
     */
    public QuotesReader(String localCachePath) throws IOException {
        this.localCachePath = localCachePath;
        File localCacheFile = new File(localCachePath);
        if (localCacheFile.exists()) {
            br = new BufferedReader(new FileReader(localCachePath));
            // load file into quotesArray
            Quote[] quotesArray = gson.fromJson(br, Quote[].class);
            // insert quotes into our hashArray
            for ( Quote q : quotesArray ) {
                quotesMap.put(q.id, q);
            }
            br.close();
        } else {
            // Create a file initialized as an empty JSON array
            BufferedWriter bw = new BufferedWriter(new FileWriter(localCacheFile));
            bw.append("[\n]");
            bw.close();
        }
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

    public void cacheLocal(Quote quote) {
        // Check to see if our quote exists in our localCache list
        // If it doesn't then save the quote to our localCachePath file
    }

    public void cacheLocal(ArrayList<Quote> quotes) {
        // Check to see if our quote exists in our localCache list
        // If it doesn't then save the quote to our localCachePath file
        //addToJSONArray(String text);


    }

    private void addToJSONArray(String text) {
        try (RandomAccessFile f = new RandomAccessFile(localCachePath, "rw")) {
            f.setLength(f.length() - 1);
        } catch (IOException e) {
            e.printStackTrace();
        }

        try (PrintWriter writer = new PrintWriter(new FileOutputStream(localCachePath, true))) {
            writer.append(text);
            writer.append("]");
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        }
    }
}