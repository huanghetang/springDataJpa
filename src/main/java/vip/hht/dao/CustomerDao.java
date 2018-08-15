package vip.hht.dao;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import vip.hht.beans.Customer;

import java.util.List;

/**
 * @author zhoumo
 * @datetime 2018/8/12 22:11
 * @desc
 */
public interface CustomerDao extends JpaRepository<Customer, Long>, JpaSpecificationExecutor<Customer> {


    public List<Customer> findByCustName(String name);

    @Query("update Customer  set custName = ?1  where custId = ?2")
    @Modifying
    public void updateCustomerById(String name, Long id);

    public List queryByCustName(String name);

    @Query("from Customer where custName = :name")
    public List queryByCustName2(@Param("name") String name);

    @Query(value = "select * from tb_customer where cust_name like ?1 ",nativeQuery = true)
    public List<Customer> queryByCustNameLike(String name);
}
