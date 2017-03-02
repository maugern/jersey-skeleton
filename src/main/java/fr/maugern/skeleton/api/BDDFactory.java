package fr.maugern.skeleton.api;

import org.skife.jdbi.v2.DBI;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.sqlite.SQLiteDataSource;

import javax.inject.Singleton;

@Singleton
public class BDDFactory {
	private static final Logger logger = LoggerFactory.getLogger(BDDFactory.class);

	private static DBI dbi = null;

	/*
	 * Utility classes should not have public constructors
	 */
	private BDDFactory () {
		throw new IllegalAccessError("Utility class");
	}

	public static DBI getDbi() {
		if(dbi == null) {
			SQLiteDataSource ds = new SQLiteDataSource();
			ds.setUrl("jdbc:sqlite:" + System.getProperty("java.io.tmpdir") + System.getProperty("file.separator") + "data.db");
			logger.debug("Sqlite database URL : " + ds.getUrl());
			dbi = new DBI(ds);
		}
		return dbi;
	}
}
