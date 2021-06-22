package Dao;

import org.junit.Test;
import org.springframework.beans.factory.BeanFactory;
import org.springframework.context.support.FileSystemXmlApplicationContext;

import static org.junit.Assert.*;

public class AdminDAOTest {

    BeanFactory beanFactory =
            new FileSystemXmlApplicationContext("web/WEB-INF/applicationContext.xml");
    AdminDAO adminDAO = (AdminDAO) beanFactory.getBean("adminDAO");

    @Test
    public void findByhql() {
        String hql = "from User as user where uid = '111111' and upassword = '111111'";

        assertEquals(1,adminDAO.findByhql(hql).size());
    }

    @Test
    public void findAll() {
        assertEquals(1,adminDAO.findAll(1,2).size());
    }

    @Test
    public void infoCount() {
        assertEquals(1L,adminDAO.infoCount().longValue());
    }

    @Test
    public void update() {
        String hql = "from Admin as admin where " +
                "aid = '1111' and apassword = '1111' and aphone = '1111'";
        assertEquals(1,adminDAO.findByhql(hql).size());
        adminDAO.update("1111","1111","19857186433");
        String hql1 = "from Admin as admin where " +
                "aid = '1111' and apassword = '1111' and aphone = '19857186433'";
        assertEquals(1,adminDAO.findByhql(hql1).size());
    }
}