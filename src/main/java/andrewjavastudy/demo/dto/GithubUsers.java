package andrewjavastudy.demo.dto;

import lombok.Data;

@Data
public class GithubUsers {
    private String name;
    private long id;
    private String bio;
    private String avatarUrl;
}
