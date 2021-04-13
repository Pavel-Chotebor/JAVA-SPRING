import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.List;
import java.util.Map;
import java.util.OptionalDouble;
import java.util.stream.Collectors;

public class Exercise12_StarWars {

    String name;
    String height;
    String mass;
    String hairColor;
    String skinColor;
    String eyeColor;
    String birthYear;
    String gender;

    public Exercise12_StarWars(String name, String height, String mass, String hairColor, String skinColor, String eyeColor, String birthYear, String gender) {

        this.name = name;
        this.height = height;
        this.mass = mass;
        this.hairColor = hairColor;
        this.skinColor = skinColor;
        this.eyeColor = eyeColor;
        this.birthYear = birthYear;
        this.gender = gender;
    }

    public String getName() {
        return name;
    }

    public float getHeight() {
        if (height.equals("unknown")) {
            return -1;
        }
        return  Float.parseFloat(height.replaceAll("[^0-9.]", ""));
    }


    public float getMass() {
        if (mass.equals("unknown")) {
            return -1;
        }
        return Float.parseFloat(mass.replaceAll("[^0-9.]", ""));
    }


    private static List<String> readFile(String filePath) {
        Path path = Paths.get(filePath);
        try {
            List<String> lines = Files.readAllLines(path);
            return lines;
        } catch (IOException e) {
            throw new RuntimeException("file not accessible");
        }
    }


    public static List<Exercise12_StarWars> createHeroes (String filePath) throws IOException {

        //The method creates a new Ex12_SW_Hero object for each line of the text and then splits the text
        // into individual hero properties, which are then assigned to the Hero using Heroe´s constructor.

        List <Exercise12_StarWars> heroes = readFile(filePath).stream()
                .skip(1)
                .map(data -> data.split(";"))
                .map(data -> new Exercise12_StarWars(
                         data[0]
                        ,data[1]
                        ,data[2]
                        ,data[3]
                        ,data[4]
                        ,data[5]
                        ,data[6]
                        ,data[7]))
                .collect(Collectors.toList());
        return heroes;
    }


    public static void main(String[] args) throws IOException {

        //The most heavy character
        Map<String, Float> heroesWeight = createHeroes("src/personalData.txt").stream()
                .collect(Collectors.toMap(Exercise12_StarWars::getName, Exercise12_StarWars::getMass));

        String mostHeavyHero = heroesWeight.entrySet().stream()
                .max(Map.Entry.comparingByValue())
                .map(Map.Entry::getKey)
                .get();

        System.out.println("The most heavy character is: " + mostHeavyHero);



        //Average height:
        Map<String, Float> heroesHeight = createHeroes("src/personalData.txt").stream()
                .collect(Collectors.toMap(Exercise12_StarWars::getName, Exercise12_StarWars::getHeight));

        double averageHeight = heroesHeight.entrySet().stream()
                .mapToDouble(hero -> hero.getValue())
                .average()
                .getAsDouble();

        System.out.println("Average height: " + averageHeight);



        //Average height of females:
        Map<String, Float> femalesHeroesHeight = createHeroes("src/personalData.txt").stream()
                .filter(hero -> hero.gender.equals("female"))
                .collect(Collectors.toMap(Exercise12_StarWars::getName, Exercise12_StarWars::getHeight));

        double averageHeightOfFemale = femalesHeroesHeight.entrySet().stream()
                .mapToDouble(hero -> hero.getValue())
                .average()
                .getAsDouble();

        System.out.println("Average height of females: " + averageHeightOfFemale);
    }
}

//Print the name of the heaviest character (if the mass is unknown, ignore that character)
//Print the average height of the male characters
//Print the average height of the female characters
//💪 Get the age distribution of the characters by gender (where the gender can be "male", "female" and "other")
//The age groups are: "below 21", "between 21 and 40", "above 40" and "unknown"
//The result should be a Map<String, Map<String, Integer>>

