import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Map;
import java.util.function.Function;
import java.util.stream.Collectors;

public class Fox {

    private String name;
    private String color;
    private int age;

    public String getColor() {
        return color;
    }

    public String getName() {
        return name;
    }

    @Override
    public String toString() {
        return name ;
    }

    public Fox (String name, String color, int age ) {
        this.name=name;
        this.color=color;
        this.age=age;
    }

    public static void main(String[] args) {

        Fox fox1 = new Fox("Fox1", "green", 3);
        Fox fox2 = new Fox("Fox2", "blue", 4);
        Fox fox3 = new Fox("Fox3", "green", 2);
        Fox fox4 = new Fox("Fox4", "green", 7);
        Fox fox5 = new Fox("Fox5", "yellow", 10);
        Fox fox6 = new Fox("Fox6", "blue", 1);

        List <Fox> foxes = new ArrayList<>();
        foxes.addAll(Arrays.asList(fox1, fox2, fox3, fox4, fox5, fox6));

        foxes.stream()      // finding foxes with green color
                .filter(fox -> fox.color.equals("green"))
                .forEach(fox -> System.out.println(fox.getName()));

        foxes.stream()      // finding foxes with green color and with age less then five
                .filter(fox -> (fox.color.equals("green")) && (fox.age < 5))
                .forEach(fox -> System.out.println(fox.getName()));

        Map<String, Long> foxMap = foxes.stream()  // finding the frequency of foxes by color
                .collect(Collectors.groupingBy(Fox::getColor, Collectors.counting()));

        System.out.println(foxMap);
    }
}
//Write a Stream Expression to find the foxes with green color!
//Write a Stream Expression to find the foxes with green color, and age less then 5 years!
//Write a Stream Expression to find the frequency of foxes by color!