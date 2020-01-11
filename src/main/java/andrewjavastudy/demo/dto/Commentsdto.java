package andrewjavastudy.demo.dto;

import lombok.Data;

@Data
public class Commentsdto {
    private Long parentId;
    private String content;
    private Integer type;

}
