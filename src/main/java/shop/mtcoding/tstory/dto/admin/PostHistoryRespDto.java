package shop.mtcoding.tstory.dto.admin;

import java.sql.Timestamp;

import lombok.Getter;
import lombok.Setter;

@Setter
@Getter
public class PostHistoryRespDto {
	private Integer number;
	private String postTitle;
	private String nickname;
	private Timestamp createdAt;
}
