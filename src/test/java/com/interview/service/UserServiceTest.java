package com.interview.service;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotNull;

import org.apache.log4j.Logger;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.transaction.annotation.Transactional;

import com.interview.model.User;

/**
 * 
 * @author Sravan
 * 
 */
@ContextConfiguration(locations = { "classpath:test-context.xml" })
@RunWith(SpringJUnit4ClassRunner.class)
public class UserServiceTest {

	/**
	 * Inject Service to test class UserServiceTest.
	 */
	@Autowired
	private UserService userService;

	private static final Logger log = Logger.getLogger(UserServiceTest.class);

	/**
	 * Test Create Scenario.
	 * 
	 * @throws Exception .
	 */
	@Test
	@Transactional
	public final void testCreate() throws Exception {
		final User user = new User();
		user.setName("test123");
		user.setPassword("password");
		user.setUsername("username");
		userService.create(user);
		assertNotNull(user.getId());
	}

	/**
	 * Test Create, find and Update Scenario.
	 * 
	 * @throws Exception .
	 */
	@Test
	@Transactional
	public final void testCreateFindUpdate() throws Exception {
		final User user = new User();
		user.setName("test123");
		user.setPassword("password");
		user.setUsername("username");
		userService.create(user);
		assertNotNull(user.getId());
		final User getUser = userService.get(user.getId());
		assertNotNull(getUser);
		getUser.setName("333333");
		userService.update(getUser);
		final User updateUser = userService.get(getUser.getId());
		assertEquals("333333", updateUser.getName());
	}

	/**
	 * Test Create, find and Delete Scenario.
	 * 
	 * @throws Exception .
	 */
	@Test
	@Transactional
	public final void testCreateFindDelete() throws Exception {
		final User user = new User();
		user.setName("test123");
		user.setPassword("password");
		user.setUsername("username");
		userService.create(user);
		assertNotNull(user.getId());
		final User getUser = userService.get(user.getId());
		assertNotNull(getUser);

		userService.delete(getUser.getId());

	}

	/**
	 * Test Create and find Scenario.
	 * 
	 * @throws Exception .
	 */
	@Test
	@Transactional
	public final void testCreateFind() throws Exception {
		User user = new User();
		user.setName("test123");
		user.setUsername("mujoko");
		user.setPassword("123");
		userService.create(user);
		assertNotNull(user.getId());
		User getUser = userService.get(user.getId());
		assertNotNull(getUser);
		String id = userService.authenticate("mujoko", "123");
		assertNotNull(id);
	}

}
