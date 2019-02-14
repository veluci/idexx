package idexx.converters;

import idexx.domain.Album;
import idexx.dto.ItemDTO;
import org.springframework.core.convert.converter.Converter;
import org.springframework.stereotype.Component;

import java.util.List;
import java.util.stream.Collectors;

@Component
public class AlbumToItemDtoConverter
            implements Converter<List<Album>, List<ItemDTO>> {

        @Override
        public List<ItemDTO> convert(List<Album> albumList) {
            return albumList.stream()
                    .map(a -> new ItemDTO(a.getCollectionName(), a.getArtistName(),"album"))
                    .collect(Collectors.toList());
        }
}

