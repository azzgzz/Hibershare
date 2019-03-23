package ru.azzgzz.main;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import ru.azzgzz.data.Product;
import ru.azzgzz.database.HibLoader;
import ru.azzgzz.datapickup.binancedata.BRow;
import ru.azzgzz.datapickup.urlparser.SimpleBinanceParser;

import org.hibernate.Query;
import java.util.List;

public class Main {
    public static void main(String[] args) {
        SessionFactory sessionFactory = HibLoader.getSessionFactory();
        System.out.println("Hibernate connection established!");

        Session session = sessionFactory.openSession();
        List<Product> products = null;
        List<BRow> bRows = null;
        try {
            session.beginTransaction();

            SimpleBinanceParser sbp = new SimpleBinanceParser();
            bRows = sbp.getTable();
            bRows.forEach(session::save);

            session.flush();
            session.clear();

            session.getTransaction().commit();

        } catch (Exception e) {
            session.getTransaction().rollback();
            e.printStackTrace();
        } finally {
            session.close();
            sessionFactory.close();
        }

        List<BRow> queryList = session.createQuery("from BRow").list();
        System.out.println("Show query:");
        if (queryList != null)
            queryList.forEach(System.out::println);
        else
            System.out.println("No query");
    }
}
