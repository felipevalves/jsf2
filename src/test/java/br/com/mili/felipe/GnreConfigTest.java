package br.com.mili.felipe;

import static org.junit.jupiter.api.Assertions.*;

import java.util.List;

import javax.inject.Inject;

import org.jboss.weld.junit5.EnableWeld;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;

import br.com.felipe.gnre.dao.GnreConfigDao;
import br.com.felipe.gnre.dao.IGnreConfigDao;
import br.com.felipe.gnre.entity.GnreConfig;


class GnreConfigTest {
	
	private IGnreConfigDao gnreDao;

	@BeforeEach
	void setUp() throws Exception {
		//injecao manual
		gnreDao = new GnreConfigDao();
	}

	@DisplayName("Test GnreConfigTest.mock()")
	@Test
	void test_mock() {
		assertTrue(true);
	}
	
	@DisplayName("Test GnreConfigTest.test_find_all_config_gnre()")
	@Test
	void test_find_all_config_gnre() {
		
		List<GnreConfig> list = gnreDao.findAllConfig();
		
		assertNotNull(list);
		System.out.println(list);
	}

}
