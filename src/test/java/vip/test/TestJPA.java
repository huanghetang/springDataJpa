package vip.test;

import org.junit.Test;
import vip.hht.beans.Customer;
import vip.hht.utils.EntityManagerUtils;

import javax.persistence.*;
import javax.persistence.criteria.CriteriaBuilder;
import java.util.List;

/**
 * @author zhoumo
 * @datetime 2018/8/11 19:55
 * @desc
 */
public class TestJPA {

    @Test
    public void test1(){
        EntityManagerFactory entityManagerFactory = Persistence.createEntityManagerFactory("myjpa");
        EntityManager entityManager = entityManagerFactory.createEntityManager();
        EntityTransaction tx =entityManager.getTransaction();
        tx.begin();
        Customer customer = new Customer();
        customer.setCustName("景甜");
        customer.setCustPhone("123456");
        entityManager.persist(customer);
        tx.commit();
        entityManager.close();
        entityManagerFactory.close();

    }

    @Test
    public void test11(){
        //实体管理器
        EntityManager em = EntityManagerUtils.getEntityManager("myjpa");
        //获取事务对象
        EntityTransaction tx = em.getTransaction();
        //开启事务
        tx.begin();
        //执行JPQL语句
        Customer customer = new Customer();
        customer.setCustName("景甜");
        customer.setCustId(10l);
        customer.setCustPhone("123");
        customer.setCustAddress("航都路18号");
        customer.setCustIndustry("UI");
        customer.setCustLevel("vip");
        customer.setCustSource("电话");
        em.persist(customer);
        //提交事务
        tx.commit();
        //关闭资源
        em.close();
        EntityManagerUtils.close();
    }

    @Test
    public void test111(){
        //实体管理器
        EntityManager em = EntityManagerUtils.getEntityManager("myjpa");
        //获取事务对象
        EntityTransaction tx = em.getTransaction();
        //开启事务
        tx.begin();
        //执行JPQL语句
        Customer customer = em.find(Customer.class, 2L);
        Customer customer1 = em.find(Customer.class, 2L);
        //getReference 是延迟加载的方法
        Customer customer3 = em.getReference(Customer.class, 2L);
        Customer customer4 = em.getReference(Customer.class, 2L);

        System.out.println("customer1 = " + customer1);
        System.out.println(customer==customer1);
        System.out.println( customer3==customer4);
        System.out.println(customer1==customer4);
        //提交事务
        tx.commit();
        //关闭资源
        em.close();
        EntityManagerUtils.close();
    }


    @Test
    public void test1111(){
        //实体管理器
        EntityManager em = EntityManagerUtils.getEntityManager("myjpa");
        //获取事务对象
        EntityTransaction tx = em.getTransaction();
        //开启事务
        tx.begin();
        //执行JPQL语句

        Customer customer = em.find(Customer.class, 2l);
        //持久态
        customer.setCustName("迪丽热巴1");
        //提交事务
        tx.commit();
        //关闭资源
        em.close();
        EntityManagerUtils.close();
    }

    @Test
    public void test122(){
        //实体管理器
        EntityManager em = EntityManagerUtils.getEntityManager("myjpa");
        //获取事务对象
        EntityTransaction tx = em.getTransaction();
        //开启事务
        tx.begin();
        //执行JPQL语句
        String sql="from Customer where custName = :name order by custId desc ";
        Query query = em.createQuery(sql);
        query.setFirstResult(0);
        query.setMaxResults(1);
//        query.setParameter(1,"迪丽热巴");
        query.setParameter("name","迪丽热巴");
        List resultList = query.getResultList();
        resultList.forEach(System.out::print);

        //提交事务
        tx.commit();
        //关闭资源
        em.close();
        EntityManagerUtils.close();
    }
}
