////////////////////////////////////////////////
/// Name: Sidney Seay                        ///
/// CSc 2010, Principles of Computer Science ///
/// Fall 2013                                ///
/// Assignment 8; Bulls Program              ///
/// Submitted:11/26/2013                     ///
////////////////////////////////////////////////

import java.util.Scanner;
public class Bulls {
public static void main(String [] args){
	int count = 1;
	int bullValue;
	int cowValue;
	String input1 = "";
	boolean input1Valid;
	String randomNum = produceRandomTarget();

printWelcomeMessage();
instructObjectives();
input1 = userInput(count);

while (count <= 10) {
	
	if (input1.length() < 4) {
		System.out.println("Your guess should contain 4 symbols (digits)");
		input1 = userInput(count);
	}else
	{
		
		input1Valid = containsNonDigits(input1);
		
		if (input1Valid) {
			if (Integer.parseInt(input1) >= 1000 && Integer.parseInt(input1) <= 9999) {
				bullValue = 0;
				cowValue = 0;
				
    			bullValue = computeBulls(input1, randomNum);
				cowValue = computeCows(input1, randomNum);    			
    			if (cowValue < 0) {
    				System.out.println("Your guess should not contain repeating digits");
    				input1 = userInput(count);    				
    			}
    			else {
    				if (bullValue == 4) {
    					System.out.println("Congratulations; You Won!!");
    					break;
    				}
    				else {
        				count++;
    					if (count == 11) {
    						System.out.println("Sorry; You ran out of guesses!!");
    						System.out.println("The correct number was: " + randomNum);
    						break;
    					}else {
    						System.out.println("Bull = " + bullValue  + " Cow = " + cowValue);    						
    						input1 = userInput(count);    						
    					}
    				}
    			}
		    }
			else
			{
				System.out.println("Enter a valid number");	
				input1 = userInput(count);
			}
		}
		else
		{
			System.out.println("Your guess should not contain non-digits");	
			count++;
			input1 = userInput(count);
		}		
	}
}
count = 0;
}

	// This method prints the welcome message
	private static void printWelcomeMessage() {
       System.out.println("Welcome to the game of BULLS and COWS.");
	}
	
	//Instructions and Objective
	private static void instructObjectives() {
	System.out.println("The objective in this game is for you to guess a 4-digit number");
	System.out.println("The computer responds with how close your guess is to the target");
	System.out.println("BULLS = # common digits with exact matches and");
	System.out.println("COWS  = # common digits in wrong position.");
	}

	private static String userInput(int count){

		System.out.println("Enter guess number " + count + ": ");
	    Scanner inputValues = new Scanner(System.in);
	    return inputValues.next();
	}	
	
	// This method produces a valid 4-digit number and returns it
	// as a String object. The following statement can be used to
	// generate a number between 1000 and 9999:
	// num = 1000 + ( (int) (Math.random() * 10000) % 9000);
	private static String produceRandomTarget(){
		int num = 1000 + ( (int) (Math.random() * 10000) % 9000);
	    return String.valueOf(num);
	}
	
	// This method receives a 4-digit number, num, as a String parameter and
	// returns true if num has repeating digits and false otherwise..
		
	private static boolean hasRepeatingDigits(String num)
	{
		char numValue1 = 0;
		char numValue2 = 0;
		boolean firstPass = true;
		
		for (int i = 0; i < 4; i++){
			numValue1 = num.charAt(i);
			if (firstPass) {
				numValue2 = num.charAt(i);
				firstPass = false;
			}
			else {
               if (numValue1 == numValue2) {
            	   return true;
               }
               else {
            	   numValue2 = numValue1;
               }
			}
		}		
				return false;
	}
	// This method received a String, num, as parameter and 
	// returns true if num contains a non-digit and false otherwise.
	private static boolean containsNonDigits(String num)
	{
		char numValue;
		for (int i = 0; i < 4; i++){
			numValue = num.charAt(i);
			String sValue = String.valueOf(numValue);
			try
			{
			int returnValue = Integer.parseInt(sValue);
			}	
		    catch(Exception e ) {
					return false;
			}
		}
		return true;
		
	}
	
	// This method receives two String parameters, num1 and num2, which
	// contain two 4-digit numbers and returns the number of "BULLS".
	private static int computeBulls(String num1, String num2)
	{
		int bull = 0;
		boolean repeating;
		repeating = hasRepeatingDigits(num1);
		if (repeating) {
			return -1;
		}
		for (int i = 0; i < 4; i++){
			for (int j = 0; j < 4; j++) {
				if (i == j) {
					if (num1.charAt(j) ==  num2.charAt(i))
					{
				      	bull++;	
					}									
				}
			}
		}
		   return bull;
	}
	
	// This method receives two String parameters, num1 and num2, which
	// contain the two 4-digit numbers and returns the number of "COWS".
	private static int computeCows(String num1, String num2)
	{
		int cow = 0;
		boolean repeating;
		repeating = hasRepeatingDigits(num1);
		if (repeating) {
			return -1;
		}
		for (int i = 0; i < 4; i++){
			for (int j = 0; j < 4; j++) {
				if (num1.charAt(j) ==  num2.charAt(i))
				{
			      	cow++;	
				}				
		    }
	    }
		  return cow;	
      }

}