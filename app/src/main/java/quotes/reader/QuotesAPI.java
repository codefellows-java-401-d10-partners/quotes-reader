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
    static final String authenticationHeader = String.format("Token token=\"%s\"", APIKEY);

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
//            System.out.println(holderString);
            //we HATE api's that are weirdly formatted
            QuoteAPIResponse curatedHolder = gson.fromJson(holderString,QuoteAPIResponse.class);
            ArrayList<Quote> quoteArray= new ArrayList<>();
            quoteArray.add(curatedHolder.quote);
            return quoteArray;

        } catch (MalformedURLException e) {
            System.out.println("Url is not right.");
            e.printStackTrace();
            return null;
        } catch (IOException e) {
            System.out.println("Server dead or bad request");
            e.printStackTrace();
            return null;
        }


    }

//     Query the API to get an array list of quotations an author
    public static ArrayList<Quote> getQuotationsByAuthor(String author) {
//        author.replace(" " ,"+");
        String encodedAuthor= URLEncoder.encode(author,StandardCharsets.UTF_8);

        try {
            URL authorURL = new URL(URL_ROOT+"quotes/?filter="+encodedAuthor+"&type=author");
            HttpURLConnection authorConnection = (HttpURLConnection) authorURL.openConnection();
            authorConnection.setRequestProperty("Content-Type","application/json");
            authorConnection.setRequestProperty("Authorization",authenticationHeader);
            authorConnection.setRequestMethod("GET");
            InputStreamReader authorStream= new InputStreamReader(authorConnection.getInputStream());
            BufferedReader readAuthor = new BufferedReader(authorStream);
            String authorStringHolder = readAuthor.readLine();
            QuotesAPIResponse curatedAuthor = gson.fromJson(authorStringHolder,QuotesAPIResponse.class);
            System.out.println(authorStringHolder);
            System.out.println(curatedAuthor.quotes);

            return curatedAuthor.quotes;
        } catch (MalformedURLException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
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
