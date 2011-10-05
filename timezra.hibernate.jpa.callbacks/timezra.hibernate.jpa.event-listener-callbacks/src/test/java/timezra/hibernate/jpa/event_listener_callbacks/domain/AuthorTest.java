package timezra.hibernate.jpa.event_listener_callbacks.domain;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotNull;
import static org.junit.Assert.assertNull;
import static org.junit.Assert.assertTrue;

import java.util.Calendar;
import java.util.Date;
import java.util.Locale;

import javax.annotation.Resource;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import timezra.hibernate.jpa.event_listener_callbacks.service.AuthorService;

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration("/applicationContext.xml")
public class AuthorTest {

	@Resource
	private AuthorService authorService;

	private Author testAuthor;

	@Before
	public void setup() {
		testAuthor = new Author();
		testAuthor.setName("timezra");
		authorService.create(testAuthor);
	}

	@After
	public void tearDown() {
		authorService.delete(testAuthor);
	}

	@Test
	public void theCreationDateIsSetAutomatically() {
		assertNotNull(testAuthor.getDateCreated());
	}

	@Test
	public void theUpdatedDateIsSetAutomaticallyOnCreation() {
		assertEquals(testAuthor.getDateCreated(), testAuthor.getLastUpdated());
	}

	@Test
	public void theUpdatedDateIsSetAutomaticallyOnUpdate() {
		testAuthor.setDateOfBirth(new Date());

		authorService.update(testAuthor);

		assertTrue(testAuthor.getDateCreated().before(testAuthor.getLastUpdated()));
	}

	@Test
	public void theAgeIsSetAutomaticallyWhenTheAuthorIsLoaded() {
		final Integer expectedAge = 42;
		final Calendar birthDate = Calendar.getInstance(Locale.getDefault());
		birthDate.add(Calendar.YEAR, -expectedAge);
		testAuthor.setDateOfBirth(birthDate.getTime());
		authorService.update(testAuthor);

		assertNull(testAuthor.getAge());
		assertEquals(expectedAge, authorService.findByName(testAuthor.getName()).getAge());
	}
}
