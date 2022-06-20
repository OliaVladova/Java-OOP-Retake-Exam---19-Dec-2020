package blueOrigin;

import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;

public class SpaceshipTests {
    private Astronaut astronaut;
    private Astronaut astronautTwo;
    private Spaceship spaceship;

    @Before
    public void setUp() {
        this.astronaut = new Astronaut("Harry", 80);
        this.astronautTwo = new Astronaut("Lolla", 74);
        this.spaceship = new Spaceship("Sky", 2);
        this.spaceship.add(astronaut);
    }

    @Test
    public void testShouldReturnCountCorrectly(){
        int count =this.spaceship.getCount();
        Assert.assertEquals(1,count);
    }
    @Test
    public void testShouldReturnNameCorrectly(){
        String name =this.spaceship.getName();
        Assert.assertEquals("Sky",name);
    }
    @Test
    public void testShouldReturnCapacityCorrectly(){
        int capacity =this.spaceship.getCapacity();
        Assert.assertEquals(2,capacity);
    }
    @Test
    public void testShouldAddAstronautCorrectly(){
        this.spaceship.add(astronautTwo);
        int size = this.spaceship.getCount();
        Assert.assertEquals(2,size);
    }
    @Test(expected = IllegalArgumentException.class)
    public void testShouldThrowExceptionWhenCapacityIsFull(){
        this.spaceship.add(astronautTwo);
        Astronaut newAstro = new Astronaut("Gary",78);
        this.spaceship.add(newAstro);
    }
    @Test(expected = IllegalArgumentException.class)
    public void testShouldThrowExceptionWhenAstronautIsHere(){
        this.spaceship.add(astronaut);
    }
    @Test
    public void testShouldRemoveAstronautCorrectly(){
        this.spaceship.add(astronautTwo);
       boolean isReturned = this.spaceship.remove(astronaut.getName());
        Assert.assertTrue(isReturned);
    }@Test
    public void testShouldSetCapacityCorrectly(){
        Spaceship newSpaceship = new Spaceship("Fling",10);
        int returned= newSpaceship.getCapacity();
        Assert.assertEquals(10,returned);
    }
    @Test
    public void testShouldSetNameCorrectly(){
        Spaceship newSpaceship = new Spaceship("Fling",10);
        String returned= newSpaceship.getName();
        Assert.assertEquals("Fling",returned);
    }
    @Test(expected =NullPointerException.class )
    public void testShouldThrowExceptionWhenNameIsNUll(){
        Spaceship newSpaceship = new Spaceship("",10);
    }
    @Test(expected =IllegalArgumentException.class )
    public void testShouldThrowExceptionWhenCapacityIsIncorrect(){
        Spaceship newSpaceship = new Spaceship("Flier",-10);
    }
}
