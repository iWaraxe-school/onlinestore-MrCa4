package by.issoft.store.services.httpService.handlers;

import by.issoft.store.services.authService.SessionInterface;
import by.issoft.store.utils.dbUtils.DBClientUtil;
import by.issoft.store.utils.dbUtils.TemplateQueryEnum;
import com.sun.net.httpserver.HttpExchange;
import com.sun.net.httpserver.HttpHandler;

import java.io.IOException;
import java.io.OutputStream;

import static by.issoft.store.services.httpService.HTTPService.readPostData;

public class ConfirmOrderHandler implements HttpHandler, SessionInterface {

        @Override
        public void handle(HttpExchange t) throws IOException {
            String response=null;

            if (!checkSessionId(t)){ setSessionId(t); }

            if (t.getRequestMethod().toLowerCase().equals("post")){
                DBClientUtil.exec(String.format(TemplateQueryEnum.INSERT_NEW_ORDER.getQuery(),readPostData(t)));
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
