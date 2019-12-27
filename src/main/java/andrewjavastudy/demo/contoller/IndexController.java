package andrewjavastudy.demo.contoller;

import andrewjavastudy.demo.mapper.UsersMapper;
import andrewjavastudy.demo.model.Users;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;

import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletRequest;

@Controller
public class IndexController {
    @Autowired
    private UsersMapper usersMapper;

    @GetMapping("/")
    public String index(HttpServletRequest request){
        Cookie[] cookies = request.getCookies();
        for (Cookie cookie:cookies){
            if(cookie.getName().equals("token")){
                String token=cookie.getValue();
                Users users = usersMapper.findByToken(token);
                if(users != null){
                    request.getSession().setAttribute("users",users);
                }
                break;
            }

        }

        return "index"; //去resource/templates下调用hello.html
    }
}
