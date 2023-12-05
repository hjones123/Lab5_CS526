import java.util.Objects;

/**
You are working for a new cybersecurity company “AlgoWare Defender” on a decoding tool.
The decoding tool is meant to help reverse a nefarious one way encoding scheme of letters to digits.

The encoding scheme coverts letter strings into numeric strings by
converting each letter to a digit based on its place in the alphabet.

Here is the encoding table
A : 1
B : 2
…
Z : 26

For example the string ZAB maps to 2612.

The nefarious part of this scheme is that it is not reversible!

However, if you are given 2612 this could be decoded in multiple ways

26 1 2 = ZAB
2 6 12 = BFL
2 6 1 2 = BFAB
26 12 = ZL

AlgoWare Defender’s decoding tool will have a few components; you are working on the first piece:
determining how many decodings are possible. For example, there are 4 decodings for 2612

You must write a function: findNumDecodings
which when given a string of digits returns the number of possible decodings.

Your program must be efficient and must use dynamic programming.

You must also provide an explanation of the running time of your code

Code Author: <Harry Jones>

--------------------
The iteration from starting point i to N, has a running time of O(N). The other functions 
in my code comprise of locating substrings within my main String "string" and converting those into integers.
This all happens in O(1) time, and repeats for each time through. The other function is simply updating
my dp array for each of these substring checks. So overall, O(n) is the time complexity of my code.
*/
public class AlgoWareDefender {
    public static int findNumDecodings(String string) {
    	/*Check for empty or null input*/
    	if (string == null || string.isEmpty()) {
    		return 1;
    	}
    	int n =string.length();
    /*An array (n + 1) is needed to store the intermediate results up to 
    	each index so we don’thave to recompute them.*/
    	int[] dp = new int[n+1];
    	dp[0] = 1;
    	dp[1] = 1;
    
    	for (int i = 2; i<=n; i++) {
    		/*For each character, check if the character itself can form a valid letter, and if so, do
something with dp[i-1] and dp[i].*/
    		if (string.charAt(i-1) != '0') {
    			/*If the single digit is valid (not zero), add the number of ways to decode the string up to the previous
character.*/
    			dp[i] += dp[i-1];
    		}
    		/*Also, check if the two-character combination with the previous character can form a valid
    		letter (i.e., is between 10 and 26), and if so, do something with dp[i-2] and dp[i].*/
    		if (i >= 2 && Integer.parseInt(string.substring(i - 2, i)) >=10 && Integer.parseInt(string.substring(i - 2, i)) <= 26) {
    			/*If the two digits form a valid number between 10 and 26, add the number of ways to decode the string
up to two characters back. Repeat the two bullets above until you reach the end of the string.*/
    			dp[i] += dp[i - 2];
    		}
    	}
        return dp[n];
    }

    /*
    DO NOT EDIT BELOW THIS
    Below is the unit testing suite for this file.
    It provides all the tests that your code must pass to get full credit.
    */
    private static void printTestResult(String testName, boolean result) {
        String color = result ? "\033[92m" : "\033[91m";
        String reset = "\033[0m";
        System.out.printf("%s[%b] %s%s\n", color, result, testName, reset);
    }

    private static void testAnswer(String testName, Integer expected, Integer actual) {
        if (Objects.equals(actual, expected)) {
            printTestResult(testName, true);
        }
        else {
            printTestResult(testName, false);
            System.out.printf("Expected: %s \nGot:      %s\n", expected, actual);
        }
    }

    public static void runTests() {
        testExample();
        testEmpty();
        testSingle();
        testDouble();
        testInvalid1();
        testInvalid2();
        testNormal1();
        testNormal2();
        testNormal3();
        testManyOnes();
    }

    private static void testExample() {
        int result = AlgoWareDefender.findNumDecodings("2612");
        int expectedAnswer = 4;

        testAnswer("testExample", result, expectedAnswer);
    }

    private static void testEmpty() {
        int result = AlgoWareDefender.findNumDecodings("");
        int expectedAnswer = 1;

        testAnswer("testEmpty", result, expectedAnswer);
    }

    private static void testSingle() {
        int result = AlgoWareDefender.findNumDecodings("8");
        int expectedAnswer = 1;

        testAnswer("testSingle", result, expectedAnswer);
    }

    private static void testDouble() {
        int result = AlgoWareDefender.findNumDecodings("25");
        int expectedAnswer = 2;

        testAnswer("testDouble", result, expectedAnswer);
    }

    private static void testInvalid1() {
        int result = AlgoWareDefender.findNumDecodings("0");
        int expectedAnswer = 0;

        testAnswer("testInvalid1", result, expectedAnswer);
    }

    private static void testInvalid2() {
        int result = AlgoWareDefender.findNumDecodings("1200");
        int expectedAnswer = 0;

        testAnswer("testInvalid2", result, expectedAnswer);
    }

    private static void testNormal1() {
        int result = AlgoWareDefender.findNumDecodings("123456789");
        int expectedAnswer = 3;

        testAnswer("testNormal1", result, expectedAnswer);
    }

    private static void testNormal2() {
        int result = AlgoWareDefender.findNumDecodings("1011121314151617181920212223242526");
        int expectedAnswer = 86528;

        testAnswer("testNormal2", result, expectedAnswer);
    }

    private static void testNormal3() {
        int result = AlgoWareDefender.findNumDecodings("1232020410105");
        int expectedAnswer = 3;

        testAnswer("testNormal3", result, expectedAnswer);
    }

    private static void testManyOnes() {
        int result = AlgoWareDefender.findNumDecodings("1111111111111111111111111111111111111111");
        int expectedAnswer = 165580141;

        testAnswer("testManyOnes", result, expectedAnswer);
    }

    public static void main(String[] args) {
        runTests();
    }
}