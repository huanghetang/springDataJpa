package vip.hht.beans;

/**
 * @author zhoumo
 * @datetime 2018/8/14 21:54
 * @desc
 */
public class A implements Cloneable {
    @Override
    protected A clone() throws CloneNotSupportedException {
        return (A)super.clone();
    }

    private String name;
    private int age;

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public static void main(String[] args) throws CloneNotSupportedException {
        A a = new A();
        A aa = a.clone();
        System.out.println(a.getName()==aa.getName());
    }
}
