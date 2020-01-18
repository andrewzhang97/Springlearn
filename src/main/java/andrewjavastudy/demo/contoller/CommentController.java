package andrewjavastudy.demo.contoller;


import andrewjavastudy.demo.dto.CommentsCreatedto;
import andrewjavastudy.demo.dto.Commentsdto;
import andrewjavastudy.demo.dto.Resultsdto;
import andrewjavastudy.demo.enums.CommentTypeEnums;
import andrewjavastudy.demo.exception.CustomizeErrorCode;
import andrewjavastudy.demo.mapper.CommentsMapper;
import andrewjavastudy.demo.model.Comments;
import andrewjavastudy.demo.model.Users;
import andrewjavastudy.demo.service.CommentsService;
import org.h2.util.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletRequest;
import java.util.List;

@Controller
public class CommentController {

    @Autowired
    private CommentsService commentsService;

    @ResponseBody//这样不需要去查找templete是否存在comment.html
    @RequestMapping(value = "/comment",method = RequestMethod.POST)
    public Object post(@RequestBody CommentsCreatedto commentsCreatedto,
                       HttpServletRequest request){
//Json也算是Object格式
        Users users= (Users) request.getSession().getAttribute("users");
        if(users==null){
            return Resultsdto.errorOf(CustomizeErrorCode.NO_LOGIN);
        }
        if(commentsCreatedto==null|| org.apache.commons.lang3.StringUtils.isBlank(commentsCreatedto.getContent())){
            //一个新的方法 不用重新去验证两次是否为空
            return Resultsdto.errorOf(CustomizeErrorCode.COMMENT_NOT_FOUND);
        }

        Comments comments=new Comments();
        comments.setParentId(commentsCreatedto.getParentId());
        comments.setContent(commentsCreatedto.getContent());
        comments.setType(commentsCreatedto.getType());
        comments.setGmtCreate(System.currentTimeMillis());
        comments.setGmtModified(System.currentTimeMillis());
        comments.setCommentator(users.getId());
        comments.setLikeCount(0L);
        commentsService.insert(comments);
        return Resultsdto.okOf();
    }
    @ResponseBody//这样不需要去查找templete是否存在comment.html
    @RequestMapping(value = "/comment/{id}",method = RequestMethod.GET)
    public Resultsdto<List<Commentsdto>> comments(@PathVariable(name="id")Long id){
        List<Commentsdto> commentsdtoList=commentsService.ListByTargetId(id, CommentTypeEnums.COMMENTS);
        return Resultsdto.okOf(commentsdtoList);

    }
}

