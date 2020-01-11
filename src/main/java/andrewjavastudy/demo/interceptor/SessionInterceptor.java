package andrewjavastudy.demo.interceptor;

import andrewjavastudy.demo.mapper.UsersMapper;
import andrewjavastudy.demo.model.Users;
import andrewjavastudy.demo.model.UsersExample;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.lang.Nullable;
import org.springframework.stereotype.Service;
import org.springframework.web.servlet.HandlerInterceptor;
import org.springframework.web.servlet.ModelAndView;

import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.util.List;

@Service //让这个bean被Spring接管
public class SessionInterceptor implements HandlerInterceptor {
    @Autowired
    private UsersMapper usersMapper;
    @Override
    public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler) throws Exception {
        Cookie[] cookies = request.getCookies();
        if(cookies !=null && cookies.length!=0){
            for (Cookie cookie:cookies){
                if(cookie.getName().equals("token")){
                    String token=cookie.getValue();
                    UsersExample usersExample=new UsersExample();//使用mybatis generator之后，生成UserExample
                    usersExample.createCriteria().andTokenEqualTo(token);//条件查询
                    List<Users> users=usersMapper.selectByExample(usersExample);//返回的是一个list
                    //Users users = usersMapper.findByToken(token);
                    if(users.size()!=0){
                        request.getSession().setAttribute("users",users.get(0));
                    }
                    break;
                }

            }
        }
        return true;
    }

    @Override
    public void postHandle(HttpServletRequest request, HttpServletResponse response, Object handler, @Nullable ModelAndView modelAndView) throws Exception {

    }

    @Override
    public void afterCompletion(HttpServletRequest request, HttpServletResponse response, Object handler, @Nullable Exception ex) throws Exception {

    }
}
