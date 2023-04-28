package shop.mtcoding.tstory.dto.category;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class InsertCategoryTitleReqDto {
    
    private Integer userId;
	private Integer categoryId;
	private String categoryTitle;
}
