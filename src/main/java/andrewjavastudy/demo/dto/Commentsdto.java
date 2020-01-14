package andrewjavastudy.demo.dto;

import andrewjavastudy.demo.model.Users;
import lombok.Data;

@Data

public class Commentsdto {
    private Long id;

    private Long parentId;


    private Integer type;


    private Long commentator;


    private Long gmtCreate;

    private Long gmtModified;


    private Long likeCount;


    private String content;

    private Users users;
}


