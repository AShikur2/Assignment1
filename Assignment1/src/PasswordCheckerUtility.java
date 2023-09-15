import java.util.regex.Matcher;
import java.util.regex.Pattern;
import java.util.ArrayList;

public class PasswordCheckerUtility {
	public static void isValidLength(String password) throws LengthException{
		if (password.length() < 6)
            throw new LengthException();
	}

    public static boolean isValidPassword(String password) throws LengthException, NoUpperAlphaException, NoLowerAlphaException,
            NoDigitException, InvalidSequenceException, NoSpecialCharacterException{
        
    	isValidLength(password);
        
        if (!hasUpperAlpha(password))
            throw new NoUpperAlphaException();


        if (!hasLowerCase(password))
            throw new NoLowerAlphaException();
        
        if (!hasDigit(password))
            throw new NoDigitException();

        if (!hasSpecialCharacter(password))
            throw new NoSpecialCharacterException();

        if (hasInvalidSequence(password))
            throw new InvalidSequenceException();
        
        return true;
    }

    public static boolean isWeakPassword(String password) throws WeakPasswordException{
        if (password.length() <= 9 && password.length() >= 6){
        	throw new WeakPasswordException();
        } else 
            return false;
    }
    
    public static boolean comparePasswordsWithReturn(String passwordString, String passwordAString) {
		if (passwordString.equals(passwordAString)){
			return true;
		}
		return false;
	}

    public static ArrayList<String> getInvalidPasswords(ArrayList<String> passwords){
        ArrayList<String> invalidPasswords = new ArrayList<>();
        
        for (String password : passwords){
            try {
				isValidPassword(password);
			} catch (LengthException | NoUpperAlphaException | NoLowerAlphaException | NoDigitException
						| InvalidSequenceException | NoSpecialCharacterException e){
                invalidPasswords.add(password + " " + e.getMessage());
            }
        }
        return invalidPasswords;
    }

    public static boolean hasUpperAlpha(String str) throws NoUpperAlphaException{
    	for (char c : str.toCharArray()){
            if (Character.isUpperCase(c))
                return true;
        }
        return false;
    }

    public static boolean hasLowerCase(String str) throws NoLowerAlphaException{
    	for (char c : str.toCharArray()){
            if (Character.isLowerCase(c))
                return true;
        }
        return false;
    }

    public static boolean hasDigit(String str)throws NoDigitException{
    	for (char c : str.toCharArray()){
            if (Character.isDigit(c))
                return true;
        }
        return false;
    }

    public static boolean hasSpecialCharacter(String str) throws NoSpecialCharacterException{
        Pattern pattern = Pattern.compile("[^a-zA-Z0-9]");
        Matcher matcher = pattern.matcher(str);
        return matcher.find();
    }

    public static boolean hasInvalidSequence(String str) throws InvalidSequenceException{
        for (int i = 0; i < str.length() - 2; i++){
            char c = str.charAt(i);
            if (c == str.charAt(i + 2) && c == str.charAt(i + 1))
                return true;
        }
        return false;
    }

    public static void comparePasswords(String password, String passwordConfirm) throws UnmatchedException{
		if (!password.equals(passwordConfirm))
			throw new UnmatchedException();
	}

}
