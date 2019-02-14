package services;

import idexx.App;
import idexx.services.AlbumsService;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

@RunWith(SpringJUnit4ClassRunner.class)
@SpringBootTest(classes = App.class)
public class AlbumServiceTest {

    @Autowired
    private AlbumsService albumsService;

    @Test
    public void getAlbumsTest(){
        albumsService.getAlbums("Jackson");
    }
}
