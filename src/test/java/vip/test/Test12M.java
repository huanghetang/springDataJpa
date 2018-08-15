package vip.test;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.annotation.Rollback;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.transaction.annotation.Transactional;
import vip.hht.beans.one2many.Customer;
import vip.hht.beans.one2many.LinkMan;
import vip.hht.dao.Customer12MDao;
import vip.hht.dao.LinkMan12MDao;

import java.util.Set;

/**
 * @author zhoumo
 * @datetime 2018/8/14 21:34
 * @desc  one to many
 */

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration("classpath:ApplicationContext.xml")
public class Test12M {

    @Autowired
    private Customer12MDao customerDao;
    @Autowired
    private LinkMan12MDao linkManDao;

    @Test
    @Transactional
    @Rollback(false)
    public void test1(){
        Customer customer = new Customer();
        customer.setCustName("景甜");

        //关联关系由多方维护，所以一方的关联关系可以不写
        LinkMan linkMan = new LinkMan();
        linkMan.setLkmName("迪丽热巴");
        linkMan.setCustomer(customer);
        LinkMan linkMan2 = new LinkMan();
        linkMan2.setLkmName("古力娜扎");
        linkMan2.setCustomer(customer);
        //建立关联
//        customer.getLinkManSet().add(linkMan);
//        customer.getLinkManSet().add(linkMan2);

        customerDao.save(customer);
        linkManDao.save(linkMan);
        linkManDao.save(linkMan2);


    }

    @Test
    @Transactional
    @Rollback(false)
    public void test12(){
//        customerDao.deleteById(1L);
        Customer customer = customerDao.findById(2L).orElse(null);
        customerDao.delete(customer);
    }

    @Test
    @Transactional(timeout = 6000)
    @Rollback(false)
    public void test123(){
        Customer customer = customerDao.findById(3L).orElse(null);
        Set<LinkMan> linkManSet = customer.getLinkManSet();
        if (linkManSet==null) return;
        linkManSet.forEach(linkMan -> linkMan.setLkmName(linkMan.getLkmName()+"AAA"));
//        customerDao.save()
    }


}


