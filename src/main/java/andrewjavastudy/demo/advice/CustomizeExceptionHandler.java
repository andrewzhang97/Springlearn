package andrewjavastudy.demo.advice;

import andrewjavastudy.demo.dto.Resultsdto;
import andrewjavastudy.demo.exception.CustomizeErrorCode;
import andrewjavastudy.demo.exception.CustomizeException;
import com.alibaba.fastjson.JSON;
import org.springframework.http.HttpStatus;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.servlet.ModelAndView;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.PrintWriter;

@ControllerAdvice
public class CustomizeExceptionHandler {
    @ExceptionHandler(Exception.class)
    ModelAndView handle(HttpServletRequest request, Throwable e, Model model, HttpServletResponse response) {
        String contentType = request.getContentType();
        if ("application/json".equals(contentType)) {
            Resultsdto resultsdto;
            if (e instanceof CustomizeException) {
                resultsdto= Resultsdto.errorOf((CustomizeException) e);
            } else {
                resultsdto= Resultsdto.errorOf((CustomizeErrorCode.SYS_ERROR));
            }
            try{
                response.setContentType("application/json");
                response.setStatus(200);
                response.setCharacterEncoding("utf-8");
                PrintWriter writer=response.getWriter();//getWriter()返回一个PrintWriter object
                writer.write(JSON.toJSONString((resultsdto)));
                writer.close();
            } catch (IOException ioe) {
                ioe.printStackTrace();
            }
            return null;
        }
        //返回JSON
        else {
            //报错
            if (e instanceof CustomizeException) {
                model.addAttribute("message",e.getMessage());
            } else {
                model.addAttribute("message",CustomizeErrorCode.SYS_ERROR.getMessage());
            }

            return new ModelAndView("error");//ModelAndView 存储处理完后的结果数据，以及显示该数据的视图
        }
    }
}
