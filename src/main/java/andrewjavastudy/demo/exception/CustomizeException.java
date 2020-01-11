package andrewjavastudy.demo.exception;

public class ICustomizeException extends RuntimeException {//如果不使用runtimeexception 我们需要在上一层进行性trycatch
    private String message;

    public ICustomizeException(String message){
        this.message=message;
    }

    @Override
    public
}
