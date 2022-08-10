import org.jsoup.Connection;
import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.select.Elements;

import java.io.File;
import java.io.IOException;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.Scanner;

public class CharacterObjectCreator {

        static ArrayList<CharacterObjects> allCharacters = new ArrayList<CharacterObjects>();

        public ArrayList<String> fillNames(){
            ArrayList<String> names = new ArrayList<String>();
            try {
                File file = new File("src/main/resources/characterNames.txt");
                Scanner scanner = new Scanner(file);
                String line = "";
                while(scanner.hasNextLine()){
                    line = scanner.nextLine();
                    names.add(line);
                }
                scanner.close();

            }catch (Exception e){
                e.printStackTrace();
            }
            return names;
        }

    String address = "https://en.touhouwiki.net/index.php?title=%s&action=edit";

    public String webScrape(String characterAddress) throws IOException {

        String thisAddress = String.format(address, characterAddress);
        String agent = "Mozilla/5.0 (Windows NT 10.0; Win64; x64; rv:103.0) Gecko/20100101 Firefox/103.0)";
        System.out.println("Running WebScrape");
        String webScrapedInfo = "";

        Connection.Response response = Jsoup.connect(thisAddress)
                .userAgent(agent)
                .execute();

        try {
            Document doc = Jsoup.connect(thisAddress)
                    .userAgent("Mozilla/5.0 (Windows NT 10.0; Win64; x64; rv:103.0) Gecko/20100101 Firefox/103.0)")
                    .referrer("https://www.google.com/")
                    .header("Accept-Encoding", "gzip, deflate").header("Accept-Language", "en-US,en;q=0.5")
                    .header("Connection", "keep-alive").header("Host", "en.touhouwiki.net")
                    .header("Sec-Fetch-Dest", "document").header("Sec-Fetch-Mode", "navigate")
                    .header("Sec-Fetch-Site", "cross-site").header("TE", "trailers")
                    .header("Upgrade-Insecure-Requests", "1")
                    .header("Accept", "text/html,application/xhtml+xml,application/xml;q=0.9,image/avif,image/webp,*/*;q=0.8")
                    .cookies(response.cookies()).maxBodySize(0)
                    .get();

            Elements body = doc.select("#wpTextbox1");

            webScrapedInfo = body.outerHtml();
            webScrapedInfo = stringCleaner(webScrapedInfo);
            //System.out.println(webScrapedInfo);


        } catch (Exception e){
            e.printStackTrace();
        }



        return webScrapedInfo;
    }
    public void characterCreation(CharacterObjects object, String name) throws IOException {
        Scanner scan = new Scanner(webScrape(name));
        String line = "";
        object.setLink(String.format(address, object.getName()));
        while(scan.hasNextLine()){
            line = scan.nextLine();
            //region parameter setter
            if(line.contains("nameEn =")){
                object.setName(line.replaceAll("\\| nameEn = ", ""));
            }if(line.contains("| image")) {
                line = line.replaceAll("^(.*?)\\[\\[", "");
                object.setImage(line.replaceAll("\\|.*", ""));
            }if(line.contains("chartitle =")){
                line = line.replaceAll("(.*title\\|)|", "");
                object.setTitle(line.replaceAll("\\|.*", ""));
            }if(line.contains("species =")){
                line = line.replaceAll("(.*\\[\\[)", "");
                object.setSpecies(line.replaceAll("\\]\\]", ""));
            }if (line.contains("abilities =")){
                object.setAbilities(line.replaceAll(".*= ", ""));
            }if (line.contains("age =")){
                object.setAge(line.replaceAll(".*= ", ""));
            }if (line.contains("occupation =")){
                object.setOccupation(line.replaceAll(".*= ", ""));
            }if (line.contains("location =")){
                line = line.replaceAll(".*\\[\\[", "");
                object.setLocation(line.replaceAll("\\]\\]" , ""));
            }if (line.contains("MusicThemes =")){
                line = line.replaceAll(".*\\)\\|","");
                object.setMusicThemes(line.replaceAll("}}.*",""));
            }if(line.contains("appOfficialgames =")){
                line = line.replaceAll(".*\\[\\[","");
                object.setOfficialGames(line.replaceAll("\\]\\].*",""));
            }
            //endregion parameter setter
        }
        System.out.println("YAY \n --------------------------\n" + object);
        scan.close();


    }
    public void createAllCharacters() throws IOException {
        ArrayList<String> names = fillNames();
        System.out.println(names);
        int count = 0;
        try {
            for(String name: names){
                System.out.println(name);
                allCharacters.add(new CharacterObjects(name));
                System.out.println(name);
                characterCreation(allCharacters.get(count), name);
                Thread.sleep(1500);
                count++;
            }
            System.out.println(allCharacters);
            System.out.println(allCharacters.size());


        } catch (Exception e){
            System.out.println("printed wrong");
            e.printStackTrace();
        }


    }

    private String stringCleaner(String received) {
        Scanner scan = new Scanner(received);
        StringBuilder temp = new StringBuilder();
        String line = "";
        boolean start = false;
        //System.out.println(webScrapedInfo);
        try {
            while (scan.hasNextLine()) {
                line = scan.nextLine();
                if (line.contains("nameEn ="))
                    start = true;

                if (line.contains("appPrintworks ="))
                    start = false;

                if (start)
                    temp.append("\n").append(line);

            }
        } catch (Exception e){
            e.printStackTrace();
        }
        scan.close();

        return temp.toString();
    }









    public static void main(String[] args) throws IOException {
        CharacterObjectCreator test = new CharacterObjectCreator();

        //test.characterCreation(new CharacterObjects("Tenshi_Hinanawi"));
        //System.out.println(test.fillAllCharacter());
        //test.createAllCharacters();
        ArrayList<CharacterObjects> characters = new ArrayList<>();
        ArrayList<String> names = test.fillNames();
        System.out.println(names);


        for (String name: names) {
            characters.add(new CharacterObjects(name));
        }
        //System.out.println(characters);
        //System.out.println(names);


        for (int i = 20; i < 30; i++){
            test.characterCreation(characters.get(i), names.get(i));

        }
    }
}
