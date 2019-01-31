package idexx.controllers;

import idexx.dto.ResponseDto;
import idexx.services.AlbumsService;
import idexx.services.BooksService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class BooksAndAlbumsController {

    @Autowired
    private AlbumsService albumsService;

    @Autowired
    private BooksService booksService;

    @GetMapping(path="get")
    public @ResponseBody ResponseDto getBooksAndAlbums (String searchKey){

        return ResponseDto.builder()
                .albumList(albumsService.getAlbums(searchKey))
                .bookList(booksService.getBooks(searchKey))
                .build();
    }
}
