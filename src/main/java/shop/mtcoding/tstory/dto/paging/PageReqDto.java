package shop.mtcoding.tstory.dto.paging;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
public class PageReqDto {
    private Integer page;
    private Integer userId;
    private String keyword;
}
