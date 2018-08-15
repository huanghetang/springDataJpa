package vip.test;

import org.hibernate.criterion.Example;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.test.annotation.Rollback;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.transaction.annotation.Transactional;
import vip.hht.beans.Customer;
import vip.hht.dao.CustomerDao;

import java.util.List;
import java.util.Optional;

/**
 * @author zhoumo
 * @datetime 2018/8/12 20:34
 * @desc
 */
@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration("classpath:ApplicationContext.xml")
public class TestSpringDataJPA {

    @Autowired
    private CustomerDao customerDao;

    @Test
    public void test1() {
        Customer customer = new Customer();
        customer.setCustName("安吉拉大宝贝");
        customerDao.save(customer);
    }

    //保存 save 方法可以把 瞬时态对象变成持久态对象
    @Test
    public void test2() {
        //获取对象
        Customer customer = new Customer();
        customer.setCustName("景甜");
        customer.setCustId(2l);
        // 对象有id 会先查询，然后更新
        //没有id 直接保存
        //提交事务 save 方法是单事务的
        customerDao.save(customer);

    }
    //修改

    @Transactional
    @Rollback(false)
    @Test
    public void test21() {
        //先查询，再修改
        Optional<Customer> optional = customerDao.findById(2L);
        Customer customer = optional.orElse(null);
        System.out.println("customer = " + customer);
        customer.setCustAddress("航都路18号3");
        customer.setCustIndustry("IT3");
        //save 方法默认开启单事务，需要手动开启事务注解指定一个事务，然后在事务提交后，对比持久化对象和快照，然后决定更新数据库
//        Customer customer1 = customerDao.save(customer);
//        System.out.println("customer1 = " + customer1);
    }

    //删除 delete 删除将游离态对象时，先查询次对象是否存在，即先select 然后delete
    @Transactional
    @Rollback(false)
    @Test
    public void test31() {
        Customer customer = new Customer();
        customer.setCustName("景甜");
        customer.setCustId(11l);//有id 事务提交后会触发insert,然后删除
//        customerDao.delete(customer);
        customerDao.deleteById(11l);// deleteById 没有这条数据会抛异常

    }

    //查询
    //懒加载

    @Transactional
    @Test
    public void test33() {
        //及时加载
//        Customer customer = customerDao.findById(4L).orElse(null);
        //getOne 单事务方法 延时加载 需要给当前方法加上事务注解. 当查不到是抛出异常
        Customer customer = customerDao.getOne(4L);
        System.out.println("customer = " + customer);
    }

    @Test
    public void test344(){
        //单条件排序
        Sort sort = new Sort(Sort.Direction.DESC,"custId");
        List<Customer> all = customerDao.findAll(sort);
        all.forEach(System.out::print);
    }
    @Test
    public void test345(){
        //多条件排序
        Sort.Order order1 = new Sort.Order(Sort.Direction.DESC,"custName");
        Sort.Order order2 = new Sort.Order(Sort.Direction.ASC,"custId");
        Sort sort = Sort.by(order1, order2);

        List<Customer> all = customerDao.findAll(sort);
        all.forEach(System.out::print);
    }

    @Test
    public void test44(){
        long count = customerDao.count();
        System.out.println("count = " + count);
    }

    @Test
    public void test45(){
        boolean b = customerDao.existsById(3l);
        System.out.println("b = " + b);
    }
    //JPQA
    @Test
    public void test46(){
        List<Customer> list = customerDao.findByCustName("景甜");
        list.forEach(System.out::println);
    }
    @Test
    @Transactional
    @Rollback(false)
    public void test47(){
        customerDao.updateCustomerById("郑爽",9l);

    }
    @Test
    public void test48(){
        List list = customerDao.queryByCustName("景甜");
        list.forEach(System.out::println);
    }

    @Test
    public void test489(){
        List list = customerDao.queryByCustName2("景甜");
        list.forEach(System.out::println);
    }
    //nativeQuery
    @Test
    public void test4891(){
        List list = customerDao.queryByCustNameLike("%景%");
        list.forEach(System.out::println);
    }
    //方法命名规则查询


}
