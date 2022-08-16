import org.jsoup.Connection;
import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.select.Elements;

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
    public void characterCreation(CharacterObjects object) throws IOException {
        Scanner scan = new Scanner(webScrape(object.getName()));
        String line = "";
        object.setLink(String.format(address, object.getName()));
        while(scan.hasNextLine()) {
            line = scan.nextLine();
            //region parameter setter
            if (line.startsWith("nameEn =")) {
                object.setName(line.replaceAll("\\| nameEn = ", ""));
            }
            if (line.startsWith("| image")) {
                line = line.replaceAll("^(.*?)\\[\\[", "");
                object.setImage(line.replaceAll("\\|.*", ""));
            }
            if (line.startsWith("| chartitle")) {
                line = line.replaceAll("(.*title\\|)|", "");
                object.setTitle(line.replaceAll("\\|.*", ""));
            }
            if (line.startsWith("| species")) {
                line = line.replaceAll("(.*\\[\\[)", "");
                object.setSpecies(line.replaceAll("\\]\\]", ""));
            }
            if (line.startsWith("| abilities")) {
                object.setAbilities(line.replaceAll(".*= ", ""));
            }
            if (line.startsWith("| age")) {
                object.setAge(line.replaceAll(".*= ", ""));
            }
            if (line.startsWith("| occupation")) {
                object.setOccupation(line.replaceAll(".*= ", ""));
            }
            if (line.startsWith("| location")) {
                line = line.replaceAll(".*\\[\\[", "");
                object.setLocation(line.replaceAll("\\]\\]", ""));
            }
            if (line.startsWith("| MusicThemes")) {
                line = line.replaceAll(".*\\)\\|", "");
                object.setMusicThemes(line.replaceAll("}}.*", ""));
            }
            if (line.startsWith("| appOfficialgames")) {
                line = line.replaceAll(".*\\[\\[", "");
                object.setOfficialGames(line.replaceAll("\\]\\].*", ""));
            }
        }
        scan.close();
        System.out.println("YAY \n --------------------------\n" + object);
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
    static Charset utf8 = StandardCharsets.UTF_8;
    static Path path = Paths.get("src/main/resources/allCharacters.txt");

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

    public static void main(String[] args) throws IOException, InterruptedException {
        CharacterObjectCreator test = new CharacterObjectCreator();

        ArrayList<CharacterObjects> characters = new ArrayList<>();
        ArrayList<String> names = test.fillNames();
        System.out.println(names);


/*        for (String name: names) {
            characters.add(new CharacterObjects(name));
        }
        for (int i = 0; i < names.size()-1; i++){
            test.characterCreation(characters.get(i));
            Thread.sleep(1*1000);

        }
        for(CharacterObjects object: characters) {
            test.writeToFile(object.toString());
        }*/
    }
}
