package shop.mtcoding.tstory.dto.user;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class UpdateNicknameDto {
    private String nickname;
    private String nicknameUpdate;
}
