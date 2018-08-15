package vip.hht.beans.many2many;

import javax.persistence.*;
import java.io.Serializable;
import java.util.HashSet;
import java.util.Set;

@Entity
@Table(name = "sys_role")
public class SysRole implements Serializable {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "role_id")
    private Long roleId;
    @Column(name = "role_name")
    private String roleName;
    @Column(name = "role_memo")
    private String roleMemo;

    //建立多对多关系
    @ManyToMany(mappedBy = "sysRoleSet",targetEntity = SysUser.class)
//    @JoinTable(
//            name = "sys_user_role",
//            joinColumns = @JoinColumn(name = "role_id",referencedColumnName = "role_id"),
//            inverseJoinColumns = @JoinColumn(name = "user_id",referencedColumnName = "user_id")
//
//    )
    private Set<SysUser> sysUserSet = new HashSet<SysUser>();

    public Long getRoleId() {
        return roleId;
    }

    public void setRoleId(Long roleId) {
        this.roleId = roleId;
    }

    public String getRoleName() {
        return roleName;
    }

    public void setRoleName(String roleName) {
        this.roleName = roleName;
    }

    public String getRoleMemo() {
        return roleMemo;
    }

    public void setRoleMemo(String roleMemo) {
        this.roleMemo = roleMemo;
    }

    public Set<SysUser> getSysUserSet() {
        return sysUserSet;
    }

    public void setSysUserSet(Set<SysUser> sysUserSet) {
        this.sysUserSet = sysUserSet;
    }

    @Override
    public String toString() {
        return "SysRole{" +
                "roleId=" + roleId +
                ", roleName='" + roleName + '\'' +
                ", roleMemo='" + roleMemo + '\'' +
                ", sysUserSet=" + sysUserSet +
                '}';
    }
}