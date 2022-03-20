package by.issoft.store.utils;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

public class StreamUtil {
    private static String data = null;
    private static BufferedReader bufferedReader = new BufferedReader(new InputStreamReader(System.in));


    public static String getInputData(){
        try {
            data = bufferedReader.readLine();
        } catch (IOException e) {
            e.printStackTrace();
        }
        return data;

    }
}
