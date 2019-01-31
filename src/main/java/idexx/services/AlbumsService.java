package idexx.services;

import com.fasterxml.jackson.databind.ObjectMapper;
import idexx.domain.Album;
import idexx.domain.ItunesWrapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.*;
import org.springframework.stereotype.Component;
import org.springframework.web.client.RestTemplate;

import java.io.IOException;
import java.util.Arrays;
import java.util.List;

@Component
public class AlbumsService {

    @Autowired
    private RestTemplate restTemplate;

    public List<Album> getAlbums(String searchKey){

        HttpHeaders headers = new HttpHeaders();
        headers.setAccept(Arrays.asList(MediaType.APPLICATION_JSON));
        HttpEntity<String> entity = new HttpEntity<>("parameters", headers);

        ResponseEntity<String> response = restTemplate.exchange("https://itunes.apple.com/search?term=jack+johnson&limit=5&entity=album", HttpMethod.GET, entity, String.class);
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
