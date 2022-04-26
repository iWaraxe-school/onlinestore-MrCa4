package by.issoft.store.services.httpService.handlers;

import by.issoft.store.utils.httpUtils.TemplateProcessingUtil;
import com.sun.net.httpserver.HttpExchange;
import com.sun.net.httpserver.HttpHandler;

import java.io.IOException;
import java.io.OutputStream;

public class IndexHandler implements HttpHandler{


        @Override
        public void handle(HttpExchange t) throws IOException {
            String response;
            if (t.getRequestMethod().toLowerCase().equals("get")){
                System.out.println("we in get");
                response = TemplateProcessingUtil.getTemplate("templates/index.html");
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

