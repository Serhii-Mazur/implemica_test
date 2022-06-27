package task_2;

public abstract class RoutCalculator {
    public String[] cities;         //  Array of cities (names)
    public int[][] routCost;        //  Array of transportation costs
    public int topCost = 200000;    //  Max value of transportation cost to neighbour

    public RoutCalculator(int citiesNumber) {
        this.cities = new String[citiesNumber];
        this.routCost = new int[citiesNumber][citiesNumber];
        for (int i = 0; i < routCost.length; i++) {
            for (int j = 0; j < citiesNumber; j++) {
                if (i != j) {
                    routCost[i][j] = topCost + 1;
                }
            }
        }
    }

    public abstract Integer[] getCheapestRoutsFrom(int source);

    //  Return the (city)index by city name
    public int getCityIndex(String cityName) {

        for (int i = 0; i < cities.length; i++) {
            if (cities[i].equals(cityName)) {
                return i;
            }
        }

        throw new IllegalArgumentException("Array of cities not contains such cityName.");
    }
}
