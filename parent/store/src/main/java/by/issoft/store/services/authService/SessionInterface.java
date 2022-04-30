package by.issoft.store.services.authService;

import com.sun.net.httpserver.HttpExchange;

import java.util.UUID;

public interface SessionInterface {


    default boolean checkSessionId(HttpExchange t){
            if(t.getRequestHeaders().get("Cookie")!=null && t.getRequestHeaders().get("Cookie").stream().anyMatch(cook -> cook.contains("MySessionHeader"))){
                return t.getRequestHeaders().get("Cookie").stream().anyMatch(cook -> cook.contains("MySessionHeader"));
            }
        return false;
       }
    default String getSessionId(HttpExchange t){
        return t.getRequestHeaders().get("Cookie").stream()
                .filter(i->i.contains("MySessionHeader")).findFirst().toString().split("=")[1];
    }
    default String generateSessionId(){
        return UUID.randomUUID().toString();
    }
    default void setSessionId(HttpExchange t){
        t.getResponseHeaders().set("Set-Cookie","MySessionHeader="+generateSessionId());
    }
}
