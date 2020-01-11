package andrewjavastudy.demo.exception;

public class CustomizeException extends RuntimeException {//如果不使用runtimeexception 我们需要在上一层进行性trycatch
    private String message;
    private Integer code;

    public CustomizeException(ICustomizeErrorCode errorCode){
        this.message=errorCode.getMessage();
        this.code=errorCode.getCode();
    }

    @Override
    public String getMessage(){
        return message;
    }


    public Integer getCode(){return code;}
}

