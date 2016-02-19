package domain.FamilyWeb;

import static org.junit.Assert.*;

import java.io.IOException;
import java.util.Date;

import org.junit.Before;
import org.junit.Test;

public class ClientTest {
	

	private Client client = null;
	
	@Before
	public void setUp() throws IOException{
		client = new Client("Wouter", "Fennis", new Date(),
				"1214BW", "Rozenstraat", "83", "Hilversum",
				"Nederlandse", "0356837473",
				"0620433040", "wouter_hilversum@hotmail.com", "0120",
				new Date());
	}
	/**
	 * test setForename method
	 * 
	 * 
	 */
	@Test
	public void setForenameTest() {
		client.setForename("Many");
		assertEquals(client.getForename(), "Many" );
	}
	
	/**
	 * test setSurname method
	 * 
	 * 
	 */
	@Test
	public void setSurnameTest() {
		client.setSurname("Many");
		assertEquals(client.getSurname(), "Many" );
	}
	
	/**
	 * test setDateOfBirth method
	 * 
	 * 
	 */
	@Test
	public void setDateOfBirthTest() {
		Date d = new Date();
		client.setDateOfBirth(d);;
		assertEquals(client.getDateOfBirth(), d );
	}

	/**
	 * test setPostcode method
	 * 
	 * 
	 */
	@Test
	public void setPostcodeTest() {
		client.setPostcode("1214BB");
		assertEquals(client.getPostcode(), "1214BB" );
	}
	
	/**
	 * test setStreet method
	 * 
	 * 
	 */
	@Test
	public void setStreetTest() {
		client.setStreet("Nijenoord");
		assertEquals(client.getStreet(), "Nijenoord" );
	}
	
	/**
	 * test setHouseNumber method
	 * 
	 * 
	 */
	@Test
	public void setHouseNumberTest() {
		client.setHouseNumber("Nijenoord");
		assertEquals(client.getHouseNumber(), "Nijenoord" );
	}
	
	/**
	 * test setCity method
	 * 
	 * 
	 */
	@Test
	public void setCityTest() {
		client.setCity("Utrecht");
		assertEquals(client.getCity(), "Utrecht" );
	}
	
	/**
	 * test setNationality method
	 * 
	 * 
	 */
	@Test
	public void setNationalityTest() {
		client.setNationality("Duitsland");
		assertEquals(client.getNationality(), "Duitsland" );
	}
	
	/**
	 * test setTelephoneNumber method
	 * 
	 * 
	 */
	@Test
	public void setTelephoneNumberTest() {
		client.setTelephoneNumber("0356837471");
		assertEquals(client.getTelephoneNumber(), "0356837471" );
	}
	
	/**
	 * test setMobilePhoneNumber method
	 * 
	 * 
	 */
	@Test
	public void setMobilePhoneNumberTest() {
		client.setMobilePhoneNumber("0620433042");
		assertEquals(client.getMobilePhoneNumber(), "0620433042" );
	}
	
	/**
	 * test setEmail method
	 * 
	 * 
	 */
	@Test
	public void setEmailTest() {
		client.setEmail("bram_hilversum@hotmail.com");
		assertEquals(client.getEmail(), "bram_hilversum@hotmail.com" );
	}
	
	/**
	 * test setDateCreated method
	 * 
	 * 
	 */
	@Test
	public void setDateCreatedTest() {
		Date d = new Date();
		client.setDateCreated(d);
		assertEquals(client.getDateCreated(), d );
	}
}