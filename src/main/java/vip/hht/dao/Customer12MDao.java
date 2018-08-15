package vip.hht.dao;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import vip.hht.beans.one2many.Customer;

/**
 * @author zhoumo
 * @datetime 2018/8/14 21:37
 * @desc
 */
public interface Customer12MDao extends JpaRepository<Customer, Long>, JpaSpecificationExecutor<Customer> {
}
