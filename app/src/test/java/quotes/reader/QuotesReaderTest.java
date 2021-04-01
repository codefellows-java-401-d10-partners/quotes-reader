package quotes.reader;

import org.junit.Test;

import java.io.FileNotFoundException;
import java.util.NoSuchElementException;

import static org.junit.Assert.*;

public class QuotesReaderTest {
    static String testFilePath1 = "src/test/resources/testquotes1.json";
    static String testFilePath2 = "src/test/resources/testquotes2.json";

    @Test
    public void getQuotationGetsQuotes() throws FileNotFoundException {
        QuotesReader qr = new QuotesReader(testFilePath1);
        assertTrue("gets a Quote", qr.getQuotation() instanceof Quote);
        assertEquals("gets the one quote", "Charles Dickens", qr.getQuotation().author);
    }

    @Test
    public void getQuotationByAuthor() throws FileNotFoundException {
        QuotesReader qr = new QuotesReader(testFilePath2);
        assertEquals("gets the quote by author when author exists", "Marilyn Monroe",
                qr.getQuotation("Marilyn Monroe", null, null).author);
    }

    @Test
    public void getQuotationByAuthorWhenAuthorDoesNotExist() throws FileNotFoundException {
        QuotesReader qr = new QuotesReader(testFilePath2);
        assertThrows("throws when no quote by author exists",
                NoSuchElementException.class, () -> qr.getQuotation("Yoda", null, null));
    }

    @Test
    public void getQuotationByTag() throws FileNotFoundException {
        QuotesReader qr = new QuotesReader(testFilePath2);
        assertEquals("gets the quote by tag when it exists", "Marilyn Monroe",
                qr.getQuotation(null, "birthdays", null).author);
    }

    @Test
    public void getQuotationByTagWhenTagDoesNotExist() throws FileNotFoundException {
        QuotesReader qr = new QuotesReader(testFilePath2);
        assertThrows("throws when no quote with tag exists",
                NoSuchElementException.class, () -> qr.getQuotation(null, "star wars", null));
    }

    @Test
    public void getQuotationByContains() throws FileNotFoundException {
        QuotesReader qr = new QuotesReader(testFilePath2);
        assertEquals("gets the quote by tag when it exists", "Marilyn Monroe",
                qr.getQuotation(null,null, "Birthday").author);
    }

    @Test
    public void getQuotationByContainsWhenDoesNotExist() throws FileNotFoundException {
        QuotesReader qr = new QuotesReader(testFilePath2);
        assertThrows("throws when no quote contains word",
                NoSuchElementException.class, () -> qr.getQuotation(null, null, "taxes"));
    }
}