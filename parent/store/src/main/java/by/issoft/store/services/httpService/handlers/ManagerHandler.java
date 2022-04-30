package by.issoft.store.services.httpService.handlers;

import by.issoft.store.services.authService.SessionInterface;
import by.issoft.store.services.httpService.HTMLPatternEnum;
import by.issoft.store.utils.dbUtils.DBClientUtil;
import by.issoft.store.utils.dbUtils.TemplateQueryEnum;
import by.issoft.store.utils.httpUtils.TemplateProcessingUtil;
import com.sun.net.httpserver.HttpExchange;
import com.sun.net.httpserver.HttpHandler;
import lombok.SneakyThrows;

import java.io.OutputStream;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.List;

public class ManagerHandler implements HttpHandler, SessionInterface {





    @Override
    @SneakyThrows
    public void handle(HttpExchange t) {
        String response=null;
        List<String> orderList = new ArrayList<>();
        if (!checkSessionId(t)){ setSessionId(t); }
        if (t.getRequestMethod().toLowerCase().equals("get")){
            ResultSet resultset = DBClientUtil.exec(TemplateQueryEnum.GET_ORDERS.getQuery());
            while (resultset.next()){
                    orderList.add(resultset.getString("order_string")
                            .replaceAll("product","")
                            .replaceAll("="," : ")
                            .replaceAll("&","\r\n"));
            }


                    response = TemplateProcessingUtil.getTemplate(
                            "templates/manager.html"
                            //TODO - defferent render collections..generics?????
                            , orderList
                            , HTMLPatternEnum.ORDER_LIST_PATTERN.getPattern()
                            , "<!--<test>-->");
                    t.sendResponseHeaders(200, response.length());
                }
                else{
                    t.getResponseHeaders().set("Location",t.getRequestHeaders().get("Host").get(0)+"index");
                    t.sendResponseHeaders(302, 0);
                }

//            }
//            else{
//                response = "Method Not Allowed";
//                t.sendResponseHeaders(405, response.length());
//            }

            OutputStream os = t.getResponseBody();
            os.write(response.getBytes());
            os.close();
        }
    }

