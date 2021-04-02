package quotes.reader;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

public class APIResponse {
    int page;
    boolean last_page;
    Quote[] quote;

    public void setPage(int page) {
        this.page = page;
    }

    public void setLast_page(boolean last_page) {
        this.last_page = last_page;
    }

    public void setQuote(Quote[] quote) {
        this.quote = quote;
    }

    public int getPage() {
        return page;
    }

    public boolean isLast_page() {
        return last_page;
    }

    public Quote[] getQuote() {
        return quote;
    }

    public APIResponse(int page, boolean last_page, Quote[] quote) {
        this.page = page;
        this.last_page = last_page;
        this.quote = quote;
    }
}
