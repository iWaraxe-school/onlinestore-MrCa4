package by.issoft.store.services.httpService.handlers;

import com.sun.net.httpserver.HttpExchange;
import com.sun.net.httpserver.HttpHandler;

import java.io.IOException;
import java.io.OutputStream;

public class AddProductHandler implements HttpHandler {

        @Override
        public void handle(HttpExchange t) throws IOException {
            String response=null;
            if (t.getRequestMethod().toLowerCase().equals("post")){

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
