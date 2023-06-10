package neordinary.hackathon.uteam.dto.userHashtag;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Getter;

import java.util.List;

@Getter
public class UserHashtagRequest {

    @Schema(description = "사용자의 해시태그 목록", example = "[\"#친환경\", \"#제로웨이스트\", \"#저렴한\"]")
    private List<String> tags;
}
