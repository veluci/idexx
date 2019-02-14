package idexx.services;

import idexx.domain.Book;
import idexx.domain.GoogleWrapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;
import org.springframework.web.util.UriComponentsBuilder;

import java.util.List;

@Service
public class BooksService {

    private final String URL = "https://www.googleapis.com/books/v1/volumes";

    @Value("${items.size.limit}")
    private String sizeLimit;

    @Autowired
    private RestTemplate customRestTemplate;

    public List<Book> getBooks(String searchKey) {

        UriComponentsBuilder builder = UriComponentsBuilder.fromHttpUrl(URL)
                .queryParam("q", searchKey)
                .queryParam("maxResults", sizeLimit);

        GoogleWrapper googleWrapper  = customRestTemplate.getForObject(builder.toUriString(), GoogleWrapper.class);

        return googleWrapper.getItems();
    }
}
