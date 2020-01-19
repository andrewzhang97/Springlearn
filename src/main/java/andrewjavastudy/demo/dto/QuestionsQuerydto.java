package andrewjavastudy.demo.dto;

import lombok.Data;

@Data
public class QuestionsQuerydto {
    private String search;
    private Integer page;
    private Integer size;
}
