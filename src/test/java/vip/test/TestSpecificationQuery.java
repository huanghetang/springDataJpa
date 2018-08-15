package vip.test;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.transaction.annotation.Transactional;
import vip.hht.beans.Customer;
import vip.hht.dao.CustomerDao;

import javax.persistence.criteria.*;
import java.util.List;

/**
 * @author zhoumo
 * @datetime 2018/8/14 20:31
 * @desc
 */
@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration("classpath:ApplicationContext.xml")
public class TestSpecificationQuery {

    @Autowired
    private CustomerDao customerDao;
    @Test
    @Transactional
    public void test1(){
        //参数化查询
        Specification<Customer> spec = new Specification<Customer>() {
            //root 查询跟对象，可以获取实体属性
            //query 自定义查询对象
            // criteriaBuilder 查询语句构建器
            @Override
            public Predicate toPredicate(Root<Customer> root, CriteriaQuery<?> query, CriteriaBuilder criteriaBuilder) {
                Path<Object> path1 = root.get("custName");
                Predicate predicate1 = criteriaBuilder.like(path1.as(String.class), "%景甜%");
//                Predicate predicate1 = criteriaBuilder.equal(path1, "景甜");

                Path<Object> path2 = root.get("custPhone");
                Predicate predicate2 = criteriaBuilder.isNotNull(path2);
                Predicate predicate = criteriaBuilder.and(predicate1, predicate2);
                return predicate;
            }
        };
        //排序并分页
        Sort sort = new Sort(Sort.Direction.DESC,"custId");
        Pageable page = PageRequest.of(0,3, sort);
        Page<Customer> pageList = customerDao.findAll(spec, page);
        //获取分页数据
        long totalElements = pageList.getTotalElements();
        System.out.println("总条数 = " + totalElements);
        int totalPages = pageList.getTotalPages();
        System.out.println("总页数 = " + totalPages);
        List<Customer> customerList = pageList.getContent();
        for (Customer customer : customerList) {
            System.out.println("customer = " + customer);
        }


    }
}
