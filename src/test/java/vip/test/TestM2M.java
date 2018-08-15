package vip.test;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.test.annotation.Rollback;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.transaction.annotation.Transactional;
import vip.hht.beans.many2many.SysRole;
import vip.hht.beans.many2many.SysUser;
import vip.hht.dao.SysRoleM2MDao;
import vip.hht.dao.SysUserM2MDao;

import javax.persistence.criteria.*;
import java.util.List;
import java.util.Set;

/**
 * @author zhoumo
 * @datetime 2018/8/15 8:52
 * @desc many to many
 */
@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration("classpath:ApplicationContext.xml")
public class TestM2M {
    @Autowired
    private SysUserM2MDao sysUserDao;
    @Autowired
    private SysRoleM2MDao sysRoleDao;


    @Test
    @Transactional(timeout = 6000)
    @Rollback(false)
    public void test1() {
        SysUser sysUser = new SysUser();
        sysUser.setUserName("管理员1");

        SysUser sysUser2 = new SysUser();
        sysUser2.setUserName("普通用户");

        SysRole sysRole = new SysRole();
        sysRole.setRoleName("角色1");


        SysRole sysRole2 = new SysRole();
        sysRole2.setRoleName("角色2");


        //角色放弃外键维护权，让用户维护
        sysUser.getSysRoleSet().add(sysRole);
//        sysUser.getSysRoleSet()
        sysUser.getSysRoleSet().add(sysRole2);

        sysUser2.getSysRoleSet().add(sysRole2);

//        sysRole.getSysUserSet().add(sysUser);
//        sysRole.getSysUserSet().add(sysUser2);
//        sysRole2.getSysUserSet().add(sysUser2);

        sysUserDao.save(sysUser);
        sysUserDao.save(sysUser2);

        sysRoleDao.save(sysRole);
        sysRoleDao.save(sysRole2);
    }


    @Test
    @Transactional
    @Rollback(false)
    public void test1234() {
        //查询用户和角色
        sysUserDao.deleteById(1L);


    }

    /**
     * 修改
     */
    @Test
    @Transactional
    @Rollback(false)
    public void test222() {
        SysRole sysRole4 = new SysRole();
        sysRole4.setRoleId(4L);
        sysRole4.setRoleName("角色4");

        SysUser sysUser = new SysUser();
        sysUser.setUserId(4L);
        sysUser.setUserName("普通用户4");

        SysRole sysRole1 = new SysRole();
        sysRole1.setRoleId(1L);
        SysRole sysRole2 = new SysRole();
        sysRole2.setRoleId(2L);
        SysRole sysRole3 = new SysRole();
        sysRole3.setRoleId(3L);

        sysUser.getSysRoleSet().add(sysRole4);
        sysUser.getSysRoleSet().add(sysRole1);
        sysUser.getSysRoleSet().add(sysRole2);
        sysUser.getSysRoleSet().add(sysRole3);

        sysRoleDao.save(sysRole4);
        sysUserDao.save(sysUser);


    }

    @Test
    @Transactional
    public void test123() {
        //查询用户和角色
//        SysUser sysUser = sysUserDao.findById(2l).orElse(null);
//        System.out.println("sysUser = " + sysUser.getSysRoleSet());
//        sysUser.getSysRoleSet().forEach(sysRole -> System.out.println("sysRole = " + sysRole));
        SysUser sysUser = sysUserDao.queryUserById(4L);
        Set<SysRole> sysRoleSet = sysUser.getSysRoleSet();
        for (SysRole sysRole : sysRoleSet) {
            System.out.println("sysRole = " + sysRole.getRoleName());
        }

    }

    /**
     * 多对多查询
     * root.getModel()获取当前的模型对象，然后关联属性
     */
    @Test
    @Transactional
    public void test123412() {
        //查询用户和角色
        Specification<SysUser> spec = new Specification<SysUser>() {
            @Override
            public Predicate toPredicate(Root<SysUser> root, CriteriaQuery<?> query, CriteriaBuilder criteriaBuilder) {
                SetJoin<SysUser, SysRole> join = root.join(root.getModel().getSet("sysRoleSet", SysRole.class), JoinType.INNER);
//                Path<Object> path = join.get("sysRoleSet");
                Path<Object> path1 = join.get("roleName");
                Predicate predicate = criteriaBuilder.equal(path1, "角色1");
                return predicate;
            }
        };

        List<SysUser> sysUser = sysUserDao.findAll(spec);
        System.out.println();
        Set<SysRole> sysRoleSet = sysUser.get(0).getSysRoleSet();
        for (SysRole sysRole : sysRoleSet) {
            System.out.println(sysRole.getRoleId());
        }


    }

}
