package org.teamneko.schrodinger.sql.test;

import static org.junit.Assert.*;

import org.apache.commons.dbutils.handlers.BeanHandler;
import org.junit.Test;
import org.teamneko.schrodinger.postgres.test.TestPostgresDatabase;
import org.teamneko.schrodinger.sql.FilteredQueryRunner;

public class FilteredQueryRunnerTest {

	@Test
	public void test() {
		FilteredQueryRunner runner = new FilteredQueryRunner(TestPostgresDatabase.get().getDataSource());
		assertNull(runner.queryFiltered("SELECT * WHERE id=?", new BeanHandler<Object>(Object.class), 0));
		assertEquals(0, runner.updateFiltered("INSERT VALUE(?)", 0));
	}
}
