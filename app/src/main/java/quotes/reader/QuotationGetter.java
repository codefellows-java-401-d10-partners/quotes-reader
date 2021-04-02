package quotes.reader;

import java.util.ArrayList;

public interface QuotationGetter {
    public ArrayList<Quote> getRandomQuotation();
    public ArrayList<Quote> getQuotationsByAuthor(String author);
    public ArrayList<Quote> getQuotationsByTag(String tag);
    public ArrayList<Quote> getQuotationsByWord(String word);
}
