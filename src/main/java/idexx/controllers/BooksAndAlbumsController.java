package idexx.controllers;

import idexx.converters.AlbumToItemDtoConverter;
import idexx.converters.BookToItemDtoConverter;
import idexx.dto.ResponseDto;
import idexx.services.AlbumsService;
import idexx.services.BooksService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

@CrossOrigin(origins = "https://albums-and-books-fe.herokuapp.com:443")
@RestController
public class BooksAndAlbumsController {

    @Autowired
    private AlbumsService albumsService;

    @Autowired
    private BooksService booksService;

    @Autowired
    private AlbumToItemDtoConverter albumConverter;

    @Autowired
    private BookToItemDtoConverter bookToItemDtoConverter;

    @GetMapping(path="get")
    public @ResponseBody ResponseDto getBooksAndAlbums (@RequestParam("search") String searchKey){

        return ResponseDto.builder()
                .albumList(albumConverter.convert(albumsService.getAlbums(searchKey)))
                .bookList(bookToItemDtoConverter.convert(booksService.getBooks(searchKey)))
                .build();
    }
}
