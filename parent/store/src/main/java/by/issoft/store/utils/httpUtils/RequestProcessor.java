package by.issoft.store.utils.httpUtils;

import com.sun.net.httpserver.HttpExchange;
import lombok.SneakyThrows;

import java.io.OutputStream;

public class RequestProcessor {

    @SneakyThrows
    public static void responseProcess(String response, HttpExchange t){
        OutputStream os = t.getResponseBody();
        os.write(response.getBytes());
        os.close();
    }

    @SneakyThrows
    public static void responseProcess(byte[] response, HttpExchange t){
        OutputStream os = t.getResponseBody();
        os.write(response);
        os.close();
    }
}
