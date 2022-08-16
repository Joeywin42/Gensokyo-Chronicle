import java.io.File;
import java.io.IOException;
import java.nio.charset.Charset;
import java.nio.charset.StandardCharsets;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.nio.file.StandardOpenOption;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Scanner;

public class CharacterObjectCreatorFromTextFile {
    static Charset utf8 = StandardCharsets.UTF_8;
    static Path path = Paths.get("src/main/resources/allCharactersNeo.txt");

    public void createAFile() throws IOException {
        if( !Files.exists(path) )
            Files.createFile(path);
    }
    public void writeToFile(String list) {
        try {
            createAFile();
            Files.write( path, Collections.singleton(list),
                    utf8, new StandardOpenOption[]{StandardOpenOption.APPEND});
        } catch (IOException e) {
            System.out.println("Error: writeToFile failed");
            e.printStackTrace();
        }
    }

    public ArrayList<CharacterObjects> readFromfile(){
        var characters = new ArrayList<CharacterObjects>();
        try{
            File file = new File("src/main/resources/allCharacters.txt");
            Scanner scan = new Scanner(file);
            String line = "";
            int count = 0;
            //region parameters
            String name = "";
            String image = "";
            String title = "";
            String species = "";
            String abilities = "";
            String age = "";
            String occupation = "";
            String location = "";
            String musicThemes = "";
            String officialGames = "";
            String link = "";
            //endregion
            int lineNumber = 1;
            while(scan.hasNextLine()){
                line = scan.nextLine();
                switch (count){
                    case 0:
                         name = line.substring(6,line.length()-1);
                         count++;
                         break;
                    case 1:
                        image = line.substring(7, line.length()-1);
                        count++;
                        break;
                    case 2:
                        title = line.substring(7, line.length()-1);
                        count++;
                        break;
                    case 3:
                        species = line.substring(9, line.length()-1);
                        count++;
                        break;
                    case 4:
                        abilities = line.substring(11, line.length()-1);
                        count++;
                        break;
                    case 5:
                        age = line.substring(5, line.length()-1);
                        count++;
                        break;
                    case 6:
                        occupation = line.substring(12, line.length()-1);
                        count++;
                        break;
                    case 7:
                        location = line.substring(10, line.length()-1);
                        count++;
                        break;
                    case 8:
                        musicThemes = line.substring(13, line.length()-1);
                        count++;
                        break;
                    case 9:
                        officialGames = line.substring(15, line.length()-1);
                        count++;
                        break;
                    case 10:
                        link = line.substring(6);
                        count++;
                        break;
                    case 11:
                        //System.out.println(count);
                        System.out.println("Clear Line and resetting");
                        count = 0;
                        characters.add(new CharacterObjects(name, image, title, species, abilities, age, occupation,
                                location, musicThemes,officialGames ,link));
                        break;
                    default:
                        System.out.println("Something went wrong");
                        System.out.println(count);
                    break;
                }
                lineNumber++;
            }
            System.out.println(lineNumber);
        }catch (Exception e){
            e.printStackTrace();
        }
        return characters;
    }

    public void linkFixer(ArrayList<CharacterObjects> chars){
        String newLink = "https://en.touhouwiki.net/wiki/";
        for (CharacterObjects character: chars) {
            newLink = newLink + character.getName();
            character.setLink(newLink);
            newLink = "https://en.touhouwiki.net/wiki/";
        }
    }

    public void imageFixer(ArrayList<CharacterObjects> chars){
        String newImage = "https://en.touhouwiki.net/wiki/";
        for (CharacterObjects characters: chars){
            newImage = newImage + characters.getImage();
            characters.setImage(newImage);
            newImage = "https://en.touhouwiki.net/wiki/";
        }
    }


    public static void main(String[] args) {
        CharacterObjectCreatorFromTextFile test = new CharacterObjectCreatorFromTextFile();

        var testList = test.readFromfile();

        test.linkFixer(testList);
        test.imageFixer(testList);

        //testList.stream().forEach(character -> System.out.println(character));

        testList.stream().forEach(character -> test.writeToFile(character.toString()));

        //System.out.println(testList.get(testList.size()-1));


    }
}
