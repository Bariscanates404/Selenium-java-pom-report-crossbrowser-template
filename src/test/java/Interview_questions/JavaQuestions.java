package Interview_questions;

import java.util.Arrays;
import java.util.HashMap;
import java.util.Map;

public class JavaQuestions {

    public static void main(String[] args) {
        System.out.println(calculateSum(10));
        System.out.println(isPrime(11));
        System.out.println(reverseString("merhaba"));
        System.out.println(calculateFactorial(5));
        generateFibonacci(10);
        System.out.println(isPalindrome("kanak"));
        System.out.println(reverseStringWithStringBuilder("tuna chan"));
        System.out.println(calculateFactorialWithoutRecursion(7));
        System.out.println(areAnagramss("merhaba", "merh a ba"));




    }

    // Question 1: Calculate the sum of all numbers from 1 to N.
    public static int calculateSum(int n) {
        int sum = 0;

        // Loop through each number from 1 to N and accumulate the sum
        for (int i = 1; i <= n; i++) {
            sum += i;
        }

        return sum;
    }

    // Question 2: Check if a given number is prime or not.
    public static boolean isPrime(int n) {
        if (n <= 1) {
            return false;
        }

        // Check divisibility from 2 to the square root of the number
        for (int i = 2; i <= Math.sqrt(n); i++) {
            if (n % i == 0) {
                return false; // If divisible, it is not prime
            }
        }

        return true; // If not divisible, it is prime
    }

    // Question 3: Reverse a string without using StringBuilder or StringBuffer.
    public static String reverseString(String str) {
        String reversedString = "";

        // Iterate through each character in reverse order and build the reversed string
        for (int i = str.length() - 1; i >= 0; i--) {
            reversedString += str.charAt(i);
        }

        return reversedString;
    }

    // Question 4: Calculate the factorial of a given number.
    public static int calculateFactorial(int num) {
        int fact = 1;

        // Multiply numbers from 1 to the given number to calculate factorial
        for (int i = 1; i <= num; i++) {
            fact *= i;
        }

        return fact;
    }

    // Question 5: Generate the Fibonacci series up to a given number N.
    public static void generateFibonacci(int num) {
        int n1 = 0, n2 = 1, n3;

        System.out.print(n1 + " " + n2);

        // Generate Fibonacci series up to the given number
        for (int i = 2; i < num; ++i) {
            n3 = n1 + n2;
            System.out.print(" " + n3);
            n1 = n2;
            n2 = n3;
        }
    }

    // Question 6: Check if a given string is a palindrome.
    public static boolean isPalindrome(String str) {
        String reversedStr = "";

        // Reverse the given string
        for (int i = str.length() - 1; i >= 0; i--) {
            reversedStr += str.charAt(i);
        }

        return str.equals(reversedStr); // Check if the original string is equal to its reversed version
    }

    // Question 7: Count the occurrences of each character in a given string.
    public static Map<Character, Integer> countCharacterOccurrences(String str) {
        Map<Character, Integer> occurrencesMap = new HashMap<>();

        // Count occurrences using a Map
        for (char ch : str.toCharArray()) {
            occurrencesMap.put(ch, occurrencesMap.getOrDefault(ch, 0) + 1);
        }

        return occurrencesMap;
    }

    // Question 8: Reverse a string using StringBuilder.
    public static String reverseStringWithStringBuilder(String str) {
        return new StringBuilder(str).reverse().toString();
    }

    // Question 9: Find the maximum element in an array of integers.
    public static int findMax(int[] arr) {
        if (arr == null || arr.length == 0) {
            throw new IllegalArgumentException("Array is empty or null");
        }

        int max = arr[0];

        // Iterate through the array to find the maximum element
        for (int num : arr) {
            if (num > max) {
                max = num;
            }
        }

        return max;
    }

    // Question 10: Calculate the sum of all elements in an array of integers.
    public static int calculateSum(int[] arr) {
        int sum = 0;

        // Add each element to the sum
        for (int num : arr) {
            sum += num;
        }

        return sum;
    }

    public static boolean template1(String str) {
        boolean result = false;
        String reversedStr = new StringBuilder(str).reverse().toString();
        if (str.equals(reversedStr)) {
            result = true;
        }
        return result;
    }

    public static boolean isPrimee(int n) {
        if (n <= 1) {
            return false;
        }

        // Check divisibility from 2 to the square root of the number
        for (int i = 2; i <= Math.sqrt(n); i++) {
            if (n % i == 0) {
                return false; // If divisible, it is not prime
            }
        }

        return true; // If not divisible, it is prime
    }

    // Question: Write a function to calculate the factorial of a given number without using recursion.
    public static int calculateFactorialWithoutRecursion(int num) {
        int sum = 1;
        for (int i = num; i > 0; i--) {
            sum*=i;
        }
        return sum;
    }

    // Question: Implement a function to check if two strings are anagrams of each other.
    public static boolean areAnagrams(String str1, String str2) {
        // Remove spaces and convert to lowercase for case-insensitive comparison
        str1 = str1.replaceAll("\\s", "").toLowerCase();
        str2 = str2.replaceAll("\\s", "").toLowerCase();

        // Check if the lengths are the same
        if (str1.length() != str2.length()) {
            return false;
        }

        // Convert strings to character arrays and sort them
        char[] charArray1 = str1.toCharArray();
        char[] charArray2 = str2.toCharArray();

        Arrays.sort(charArray1);
        Arrays.sort(charArray2);

        // Compare sorted character arrays
        return Arrays.equals(charArray1, charArray2);
    }


    // Question: Implement a function to check if two strings are anagrams of each other.
    public static boolean areAnagramss(String str1, String str2) {
        str1 = str1.replaceAll("\\s", "").toLowerCase();
        str2 = str2.replaceAll("\\s", "").toLowerCase();

        if (str1.length() != str2.length()){
            return false;
        }

        char arr[] = str1.toCharArray();
        char arr2[] = str2.toCharArray();
        Arrays.sort(arr);
        Arrays.sort(arr2);

        return Arrays.equals(arr,arr2);
    }






}
