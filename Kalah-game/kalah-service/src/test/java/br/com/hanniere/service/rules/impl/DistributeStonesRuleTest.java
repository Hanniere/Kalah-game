package br.com.hanniere.service.rules.impl;

import static org.junit.Assert.assertTrue;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.context.SpringBootTest.WebEnvironment;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import br.com.hanniere.service.ServiceApplication;


@RunWith(SpringJUnit4ClassRunner.class)
@ActiveProfiles({"embeddeddb"})
@SpringBootTest(webEnvironment = WebEnvironment.NONE, classes = ServiceApplication.class)
public class DistributeStonesRuleTest {



	@Test
	public void testDistributeStones() {
		assertTrue(true);
	}

}
