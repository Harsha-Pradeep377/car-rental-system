package lk.ijse.carrental.util;

import lk.ijse.carrental.entity.BookingEntity;
import lk.ijse.carrental.entity.CarEntity;
import lk.ijse.carrental.entity.CategoryEntity;
import lk.ijse.carrental.entity.CustomerEntity;
import org.hibernate.SessionFactory;
import org.hibernate.boot.Metadata;
import org.hibernate.boot.MetadataSources;
import org.hibernate.boot.model.naming.ImplicitNamingStrategyJpaCompliantImpl;
import org.hibernate.boot.registry.StandardServiceRegistry;
import org.hibernate.boot.registry.StandardServiceRegistryBuilder;

public class SessionFactoryConfiguration {
    private static SessionFactory sessionFactory = buildSessionFActory();

    private static SessionFactory buildSessionFActory() {
        StandardServiceRegistry standardRegistry = new StandardServiceRegistryBuilder()
                .loadProperties("/cfg/application.properties")
                .build();

        Metadata metadata = new MetadataSources(standardRegistry)
                .addAnnotatedClass(CustomerEntity.class)
                .addAnnotatedClass(CategoryEntity.class)
                .addAnnotatedClass(CarEntity.class)
                .addAnnotatedClass(BookingEntity.class)
                .getMetadataBuilder()
                .applyImplicitNamingStrategy(ImplicitNamingStrategyJpaCompliantImpl.INSTANCE)
                .build();

        SessionFactory sessionFactory = metadata.getSessionFactoryBuilder()
                .build();
        return sessionFactory;
    }
    public static SessionFactory getSessionFactory(){
        return sessionFactory;
    }
}
