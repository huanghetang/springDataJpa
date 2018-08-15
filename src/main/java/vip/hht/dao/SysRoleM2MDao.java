package vip.hht.dao;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import vip.hht.beans.many2many.SysRole;

/**
 * @author zhoumo
 * @datetime 2018/8/15 8:54
 * @desc
 */
public interface SysRoleM2MDao extends JpaRepository<SysRole,Long>,JpaSpecificationExecutor<SysRole> {
}
