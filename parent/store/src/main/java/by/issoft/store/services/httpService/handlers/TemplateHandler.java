package by.issoft.store.services.httpService.handlers;

import by.issoft.store.utils.httpUtils.TemplateProcessingUtil;
import com.sun.net.httpserver.HttpExchange;
import com.sun.net.httpserver.HttpHandler;

import java.io.IOException;
import java.io.OutputStream;

public class TemplateHandler implements HttpHandler{


        @Override
        public void handle(HttpExchange t) throws IOException {
            String response;
            if (t.getRequestMethod().toLowerCase().equals("get")){
                response = TemplateProcessingUtil.getTemplate(String.valueOf(t.getRequestURI()));
                t.sendResponseHeaders(200, response.length());
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
