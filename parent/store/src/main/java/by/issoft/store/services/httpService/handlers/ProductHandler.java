package by.issoft.store.services.httpService.handlers;

import by.issoft.store.services.httpService.HTMLPatternEnum;
import by.issoft.store.services.httpService.HTTPService;
import by.issoft.store.utils.httpUtils.TemplateProcessingUtil;
import com.sun.net.httpserver.HttpExchange;
import com.sun.net.httpserver.HttpHandler;

import java.io.IOException;
import java.io.OutputStream;

import static by.issoft.store.services.httpService.HTTPService.getHttpStoreFabricCommands;

public class ProductHandler implements HttpHandler{


    private void commandSwitch(HttpExchange t){

        if (t.getRequestURI().getPath().toLowerCase().contains("top5")){
            System.out.println("Top5");
            getHttpStoreFabricCommands().exec("Top5");
        }
        if (t.getRequestURI().getPath().toLowerCase().contains("products")){
            getHttpStoreFabricCommands().exec("List All");
        }

    }


    @Override
    public void handle(HttpExchange t) throws IOException {
            String response;
            if (t.getRequestMethod().toLowerCase().equals("get")){
                commandSwitch(t);
                response = TemplateProcessingUtil.getTemplate(
                        "templates/products.html"
                        //TODO - defferent render collections..generics?????
                        , HTTPService.getCategoriesAndProductsList()
                        , HTMLPatternEnum.PRODUCTS_WITH_LINK_TO_ORDER.getPattern()
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

