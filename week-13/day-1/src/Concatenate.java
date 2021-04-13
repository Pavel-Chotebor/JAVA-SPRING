import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;

public class Concatenate {

    public static void main(String[] args) {


        List <Character> characters = new ArrayList<>();
        characters.addAll(Arrays.asList('e', 'x', 'a', 'm', 'p', 'l', 'e'));

        String finalString = characters.stream()
                .map(character -> character.toString())
                .collect(Collectors.joining());

        System.out.println(finalString);

    }
}
//Write a Stream Expression to concatenate a Character list to a string!