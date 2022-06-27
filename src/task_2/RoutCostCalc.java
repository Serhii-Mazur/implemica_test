package task_2;

import java.io.File;
import java.io.FileNotFoundException;
import java.nio.file.Path;
import java.util.NoSuchElementException;
import java.util.Scanner;
import java.util.regex.Pattern;
import java.util.zip.DataFormatException;

public class RoutCostCalc {
    public static final String delimiter = " ";                         //  Delimiter symbol

    public static void main(String[] args) {
        String cityNameRegex = "[a-z]{1,10}";                           //  Regex for city name format validation
        String nrCostRegex = "[0-9][" + delimiter + "][0-9]";           //  Regex for parameter 'nr cost' validation
        String srcToDstRegex = cityNameRegex + "[" + delimiter + "]" + cityNameRegex;   //  Regex for parameter 'path' validation
        Pattern cityNamePattern = Pattern.compile(cityNameRegex);
        Pattern nrCostPattern = Pattern.compile(nrCostRegex);
        Pattern srcToDstPattern = Pattern.compile(srcToDstRegex);
        final String fileName = "task_2_input.txt";                     //  Input file name
        final Path srcPath = Path.of(System.getProperty("user.dir"), "src", fileName).toAbsolutePath();

        try (Scanner source = new Scanner(new File(String.valueOf(srcPath)))) {
            int s = Integer.parseInt(source.nextLine().trim());         //  The number of tests (<=10)
            int n = Integer.parseInt(source.nextLine().trim());         //  The number of cities (<=100000

            //  Get RoutCalculator instance
            RoutCalculator routCalculator = new DijkstrasAlgorithmBasedRoutCalculator(n);

            for (int t = 0; t < s; t++) {                               //  Test #t
                for (int c = 0; c < n; c++) {
                    String cityName = source.nextLine().trim();         //  Get city name
                    if (cityNamePattern.matcher(cityName).matches()) {  //  Set cityNames
                        routCalculator.cities[c] = cityName;
                    } else {
                        throw new DataFormatException("City name contains not only letters.");
                    }
                    int p = Integer.parseInt(source.nextLine().trim()); //  The number of neighbors of cityName
                    for (int k = 0; k < p; k++) {
                        String nrc = source.nextLine().trim();          //  Get parameter 'nr cost':
                        //  nr - index of a city connected to NAME (the index of the first city is 1)
                        //  cost - the transportation cost (<=200000)
                        if (nrCostPattern.matcher(nrc).matches()) {
                            String[] nrcArr = nrc.split(delimiter);
                            int nr = Integer.parseInt(nrcArr[0]);
                            if (nr > n || nr < 1) {
                                throw new DataFormatException("Parameter 'nr' is incorrect.");
                            }
                            int cost = Integer.parseInt(nrcArr[1]);
                            if (cost > 200000) {
                                throw new DataFormatException("Parameter 'cost' is incorrect");
                            }
                            routCalculator.routCost[c][nr - 1] = cost;  //  Set the cost of transportation to 'nr'
                        } else {
                            throw new DataFormatException("Parameter 'nr cost' is incorrect.");
                        }
                    }
                }
                int r = Integer.parseInt(source.nextLine().trim()); //  The number of paths to find (<=100)
                if (r > 100) {
                    throw new DataFormatException("Number 'r' is more than 100");
                }
                String[] paths = new String[r];
                for (int d = 0; d < r; d++) {                           //  Init array 'paths'
                    String sd = source.nextLine().trim();
                    if (srcToDstPattern.matcher(sd).matches()) {
                        paths[d] = sd;
                    } else {
                        throw new DataFormatException("Parameter 'source destination' is incorrect.");
                    }
                }
                for (int i = 0; i < paths.length; i++) {
                    String[] pathArr = paths[i].split(delimiter);
                    String src = pathArr[0];                            //  Get 'source' from 'paths'
                    String dst = pathArr[1];                            //  Get 'destination' from 'paths'
                    int srcIndex = routCalculator.getCityIndex(src);    //  Get index of 'source'
                    int dstIndex = routCalculator.getCityIndex(dst);    //  Get index of 'destination'
                    //  Result output
                    System.out.println(routCalculator.getCheapestRoutsFrom(srcIndex)[dstIndex]);
                }
                System.out.println();                                   //  empty line separating the tests
            }
        } catch (NumberFormatException | DataFormatException e) {
            System.out.printf("Invalid format data: %s \nFix source data and try again.", e.getMessage());
        } catch (NoSuchElementException e) {
            System.out.printf("Not enough data: %s \nFix Fix source data and try again.", e.getMessage());
        } catch (FileNotFoundException e) {
            System.out.println("File not found. Check file and try again.");
        }
    }
}
