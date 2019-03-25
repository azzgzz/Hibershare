package ru.azzgzz.main;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import ru.azzgzz.data.Product;
import ru.azzgzz.database.HibLoader;
import ru.azzgzz.datapickup.binancedata.BRow;
import ru.azzgzz.datapickup.urlparser.SimpleBinanceParser;


import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import java.util.List;

public class Main {
    public static void main(String[] args) {
        SessionFactory sessionFactory = HibLoader.getSessionFactory();
        System.out.println("Hibernate connection established!");

        Session session = sessionFactory.openSession();
        try {

            CriteriaBuilder criteriaBuilder = session.getCriteriaBuilder();
            CriteriaQuery<BRow> criteriaQuery = criteriaBuilder.createQuery(BRow.class);

            criteriaQuery.from(BRow.class);

            List<BRow> bRows = session.createQuery(criteriaQuery).list();

            bRows.forEach(System.out::println);


        } catch (Exception e) {
            session.getTransaction().rollback();
            e.printStackTrace();
        } finally {
            session.close();
            sessionFactory.close();
        }

//        List<BRow> queryList = session.createQuery("from BRow").list();
//        System.out.println("Show query:");
//        if (queryList != null)
//            queryList.forEach(System.out::println);
//        else
//            System.out.println("No query");
    }

    private static int parseAndSaveToDB (Session session) {

        List<BRow> bRows = null;

        session.beginTransaction();

        SimpleBinanceParser sbp = new SimpleBinanceParser();
        bRows = sbp.getTable();
        bRows.forEach(session::save);

        session.flush();
        session.clear();

        session.getTransaction().commit();

        return bRows.size();
    }

    private static <tableClass> List getFullTable (Session session) {
        List<tableClass> queryList = session.createQuery("from BRow").list();
        System.out.println("Show query:");
        if (queryList != null)
            queryList.forEach(System.out::println);
        else
            System.out.println("No query");

        return queryList;
    }
}
