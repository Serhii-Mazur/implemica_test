package task_2;

import java.util.Arrays;

public class DijkstrasAlgorithmBasedRoutCalculator extends RoutCalculator {

    public DijkstrasAlgorithmBasedRoutCalculator(int citiesNumber) {
        super(citiesNumber);
    }

    @Override
    public Integer[] getCheapestRoutsFrom(int source) {
        Integer[] result = new Integer[routCost.length];
        Arrays.fill(result, topCost + 1);               //  Set default (unreachable) cost values
        result[source] = routCost[source][source];          //  Set the source city index
        boolean[] visited = new boolean[routCost.length];   //  Set all cities as "non-visited"
        for (int[] costTo : routCost) {
            int cheapestDirectionCityIndex = getCheapestDirection(result, visited); //   Get next cheapest direction (index of next city)
            visited[cheapestDirectionCityIndex] = true;     //  Mark city as "visited"
            for (int j = 0; j < routCost.length; j++) {     //  Fill the cheapest routs array from source city
                result[j] = Math.min(result[j],
                        result[cheapestDirectionCityIndex] + routCost[cheapestDirectionCityIndex][j]);
            }
        }

        return result;
    }

    protected int getCheapestDirection(Integer[] result, boolean[] visited) {
        int cheapest = -1;
        for (int i = 0; i < routCost.length; i++) {
            if (!visited[i]) {
                if ((cheapest < 0) || (result[i] < result[cheapest])) {
                    cheapest = i;
                }
            }
        }

        return cheapest;
    }
}
