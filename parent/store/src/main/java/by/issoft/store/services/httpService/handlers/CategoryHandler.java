package by.issoft.store.services.httpService.handlers;

import by.issoft.store.services.authService.SessionInterface;
import by.issoft.store.services.httpService.HTMLPatternEnum;
import by.issoft.store.services.httpService.HTTPService;
import by.issoft.store.utils.httpUtils.TemplateProcessingUtil;
import com.sun.net.httpserver.HttpExchange;
import com.sun.net.httpserver.HttpHandler;

import java.io.IOException;
import java.io.OutputStream;

import static by.issoft.store.services.httpService.HTTPService.getCategoryListCashe;

public class CategoryHandler implements HttpHandler, SessionInterface {
    @Override
    public void handle(HttpExchange t) throws IOException {
        String response;
        if (!checkSessionId(t)){ setSessionId(t); }
        if (t.getRequestMethod().toLowerCase().equals("get")){
            HTTPService.getHttpStoreFabricCommands().exec("List Category");
            response = TemplateProcessingUtil.getTemplate(
                    "templates/categories.html"
                    ,getCategoryListCashe()
                    , HTMLPatternEnum.H3_WHITE_TEXT_WITH_BRTAG.getPattern()
                    ,"<!--<test>-->");
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


