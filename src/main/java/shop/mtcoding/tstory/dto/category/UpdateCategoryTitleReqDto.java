package shop.mtcoding.tstory.dto.category;

import lombok.Getter;
import lombok.Setter;

@Setter
@Getter
public class UpdateCategoryTitleReqDto {
	private Integer userId;
	private Integer categoryId;
	private String categoryTitle;
}

