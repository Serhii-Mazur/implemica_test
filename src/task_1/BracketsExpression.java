package task_1;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.Scanner;

public class BracketsExpression {
    public static final String BRACKETS_PAIR = "()";    // Define brackets type ((), [], {}...)

    public static void main(String[] args) {
        //  Get number of brackets pairs from console
        int bracketsPairsNumber = getNumberFromConsole(new Scanner(System.in));
        //  Get correct brackets expressions list
        List<String> validBracketExpressionList = getCorrectBracketExpressionList(bracketsPairsNumber);
        //  Display Result
        System.out.printf("Correct bracket expressions number: %d -> %s", validBracketExpressionList.size(), validBracketExpressionList);
    }

    private static int getNumberFromConsole(Scanner in) {
        int number;
        System.out.println("Enter a brackets pairs number (must be non-negative integer):");

        while (true) {  //  Eternal cycle until correct data is received
            if (in.hasNextInt()) {
                int temp = in.nextInt();
                if (temp < 0) {
                    System.out.println("Invalid number (can't be negative). Try again:");
                } else {    //  Correct data received
                    number = temp;
                    break;
                }
            } else {    //  Case when the entered data is not an integer
                in.nextInt();
                System.out.println("Invalid character set. Try again:");
            }
        }

        return number;
    }

    private static List<String> getCorrectBracketExpressionList(int bracketsPairsNumber) {
        //  Setting initial values
        List<String> bracketsPairsList = new ArrayList<>();
        bracketsPairsList.add(BRACKETS_PAIR);
        List<String> tempBracketsPairsList = new ArrayList<>();

        if (bracketsPairsNumber == 0) {
            bracketsPairsList = Collections.emptyList();
        } else {
            int counter = 1;
            String temp;
            //  Correct brackets expressions list formation algorithm
            while (counter < bracketsPairsNumber) {
                for (String s : bracketsPairsList) {
                    for (int i = 0; i < s.length(); i++) {
                        temp = s.substring(0, i) + BRACKETS_PAIR + s.substring(i);
                        if (tempBracketsPairsList.contains(temp))
                            continue;
                        tempBracketsPairsList.add(temp);
                    }
                }
                bracketsPairsList = tempBracketsPairsList;
                tempBracketsPairsList = new ArrayList<>();
                counter++;
            }
        }

        return bracketsPairsList;
    }
}
