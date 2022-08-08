

import org.jsoup.Connection;
import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;

import java.io.IOException;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.URL;
import java.net.URLEncoder;
import java.nio.charset.Charset;
import java.nio.charset.StandardCharsets;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.nio.file.StandardOpenOption;
import java.sql.Array;
import java.util.*;


public class CharacterWebscraper {
    String address = "";
    String agent = "";
    static Charset utf8 = StandardCharsets.UTF_8;
    static Path path = Paths.get("src/main/resources/rawHTML.txt");


    public void createAFile() throws IOException {
        if( !Files.exists(path) )
            Files.createFile(path);
    }
    public void writeToFile(String list) {
        try {
            createAFile();
            Files.write( path, Collections.singleton(list),
                    utf8, new StandardOpenOption[]{StandardOpenOption.TRUNCATE_EXISTING});
        } catch (IOException e) {
            System.out.println("Error: writeToFile failed");
            e.printStackTrace();
        }
    }

    //Scraping the Website
    public String webScrape() throws IOException {
        address = "https://en.touhouwiki.net/wiki/Characters";
        agent = "Mozilla/5.0 (Windows NT 10.0; Win64; x64; rv:103.0) Gecko/20100101 Firefox/103.0)";
        System.out.println("Running WebScrape");
        String webScrapedInfo = "";


        Connection.Response response = Jsoup.connect(address)
                .userAgent(agent)
                .execute();

        try {
            Document doc = Jsoup.connect(address)
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





            Elements body = doc
                    .select("table.outcell:nth-child(7) > tbody:nth-child(1)");

           // System.out.println(body);


                webScrapedInfo = (body.select("td").select("p").toString());
                webScrapedInfo = cleaner(webScrapedInfo);




        } catch (Exception e){
            e.printStackTrace();
        }

        return webScrapedInfo;
    }

    private String cleaner(String recievedList){
        recievedList = recievedList.replaceAll("[\u2022]", "\n");
        recievedList = recievedList.replaceAll("<a href=\"/wiki/|\\\".*", "");
        recievedList = recievedList.replaceAll("<p>|File:|.png|\\(", "");

        Scanner scan = new Scanner(recievedList);
        String temp = "";
        try{
            while(scan.hasNextLine()){
                temp += "\n" + scan.nextLine().replaceAll("\\s", "");
            }
        } catch (Exception e){
            e.printStackTrace();
        }

        return temp;

    }


    public static void main(String[] args) throws IOException {
        CharacterWebscraper test = new CharacterWebscraper();
        test.writeToFile(test.webScrape());
        /*for (String str: test.webScrape()) {
            System.out.println(str);
        }*/


        System.out.println("ran");

    }
}
