package andrewjavastudy.demo.service;

import andrewjavastudy.demo.enums.CommentTypeEnums;
import andrewjavastudy.demo.exception.CustomizeErrorCode;
import andrewjavastudy.demo.exception.CustomizeException;
import andrewjavastudy.demo.mapper.CommentsMapper;
import andrewjavastudy.demo.mapper.QuestionsMapper;
import andrewjavastudy.demo.model.Comments;
import andrewjavastudy.demo.model.Questions;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
public class CommentsService {

    @Autowired
    private CommentsMapper commentsMapper;
    @Autowired
    private QuestionsMapper questionsMapper;

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
                throw new CustomizeException(CustomizeErrorCode.COMMENT_NOT_FOUND);}
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
}
