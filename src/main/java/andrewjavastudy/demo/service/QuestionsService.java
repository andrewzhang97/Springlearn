package andrewjavastudy.demo.service;

import andrewjavastudy.demo.dto.Paginationdto;
import andrewjavastudy.demo.dto.Questionsdto;
import andrewjavastudy.demo.mapper.QuestionsMapper;
import andrewjavastudy.demo.mapper.UsersMapper;
import andrewjavastudy.demo.model.Questions;
import andrewjavastudy.demo.model.Users;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
@Service
public class QuestionsService {
    @Autowired
    private QuestionsMapper questionsMapper;

    @Autowired
    private UsersMapper usersMapper;

    public Paginationdto list(Integer page, Integer size){

        Paginationdto paginationdto =new Paginationdto();
        Integer totalCount=questionsMapper.count();
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
        List<Questions> questions=questionsMapper.list(offset,size);
        List<Questionsdto>  questionsdtoList=new ArrayList<>();



        for (Questions question : questions){
            Users users = usersMapper.findById(question.getCreatorId());
            Questionsdto questionsdto= new Questionsdto();
            BeanUtils.copyProperties(question,questionsdto);
            questionsdto.setUsers(users);
            questionsdtoList.add(questionsdto);
        }
        paginationdto.setQuestions(questionsdtoList);


        return paginationdto;//建立一个List来存放Q的信息
    }

    public Paginationdto list(Integer userId, Integer page, Integer size) {
        Paginationdto paginationdto =new Paginationdto();
        Integer totalCount=questionsMapper.countByUserId(userId);
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
        List<Questions> questions=questionsMapper.listByUserId(userId,offset,size);
        List<Questionsdto>  questionsdtoList=new ArrayList<>();



        for (Questions question : questions){
            Users users = usersMapper.findById(question.getCreatorId());
            Questionsdto questionsdto= new Questionsdto();
            BeanUtils.copyProperties(question,questionsdto);
            questionsdto.setUsers(users);
            questionsdtoList.add(questionsdto);
        }
        paginationdto.setQuestions(questionsdtoList);


        return paginationdto;//建立一个List来存放Q的信息
    }

    public Questionsdto getById(Integer id) {
        Questions questions=questionsMapper.getById(id);
        Questionsdto questionsdto=new Questionsdto();
        BeanUtils.copyProperties(questions,questionsdto);
        Users users = usersMapper.findById(questions.getCreatorId());
        questionsdto.setUsers(users);
        return questionsdto;
    }

    public void createOrUpdate(Questions questions) {
        if(questions.getId()==null){
            questions.setGmtCreate(System.currentTimeMillis());
            questions.setGmtModified(questions.getGmtCreate());
            questionsMapper.create(questions);
        }else{
            //update
            questions.setGmtModified(questions.getGmtCreate());
            questionsMapper.update(questions);
        }
    }
}
