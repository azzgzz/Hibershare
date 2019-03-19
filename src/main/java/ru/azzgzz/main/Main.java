package ru.azzgzz.main;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.query.NativeQuery;
import ru.azzgzz.data.Product;
import ru.azzgzz.data.User;
import ru.azzgzz.database.HibLoader;

import java.util.Iterator;
import java.util.List;

public class Main {
    public static void main(String[] args) {
        SessionFactory sessionFactory = HibLoader.getSessionFactory();
        System.out.println("Hibernate connection established!");

        Session session = sessionFactory.openSession();
        List<Product> products = null;
        try {
            session.beginTransaction();

            products = session.createNativeQuery("Select * from product", Product.class)
                    .list();


            session.getTransaction().commit();

        } catch (Exception e) {
            session.getTransaction().rollback();
            e.printStackTrace();
        } finally {
            session.close();
            sessionFactory.close();
        }

//        System.out.println("product instance of Product: " + (products.get(0) instanceof Product));
        System.out.println("Show query:");
        if (products != null)
            for (Product p : products) {
                System.out.println(p);
            }
        else
            System.out.println("No query");
    }
}
