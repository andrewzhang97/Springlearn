package andrewjavastudy.demo.contoller;

import andrewjavastudy.demo.dto.Accesstokendto;
import andrewjavastudy.demo.dto.GithubUsers;
import andrewjavastudy.demo.model.Users;
import andrewjavastudy.demo.provider.GitProvider;
import andrewjavastudy.demo.service.UsersService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;

import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.util.UUID;

@Controller
@Slf4j
public class AuthorizeController {

    @Value("${github.client.id}")
    private String clientId;

    @Value("${github.client.secret}")
    private String clientSecret;

    @Value("${github.redirect.uri}")
    private String redirectUri;


    @Autowired
    private UsersService usersService;



    @GetMapping("/callback")
    public String callback(@RequestParam(name="code") String code,
                           @RequestParam(name="state") String state,
                           HttpServletRequest request,
                           HttpServletResponse response){
        Accesstokendto accesstokendto=new Accesstokendto();
        accesstokendto.setCode(code);
        accesstokendto.setRedirect_uri(redirectUri);
        accesstokendto.setClient_id(clientId);
        accesstokendto.setClient_secret(clientSecret);
        accesstokendto.setState(state);
        String accessToken = GitProvider.getaccesstoken(accesstokendto);
        GithubUsers githubUsers = GitProvider.getUsers(accessToken);
        System.out.println(githubUsers.getName());
        System.out.println(githubUsers.getId());
        if(githubUsers!=null){
            //login successfully
            Users users=new Users();
            String token=UUID.randomUUID().toString();
            users.setToken(token);
            users.setAccountId(String.valueOf(githubUsers.getId()));
            users.setGmtCreate(System.currentTimeMillis());
            users.setGmtModified(users.getGmtCreate());
            users.setAvatarUrl(githubUsers.getAvatarUrl());
            usersService.createOrUpdate(users);//在这个地方判断是否已经登陆，若登陆过则不许需要重新在数据库中加入只需要更新

            response.addCookie(new Cookie("token",token));

            //request.getSession().setAttribute("users",githubUsers);
            //cookies and session
            return "redirect:/";
        }else{
            //login fail, log again
            log.error("callback get github error {}",githubUsers);//打日志进行记录
            return "redirect:/";//redirect返回的是路径
        }

    }
    @GetMapping("/logout")
    public String logout(HttpServletRequest request,
                         HttpServletResponse response){
        request.getSession().removeAttribute("users");//删去session中关于users的信息
        Cookie cookie=new Cookie("token",null);
        cookie.setMaxAge(0);
        response.addCookie(cookie);//删除已知的cookie直接建立一个一个新的同名cookie即可
        return "redirect:/";
    }


}
//用java模拟post请求
