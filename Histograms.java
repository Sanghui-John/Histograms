// Histograms

// This class is an output program of histograms based on input files.
// The program reads input file, organizes and computes data into new output file.
// Our main method does :
//      1. introduction of the program.
//      2. asks & reads input file
//      3. finds max value and counts each value.
//      4. prints histogram & computing printSummary.
//      5. repeats until the user input is other than "yes"(case insensitive).

import java.util.*;
import java.io.*;

public class Histograms {
    public static void main(String[] args) throws FileNotFoundException {
        intro();
        Scanner console = new Scanner(System.in);
        String answer = "yes";
        while(answer.equalsIgnoreCase("yes")) {
            System.out.println();

            System.out.print("Input file name? ");
            String inputFileName = console.next();
            Scanner fileScan = new Scanner(new File(inputFileName));
            int[] dataArray = readData(fileScan);
            int[] valCount = getCounts(dataArray);

            System.out.print("Output file name? ");
            String outputFileName = console.next();
            PrintStream output = new PrintStream(new File(outputFileName));
            output.println("Histogram for " + inputFileName);
            printHistogram(valCount, output);
            printSummary(dataArray, valCount, output);

            System.out.println();
            System.out.println("Histogram Created!");
            System.out.print("Do you want to create another histogram? ");
            answer = console.next();
        }
        System.out.println();
        System.out.println("Thanks for having fun with histograms!");
    }
    
    // method 1
    // This method prints out an introduction message of the program
    public static void intro() {
        System.out.println("The program will analyze data from a dataset of");
        System.out.println("non-negative integer values. It will produce a");
        System.out.println("histogram of the data and some statistics.");
        System.out.println("The result will be written to an output file.");
    }

    // method 2
    // File Scanner reads data from an input file, then puts into an array
    // Parameters:
    //      Scanner fileScan: file scanner
    // Returns created data array
    public static int[] readData(Scanner fileScan) {
        int numData = fileScan.nextInt();
        int[] dataArray = new int[numData];
        for(int i = 0; i < dataArray.length; i++) {
            dataArray[i] = fileScan.nextInt();
        }
        return dataArray;
    }

    // method 3
    // Within the given array, it finds the first index of the maximum value
    // Parameters:
    //      int[] dataArray: Array of integers
    // Returns the index found
    public static int findMaxIndex(int[] dataArray) {
        int maxVal = 0;
        int maxValIndex = 0;
        for(int i = 0; i < dataArray.length; i++) {
            if(dataArray[i] > maxVal) {
                maxVal = dataArray[i];
                maxValIndex = i;
            }
        }
        return maxValIndex;
    }

    // method 4
    // This method reads the array of integers, counts occurences of each value in an array.
    // Parameters:
    //      int[] dataArray: Array of integers
    // Returns the data array which was counted into
    public static int[] getCounts(int[] dataArray) {
        int[] getCounts = new int[dataArray[findMaxIndex(dataArray)] + 1];
        for(int i = 0; i < dataArray.length; i++) {
            getCounts[dataArray[i]]++;
        }
        return getCounts;
    }

    // method 5
    // This method computes the average of the data in an array
    // average is calculated as, the sum from the array divded by the length of array
    // Parameters:
    //      int[] dataArray: Array of integers
    // Returns the average as a double data type
    public static double avg(int[] dataArray) {
        int sum = 0;
        for(int i = 0; i < dataArray.length; i++) {
            sum += dataArray[i];
        }
        return (double)sum / dataArray.length;
    }

    // method 6
    // This method prints the counted array as a text-based histogram into a new output file
    // In output file, it begins at 0 and ends at the largest value in the dataset
    // and prints one asterisk ('*') for each occurrence of that value in the dataset
    // Parameters:
    //      int[] getCounts: the data array which was counted into
    //      PrintStream output: printstream for the output file
    public static void printHistogram(int[] getCounts, PrintStream output) {
        output.println();
        for(int i = 0; i < getCounts.length; i++) {
            output.print(i + "| ");
            for(int j = 0; j < getCounts[i]; j++) {
                output.print("*");
            }
            output.println();
        }
    }

    // method 7
    // This method computes number of values, mean, and mode of the input file
    // then it prints these summary statistics into a new output file using printstream
    // Parameters:
    //      int[] dataArray: Array of integers
    //      int[] getCounts: the data array which was counted into
    //      PrintStream output: printstream for the output file
    public static void printSummary(int[] dataArray, int[] getCounts, PrintStream output) {
        output.println();
        output.println("Num. Values: " + dataArray.length);
        output.println("Mean: " + avg(dataArray));
        output.println("Mode: " + findMaxIndex(getCounts));
    }
}
