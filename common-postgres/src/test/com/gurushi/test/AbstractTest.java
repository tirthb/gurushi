package com.gurushi.test;

import java.util.ArrayList;
import java.util.List;

import org.apache.log4j.Logger;
import org.junit.Before;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.test.context.transaction.TransactionConfiguration;

import com.gurushi.dao.UtilDao;
import com.gurushi.dao.impl.TestDaoImpl;
import com.gurushi.domain.Audio;
import com.gurushi.domain.Author;
import com.gurushi.domain.Chapter;
import com.gurushi.domain.Commentary;
import com.gurushi.domain.Meaning;
import com.gurushi.domain.Scripture;
import com.gurushi.domain.Translation;
import com.gurushi.domain.Verse;
import com.gurushi.domain.VerseLine;

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(locations = { "/application-context.xml" })
@TransactionConfiguration(transactionManager="transactionManager", defaultRollback=false)
public class AbstractTest {
	
	private static Logger logger = Logger.getLogger(AbstractTest.class);
	
	public static final String LS = System.getProperty("line.separator");
	
	@Autowired
	private UtilDao utilDao;
	
	@Autowired
	private TestDaoImpl testDao;
	
	private Scripture gita;
	
	private Chapter chPreface, chIntro, c1;
	
	private Verse v1, v2;
	
	@Before
	public void setUp() {
		
		testDao.cleanupTables();
		
		createScripture();
		createChapters();
		createVerses();
		
	}
	
	private void createScripture() {
		gita = new Scripture("Bhagavad Gita");
		gita.setDescription("Lord Krishna clears the doubts of Arjuna in the middle of a battlefield.");
		
		gita = utilDao.save(gita);
	}
	
	private void createChapters() {
		chPreface = new Chapter(null, "Preface", gita);
		chPreface.setDescription("Originally I wrote Bhagavad-gītā As It Is in the form in which it is presented now.");
		
		chPreface = utilDao.save(chPreface);
		
		chIntro = new Chapter(null, "Introduction", gita);
		chIntro.setDescription("Bhagavad-gītā is also known as Gītopaniṣad. It is the essence of Vedic knowledge and one of the most important Upaniṣads in Vedic literature.");
		chIntro = utilDao.save(chIntro);
		
		c1 = new Chapter("1", "Observing the Armies on the Battlefield of Kurukṣetra", gita);
		c1 = utilDao.save(c1);
	}
	
	private void createVerses() {
		
		v1 = new Verse("1", c1);
		
		List<VerseLine> lines = new ArrayList<VerseLine>();
		lines.add(new VerseLine(v1, "dhṛtarāṣṭra uvāca", 10));
		lines.add(new VerseLine(v1, "dharma-kṣetre kuru-kṣetre", 20));
		lines.add(new VerseLine(v1, "samavetā yuyutsavaḥ", 30));
		lines.add(new VerseLine(v1, "māmakāḥ pāṇḍavāś caiva", 40));
		lines.add(new VerseLine(v1, "kim akurvata sañjaya", 50));
		
		v1.setVerseLines(lines);
		v1 = utilDao.save(v1);
		
		Author a = new Author("Srila Prabhupada", "http://en.wikipedia.org/wiki/A._C._Bhaktivedanta_Swami_Prabhupada");
		
		a = utilDao.save(a);
		
		Translation t = new Translation(a, v1);
		t.setText("Dhṛtarāṣṭra said: O Sañjaya, after my sons and the sons of Pāṇḍu assembled in the place of pilgrimage at Kurukṣetra, desiring to fight, what did they do?");
		t.setSourceUrl("http://vedabase.net/bg/1/1/en");
		
		t = utilDao.save(t);
		
		Commentary com = new Commentary(
				"Bhagavad-gītā is the widely read theistic science summarized in the Gītā-māhātmya (Glorification of the Gītā). There it says that one should read Bhagavad-gītā very scrutinizingly with the help of a person who is a devotee of Śrī Kṛṣṇa and try to understand it without personally motivated interpretations. The example of clear understanding is there in the Bhagavad-gītā itself, in the way the teaching is understood by Arjuna, who heard the Gītā directly from the Lord. If someone is fortunate enough to understand Bhagavad-gītā in that line of disciplic succession, without motivated interpretation, then he surpasses all studies of Vedic wisdom, and all scriptures of the world. One will find in the Bhagavad-gītā all that is contained in other scriptures, but the reader will also find things which are not to be found elsewhere. That is the specific standard of the Gītā. It is the perfect theistic science because it is directly spoken by the Supreme Personality of Godhead, Lord Śrī Kṛṣṇa." 
				, "http://vedabase.net/bg/1/1/en"
				, v1
				, a);
		utilDao.save(com);
		
		Author a2 = new Author("Sri Vishwanath Chakravarti Thakur", "http://en.wikipedia.org/wiki/Visvanatha_Chakravarti");
		
		a2 = utilDao.save(a2);
		
		com = new Commentary("How did Arjuna become bewildered and fall into ignorance? The speaker of the Mahabharata, Vaisampayana, started explaining the topic to Janmejaya in the Bhisma Parva, with the following words."
				, "http://www.bhagavad-gita.us/category/bhagavad-gita-chapter-1/"
				, v1
				, a2);
		utilDao.save(com);
		
		Author a3 = new Author("Sridhara Swami", null, "Rudra Vaisnava");
		
		a3 = utilDao.save(a3);
		
		com = new Commentary("How did Arjuna become bewildered and fall into ignorance? The speaker of the Mahabharata, Vaisampayana, started explaining the topic to Janmejaya in the Bhisma Parva, with the following words."
				, "http://www.bhagavad-gita.org/Gita/verse-01-01.html"
				, v1
				, a3);
		utilDao.save(com);
		
		utilDao.save(new Meaning("dhṛtarāṣṭraḥ uvāca", "King Dhṛtarāṣṭra said", 10, v1));
		utilDao.save(new Meaning("dharma-kṣetre", "in the place of pilgrimage", 20, v1));
		
		utilDao.save(new Audio("SP BG 01-01 London 1973-07-07 The Material World Means--etc", 
				"http://audio.iskcondesiretree.info/01_-_His_Divine_Grace_A_C_Bhaktivedanta_Swami_Srila_Prabhupada/Lectures/English/Bhagavad_Gita/Chapter-01/SP_BG_01-01_London_1973-07-07_The_Material_World_Means--etc.mp3"
				, 1
				, v1));
		
		//could not find a good video
		
		//Adding another verse
		
		v2 = new Verse(" 2 -5 ", c1);
		
		lines = new ArrayList<VerseLine>();
		lines.add(new VerseLine(v1, "sañjaya uvāca", 10));
		lines.add(new VerseLine(v1, "dṛṣṭvā tu pāṇḍavānīkam", 20));
		lines.add(new VerseLine(v1, "vyūḍhaḿ duryodhanas tadā", 30));
		lines.add(new VerseLine(v1, "ācāryam upasańgamya", 40));
		lines.add(new VerseLine(v1, "rājā vacanam abravīt", 50));
		
		v2.setVerseLines(lines);
		
		v2 = utilDao.save(v2);
		
		t = new Translation(a, v2);
		t.setText("Sañjaya said: O King, after looking over the army arranged in military formation by the sons of Pāṇḍu, King Duryodhana went to his teacher and spoke the following words.");
		t.setSourceUrl("http://vedabase.net/bg/1/2/en");
		
		t = utilDao.save(t);
		
		//Srila Prabhupada
		com = new Commentary(
				"Dhṛtarāṣṭra was blind from birth. Unfortunately, he was also bereft of spiritual vision. He knew very well that his sons were equally blind in the matter of religion, and he was sure that they could never reach an understanding with the Pāṇḍavas, who were all pious since birth." 
				, "http://vedabase.net/bg/1/2/en"
				, v2
				, a);
		utilDao.save(com);
		
		//Sri Vishwanath Chakravarti Thakur
		com = new Commentary("“O teacher, see this great army of the sons of Pandu, arranged for battle by your intelligent disciple, the son of Drupada.”"
				, "http://www.bhagavad-gita.us/category/bhagavad-gita-chapter-1/"
				, v2
				, a2);
		utilDao.save(com);
		
		//Sridhara Swami
		com = new Commentary("Having seen the armies of the Pandavas arrayed in military formation ready for battle, King Duryodhana approaches his preceptor Drona and spoke the following words."
				, "http://www.bhagavad-gita.org/Gita/verse-01-02.html"
				, v2
				, a3);
		utilDao.save(com);
		
		utilDao.save(new Meaning("sañjayaḥ uvāca", "Sañjaya said", 10, v2));
		utilDao.save(new Meaning("dṛṣṭvā", "after seeing", 20, v2));
		
		utilDao.save(new Audio("SP BG 01-02 London 1973-07-09 Death Is God" 
				, "http://audio.iskcondesiretree.info/01_-_His_Divine_Grace_A_C_Bhaktivedanta_Swami_Srila_Prabhupada/Lectures/English/Bhagavad_Gita/Chapter-01/SP_BG_01-02_London_1973-07-09_Death_Is_God.mp3"
				, 1
				, v2));
		
	}
	
}