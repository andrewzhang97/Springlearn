package andrewjavastudy.demo.dto;


import andrewjavastudy.demo.model.Users;
import lombok.Data;

@Data
public class Questionsdto {
    private Integer id;
    private String title;
    private String description;
    private String tags;
    private Long gmtCreate;
    private Long gmtModified;
    private Integer creatorId;
    private Integer viewCount;
    private Integer likeCount;
    private Users users;
}
