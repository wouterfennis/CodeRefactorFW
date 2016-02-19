package domain.FamilyWeb;

import static org.junit.Assert.*;

import java.io.IOException;

import org.junit.Before;
import org.junit.Test;

public class AnswerTest {
	

	private Answer answer = null;
	
	@Before
	public void setUp() throws IOException{
		answer = new Answer("Many", 1);
	}
	/**
	 * Test the normal constructor
	 * 
	 * 
	 */
	@Test
	public void AnswerConstructorTest1() {
		assertEquals(answer.getAnswer(), "Many" );
	}

	/**
	 * Test the getAnswer method
	 */
	@Test
	public void getAnswerTest() {
		assertEquals(answer.getAnswer(), "Many" );
	}

	/**
	 * Test the setAnswer method
	 */
	@Test
	public void setAnswerTest() {
		answer.setAnswer("Many");
		assertEquals(answer.getAnswer(), "Many" );
	}

	/**
	 * Test the getAnswer_id method
	 */
	@Test
	public void getAnswer_idTest() {
		assertEquals(answer.getAnswer_id(), 1 );
	}

	/**
	 * Test the setAnswer_id method
	 */
	@Test
	public void setAnswer_idTest() {
		answer.setAnswer_id(2);
		assertEquals(answer.getAnswer_id(), 2 );
	}
	/**
	 * Test the toString method
	 */
	@Test
	public void toStringTest() {
		assertEquals(answer.toString(), "Answer [Answer_id = 1, Answer = Many]");
	}
}