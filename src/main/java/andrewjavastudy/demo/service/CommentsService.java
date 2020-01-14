package andrewjavastudy.demo.service;

import andrewjavastudy.demo.dto.Commentsdto;
import andrewjavastudy.demo.enums.CommentTypeEnums;
import andrewjavastudy.demo.exception.CustomizeErrorCode;
import andrewjavastudy.demo.exception.CustomizeException;
import andrewjavastudy.demo.mapper.CommentsMapper;
import andrewjavastudy.demo.mapper.QuestionsMapper;
import andrewjavastudy.demo.mapper.UsersMapper;
import andrewjavastudy.demo.model.*;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.Set;
import java.util.stream.Collector;
import java.util.stream.Collectors;

@Service
public class CommentsService {

    @Autowired
    private CommentsMapper commentsMapper;
    @Autowired
    private QuestionsMapper questionsMapper;
    @Autowired
    private UsersMapper usersMapper;

    @Transactional//事务，封装为一个事务，如果一旦出错 全部回滚
    public void insert(Comments comments) {
        if(comments.getParentId()==null||comments.getParentId()==0){
            throw new CustomizeException(CustomizeErrorCode.TARGET_PARAM_NOT_FOUND);
        }
        if(comments.getType()==null|| !CommentTypeEnums.isExist(comments.getType())){
            throw new CustomizeException(CustomizeErrorCode.TYPE_PARAM_WRONG);

        }
        if(comments.getType()==CommentTypeEnums.COMMENTS.getType()){
            //回复评论
            Comments dbComments=commentsMapper.selectByPrimaryKey(comments.getParentId());
            if(dbComments==null){
                throw new CustomizeException(CustomizeErrorCode.COMMENT_NOT_FOUND);
            }
                commentsMapper.insert(comments);

        }else{
            //回复问题
                Questions questions=questionsMapper.selectByPrimaryKey(comments.getParentId());
                if(questions==null){
                    throw new CustomizeException(CustomizeErrorCode.QUESTION_NOT_FOUND);
                }
                commentsMapper.insert(comments);
        }
    }

    public List<Commentsdto> ListByParentId(Long id) {
        CommentsExample commentsExample=new CommentsExample();
        commentsExample.createCriteria().andParentIdEqualTo(id);
        List<Comments> comments=commentsMapper.selectByExample(commentsExample);
        if(comments.size()==0){
            return new ArrayList<>();
        }
        Set<Long> commentators=comments.stream().map(comment -> comment.getCommentator()).collect(Collectors.toSet());//map方法将流中的元素映射成另外的值，新的值类型可以和原来的元素的类型不同。
        //collect是计算结果集 这样可以很好的避免重复读取

        List<Long> userslist=new ArrayList();//此处使用范型
        userslist.addAll(commentators);

        UsersExample usersExample= new UsersExample();
        usersExample.createCriteria().andIdIn(userslist);
        List<Users> users=usersMapper.selectByExample(usersExample);
        //不使用暴力破解，用map处理该问题
        Map<Long,Users> usersMap=users.stream().collect(Collectors.toMap(user -> user.getId(),user ->user));
        //使用lambda表达式获取评论和评论人之后转化为dto
        List<Commentsdto> commentsdtos =comments.stream().map(comment->{
            Commentsdto commentsdto=new Commentsdto();
            BeanUtils.copyProperties(comment,commentsdto);
            commentsdto.setUsers(usersMap.get(comment.getCommentator()));
            return commentsdto;
        }).collect(Collectors.toList());
        return commentsdtos;
    }
}
