package andrewjavastudy.demo.contoller;

import andrewjavastudy.demo.dto.Paginationdto;
import andrewjavastudy.demo.mapper.UsersMapper;
import andrewjavastudy.demo.model.Users;
import andrewjavastudy.demo.service.QuestionsService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestParam;

import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletRequest;


@Controller
public class ProfileController {
    @Autowired
    private UsersMapper usersMapper;

    @Autowired
    private QuestionsService questionsService;

    @GetMapping("/profile/{action}")
    public String profile(@PathVariable(name = "action") String action, Model model, HttpServletRequest request,
                          @RequestParam(name ="page", defaultValue = "1") Integer page,
                          @RequestParam(name ="size", defaultValue = "5") Integer size){
        Users users=(Users)request.getSession().getAttribute("users");//从request的session中拿到users元素，再进行判断
        if(users==null){
            return "redirect:/";
        }
        if("questions".equals(action)){
            model.addAttribute("section","questions");
            model.addAttribute("sectionName","我的提问");

        }else if("replies".equals(action)){
            model.addAttribute("section","replies");
            model.addAttribute("sectionName","最新回复");
        }
        Paginationdto paginationdto=questionsService.list(users.getId(),page,size);//在questionService传入一个方法
        model.addAttribute("pagination", paginationdto);
        return "Profile";
    }
}
