package andrewjavastudy.demo.exception;

public enum CustomizeErrorCode implements ICustomizeErrorCode {
    QUESTION_NOT_FOUND(2001,"你的问题消失在一片新大陆，要不换一个？"),
    TARGET_PARAM_NOT_FOUND(2002,"未选中任何问题回复"),
    NO_LOGIN(2003,"未登录"),
    SYS_ERROR(2004,"服务器去火星了"),
    TYPE_PARAM_WRONG(2005,"评论类型有误，不存在"),
    COMMENT_NOT_FOUND(2006,"评论不存在")
    ;
    @Override
    public String getMessage(){
        return message;
    }
    @Override
    public Integer getCode(){ return code;}

    private Integer code;
    private String message;
    CustomizeErrorCode(Integer code,String message){
        this.message=message;
        this.code=code;
    }
}
