package idexx.services;

import com.fasterxml.jackson.databind.ObjectMapper;
import idexx.domain.Book;
import idexx.domain.GoogleWrapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.*;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;
import org.springframework.web.util.UriComponentsBuilder;

import java.io.IOException;
import java.util.Arrays;
import java.util.List;

@Service
public class BooksService {

    private final String URL = "https://www.googleapis.com/books/v1/volumes";

    @Value("${items.size.limit}")
    private String sizeLimit;

    @Autowired
    private RestTemplate restTemplate;

    public List<Book> getBooks(String searchKey) {

        HttpHeaders headers = new HttpHeaders();
        headers.setAccept(Arrays.asList(MediaType.APPLICATION_JSON));
        HttpEntity<String> entity = new HttpEntity<>("parameters", headers);

        UriComponentsBuilder builder = UriComponentsBuilder.fromHttpUrl(URL)
                .queryParam("q", "Steve+Jobs")
                .queryParam("Results", "5");

        ResponseEntity<String> response = restTemplate.exchange(builder.toUriString(), HttpMethod.GET, entity, String.class);
        GoogleWrapper googleWrapper = null;
        try {
            googleWrapper = getResponse(response.getBody());
        } catch (IOException e) {
            e.printStackTrace();
        }

        return googleWrapper.getItems();
    }

    private GoogleWrapper getResponse (String responseBody) throws IOException {
        ObjectMapper mapper = new ObjectMapper();
        return mapper.readValue(responseBody,GoogleWrapper.class);
    }
}
