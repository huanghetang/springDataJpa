package vip.hht.beans.one2many;

import javax.persistence.*;
import java.io.Serializable;
import java.util.HashSet;
import java.util.Set;

@Entity
@Table(name = "cst_customer")
public class Customer implements Serializable {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "cust_id")
    private Long custId;
    @Column(name = "cust_name")
    private String custName;
    @Column(name = "cust_source")
    private String custSource;
    @Column(name = "cust_industry")
    private String custIndustry;
    @Column(name = "cust_level")
    private String custLevel;
    //    @Column(name = "cust_address")
//    private String custAddress;
    @Column(name = "cust_phone")
    private String custPhone;

    //一对多关系，外键关系交给多的一方自己维护，mappedBy指定对方维护外键的属性,cascade级联删除(有主导方向，此时为客户主导删除联系人)
    @OneToMany(targetEntity = LinkMan.class, mappedBy = "customer",cascade = CascadeType.REMOVE)
    private Set<LinkMan> linkManSet = new HashSet<LinkMan>();


    public Long getCustId() {
        return custId;
    }

    public void setCustId(Long custId) {
        this.custId = custId;
    }

    public String getCustName() {
        return custName;
    }

    public void setCustName(String custName) {
        this.custName = custName;
    }

    public String getCustSource() {
        return custSource;
    }

    public void setCustSource(String custSource) {
        this.custSource = custSource;
    }

    public String getCustIndustry() {
        return custIndustry;
    }

    public void setCustIndustry(String custIndustry) {
        this.custIndustry = custIndustry;
    }

    public String getCustLevel() {
        return custLevel;
    }

    public void setCustLevel(String custLevel) {
        this.custLevel = custLevel;
    }

//    public String getCustAddress() {
//        return custAddress;
//    }
//
//    public void setCustAddress(String custAddress) {
//        this.custAddress = custAddress;
//    }

    public String getCustPhone() {
        return custPhone;
    }

    public void setCustPhone(String custPhone) {
        this.custPhone = custPhone;
    }

    public Set<LinkMan> getLinkManSet() {
        return linkManSet;
    }

    public void setLinkManSet(Set<LinkMan> linkManSet) {
        this.linkManSet = linkManSet;
    }


}