package life.majiang.community.provider;


import com.alibaba.fastjson.JSON;
import life.majiang.community.dto.AccessTokenDTO;
import life.majiang.community.dto.GithubUser;
import okhttp3.*;
import org.springframework.stereotype.Component;

import java.security.KeyManagementException;
import java.security.NoSuchAlgorithmException;
import java.util.concurrent.TimeUnit;

import static life.majiang.community.utils.HttpUtils.getUnsafeOkHttpClient;


@Component
public class GithubProvider {

    public String getAccessToken(AccessTokenDTO accessTokenDTO) throws KeyManagementException, NoSuchAlgorithmException {
        MediaType mediaType = MediaType.get("application/json; charset=utf-8");

        OkHttpClient client = getUnsafeOkHttpClient().newBuilder().connectTimeout(50000, TimeUnit.MILLISECONDS)
                .readTimeout(50000, TimeUnit.MILLISECONDS)
                .build();

        RequestBody body = RequestBody.create(mediaType,JSON.toJSONString(accessTokenDTO));
        Request request = new Request.Builder()
                .url("https://github.com/login/oauth/access_token")
                .post(body)
                .build();
        try (Response response = client.newCall(request).execute()) {
            String string = response.body().string();
            String token  = string.split("&")[0].split("=")[1];
            return token;
        } catch (Exception e) {
            e.printStackTrace();
        }
        return null;
    }

    public GithubUser getGithubUser(String accessToken,AccessTokenDTO accessTokenDTO) throws KeyManagementException, NoSuchAlgorithmException {

        OkHttpClient client = getUnsafeOkHttpClient().newBuilder().connectTimeout(50000, TimeUnit.MILLISECONDS)
                .readTimeout(50000, TimeUnit.MILLISECONDS)
                .build();
        Request request = new Request.Builder()
                .url("https://api.github.com/user?access_token=" + accessToken)
                .build();
        try {
            Response response = client.newCall(request).execute();
            String string = response.body().string();
            GithubUser githubUser = JSON.parseObject(string, GithubUser.class);
            return githubUser;
        } catch (Exception e) {

        }
        return null;
    }
}
