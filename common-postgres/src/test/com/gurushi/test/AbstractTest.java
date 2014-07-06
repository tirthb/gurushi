package com.gurushi.test;

import org.apache.log4j.Logger;
import org.junit.Before;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.test.context.transaction.TransactionConfiguration;

import com.gurushi.dao.UtilDao;
import com.gurushi.dao.impl.TestDaoImpl;
import com.gurushi.domain.Chapter;
import com.gurushi.domain.Scripture;

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(locations = { "/application-context.xml" })
@TransactionConfiguration(transactionManager="transactionManager", defaultRollback=false)
public class AbstractTest {
	
	private static Logger logger = Logger.getLogger(AbstractTest.class);
	
	@Autowired
	private UtilDao utilDao;
	
	@Autowired
	private TestDaoImpl testDao;
	
	@Before
	public void setUp() {
		
		testDao.cleanupTables();
		
		Scripture gita = new Scripture("Bhagavad Gita");
		gita.setDescription("Lord Krishna clears the doubts of Arjuna in the middle of a battlefield.");
		
		gita = utilDao.save(gita);
		
		logger.info("::::::::::::::Scripture::" + gita);
		
		Chapter chPreface = new Chapter(null, "Preface", gita);
		chPreface.setDescription("Originally I wrote Bhagavad-gītā As It Is in the form in which it is presented now.");
		
		chPreface = utilDao.save(chPreface);
		
		Chapter chIntro = new Chapter(null, "Introduction", gita);
		chIntro.setDescription("Bhagavad-gītā is also known as Gītopaniṣad. It is the essence of Vedic knowledge and one of the most important Upaniṣads in Vedic literature.");
		chIntro = utilDao.save(chIntro);
		
		Chapter c1 = new Chapter("1", "Observing the Armies on the Battlefield of Kurukṣetra", gita);
		c1 = utilDao.save(c1);
		
	}
	
}