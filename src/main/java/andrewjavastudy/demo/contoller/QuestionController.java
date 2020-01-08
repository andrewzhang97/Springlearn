package andrewjavastudy.demo.contoller;

import andrewjavastudy.demo.dto.Questionsdto;
import andrewjavastudy.demo.mapper.QuestionsMapper;
import andrewjavastudy.demo.service.QuestionsService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

@Controller
public class QuestionController {
    @Autowired
    private QuestionsService questionsService;

    @GetMapping("/questions/{id}")
    public String question(@PathVariable(name="id") Integer id,Model model){
        Questionsdto questionsdto=questionsService.getById(id);
        model.addAttribute("question",questionsdto);
        return "question";
    }
}
