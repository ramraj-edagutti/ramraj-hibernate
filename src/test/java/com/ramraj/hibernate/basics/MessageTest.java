package com.ramraj.hibernate.basics;

import java.util.List;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;
import org.hibernate.cfg.Configuration;
import org.hibernate.service.ServiceRegistry;
import org.hibernate.service.ServiceRegistryBuilder;
import org.testng.Assert;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;

public class MessageTest {
    SessionFactory factory;

    @BeforeClass
    public void setup() {
        Configuration configuration = new Configuration();
        configuration.configure("basics/hibernate.cfg.xml");
        ServiceRegistryBuilder srBuilder = new ServiceRegistryBuilder();
        srBuilder.applySettings(configuration.getProperties());
        ServiceRegistry serviceRegistry = srBuilder.buildServiceRegistry();
        factory = configuration.buildSessionFactory(serviceRegistry);
    }

    @Test
    public void saveMessage() {
        Message message = new Message("Hello, world");
        Session session = factory.openSession();
        Transaction tx = session.beginTransaction();
        session.persist(message);
        tx.commit();
        session.close();
    }

    @Test(dependsOnMethods = "saveMessage")
    public void readMessage() {
        Session session = factory.openSession();
        @SuppressWarnings("unchecked")
        List<Message> list = (List<Message>) session.createQuery("from Message").list();

        if (list.size() > 1) {
            Assert.fail("Message configuration in error; table should contain only one."
                    + " Set ddl to create-drop.");
        }
        if (list.size() == 0) {
            Assert.fail("Read of initial message failed; check saveMessage() for errors."
                    + " How did this test run?");
        }
        for (Message m : list) {
            System.out.println(m);
        }
        session.close();
    }
}
