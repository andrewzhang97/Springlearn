package andrewjavastudy.demo.dto;

import lombok.Data;

import java.util.ArrayList;
import java.util.List;
@Data
public class Paginationdto {
    private List<Questionsdto> questions;
    private boolean hasPrevious;
    private boolean hasFirstPage;
    private boolean showNext;
    private boolean showEndPage;
    private Integer page;
    private List<Integer> pages =new ArrayList<>();
    private  Integer totalPage;

    public void setPagination(Integer page,  Integer totalPage) {

        this.totalPage=totalPage;
        //计算pages的内容

        pages.add(page);

        for (int i=1;i<=3;i++){
            if(page-i>0){
                pages.add(0,page-i);//头部插入和尾部插入
            }
            if(page+i<=totalPage){
                pages.add(page+i);
            }
        }

        this.page=page;
        if(page==1){
            hasPrevious=false;
        }else{
            hasPrevious=true;
        }

        if(page==totalPage){
            showNext=false;
        }else{
            showNext=true;
        }

        if(pages.contains(1)){//给前端展示的页码包含第一页时，展示，else
            hasFirstPage=false;
        }else{
            hasFirstPage=true;
        }

        if(pages.contains(totalPage)){
            showEndPage=false;
        }else{
            showEndPage=true;
        }
    }
}
