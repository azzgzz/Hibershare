package ru.azzgzz.datapickup.urlparser;

import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.select.Elements;
import ru.azzgzz.datapickup.binancedata.BRow;

import java.io.FileWriter;
import java.io.IOException;
import java.util.LinkedList;
import java.util.List;

public class SimpleBinanceParser {

    private static String url= "https://www.binance.com/ru";
    private Document document;

    public static void main(String args[]) {


        SimpleBinanceParser sjp = new SimpleBinanceParser(url);

        sjp.getTable();


    }

    public SimpleBinanceParser(String url) {
        try {
            document = Jsoup.connect(url).get();
        } catch (IOException e) {
            e.printStackTrace();
            System.out.println("Cannot open url");
        }
    }


    public Document getDocument() {
        return document;
    }

    public void setDocument(Document document) {
        this.document = document;
    }

    public List<BRow> getTable() {
        Elements elements = document.select(".ReactVirtualized__Table__row");

        List<BRow> table = new LinkedList<>();

        elements.forEach(i -> {

            BRow bRow = BRow.createBRow();

            bRow.setTicker(i.children().get(1).text().split("/")[0]);
            bRow.setTickerBase(i.children().get(1).text().split("/")[1]);
            bRow.setCoinName(i.children().get(2).text());
            bRow.setLastPrice(i.children().get(3).child(0).child(0).text());
            bRow.setDayDelta(i.children().get(4).text());
            bRow.setDayMax(i.children().get(5).text());
            bRow.setDayMin(i.children().get(6).text());
            bRow.setDayVolume(i.children().get(7).text());

            table.add(bRow);
        });

        return table;
    }
}