package tests;

import org.testng.Assert;
import org.testng.annotations.Listeners;
import org.testng.annotations.Test;

public class FirstTest {

    @Test
    public void one(){
        Assert.assertTrue(true);
    }

    @Test
    public void two(){
        Assert.assertTrue(false);
    }

    @Test
    public void tree(){
        Assert.assertTrue(true);
    }
}
