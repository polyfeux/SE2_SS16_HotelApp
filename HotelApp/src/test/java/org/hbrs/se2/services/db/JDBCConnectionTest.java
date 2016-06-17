package org.hbrs.se2.services.db;

import java.sql.PreparedStatement;
import java.sql.Statement;
import org.junit.After;
import org.junit.AfterClass;
import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Test;
import static org.junit.Assert.*;

/**
 *
 * @author Felix
 */
public class JDBCConnectionTest {
    
    public JDBCConnectionTest() {
    }
    
    @BeforeClass
    public static void setUpClass() {
    }
    
    @AfterClass
    public static void tearDownClass() {
    }
    
    @Before
    public void setUp() {
    }
    
    @After
    public void tearDown() {
    }

    /**
     * Test of getInstance method, of class JDBCConnection.
     */
    @org.junit.Test
    public void testGetInstance() throws Exception {
        System.out.println("getInstance");
        JDBCConnection expResult = null;
        JDBCConnection result = JDBCConnection.getInstance();
        assertEquals(expResult, result);
        fail("The test case is a prototype.");
    }

    /**
     * Test of initConnection method, of class JDBCConnection.
     */
    @org.junit.Test
    public void testInitConnection() throws Exception {
        System.out.println("initConnection");
        JDBCConnection instance = null;
        instance.initConnection();
        fail("The test case is a prototype.");
    }

    /**
     * Test of openConnection method, of class JDBCConnection.
     */
    @org.junit.Test
    public void testOpenConnection() throws Exception {
        System.out.println("openConnection");
        JDBCConnection instance = null;
        instance.openConnection();
        fail("The test case is a prototype.");
    }

    /**
     * Test of getStatement method, of class JDBCConnection.
     */
    @org.junit.Test
    public void testGetStatement() throws Exception {
        System.out.println("getStatement");
        JDBCConnection instance = null;
        Statement expResult = null;
        Statement result = instance.getStatement();
        assertEquals(expResult, result);
        fail("The test case is a prototype.");
    }

    /**
     * Test of getPreparedStatement method, of class JDBCConnection.
     */
    @org.junit.Test
    public void testGetPreparedStatement() throws Exception {
        System.out.println("getPreparedStatement");
        String sql = "";
        JDBCConnection instance = null;
        PreparedStatement expResult = null;
        PreparedStatement result = instance.getPreparedStatement(sql);
        assertEquals(expResult, result);
        fail("The test case is a prototype.");
    }

    /**
     * Test of closeConnection method, of class JDBCConnection.
     */
    @org.junit.Test
    public void testCloseConnection() {
        System.out.println("closeConnection");
        JDBCConnection instance = null;
        instance.closeConnection();
        fail("The test case is a prototype.");
    }
    
}
