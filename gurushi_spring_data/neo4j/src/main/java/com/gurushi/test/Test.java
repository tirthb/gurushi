package com.gurushi.test;	

import com.gurushi.data.Author;
import com.gurushi.data.Chapter;
import com.gurushi.data.Commentary;
import com.gurushi.data.Scripture;
import com.gurushi.data.Translation;
import com.gurushi.data.Verse;

public class Test {
	
	public static final String LS = System.getProperty("line.separator");

	/**
	 * @param args
	 */
	public static void main(String[] args) {
		
		Scripture gita = new Scripture("Bhagavad Gita");
		
		Chapter c1 = new Chapter("-1", "Preface", gita);
		//Also need to set the previous and next chapter
		
		c1.setDescription("Originally I wrote Bhagavad-gītā As It Is in the form in which it is presented now.");
		
		Chapter c2 = new Chapter("-1", "Introduction", gita);
		c1.setNextChapter(c2);
		
		c2.setDescription("Bhagavad-gītā is also known as Gītopaniṣad. It is the essence of Vedic knowledge and one of the most important Upaniṣads in Vedic literature.");
		
		Chapter c3 = new Chapter("1", "Observing the Armies on the Battlefield of Kurukṣetra", gita);
		c2.setNextChapter(c3);
		
		Verse v = new Verse("1", c3);
		//Also need to set the previous and next verse if any
		
		v.setText("dhṛtarāṣṭra uvāca"
					+ LS + "dharma-kṣetre kuru-kṣetre"
					+ LS + "samavetā yuyutsavaḥ"
					+ LS + "māmakāḥ pāṇḍavāś caiva"
					+ LS + "kim akurvata sañjaya");
		
		Author a = new Author("Srila Prabhupada");
		
		Translation t = new Translation(a);
		v.setTranslation(t);
		t.setText("Dhṛtarāṣṭra said: O Sañjaya, after my sons and the sons of Pāṇḍu assembled in the place of pilgrimage at Kurukṣetra, desiring to fight, what did they do?");
		
		//the commentary is being added to the verse through the constructor
		v.addCommentary(new Commentary("Bhagavad-gītā is the widely read theistic science summarized in the Gītā-māhātmya (Glorification of the Gītā). There it says that one should read Bhagavad-gītā very scrutinizingly with the help of a person who is a devotee of Śrī Kṛṣṇa and try to understand it without personally motivated interpretations. The example of clear understanding is there in the Bhagavad-gītā itself, in the way the teaching is understood by Arjuna, who heard the Gītā directly from the Lord. If someone is fortunate enough to understand Bhagavad-gītā in that line of disciplic succession, without motivated interpretation, then he surpasses all studies of Vedic wisdom, and all scriptures of the world. One will find in the Bhagavad-gītā all that is contained in other scriptures, but the reader will also find things which are not to be found elsewhere. That is the specific standard of the Gītā. It is the perfect theistic science because it is directly spoken by the Supreme Personality of Godhead, Lord Śrī Kṛṣṇa." 
						, "http://vedabase.net/something"
						, a));
		
		a = new Author("Sri Vishwanath Chakravarti Thakur");
		v.addCommentary(new Commentary("Another commentary."
						, "http://bg.net/something"
						, a));
		
		v.addMeaning("dhṛtarāṣṭraḥ uvāca", "King Dhṛtarāṣṭra said");
		
		//set next previous verse
		
		//printing out for testing
		
		System.out.println(gita);
		System.out.println(c1);
		System.out.println(c2);
		System.out.println(c3);
		
	}

}

/*
dhṛtarāṣṭra uvāca
dharma-kṣetre kuru-kṣetre
samavetā yuyutsavaḥ
māmakāḥ pāṇḍavāś caiva
kim akurvata sañjaya
SYNONYMS

dhṛtarāṣṭraḥ uvāca — King Dhṛtarāṣṭra said; dharma-kṣetre — in the place of pilgrimage; kuru-kṣetre — in the place named Kurukṣetra; samavetāḥ — assembled; yuyutsavaḥ — desiring to fight; māmakāḥ — my party (sons); pāṇḍavāḥ — the sons of Pāṇḍu; ca — and; eva — certainly; kim — what; akurvata — did they do; sañjaya — O Sañjaya.
TRANSLATION

Dhṛtarāṣṭra said: O Sañjaya, after my sons and the sons of Pāṇḍu assembled in the place of pilgrimage at Kurukṣetra, desiring to fight, what did they do?
PURPORT

Bhagavad-gītā is the widely read theistic science summarized in the Gītā-māhātmya (Glorification of the Gītā). There it says that one should read Bhagavad-gītā very scrutinizingly with the help of a person who is a devotee of Śrī Kṛṣṇa and try to understand it without personally motivated interpretations. The example of clear understanding is there in the Bhagavad-gītā itself, in the way the teaching is understood by Arjuna, who heard the Gītā directly from the Lord. If someone is fortunate enough to understand Bhagavad-gītā in that line of disciplic succession, without motivated interpretation, then he surpasses all studies of Vedic wisdom, and all scriptures of the world. One will find in the Bhagavad-gītā all that is contained in other scriptures, but the reader will also find things which are not to be found elsewhere. That is the specific standard of the Gītā. It is the perfect theistic science because it is directly spoken by the Supreme Personality of Godhead, Lord Śrī Kṛṣṇa.
*/