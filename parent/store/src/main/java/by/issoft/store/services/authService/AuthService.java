package by.issoft.store.services.authService;

import com.google.common.hash.Hashing;
import com.sun.net.httpserver.BasicAuthenticator;
import com.sun.net.httpserver.HttpContext;

import java.nio.charset.StandardCharsets;

public class AuthService {


    public static void setSecurity(HttpContext hc){
        hc.setAuthenticator(new BasicAuthenticator("get") {
            @Override
            public boolean checkCredentials(String user, String pwd) {
                //TODO тут по идуи обращение к базе сравнение хэшей и тд)))
                return user.equals("admin") && pwd.equals("admin");
            }
        });
    }
    public static String sha256Hash(String password){
    return Hashing.sha256()
            .hashString(password,StandardCharsets.UTF_8)
                .toString().replace(" ","");
    }
}
