package life.majiang.community.model;

import lombok.*;

@AllArgsConstructor
@NoArgsConstructor
@ToString
@Data
public class User {
    private Integer id;
    private String name;
    private String accountId;
    private String token;
    private Long gmtCreate;
    private Long gmtModified;
    private String avatarUrl;
    private String bio;
}
