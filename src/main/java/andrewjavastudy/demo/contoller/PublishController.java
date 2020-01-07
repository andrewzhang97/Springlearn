package andrewjavastudy.demo.contoller;

import andrewjavastudy.demo.mapper.QuestionsMapper;
import andrewjavastudy.demo.mapper.UsersMapper;
import andrewjavastudy.demo.model.Questions;
import andrewjavastudy.demo.model.Users;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;

import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletRequest;

@Controller
public class PublishController {
    @Autowired
    private QuestionsMapper questionsMapper;


    @GetMapping("/publish")//get publish
    public String publish(){
        return "publish";
    }

    @PostMapping("/publish")
    public String doPublish(@RequestParam("title") String title,
                            @RequestParam("description") String description,
                            @RequestParam("tags") String tags,
                            HttpServletRequest request,
                            Model model
                            ){
        Users users=(Users)request.getSession().getAttribute("users");//从request的session中拿到users元素，再进行判断
        if (users==null){
            model.addAttribute("error","didn't login");
            return "publish";
        }
        Questions questions=new Questions();
        questions.setTitle(title);
        questions.setDescription(description);
        questions.setTags(tags);
        questions.setCreatorId(users.getId());
        questions.setGmtCreate(System.currentTimeMillis());
        questions.setGmtModified(questions.getGmtCreate());
        questionsMapper.create(questions);
        return "redirect:/";
    }
}
