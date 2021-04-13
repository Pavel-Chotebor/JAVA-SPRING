import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;

public class AverageOfOdddNumbers {

    public static void main(String[] args) {

        List<Integer> numbers = Arrays.asList(1, 3, -2, -4, -7, -3, -8, 12, 19, 6, 9, 10, 14);

      numbers.stream()
              .filter(number -> number % 2 != 0)
              .mapToInt(number -> number)   // because from list  -> to int
              .average()
              .ifPresent(System.out::println);

    }
}
//Write a Stream Expression to get the average value of the odd numbers from the following list: