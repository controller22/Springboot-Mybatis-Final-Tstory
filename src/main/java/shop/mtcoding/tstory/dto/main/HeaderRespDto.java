package shop.mtcoding.tstory.dto.main;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class HeaderRespDto {
	private Integer categoryId;
	private String categoryTitle;
	private Integer userId;
	private String username;
}
