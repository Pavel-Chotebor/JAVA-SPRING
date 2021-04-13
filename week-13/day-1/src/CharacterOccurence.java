import java.util.Arrays;
import java.util.Map;
import java.util.stream.Collectors;

import static java.util.stream.Collectors.toMap;

public class CharacterOccurence {

    public static void main(String[] args) {

        String string = "Example";

        Map<Character, Long> letterOccurence = string.chars()
                .mapToObj(letter -> (char) letter)
                .collect(Collectors.groupingBy(ch -> ch, Collectors.counting()));

        System.out.println(letterOccurence);

    }
}
//Write a Stream Expression to find the frequency of characters in a given string!