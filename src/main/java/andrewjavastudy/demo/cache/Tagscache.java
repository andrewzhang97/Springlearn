package andrewjavastudy.demo.cache;

import andrewjavastudy.demo.dto.Tagsdto;
import org.apache.commons.lang3.StringUtils;

import java.lang.reflect.Array;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class Tagscache {
    public static List<Tagsdto> get(){
        ArrayList<Tagsdto> tagsdtos=new ArrayList<>();
        Tagsdto e=new Tagsdto();
        e.setCategoryName("开发语言");
        e.setTags(Arrays.asList("Js","Php","Java","Python","C++","C","Html"));
        tagsdtos.add(e);

        Tagsdto structure=new Tagsdto();
        structure.setCategoryName("平台框架");
        structure.setTags(Arrays.asList("Spring Boot","Django","Flask","Struts","Tornado"));
        tagsdtos.add(structure);

        Tagsdto db=new Tagsdto();
        db.setCategoryName("数据库");
        db.setTags(Arrays.asList("Mysql","Sqlite","Redis","MongoDb"));
        tagsdtos.add(db);

        Tagsdto entertainment=new Tagsdto();
        entertainment.setCategoryName("生活爱好");
        entertainment.setTags(Arrays.asList("足球","美食","旅游","影视","游戏","成人🔞"));
        tagsdtos.add(entertainment);
        return tagsdtos;
    }


}
