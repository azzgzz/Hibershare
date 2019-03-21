package ru.azzgzz.datapickup.binancedata;

import java.time.LocalDateTime;

public class BRow {
    private String ticker;
    private String tickerBase;
    private String coinName;
    private double lastPrice;
    private double dayDelta;
    private double dayMax;
    private double dayMin;
    private double dayVolume;
    private LocalDateTime timestamp;

    private BRow(){
        timestamp = LocalDateTime.now();
    }

    public static BRow createBRow(){
        return new BRow();
    }

    public String getTicker() {
        return ticker;
    }

    public void setTicker(String ticker) {
        this.ticker = ticker;
    }

    public String getTickerBase() {
        return tickerBase;
    }

    public void setTickerBase(String tickerBase) {
        this.tickerBase = tickerBase;
    }

    public String getCoinName() {
        return coinName;
    }

    public void setCoinName(String coinName) {
        this.coinName = coinName;
    }

    public double getLastPrice() {
        return lastPrice;
    }

    public void setLastPrice(double lastPrice) {
        this.lastPrice = lastPrice;
    }

    public double getDayDelta() {
        return dayDelta;
    }

    public void setDayDelta(double dayDelta) {
        this.dayDelta = dayDelta;
    }

    public double getDayMax() {
        return dayMax;
    }

    public void setDayMax(double dayMax) {
        this.dayMax = dayMax;
    }

    public double getDayMin() {
        return dayMin;
    }

    public void setDayMin(double dayMin) {
        this.dayMin = dayMin;
    }

    public double getDayVolume() {
        return dayVolume;
    }

    public void setDayVolume(double dayVolume) {
        this.dayVolume = dayVolume;
    }

    public void setLastPrice(String s) {
        s = s.replace(",","");
        this.lastPrice = Double.parseDouble(s);
    }

    public void setDayDelta(String s) {
        s = s.replace(",","");
        this.dayDelta = Double.parseDouble(s.substring(0, s.indexOf('%')));
    }

    public void setDayMax(String s){
        s = s.replace(",","");
        this.dayMax = Double.parseDouble(s);
    }

    public void setDayMin(String s){
        s = s.replace(",","");
        this.dayMin= Double.parseDouble(s);
    }

    public void setDayVolume (String s) {
        s = s.replace(",","");
        this.dayVolume = Double.parseDouble(s);
    }

    @Override
    public String toString() {
        return ticker + "/" + tickerBase + "\t" + coinName + "\t" + lastPrice + "\t"
                + dayDelta + "\t" + dayMax + "\t" + dayMin + "\t" + dayVolume + timestamp;
    }
}
