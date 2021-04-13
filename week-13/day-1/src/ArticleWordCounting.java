import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.*;
import java.util.stream.Collectors;

public class ArticleWordCounting {

    public static void main(String[] args) throws IOException {

        Path path = Paths.get("src/article.txt");

        Map<String, Long> content = Files.readAllLines(path)
                .stream()
                .flatMap(word -> Arrays.stream(word.split(" ")))
                .collect(Collectors.groupingBy(string -> string, Collectors.counting()))
                .entrySet()
                .stream()
                .sorted(Map.Entry.comparingByValue(Comparator.reverseOrder()))
                .limit(20)
                .collect(Collectors.toMap(Map.Entry::getKey, Map.Entry::getValue,
                        (oldValue, newValue) -> oldValue, LinkedHashMap::new));

        System.out.println(content);

    }
}