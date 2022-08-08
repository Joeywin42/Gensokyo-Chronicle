import org.jsoup.Connection;
import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.select.Elements;

import java.io.IOException;
import java.util.ArrayList;

public class CharacterObjectCreator {
    ArrayList<CharacterObjects> allCharacters = new ArrayList<CharacterObjects>();
    String address = "https://en.touhouwiki.net/index.php?title=%s&action=edit";

    public String webScrape(String characterAddress) throws IOException {
        address = String.format(address, characterAddress);
        String agent = "Mozilla/5.0 (Windows NT 10.0; Win64; x64; rv:103.0) Gecko/20100101 Firefox/103.0)";
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

        } catch (Exception e){
            e.printStackTrace();
        }

        return webScrapedInfo;
    }
}
