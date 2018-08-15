package vip.hht.dao;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.data.jpa.repository.Query;
import vip.hht.beans.many2many.SysUser;

import java.util.List;

/**
 * @author zhoumo
 * @datetime 2018/8/15 8:55
 * @desc
 */
public interface SysUserM2MDao extends JpaRepository<SysUser,Long>,JpaSpecificationExecutor<SysUser> {

    @Query(value = "select u.* ,r.* from sys_user u,sys_role r,sys_user_role sur" +
            "   where u.user_id=sur.user_id and r.role_id = sur.role_id and u.user_id = ?1 ",nativeQuery = true)
    public SysUser queryUserById(long userId);
}
