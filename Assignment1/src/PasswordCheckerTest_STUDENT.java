
import static org.junit.Assert.*;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Scanner;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;

/**
 * STUDENT tests for the methods of PasswordChecker
 * @author Abass Shikur
 *
 */
public class PasswordCheckerTest_STUDENT {

    ArrayList<String> passwordsArray;
    String validPassword = "Hello@123";
    String invalidPassword = "AAAbb@123";
    String weakPassword = "a1b1@123";

    @Before
    public void setUp() {
        String[] passwords = {validPassword, invalidPassword, weakPassword};
        passwordsArray = new ArrayList<>(Arrays.asList(passwords));
    }

    @After
    public void tearDown() {
        passwordsArray = null;
    }

    @Test
    public void testIsValidPasswordTooShort() {
    	try{
			assertTrue(PasswordCheckerUtility.isValidPassword("AB1"));
			assertTrue("Did not throw lengthException",false);
		}
		catch(LengthException e)
		{
			assertTrue("Successfully threw a lengthExcepetion",true);
		}
    	catch(Exception e)
		{
			assertTrue("Threw some other exception besides lengthException",false);
		}
    }

    @Test
    public void testIsValidPasswordNoUpperAlpha() {
    	try{
			assertTrue(PasswordCheckerUtility.isValidPassword("abcde@1234"));
			assertTrue("Did not throw UpperAlphaException",false);
		}
		catch(NoUpperAlphaException e)
		{
			assertTrue("Successfully threw a UpperAlphaException",true);
		}
		 
		catch(Exception e)
		{
			System.out.println(e.getMessage());
			assertTrue("Threw some other exception besides UpperAlphaException",false);
			
		}
    }

    @Test
    public void testIsValidPasswordNoLowerAlpha() {
    	try{
			assertTrue(PasswordCheckerUtility.isValidPassword("5C6@AD"));
 
			assertTrue("Did not throw NoLowerAlphaException",false);
		}
		catch(NoLowerAlphaException e)
		{
			assertTrue("Successfully threw a NoLowerAlphaExcepetion",true);
		}
		catch(Exception e)
		{
			assertTrue("Threw some other exception besides NoLowerAlphaException",false);
		}
    }

    @Test
    public void testIsWeakPassword() {
    	try{
			 
			boolean weak = PasswordCheckerUtility.isWeakPassword("pasWor@2");
			assertTrue("Did not throw WeakPassword Exception",weak);
		}
		catch(WeakPasswordException e)
		{
			assertTrue("Successfully threw a NoLowerAlphaExcepetion",true);
		}
		catch(Exception e)
		{
			System.out.println(e.getMessage());
			assertTrue("Threw some incorrect exception",false);
		}
    }

    @Test
    public void testIsValidPasswordInvalidSequence() {
    	try{
			assertEquals(true,PasswordCheckerUtility.isValidPassword("12a34a@AAaaa"));
		 	assertTrue("Did not throw an InvalidSequenceException",false);
		}
		catch(InvalidSequenceException e)
		{
			assertTrue("Successfully threw an InvalidSequenceExcepetion",true);
		}
		catch(Exception e)
		{
			System.out.println(e.getMessage());
			assertTrue("Threw some other exception besides an InvalidSequenceException",false);
		}
    }

    @Test
    public void testIsValidPasswordNoDigit() {
    	try{
			assertEquals(true,PasswordCheckerUtility.isValidPassword("abcD@Efgreb"));
		 	assertTrue("Did not throw an NoDigitException",false);
		}
		catch(NoDigitException e)
		{
			assertTrue("Successfully threw an NoDigitException",true);
		}
		catch(Exception e)
		{
			System.out.println(e.getMessage());
			assertTrue("Threw some other exception besides an NoDigitException",false);
		}
    }

    @Test
    public void testIsValidPasswordSuccessful() {
        try {
            assertTrue(PasswordCheckerUtility.isValidPassword(validPassword));
        } catch (Exception e) {
            fail("Unexpected exception thrown");
        }
    }

    @Test
    public void testInvalidPasswords() {
        ArrayList<String> results = PasswordCheckerUtility.getInvalidPasswords(passwordsArray);
        assertEquals(results.size(), 2);
        assertEquals("AAAbb@123 The password cannot contain more than two of the same character in sequence", results.get(0));
        assertEquals("a1b1@123 The password must contain at least one uppercase alphabetic character", results.get(1));
    }

}
