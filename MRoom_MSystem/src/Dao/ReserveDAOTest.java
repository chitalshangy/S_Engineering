package Dao;

import Po.Reserve;
import org.junit.Test;
import org.springframework.beans.factory.BeanFactory;
import org.springframework.context.support.FileSystemXmlApplicationContext;

import java.sql.Date;
import java.sql.Time;

import static org.junit.Assert.*;

public class ReserveDAOTest {

    BeanFactory beanFactory =
            new FileSystemXmlApplicationContext("web/WEB-INF/applicationContext.xml");
    ReserveDAO reserveDAO = (ReserveDAO) beanFactory.getBean("reserveDAO");

    @Test
    public void findByhql() {
        String hql = "from Reserve as reserve where uid = '111111' and rid = 'R01'";
        assertEquals(3,reserveDAO.findByhql(hql).size());
    }

    @Test
    public void judge(){
        assertEquals(false,reserveDAO.judge("r01", new Date(121,4,27),new Time(0,0,0),new Time(12,0,0)));
        assertEquals(false,reserveDAO.judge("r03", new Date(121,4,27),new Time(13,0,0),new Time(18,0,0)));
        assertEquals(true,reserveDAO.judge("r03", new Date(121,4,28),new Time(0,0,0),new Time(12,0,0)));
    }

    @Test
    public void findAll() {
        assertEquals(5,reserveDAO.findAll(1,50).size());
    }

    @Test
    public void infoCount() {
        assertEquals(5,reserveDAO.infoCount().longValue());
    }

    @Test
    public void history() {
        assertEquals(22,reserveDAO.history(1,50).size());
    }

    @Test
    public void historyCount() {
        assertEquals(21,reserveDAO.historyCount().longValue());
    }

    @Test
    public void findMyAll() {
        assertEquals(20,reserveDAO.findMyAll(1,50,"111111").size());
    }

    @Test
    public void infoMyCount() {
        assertEquals(20,reserveDAO.infoMyCount("111111").longValue());
    }

    @Test
    public void save() {
        String hql = "from Reserve as reserve where reid = '111111'";
        assertEquals(0,reserveDAO.findByhql(hql).size());
        Reserve reserve  = new Reserve();
        reserve.setReid("111111");
        reserveDAO.save(reserve);
        String hql1 = "from Reserve as reserve where reid = '111111'";
        assertEquals(1,reserveDAO.findByhql(hql1).size());
    }

    @Test
    public void deleteReserve() {
        String hql = "from Reserve as reserve where reid = '111111R012021-01-2718:00:00' and state = '1'";
        assertEquals(1,reserveDAO.findByhql(hql).size());
        reserveDAO.deleteReserve("111111R012021-01-2718:00:00");
        String hql1 = "from Reserve as reserve where reid = '111111R012021-01-2718:00:00' and state = '1'";
        assertEquals(0,reserveDAO.findByhql(hql1).size());
    }

    @Test
    public void updateReserve() {
        String hql = "from Reserve as reserve where reid = '111111R012021-01-2712:46:40' and state = '1'";
        assertEquals(1,reserveDAO.findByhql(hql).size());
        reserveDAO.updateReserve("111111R012021-01-2712:46:40");
        String hql1 = "from Reserve as reserve where reid = '111111R012021-01-2712:46:40' and state = '2'";
        assertEquals(1,reserveDAO.findByhql(hql1).size());
    }
}