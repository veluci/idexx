package idexx.converters;

import idexx.domain.Album;
import idexx.dto.ItemDTO;
import org.springframework.core.convert.converter.Converter;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.List;

@Component
public class AlbumToItemDtoConverter
            implements Converter<List<Album>, List<ItemDTO>> {

        @Override
        public List<ItemDTO> convert(List<Album> albumList) {
            List<ItemDTO> itemList = new ArrayList<>();
            albumList.forEach(a -> itemList.add(new ItemDTO(a.getCollectionName(), a.getArtistName(),"album")));

            return itemList;
        }
}

