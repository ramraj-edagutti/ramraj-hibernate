package com.ramraj.hibernate.util;

import org.hibernate.Session;
import org.testng.annotations.Test;

public class SessionBuilderTest {
    @Test
    public void testSessionFactory() {
        Session session = SessionUtil.getSession();
        session.close();
    }
}
