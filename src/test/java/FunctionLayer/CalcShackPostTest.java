package FunctionLayer;

/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */



import java.util.List;
import org.junit.Test;
import static org.junit.Assert.*;

/**
 *
 * @author Alexander W. Hørsted-Andersen 
 */
public class CalcShackPostTest {
    
    
    /**
     * Test of getPostsShack method, of class CalcShackPost.
     */
    @Test
    public void testGetPostsShackSameWidth() throws FogException {
        System.out.println("getPostsShack - Same Width");
        int carportLength = 730;
        int carportWidth = 360;
        int height = 210;
        int shackLength = 730;
        int shackWidth = 360;
        List<Product> posts = Calculator.getChosenCategory("stolpe", DataLayer.ProductMapper.getProducts());;
        int expPosts = 3; //2 for length, 1 for door
        OrderLine result = CalcShackPost.getPostsShack(carportLength, carportWidth, height, shackLength, shackWidth, posts);
        assertEquals(expPosts, result.quantity);
    }
    @Test
    public void testGetPostsShackLesserWidth() throws FogException {
        System.out.println("getPostsShack - lasser Width");
        int carportLength = 730;
        int carportWidth = 360;
        int height = 210;
        int shackLength = 730;
        int shackWidth = 330;
        List<Product> posts = Calculator.getChosenCategory("stolpe", DataLayer.ProductMapper.getProducts());;
        int expPosts = 4; //1 for length, 1 for "length", 1 for width, 1 for door
        OrderLine result = CalcShackPost.getPostsShack(carportLength, carportWidth, height, shackLength, shackWidth, posts);
        assertEquals(expPosts, result.quantity);
    }
    
}
