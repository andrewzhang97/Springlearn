package andrewjavastudy.demo.contoller;


import andrewjavastudy.demo.dto.Commentsdto;
import andrewjavastudy.demo.dto.Resultsdto;
import andrewjavastudy.demo.exception.CustomizeErrorCode;
import andrewjavastudy.demo.mapper.CommentsMapper;
import andrewjavastudy.demo.model.Comments;
import andrewjavastudy.demo.model.Users;
import andrewjavastudy.demo.service.CommentsService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import javax.servlet.http.HttpServletRequest;
import java.util.HashMap;
import java.util.Map;

@Controller
public class CommentController {

    @Autowired
    private CommentsService commentsService;

    @RequestMapping(value = "/comment",method = RequestMethod.POST)
    public Object post(@RequestBody Commentsdto commentsdto,
                       HttpServletRequest request){
//Json也算是Object格式
        Users users= (Users) request.getSession().getAttribute("users");
        if(users==null){
            return Resultsdto.errorOf(CustomizeErrorCode.NO_LOGIN);}
        Comments comments=new Comments();
        comments.setParentId(commentsdto.getParentId());
        comments.setContent(commentsdto.getContent());
        comments.setType(commentsdto.getType());
        comments.setGmtCreate(System.currentTimeMillis());
        comments.setGmtModified(System.currentTimeMillis());
        comments.setCommentator(users.getId());
        comments.setLikeCount(0L);
        commentsService.insert(comments);
        return Resultsdto.okOf();
    }
}

