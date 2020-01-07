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
        paginationdto.setPagination(page,size,totalCount);

        if(page<1){
            page=1;
        }
        if(page>paginationdto.getTotalPage()){
            page=paginationdto.getTotalPage();
        }

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
        paginationdto.setPagination(page,size,totalCount);

        if(page<1){
            page=1;
        }
        if(page>paginationdto.getTotalPage()){
            page=paginationdto.getTotalPage();
        }

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

}
