package idexx.converters;

import idexx.domain.Book;
import idexx.dto.ItemDTO;
import org.springframework.core.convert.converter.Converter;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.List;


@Component
public class BookToItemDtoConverter
            implements Converter<List<Book>, List<ItemDTO>> {

        @Override
        public List<ItemDTO> convert(List<Book> bookList) {
            List<ItemDTO> itemList = new ArrayList<>();
            bookList.forEach(b -> itemList.add(new ItemDTO(b.getVolumeInfo().getTitle(),b.getVolumeInfo().getAuthors().toString().replaceAll("(^\\[|\\]$)", ""),"book")));

            return itemList;
        }
}
