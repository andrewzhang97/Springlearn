package andrewjavastudy.demo.contoller;

import andrewjavastudy.demo.dto.Accesstokendto;
import andrewjavastudy.demo.dto.GithubUsers;
import andrewjavastudy.demo.provider.GitProvider;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;

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


    @GetMapping
    public String callback(@RequestParam(name="code") String code,
                           @RequestParam(name="state") String state){
        Accesstokendto accesstokendto=new Accesstokendto();
        accesstokendto.setCode(code);
        accesstokendto.setRedirect_uri(redirectUri);
        accesstokendto.setClient_id(clientId);
        accesstokendto.setClient_secret(clientSecret);
        accesstokendto.setState(state);
        String accessToken = GitProvider.getaccesstoken(accesstokendto);
        GithubUsers users = GitProvider.getUsers(accessToken);
        System.out.println(users.getName());
        System.out.println(users.getId());
        System.out.println(users.getBio());

        return "index";
    }
}
//用java模拟post请求
