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
 * Contains test method of class OrdersModel
 */
public class OrdersModelITest {
    
    public OrdersModelITest() {
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
     * Test of sumPrice method, of class OrdersModel.
     */
    @Test
    public void testSumPrice() {
        System.out.println("sumPrice");
        OrdersModel instance = new OrdersModel();
        int expResult = 0;
        int result = instance.sumPrice();
        assertEquals(expResult, result);
    }

    /**
     * Test of getNumberOfRealisedSales method, of class OrdersModel.
     */
    @Test
    public void testGetNumberOfRealisedSales() {
        System.out.println("getNumberOfRealisedSales");
        OrdersModel instance = new OrdersModel();
        String expResult = "1";
        String result = instance.getNumberOfRealisedSales();
        assertEquals(expResult, result);
    }

    /**
     * Test of getNumberOfCanceledSales method, of class OrdersModel.
     */
    @Test
    public void testGetNumberOfCanceledSales() {
        System.out.println("getNumberOfCanceledSales");
        OrdersModel instance = new OrdersModel();
        String expResult = "1";
        String result = instance.getNumberOfCanceledSales();
        assertEquals(expResult, result);
    }


}
