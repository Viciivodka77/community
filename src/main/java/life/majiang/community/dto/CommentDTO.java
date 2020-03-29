package life.majiang.community.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.ToString;

@AllArgsConstructor
@NoArgsConstructor
@ToString
@Data
public class CommentDTO {
    private Long parentId;
    private String content;
    private Integer type;
}
