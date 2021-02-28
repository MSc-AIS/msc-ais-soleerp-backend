package msc.ais.soleerp.db;

import com.typesafe.config.Config;
import com.typesafe.config.ConfigException;
import com.typesafe.config.ConfigFactory;
import org.apache.commons.dbcp2.BasicDataSource;
import org.apache.commons.lang3.SystemUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.io.File;
import java.sql.Connection;
import java.sql.SQLException;

/**
 * @author Konstantinos Raptis [kraptis at unipi.gr] on 19/2/21.
 */
public class DBCPDataSource {

    private static final Logger LOGGER = LoggerFactory.getLogger(DBCPDataSource.class);
    private static final BasicDataSource DATA_SOURCE = new BasicDataSource();

    static {
        final String confFileName = "db.conf";
        final String confFilePath = SystemUtils.getUserDir()
            .getParentFile().getParent()
            + "/" + confFileName;

        try {
            final Config config = ConfigFactory
                .parseFile(new File(confFilePath))
                .resolve();
            final String dbName = config.getConfig("db").getString("dbName");
            final String dbPath = config.getConfig("db").getString("dbPath");
            final String dbUser = config.getConfig("db").getString("dbUser");
            final String dbPass = config.getConfig("db").getString("dbPass");

            LOGGER.info("DB Name: " + dbName);
            LOGGER.info("DB Path: " + dbPath);

            DATA_SOURCE.setUrl(dbPath);
            DATA_SOURCE.setUsername(dbUser);
            DATA_SOURCE.setPassword(dbPass);
            DATA_SOURCE.setMinIdle(5);
            DATA_SOURCE.setMaxIdle(10);
            DATA_SOURCE.setTimeBetweenEvictionRunsMillis(100);
            DATA_SOURCE.setMaxOpenPreparedStatements(100);
        } catch (ConfigException e) {
            final String sqliteConfFileStructure = "db {" + System.lineSeparator()
                + "\t dbName: \"the-db-name\"" + System.lineSeparator()
                + "\t dbSchema: \"the-db-schema\"" + System.lineSeparator()
                + "\t dbPath: \"the-db-path\"" + System.lineSeparator()
                + "\t dbUser: \"the-db-user\"" + System.lineSeparator()
                + "\t dbPass: \"the-db-pass\"" + System.lineSeparator()
                + "}";
            LOGGER.error("Unable to find db.conf file in: " + confFilePath);
            LOGGER.error("Please add db.conf in path with the following structure: "
                + System.lineSeparator()
                + sqliteConfFileStructure);
        }
    }

    private DBCPDataSource() {
    }

    public static Connection getConnection() throws SQLException {
        return DATA_SOURCE.getConnection();
    }

}
