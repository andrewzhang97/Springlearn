package andrewjavastudy.demo.model;

import lombok.Data;

import java.util.List;

@Data
public class Questions {
    private Integer id;
    private String title;
    private String description;
    private String tags;
    private Long gmtCreate;
    private Long gmtModified;
    private Integer creatorId;
    private Integer viewCount;
    private Integer likeCount;

}
