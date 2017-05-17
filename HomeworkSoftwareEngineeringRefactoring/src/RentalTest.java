import org.jboss.arquillian.container.test.api.Deployment;
import org.jboss.arquillian.junit.Arquillian;
import org.jboss.shrinkwrap.api.ShrinkWrap;
import org.jboss.shrinkwrap.api.asset.EmptyAsset;
import org.jboss.shrinkwrap.api.spec.JavaArchive;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;

import static org.junit.Assert.*;

/**
 * Created by MarcWeickenmeier on 16.05.2017.
 */
@RunWith(Arquillian.class)
public class RentalTest {
	
    Movie firstMovie, secondMovie;
 	int days = 5;
 	Rental testRent1, testRent2, testRental3;

    @Before
 	public void setUp(){
        firstMovie = new Movie("firstMovie", 1);
        secondMovie = new Movie("secondMovie", 2);

        testRent1 = new Rental(firstMovie, days);
        testRent2 = new Rental(secondMovie, days);
        testRental3 = new Rental(firstMovie, days);
	}

    @Test
 	public void testGetDaysRented() {
        assertEquals(days, testRent1.getDaysRented());
    }

    @Test
 	public void testGetMovie() {
    	assertEquals(firstMovie, testRent1.getMovie());
    }

    @Deployment
    public static JavaArchive createDeployment() {
        return ShrinkWrap.create(JavaArchive.class)
                .addClass(Rental.class)
                .addAsManifestResource(EmptyAsset.INSTANCE, "beans.xml");
    }
}