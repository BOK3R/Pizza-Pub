/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package pl.pizza.pub.models;

import org.junit.After;
import org.junit.AfterClass;
import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Test;
import static org.junit.Assert.*;

/**
 * Contains test method of class EmployeesModel
 */
public class EmployeesModelITest {
    
    public EmployeesModelITest() {
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
     * Test of login method, of class EmployeesModel.
     */
    @Test
    public void testLogin1() {
        System.out.println("login");
        String login = "kelner";
        String password = "kelner";
        EmployeesModel instance = new EmployeesModel();
        int expResult = 1;
        int result = instance.login(login, password);
        assertEquals(expResult, result);
    }

    /**
     * Test of login method, of class EmployeesModel.
     */
    @Test
    public void testLogin2() {
        System.out.println("login");
        String login = "kucharz";
        String password = "kucharz";
        EmployeesModel instance = new EmployeesModel();
        int expResult = 2;
        int result = instance.login(login, password);
        assertEquals(expResult, result);
    }

    /**
     * Test of login method, of class EmployeesModel.
     */
    @Test
    public void testLogin3() {
        System.out.println("login");
        String login = "manager";
        String password = "manager";
        EmployeesModel instance = new EmployeesModel();
        int expResult = 3;
        int result = instance.login(login, password);
        assertEquals(expResult, result);
    }

    /**
     * Test of login method, of class EmployeesModel.
     */
    @Test
    public void testLogin4() {
        System.out.println("login");
        String login = "admin";
        String password = "admin";
        EmployeesModel instance = new EmployeesModel();
        int expResult = 4;
        int result = instance.login(login, password);
        assertEquals(expResult, result);
    }
}
