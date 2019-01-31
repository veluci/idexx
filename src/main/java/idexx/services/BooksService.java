package idexx.services;

import com.fasterxml.jackson.databind.ObjectMapper;
import idexx.domain.Book;
import idexx.domain.GoogleWrapper;
import idexx.domain.ItunesWrapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.*;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import java.io.IOException;
import java.util.Arrays;
import java.util.List;

@Service
public class BooksService {

    @Autowired
    private RestTemplate restTemplate;

    public List<Book> getBooks(String searchKey) {

        HttpHeaders headers = new HttpHeaders();
        headers.setAccept(Arrays.asList(MediaType.APPLICATION_JSON));
        HttpEntity<String> entity = new HttpEntity<>("parameters", headers);

        ResponseEntity<String> response = restTemplate.exchange("https://www.googleapis.com/books/v1/volumes?q=Steve+Jobs&maxResults=5", HttpMethod.GET, entity, String.class);
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
