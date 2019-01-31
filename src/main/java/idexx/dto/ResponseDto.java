package idexx.dto;

import idexx.domain.Album;
import idexx.domain.Book;
import lombok.Builder;
import lombok.Getter;
import lombok.Setter;

import java.util.List;

@Getter
@Setter
@Builder
public class ResponseDto {
    private List<Album> albumList;
    private List<Book> bookList;
}
