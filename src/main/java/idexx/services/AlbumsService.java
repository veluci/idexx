package idexx.services;

import idexx.domain.Album;
import idexx.domain.ItunesWrapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;
import org.springframework.web.client.RestTemplate;
import org.springframework.web.util.UriComponentsBuilder;

import java.util.List;

@Component
public class AlbumsService {

    private final String URL = "https://itunes.apple.com/search";

    @Value("${items.size.limit}")
    private String sizeLimit;

    @Autowired
    private RestTemplate customRestTemplate;

    public List<Album> getAlbums(String searchKey){

        UriComponentsBuilder builder = UriComponentsBuilder.fromHttpUrl(URL)
                .queryParam("term", searchKey)
                .queryParam("limit", sizeLimit)
                .queryParam("entity", "album");

        ItunesWrapper itunesWrapper = customRestTemplate.getForObject(builder.toUriString(), ItunesWrapper.class);

        return itunesWrapper.getResults();
    }
}
