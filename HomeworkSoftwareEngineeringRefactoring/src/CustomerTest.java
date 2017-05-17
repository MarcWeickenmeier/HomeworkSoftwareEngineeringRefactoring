import org.jboss.arquillian.container.test.api.Deployment;
import org.jboss.arquillian.junit.Arquillian;
import org.jboss.shrinkwrap.api.ShrinkWrap;
import org.jboss.shrinkwrap.api.asset.EmptyAsset;
import org.jboss.shrinkwrap.api.spec.JavaArchive;
import org.junit.Test;
import org.junit.runner.RunWith;

import static org.junit.Assert.*;

/**
 * Created by MarcWeickenmeier on 18.05.2017.
 */
@RunWith(Arquillian.class)
public class CustomerTest {

    Customer testCustomer;

    @Test
    public void testStatement(){
		
        testCustomer = new Customer("Marc");

        Movie movieOne = new Movie("1", 5);
        Movie movieTwo = new Movie("1", 3);
        Movie movieThree = new Movie("3", 4);
        Movie movieFour = new Movie("4", 2);

        Rental rentalOne = new Rental(movieOne, 3);
        Rental rentalTwo = new Rental(movieTwo, 3);
        Rental rentalThree = new Rental(movieThree, 3);

        testCustomer.addRental(rentalOne);
        testCustomer.addRental(rentalTwo);
        testCustomer.addRental(rentalThree);

        String expect ="Rental Record for fabi\n"+
            "\tTitle		Days	Amount\n"+
            "\ta		4	5.0\n"+
            "\tb		5	15.0\n"+
            "\tc		6	6.0\n"+
            "Amount owed is 26.0\n"+
            "You earned 4 frequent renter points";
			
        String statement = testCustomer.statement();
        assertEquals(expect, statement);
    }

    @Deployment
    public static JavaArchive createDeployment() {
        return ShrinkWrap.create(JavaArchive.class)
                .addClass(Customer.class)
                .addAsManifestResource(EmptyAsset.INSTANCE, "beans.xml");
    }

}