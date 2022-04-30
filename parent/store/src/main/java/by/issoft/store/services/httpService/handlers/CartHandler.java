package by.issoft.store.services.httpService.handlers;

import by.issoft.store.services.authService.SessionInterface;
import by.issoft.store.services.httpService.HTMLPatternEnum;
import by.issoft.store.utils.httpUtils.TemplateProcessingUtil;
import com.sun.net.httpserver.HttpExchange;
import com.sun.net.httpserver.HttpHandler;

import java.io.IOException;
import java.io.OutputStream;
import java.util.List;

import static by.issoft.store.services.httpService.HTTPService.getOrderMap;

public class CartHandler implements HttpHandler, SessionInterface {





    @Override
    public void handle(HttpExchange t) throws IOException {
            String response=null;

        if (!checkSessionId(t)){ setSessionId(t); }

            if (t.getRequestMethod().toLowerCase().equals("get")){
                List<String> order = getOrderMap().get(getSessionId(t));
                if (checkSessionId(t) && order!=null) {
                   // List<String> order = getOrderMap().get(getSessionId(t));
                    response = TemplateProcessingUtil.getTemplate(
                            "templates/order.html"
                            //TODO - defferent render collections..generics?????
                            , getOrderMap().get(getSessionId(t))
                            , HTMLPatternEnum.ORDER_HTML_PATTERN.getPattern()
                            , "<!--<test>-->");
                    t.sendResponseHeaders(200, response.length());
                }
                else{
                    t.getResponseHeaders().set("Location",t.getRequestHeaders().get("Host").get(0)+"index");
                    t.sendResponseHeaders(302, 0);
                }

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

