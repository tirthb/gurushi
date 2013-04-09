package com.gurushi.test;	

import com.gurushi.bo.Author;
import com.gurushi.bo.Chapter;
import com.gurushi.bo.Commentary;
import com.gurushi.bo.Scripture;
import com.gurushi.bo.Translation;
import com.gurushi.bo.Verse;

public class Test {
	
	public static final String LS = System.getProperty("line.separator");

	/**
	 * @param args
	 */
	public static void main(String[] args) {
		
		Scripture gita = new Scripture("Bhagavad Gita");
		
		Chapter c = new Chapter("-1", "Preface", gita);
		c.setDescription("Originally I wrote Bhagavad-gītā As It Is in the form in which it is presented now.");
		
		c = new Chapter("-1", "Introduction", gita);
		c.setDescription("Bhagavad-gītā is also known as Gītopaniṣad. It is the essence of Vedic knowledge and one of the most important Upaniṣads in Vedic literature.");
		
		c = new Chapter("1", "Observing the Armies on the Battlefield of Kurukṣetra", gita);
		Verse v = new Verse(1, c);
		v.setText("dhṛtarāṣṭra uvāca"
					+ LS + "dharma-kṣetre kuru-kṣetre"
					+ LS + "samavetā yuyutsavaḥ"
					+ LS + "māmakāḥ pāṇḍavāś caiva"
					+ LS + "kim akurvata sañjaya");
		
		Author a = new Author("Srila Prabhupada");
		
		Translation t = new Translation(v, a);
		t.setText("Dhṛtarāṣṭra said: O Sañjaya, after my sons and the sons of Pāṇḍu assembled in the place of pilgrimage at Kurukṣetra, desiring to fight, what did they do?");
		
		//the commentary is being added to the verse through the constructor
		new Commentary("Bhagavad-gītā is the widely read theistic science summarized in the Gītā-māhātmya (Glorification of the Gītā). There it says that one should read Bhagavad-gītā very scrutinizingly with the help of a person who is a devotee of Śrī Kṛṣṇa and try to understand it without personally motivated interpretations. The example of clear understanding is there in the Bhagavad-gītā itself, in the way the teaching is understood by Arjuna, who heard the Gītā directly from the Lord. If someone is fortunate enough to understand Bhagavad-gītā in that line of disciplic succession, without motivated interpretation, then he surpasses all studies of Vedic wisdom, and all scriptures of the world. One will find in the Bhagavad-gītā all that is contained in other scriptures, but the reader will also find things which are not to be found elsewhere. That is the specific standard of the Gītā. It is the perfect theistic science because it is directly spoken by the Supreme Personality of Godhead, Lord Śrī Kṛṣṇa." 
						, a, v);
		
		a = new Author("Sri Vishwanath Chakravarti Thakur");
		new Commentary("Another commentary.", a, v);
		
		v.putMeaning("dhṛtarāṣṭraḥ uvāca", "King Dhṛtarāṣṭra said");
		
		//printing out for testing
		
		System.out.println(gita);
		
		for (Chapter ch : gita.getChapters()) {
			System.out.println(ch);
			System.out.println("---------------");
			if (ch.getVerses() != null) {
				for (Verse ve : ch.getVerses()) {
					System.out.println(ve);	
				}
			}
		}

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