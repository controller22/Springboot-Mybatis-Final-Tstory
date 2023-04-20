package shop.mtcoding.tstory.model.love;

import java.sql.Timestamp;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class Love {
    private Integer loveId;
	private Integer postId;
	private Integer userId;
	private Timestamp updatedAt;
	private Timestamp createdAt;
}
