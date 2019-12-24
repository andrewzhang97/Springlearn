package andrewjavastudy.demo.provider;

import andrewjavastudy.demo.dto.Accesstokendto;
import andrewjavastudy.demo.dto.GithubUsers;
import com.alibaba.fastjson.JSON;
import okhttp3.*;
import org.springframework.stereotype.Component;

import java.io.IOException;

//dto 数据到对象
@Component //Controller是将类作为路由API的承载者 Component是将类初始化到Spring容器的上下文 不再需要实例化对象
public class GitProvider {
    public static String getaccesstoken(Accesstokendto accesstokendto){
        MediaType mediaType = MediaType.get("application/json; charset=utf-8");

        OkHttpClient client = new OkHttpClient();

        RequestBody body = RequestBody.create(mediaType, JSON.toJSONString(accesstokendto));
        Request request = new Request.Builder()
                .url("https://github.com/login/oauth/access_token")
                .post(body)
                .build();
            try (Response response = client.newCall(request).execute()) {
                String string= response.body().string();
                String token=string.split("&")[0].split("=")[1];
                System.out.println(string);
                return token;
            } catch (IOException e) {
                e.printStackTrace();
            }
            return null;
    }

    public static GithubUsers getUsers(String accessToken){
        OkHttpClient client = new OkHttpClient();
        Request request = new Request.Builder()
                .url("https://api.github.com/user?access_token="+accessToken)
                .build();
        try{
            Response response = client.newCall(request).execute();
            String string= response.body().string();
            GithubUsers githubUsers=JSON.parseObject(string,GithubUsers.class);
            return githubUsers;
        } catch (IOException e) {
            e.printStackTrace();
        }
        return null;


    }


}
