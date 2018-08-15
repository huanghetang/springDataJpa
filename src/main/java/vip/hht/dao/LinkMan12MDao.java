package vip.hht.dao;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import vip.hht.beans.one2many.LinkMan;

/**
 * @author zhoumo
 * @datetime 2018/8/14 21:38
 * @desc
 */
public interface LinkMan12MDao extends JpaRepository<LinkMan,Long>,JpaSpecificationExecutor<LinkMan>{
}
