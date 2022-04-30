package by.issoft.store.services.httpService.handlers;

import by.issoft.store.services.authService.SessionInterface;
import com.sun.net.httpserver.HttpExchange;
import com.sun.net.httpserver.HttpHandler;

import java.io.IOException;
import java.io.OutputStream;
import java.util.ArrayList;
import java.util.List;

import static by.issoft.store.services.httpService.HTTPService.getOrderMap;
import static by.issoft.store.services.httpService.HTTPService.readPostData;

public class AddProductHandler implements HttpHandler, SessionInterface {

        @Override
        public void handle(HttpExchange t) throws IOException {
            String response=null;

            if (!checkSessionId(t)){ setSessionId(t); }

            if (t.getRequestMethod().toLowerCase().equals("post")){
                if(getOrderMap().containsKey(getSessionId(t))) {
                    //TODO возможна инъекция html/sql а также xss надо валидировать ввод
                    getOrderMap().get(getSessionId(t)).add(readPostData(t).replace("product=",""));
                }
                else {
                        List<String> orderList= new ArrayList<>();
                        orderList.add(readPostData(t).replace("product=",""));
                         getOrderMap().put(getSessionId(t),orderList);
                }
               // HTTPService.getHttpOrderFabricCommands().exec("Add product");

                t.getResponseHeaders().set("Location",t.getRequestHeaders().get("Referer").get(0));
                t.sendResponseHeaders(302, 0);
            }
            else{
                response = "Method Not Allowed";
                t.sendResponseHeaders(405, response.length());
            }

            OutputStream os = t.getResponseBody();
            os.write(response.getBytes());
            os.close();
        }




}
