package idexx.services;

import com.fasterxml.jackson.databind.ObjectMapper;
import idexx.domain.Album;
import idexx.domain.ItunesWrapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.*;
import org.springframework.stereotype.Component;
import org.springframework.web.client.RestTemplate;
import org.springframework.web.util.UriComponentsBuilder;

import java.io.IOException;
import java.util.Arrays;
import java.util.List;

@Component
public class AlbumsService {

    private final String URL = "https://itunes.apple.com/search";

    @Value("${items.size.limit}")
    private String sizeLimit;

    @Autowired
    private RestTemplate restTemplate;

    public List<Album> getAlbums(String searchKey){

        HttpHeaders headers = new HttpHeaders();
        headers.setAccept(Arrays.asList(MediaType.APPLICATION_JSON));
        HttpEntity<String> entity = new HttpEntity<>("parameters", headers);

        UriComponentsBuilder builder = UriComponentsBuilder.fromHttpUrl(URL)
                .queryParam("term", "jack+johnson")
                .queryParam("limit", "5")
                .queryParam("entity", "album");

        ResponseEntity<String> response = restTemplate.exchange(builder.toUriString(), HttpMethod.GET, entity, String.class);

        ItunesWrapper itunesWrapper = null;
        try {
            itunesWrapper = getResponse(response.getBody());
        } catch (IOException e) {
            e.printStackTrace();
        }

        return itunesWrapper.getResults();
    }

    private ItunesWrapper getResponse (String responseBody) throws IOException {
        ObjectMapper mapper = new ObjectMapper();
        return mapper.readValue(responseBody,ItunesWrapper.class);
    }
}
