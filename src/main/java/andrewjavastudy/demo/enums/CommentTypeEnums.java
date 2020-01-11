package andrewjavastudy.demo.contoller;

public enum  CommentTypeEnums {
    QUESTIONS(1),
    COMMENTS(2);
    private Integer type;

    CommentTypeEnums(Integer type) {
        this.type = type;
    }

    public Integer getType() {
        return type;
    }
}
