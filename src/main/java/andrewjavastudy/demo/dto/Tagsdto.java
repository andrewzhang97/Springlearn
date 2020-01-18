package andrewjavastudy.demo.dto;

import lombok.Data;

import java.util.List;

@Data
public class Tagsdto {
    private String categoryName;
    private List<String> tags;
}
