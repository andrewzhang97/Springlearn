package andrewjavastudy.demo.contoller;

import org.springframework.boot.web.servlet.error.ErrorController;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;

import javax.servlet.http.HttpServletRequest;
import java.awt.*;

@Controller

@RequestMapping("${server.error.path:${error.path:/error}}")
public class CustomizeErrorController implements ErrorController{
    @Override
    public String getErrorPath(){
        return "error";
    }

    @RequestMapping(produces = MediaType.TEXT_HTML_VALUE)
    public ModelAndView errorHtml(HttpServletRequest request, Model model){
        HttpStatus status=getStatus(request);
        if(status.is4xxClientError()){
            model.addAttribute("message","请求错了，换个姿势");
        }
        if(status.is5xxServerError()){
            model.addAttribute("message","服务器错了，工程师正在路上");//这个地方有BUG待修改
        }
        return new ModelAndView("error");
    }

    private HttpStatus getStatus(HttpServletRequest request) {
        Integer statuscode = (Integer) request.getAttribute("javax.servlet.error.status.code");
        if (statuscode == null) {
            return HttpStatus.INTERNAL_SERVER_ERROR;
        } try {
            return HttpStatus.valueOf(statuscode);
        }catch (Exception e){
            return HttpStatus.INTERNAL_SERVER_ERROR;
        }
    }
}
