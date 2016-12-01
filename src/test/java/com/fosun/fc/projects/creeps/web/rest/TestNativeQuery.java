package com.fosun.fc.projects.creeps.web.rest;

import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.Query;

import org.junit.Test;
import org.junit.experimental.categories.Category;
import org.springframework.test.annotation.DirtiesContext;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.transaction.TransactionConfiguration;

import com.fosun.fc.modules.test.category.UnStable;

/**
 * 测试JPA原生SQL查询
 */

@Category(UnStable.class)
@DirtiesContext
@ContextConfiguration(locations = { "/applicationContext.xml" })
@TransactionConfiguration(transactionManager = "transactionManager", defaultRollback = false)
public class TestNativeQuery {
    // EntityManagerFactory emf = null;

    @PersistenceContext
    private EntityManager em;

    /*
     * @Before public void before() { //根据在persistence.xml中配置的persistence-unit
     * name 创建EntityManagerFactory // emf =
     * Persistence.createEntityManagerFactory("myJpa"); }
     * 
     * @After public void after() { //关闭EntityManagerFactory if(null != emf) {
     * emf.close(); } }
     */

    /**
     * 查询的结果是对象数组的集合
     */

    @SuppressWarnings("rawtypes")
    public void testNativeQuery() {
        System.out.println("Begin:......");
        // EntityManager em =
        // entityManagerFactory.getNativeEntityManagerFactory().createEntityManager();
        // 定义SQL
        String sql = "SELECT * FROM t_dict";
        // 创建原生SQL查询QUERY实例
        Query query = em.createNativeQuery(sql);
        // 执行查询，返回的是对象数组(Object[])列表,
        // 每一个对象数组存的是相应的实体属性
        List objecArraytList = query.getResultList();

        System.out.println("list:" + objecArraytList);

        for (int i = 0; i < objecArraytList.size(); i++) {
            Object[] obj = (Object[]) objecArraytList.get(i);
            // 使用obj[0],obj[1],obj[2]...取出属性
            System.out.println(obj);
        }
        em.close();
    }

    @Test
    public void run() {
        // testNativeQuery();
    }

}
