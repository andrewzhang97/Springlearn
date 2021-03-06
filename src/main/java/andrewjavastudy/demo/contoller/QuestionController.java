package andrewjavastudy.demo.contoller;

import andrewjavastudy.demo.dto.CommentsCreatedto;
import andrewjavastudy.demo.dto.Commentsdto;
import andrewjavastudy.demo.dto.Questionsdto;
import andrewjavastudy.demo.enums.CommentTypeEnums;
import andrewjavastudy.demo.model.Questions;
import andrewjavastudy.demo.service.CommentsService;
import andrewjavastudy.demo.service.QuestionsService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

import java.util.List;

@Controller
public class QuestionController {
    @Autowired
    private QuestionsService questionsService;
    @Autowired
    private CommentsService commentsService;

    @GetMapping("/questions/{id}")
    public String question(@PathVariable(name="id") Long id,Model model){
        Questionsdto questionsdto=questionsService.getById(id);
        List<Questionsdto> relatedQuestions=questionsService.selectRelated(questionsdto);
        List<Commentsdto> comments=commentsService.ListByTargetId(id, CommentTypeEnums.QUESTIONS);
        questionsService.incView(id);
        model.addAttribute("question",questionsdto);
        model.addAttribute("comments",comments);
        model.addAttribute("relatedQuestions",relatedQuestions);
        return "question";
    }
}
