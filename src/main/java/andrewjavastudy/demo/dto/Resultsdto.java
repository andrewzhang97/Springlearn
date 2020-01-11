package andrewjavastudy.demo.dto;

import andrewjavastudy.demo.exception.CustomizeErrorCode;
import andrewjavastudy.demo.exception.CustomizeException;
import lombok.Data;

@Data
public class Resultsdto {
    private Integer code;
    private String message;

    public static Resultsdto errorOf(Integer code,String message){
        Resultsdto resultsdto=new Resultsdto();
        resultsdto.setCode(code);
        resultsdto.setMessage(message);
        return resultsdto;
    }
    public static Resultsdto errorOf(CustomizeErrorCode errorCode){
        return errorOf(errorCode.getCode(),errorCode.getMessage());
    }

    public static Resultsdto okOf(){
        Resultsdto resultsdto=new Resultsdto();
        resultsdto.setCode(200);
        resultsdto.setMessage("OK");
        return resultsdto;
    }
    public static Resultsdto errorOf(CustomizeException e){
        return errorOf(e.getCode(),e.getMessage());
    }
}
