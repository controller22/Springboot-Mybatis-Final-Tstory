package shop.mtcoding.tstory.model.category;

import java.sql.Timestamp;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class Category {
	private Integer categoryId;
	private String categoryTitle;
	private Integer userId;
	private Timestamp updatedAt;
	private Timestamp createdAt;
}
