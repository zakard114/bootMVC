package com.heeju.bootmvc;

import lombok.Cleanup;
import lombok.extern.log4j.Log4j2;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import javax.sql.DataSource;
import java.sql.Connection;
import java.sql.SQLException;

@SpringBootTest
@Log4j2
public class DataSourceTests {

    @Autowired
    private DataSource dataSource;

    @Test
    public void testConnection() throws SQLException {

        @Cleanup //When doing IO processing or JDBC coding, it was quite cumbersome
        // to call the close() method through the finally clause of the try-catch-finally
        // statement. However, using the @Cleanup annotation ensures that the resource is
        // automatically closed.
        Connection con = dataSource.getConnection();

        log.info(con);

        Assertions.assertNotNull(con);
    }

}
