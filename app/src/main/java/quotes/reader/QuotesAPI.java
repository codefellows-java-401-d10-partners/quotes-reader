package quotes.reader;

import com.google.gson.Gson;
import org.checkerframework.checker.units.qual.A;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.URL;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

public class QuotesAPI {
    static final String URL_ROOT = "https://favqs.com/api/";
    static final String APIKEY = System.getenv("API_KEY");
    static final Gson gson = new Gson();


    public QuotesAPI () {
    }

//     Query the quote of the day and return an array that contains just
    public static ArrayList<Quote> getRandomQuotation() {
        try {
            URL randomQuote = new URL(URL_ROOT+"qotd");
            HttpURLConnection randomConnection = (HttpURLConnection) randomQuote.openConnection();
            randomConnection.setRequestProperty("Content-Type","application/json");
            randomConnection.setRequestMethod("GET");
            InputStreamReader quoteStream = new InputStreamReader(randomConnection.getInputStream());

            BufferedReader readQuotes = new BufferedReader(quoteStream);

            String holderString = readQuotes.readLine();
            System.out.println(holderString);


            System.out.println(jsonInfo);

        } catch (MalformedURLException e) {
            e.printStackTrace();
            return null;
        } catch (IOException e) {
            e.printStackTrace();
            return null;
        }

        return null;
    }

    // Query the API to get an array list of quotations an author
//    public ArrayList<Quote> getQuotationsByAuthor(String author) {
//
//    }
//
    // Query the API to get an array list of quotations with a certain tag
//    public ArrayList<Quote> getQuotationsByTag(String tag) {
//
//    }
//
//    public ArrayList<Quote> getQuotationsByWord(String word) {
//
//    }
//
//    private Object makeQuery(String URLString) {
//
//    }
}
