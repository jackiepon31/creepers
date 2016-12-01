package com.fosun.fc.projects.creepers.spider;

import org.junit.experimental.categories.Category;
import org.springframework.test.annotation.DirtiesContext;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.transaction.TransactionConfiguration;

import com.fosun.fc.modules.test.category.UnStable;
import com.fosun.fc.modules.test.spring.SpringTransactionalTestCase;

@Category(UnStable.class)
@DirtiesContext
@ContextConfiguration(locations = { "/applicationContext.xml", "/redis/applicationContext-redis.xml",
        "/schedule/applicationContext-spring-scheduler.xml" })
@TransactionConfiguration(defaultRollback = false)
public class SpiderBaseTest extends SpringTransactionalTestCase {
    
}
