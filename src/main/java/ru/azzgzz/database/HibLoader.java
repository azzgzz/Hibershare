package ru.azzgzz.database;

import org.hibernate.SessionFactory;
import org.hibernate.boot.Metadata;
import org.hibernate.boot.MetadataSources;
import org.hibernate.boot.registry.StandardServiceRegistry;
import org.hibernate.boot.registry.StandardServiceRegistryBuilder;
import org.hibernate.cfg.Environment;
import ru.azzgzz.data.*;
import ru.azzgzz.datapickup.binancedata.BRow;

import java.util.HashMap;
import java.util.Map;

public class HibLoader {

    private static SessionFactory sessionFactory;
    private static StandardServiceRegistry registry;


    public static SessionFactory getSessionFactory() {
        if (sessionFactory == null) {
            try {

                StandardServiceRegistryBuilder registryBuilder = new StandardServiceRegistryBuilder();

                //Configuration properties
                Map<String, Object> settings = new HashMap<String, Object>();
                settings.put(Environment.DRIVER, "org.postgresql.Driver");
                settings.put(Environment.URL, "jdbc:postgresql://localhost/hibernate");
                settings.put(Environment.USER, "hibernate");
                settings.put(Environment.PASS, "holopass");
                settings.put(Environment.HBM2DDL_AUTO, "validate");
                settings.put(Environment.SHOW_SQL, true);

                registryBuilder.applySettings(settings);
                registry = registryBuilder.build();

                MetadataSources sources = new MetadataSources(registry);
                sources.addAnnotatedClass(User.class);
                sources.addAnnotatedClass(Role.class);
                sources.addAnnotatedClass(Order.class);
                sources.addAnnotatedClass(Product.class);
                sources.addAnnotatedClass(ProductCategory.class);
                sources.addAnnotatedClass(BRow.class);
                Metadata metadata = sources.getMetadataBuilder().build();

                sessionFactory = metadata.getSessionFactoryBuilder().build();
            } catch (Exception e) {
                if (registry != null) {
                    StandardServiceRegistryBuilder.destroy(registry);
                }
                e.printStackTrace();
            }

        }
        return sessionFactory;
    }

    public static void shutdown() {
        if (registry != null) {
            StandardServiceRegistryBuilder.destroy(registry);
        }
    }
}

