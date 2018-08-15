package vip.hht.utils;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;

/**
 * @author zhoumo
 * @datetime 2018/8/11 19:55
 * @desc
 */
public class EntityManagerUtils {
    private static EntityManagerFactory entityManagerFactory;

    //        static{
//
//    }
    public synchronized static EntityManager getEntityManager(String unitName) {
        if (entityManagerFactory == null) {
            entityManagerFactory = Persistence.createEntityManagerFactory(unitName);
        }
        return entityManagerFactory.createEntityManager();
    }

    public static void close() {
        if (entityManagerFactory != null) {
            entityManagerFactory.close();
        }

    }


}
