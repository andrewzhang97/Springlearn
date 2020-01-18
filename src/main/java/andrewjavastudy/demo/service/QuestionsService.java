package andrewjavastudy.demo.service;

import andrewjavastudy.demo.dto.Commentsdto;
import andrewjavastudy.demo.dto.Paginationdto;
import andrewjavastudy.demo.dto.Questionsdto;
import andrewjavastudy.demo.exception.CustomizeErrorCode;
import andrewjavastudy.demo.exception.CustomizeException;
import andrewjavastudy.demo.mapper.QuestionsExtMapper;
import andrewjavastudy.demo.mapper.QuestionsMapper;
import andrewjavastudy.demo.mapper.UsersMapper;
import andrewjavastudy.demo.model.Questions;
import andrewjavastudy.demo.model.QuestionsExample;
import andrewjavastudy.demo.model.Users;
import org.apache.commons.lang3.StringUtils;
import org.apache.ibatis.session.RowBounds;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.stereotype.Service;

import java.lang.reflect.Array;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;

@Service
public class QuestionsService {
    @Autowired
    private QuestionsMapper questionsMapper;

    @Autowired
    private UsersMapper usersMapper;

    @Autowired
    private QuestionsExtMapper questionsExtMapper;

    public Paginationdto list(Integer page, Integer size){

        Paginationdto paginationdto =new Paginationdto();
        Integer totalCount=(int)questionsMapper.countByExample(new QuestionsExample());
        Integer totalPage;
        if(totalCount%size==0){
            totalPage=totalCount/size;
        }else{
            totalPage=totalCount/size +1;
        }
        //计算pages的内容


        if(page<1){
            page=1;
        }
        if(page>totalPage){
            page=totalPage;
        }
        paginationdto.setPagination(page,totalPage);//计算page数
        Integer offset=size*(page-1);
        //List<Questions> questions=questionsMapper.list(offset,size);
        List<Questions> questions=questionsMapper.selectByExampleWithRowbounds(new QuestionsExample(),new RowBounds(offset,size));
        List<Questionsdto>  questionsdtoList=new ArrayList<>();



        for (Questions question : questions){
            Users users = usersMapper.selectByPrimaryKey(question.getCreatorId());
            Questionsdto questionsdto= new Questionsdto();
            BeanUtils.copyProperties(question,questionsdto);
            questionsdto.setUsers(users);
            questionsdtoList.add(questionsdto);
        }
        paginationdto.setQuestions(questionsdtoList);


        return paginationdto;//建立一个List来存放Q的信息
    }

    public Paginationdto list(Long userId, Integer page, Integer size) {
        Paginationdto paginationdto =new Paginationdto();
        //Integer totalCount=questionsMapper.countByUserId(userId);
        QuestionsExample questionsExample=new QuestionsExample();
        questionsExample.createCriteria().andCreatorIdEqualTo(userId);
        Integer totalCount=(int)questionsMapper.countByExample(questionsExample);
        Integer totalPage;
        if(totalCount%size==0){
            totalPage=totalCount/size;
        }else{
            totalPage=totalCount/size +1;
        }
        //计算pages的内容


        if(page<1){
            page=1;
        }
        if(page>totalPage){
            page=totalPage;
        }
        paginationdto.setPagination(page,totalPage);
        Integer offset=size*(page-1);
        //List<Questions> questions=questionsMapper.listByUserId(userId,offset,size);
        QuestionsExample example=new QuestionsExample();
        example.setOrderByClause("gmt_create desc");
        List<Questions> questions=questionsMapper.selectByExampleWithRowbounds(example,new RowBounds(offset,size));
        List<Questionsdto>  questionsdtoList=new ArrayList<>();



        for (Questions question : questions){
            Users users = usersMapper.selectByPrimaryKey(question.getCreatorId());
            Questionsdto questionsdto= new Questionsdto();
            BeanUtils.copyProperties(question,questionsdto);
            questionsdto.setUsers(users);
            questionsdtoList.add(questionsdto);
        }
        paginationdto.setQuestions(questionsdtoList);


        return paginationdto;//建立一个List来存放Q的信息
    }

    public Questionsdto getById(Long id) {
        Questions questions=questionsMapper.selectByPrimaryKey(id);
        if(questions==null){
            throw new CustomizeException(CustomizeErrorCode.QUESTION_NOT_FOUND);
        }
        Questionsdto questionsdto=new Questionsdto();
        BeanUtils.copyProperties(questions,questionsdto);
        Users users = usersMapper.selectByPrimaryKey(questions.getCreatorId());
        questionsdto.setUsers(users);
        return questionsdto;
    }

    public void createOrUpdate(Questions questions) {
        if(questions.getId()==null){
            questions.setGmtCreate(System.currentTimeMillis());
            questions.setGmtModified(questions.getGmtCreate());
            questions.setLikeCount(0);
            questions.setViewCount(0);
            questionsMapper.insert(questions);
        }else{
            //update
            //questions.setGmtModified(questions.getGmtCreate());
            //questionsMapper.update(questions);
            Questions updateQuestions=new Questions();
            updateQuestions.setGmtModified(System.currentTimeMillis());
            updateQuestions.setTitle(questions.getTitle());
            updateQuestions.setDescription(questions.getDescription());
            updateQuestions.setTags(questions.getTags());
            QuestionsExample example=new QuestionsExample();
            example.createCriteria().andIdEqualTo(questions.getId());
            int updated=questionsMapper.updateByExampleSelective(updateQuestions,example);
            if(updated!=1){
                throw new CustomizeException(CustomizeErrorCode.QUESTION_NOT_FOUND);
            }
        }
    }

    public void incView(Long id) {
        Questions questions =new Questions();
        questions.setId(id);
        questions.setViewCount(1);
        questionsExtMapper.incView(questions);
    }

    public List<Questionsdto> selectRelated(Questionsdto questionsdto) {
        if(StringUtils.isBlank(questionsdto.getTags())){
            return new ArrayList<>();
        }
        String tags[]=StringUtils.split(questionsdto.getTags(),",");//把tags分开，分为一个数组
        String regexTags=Arrays.stream(tags).collect(Collectors.joining("|"));//读取tags
        Questions questions=new Questions();
        questions.setId(questionsdto.getId());
        questions.setTags(regexTags);
        List<Questions> questionsList=questionsExtMapper.selectRelated(questions);
        List<Questionsdto>questionsdtos=questionsList.stream().map(q->{
            Questionsdto questionsdtolist=new Questionsdto();
            BeanUtils.copyProperties(q,questionsdtolist);
            return  questionsdtolist;
        }).collect(Collectors.toList());//变成Questiondto
        return questionsdtos;
    }
}
