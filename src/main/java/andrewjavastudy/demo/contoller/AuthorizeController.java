package andrewjavastudy.demo.contoller;

import andrewjavastudy.demo.dto.Accesstokendto;
import andrewjavastudy.demo.dto.GithubUsers;
import andrewjavastudy.demo.mapper.UsersMapper;
import andrewjavastudy.demo.model.Users;
import andrewjavastudy.demo.provider.GitProvider;
import org.apache.ibatis.annotations.Mapper;
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
public class AuthorizeController {

    @Autowired
    private GitProvider gitProvider;

    @Value("${github.client.id}")
    private String clientId;

    @Value("${github.client.secret}")
    private String clientSecret;

    @Value("${github.redirect.uri}")
    private String redirectUri;

    @Autowired
    private UsersMapper usersMapper;

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
            usersMapper.insert(users);

            response.addCookie(new Cookie("token",token));

            //request.getSession().setAttribute("users",githubUsers);
            //cookies and session
            return "redirect:/";
        }else{
            //login fail, log again
            return "redirect:/";//redirect返回的是路径
        }

    }
}
//用java模拟post请求
