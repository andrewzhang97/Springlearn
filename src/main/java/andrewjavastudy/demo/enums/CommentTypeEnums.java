package andrewjavastudy.demo.enums;

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

    public static boolean isExist(Integer type) {
        for(CommentTypeEnums commentTypeEnums:CommentTypeEnums.values()){
            if(commentTypeEnums.getType()==type){
                return true;
            }
        }
        return false;
    }
}
