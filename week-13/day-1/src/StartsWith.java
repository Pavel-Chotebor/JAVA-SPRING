import java.util.Arrays;
import java.util.List;

public class StartsWith {

    public static void main(String[] args) {

        List<String> cities = Arrays.asList("ROME", "LONDON", "NAIROBI", "CALIFORNIA", "ZURICH", "NEW DELHI", "AMSTERDAM", "ABU DHABI", "PARIS");

        cities.stream()
                .filter(city -> city.startsWith("A"))
                .forEach(city -> System.out.println(city));

    }
}

//Write a Stream Expression to find the strings which starts with a given letter(as parameter), in the following list:
