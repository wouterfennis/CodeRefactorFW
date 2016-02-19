package domain.FamilyWeb;

import static org.junit.Assert.*;

import java.io.IOException;

import org.junit.Before;
import org.junit.Test;

public class CategoryTest {
	
	private Category category;

	@Before
	public void setUp() throws IOException{
		category = new Category("Familie", 1);
	}

	/**
	 * test getName method
	 */
	@Test
	public void getNameTest() {
		assertEquals(category.getName(), "Familie" );
	}

	/**
	 * test setName method
	 */
	@Test
	public void setNameTest() {
		category.setName("Politie");
		assertEquals(category.getName(), "Politie" );
	}

	/**
	 * test toString method
	 */
	@Test
	public void toStringTest() {
		assertEquals(category.toString(), "Category [Name = Familie]" );
	}

	/**
	 * test getGroup_id method
	 */
	@Test
	public void getGroup_idTest() {
		assertEquals(category.getGroup_id(), 1);
	}

	/**
	 * test setGroup_id method
	 */
	@Test
	public void setGroup_idTest() {
		category.setName("Politie");
		assertEquals(category.getName(), "Politie" );
	}
}