package shop.mtcoding.tstory.model.visit;

import java.sql.Timestamp;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class Visit {
    private Integer visitId;
	private Integer userId;
	private Integer totalCount;
	private Timestamp updatedAt;
	private Timestamp createdAt;
}
