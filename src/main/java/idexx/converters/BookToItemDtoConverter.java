package idexx.converters;

import idexx.domain.Book;
import idexx.dto.ItemDTO;
import org.springframework.core.convert.converter.Converter;
import org.springframework.stereotype.Component;

import java.util.List;
import java.util.stream.Collectors;


@Component
public class BookToItemDtoConverter
            implements Converter<List<Book>, List<ItemDTO>> {

        @Override
        public List<ItemDTO> convert(List<Book> bookList) {
            return bookList.stream()
                    .map(b -> new ItemDTO(b.getVolumeInfo().getTitle(),
                            b.getVolumeInfo().getAuthors().toString().replaceAll("(^\\[|\\]$)", ""),
                            "book"))
                    .collect(Collectors.toList());
        }
}
