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

    private String url= "https://www.binance.com/ru";
    private String title;
    private String savePath;
    private Document document;

    public static void main(String args[]) {

        String gitUrl = "https://github.com/azzgzz/UrlDownloader/tree/master/UrlDownloader/src/ru/ncedu/iskandarov/urld";
        String url = "https://www.binance.com/ru";
        String path = "my_tests/";

        SimpleBinanceParser sjp = new SimpleBinanceParser(url);
        sjp.setSavePath(path + sjp.getTitle().replaceAll("[/&^*%$:;\'\"`~<>]", "") + ".html");


//        sjp.saveToFile();
        sjp.getTable();

//        LocalDate date = LocalDate.now();
//        LocalDateTime dateTime = LocalDateTime.now();
//        System.out.println(dateTime.toLocalTime());

    }

    public SimpleBinanceParser(String url) {
        try {
            document = Jsoup.connect(url).get();
            title = document.title();
        } catch (IOException e) {
            e.printStackTrace();
            System.out.println("Cannot open url");
        }
    }

    public String getSavePath() {
        return savePath;
    }

    public void setSavePath(String savePath) {
        this.savePath = savePath;
    }

    public Document getDocument() {
        return document;
    }

    public void setDocument(Document document) {
        this.document = document;
    }

    public String getTitle() {
        return title;
    }

    public void saveToFile() {
        try {
            FileWriter fos = new FileWriter(savePath);
            fos.write(document.toString());
            fos.close();
        } catch (IOException e) {
            e.printStackTrace();
        }
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