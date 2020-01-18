package andrewjavastudy.demo.contoller;

import andrewjavastudy.demo.cache.Tagscache;
import andrewjavastudy.demo.dto.Questionsdto;
import andrewjavastudy.demo.dto.Tagsdto;
import andrewjavastudy.demo.model.Questions;
import andrewjavastudy.demo.model.Users;
import andrewjavastudy.demo.service.QuestionsService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;

import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletRequest;

@Controller
public class PublishController {

    @Autowired
    private QuestionsService questionsService;


    @GetMapping("/publish")//get publish
    public String publish(Model model){
        model.addAttribute("tagsList", Tagscache.get());
        return "publish";
    }

    @PostMapping("/publish")
    public String doPublish(@RequestParam(value="title",required = false) String title,
                            @RequestParam(value="description",required = false) String description,
                            @RequestParam(value="tags",required = false) String tags,
                            @RequestParam(value ="id",required = false )Long id,
                            HttpServletRequest request,
                            Model model
                            ){
        model.addAttribute("title",title);
        model.addAttribute("description",description);
        model.addAttribute("tags",tags);
        model.addAttribute("tagsList", Tagscache.get());
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
        questions.setId(id);
        questionsService.createOrUpdate(questions);
        return "redirect:/";
    }

    @GetMapping("/publish/{id}")
    public String edit(@PathVariable(name="id") Long id,Model model){
        Questionsdto questions=questionsService.getById(id);
        model.addAttribute("title",questions.getTitle());
        model.addAttribute("description",questions.getDescription());
        model.addAttribute("tags",questions.getTags());
        model.addAttribute("id",questions.getId());
        model.addAttribute("tagsList", Tagscache.get());
        return "publish";
    }
    @PostMapping("/publish/{id}")
    public String editPublish(@RequestParam(value="title",required = false) String title,
                            @RequestParam(value="description",required = false) String description,
                            @RequestParam(value="tags",required = false) String tags,
                            @RequestParam(value ="id",required = false )Long id,
                            HttpServletRequest request,
                            Model model
    ){
        model.addAttribute("title",title);
        model.addAttribute("description",description);
        model.addAttribute("tags",tags);
        model.addAttribute("tagsList", Tagscache.get());
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
        questions.setId(id);
        questionsService.createOrUpdate(questions);
        return "redirect:/";
    }
//这段有问题 冗余代码 待解决

}
