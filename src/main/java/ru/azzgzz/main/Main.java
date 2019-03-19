package ru.azzgzz.main;

import org.hibernate.SessionFactory;
import ru.azzgzz.database.HibLoader;

public class Main {
    public static void main(String[] args) {
        SessionFactory sessionFactory = HibLoader.getSessionFactory();
        System.out.println("Hibernate connection established!");
    }
}
