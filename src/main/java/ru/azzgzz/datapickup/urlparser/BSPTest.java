package ru.azzgzz.datapickup.urlparser;

import java.io.IOException;
import java.net.MalformedURLException;

public class BSPTest {


    public static void main(String[] args) throws MalformedURLException, IOException {

        String url = "https://www.binance.com/ru";
        String path = "my_tests/";
        boolean toOpen = false;

        BinanceSiteParser bsp = new BinanceSiteParser();



        bsp.setUrl(url);
        bsp.setPath(path);

        try {
            bsp.save();
        } catch (IllegalStateException e) {
            System.out.println("You must call setUrl first");
        } catch (SecurityException e) {
            System.out.println("Security issues...");
        } catch (IOException e) {
            System.out.println("Can`t download this Url or cant save to this path. Maybe it wrong");
        } catch (Exception e) {
            System.out.println("OK");
            return;
        }

        if (toOpen)
            bsp.open();
    }
}
