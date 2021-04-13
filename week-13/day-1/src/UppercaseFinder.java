import java.util.Optional;
import java.util.stream.Collectors;
import java.util.stream.Stream;

public class UppercaseFinder {

    public static void main(String[] args) {


        String string = "Example";
        string.codePoints()                                    // at this point String can be use in stream.  Or  2.way is:  string.char()
                .filter(Character::isUpperCase)                // or .filter(s -> Character.isUpperCase(s))
                .forEach(s -> System.out.println((char)s));
    }
}

//Write a Stream Expression to find the uppercase characters in a string!