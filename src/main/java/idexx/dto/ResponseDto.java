package idexx.dto;

import lombok.Builder;
import lombok.Getter;
import lombok.Setter;

import java.util.List;

@Getter
@Setter
@Builder
public class ResponseDto {
    private List<ItemDTO> albumList;
    private List<ItemDTO> bookList;
}
