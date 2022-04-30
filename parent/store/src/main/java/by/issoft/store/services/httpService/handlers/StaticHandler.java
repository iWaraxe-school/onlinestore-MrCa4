package by.issoft.store.services.httpService.handlers;

import by.issoft.store.services.authService.SessionInterface;
import by.issoft.store.utils.httpUtils.TemplateProcessingUtil;
import com.sun.net.httpserver.HttpExchange;
import com.sun.net.httpserver.HttpHandler;

import java.io.IOException;
import java.io.OutputStream;

public class StaticHandler implements HttpHandler, SessionInterface {
    @Override
    public void handle(HttpExchange t) throws IOException {
        byte[] response;
        if (t.getRequestMethod().toLowerCase().equals("get")){

            response = TemplateProcessingUtil.getStatic(String.valueOf(t.getRequestURI()));
            t.sendResponseHeaders(200, response.length);
        }
        else{
            response =null;
            t.sendResponseHeaders(405, response.length);
        }

        OutputStream os = t.getResponseBody();
        os.write(response);
        os.close();
    }

}
