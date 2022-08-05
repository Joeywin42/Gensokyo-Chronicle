

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
import java.util.Scanner;



public class CharacterWebscraper {
    String address = "";
    String agent = "";

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




//            System.out.println(doc.nodeName());
//
//            URL url = new URL(address);
//            HttpURLConnection connect = (HttpURLConnection) url.openConnection();
//            connect.setRequestProperty("userAgent", "Mozilla/5.0 (Windows NT 10.0; Win64; x64; rv:103.0) Gecko/20100101 Firefox/103.0)");
//            connect.setRequestProperty("Accept-Encoding", "gzip, deflate, br");
//            connect.setRequestProperty("Accept-Language", "en-US,en;q=0.5");
//            connect.setRequestProperty("Connection", "keep-alive");
//            connect.setRequestProperty("Host", "en.touhouwiki.net");
//            connect.setRequestProperty("Sec-Fetch-Dest", "document");
//            connect.setRequestProperty("Sec-Fetch-Mode", "navigate");
//            connect.setRequestProperty("Sec-Fetch-Site", "cross-site");
//            connect.setRequestProperty("TE", "trailers");
//            connect.setRequestProperty("Upgrade-Insecure-Requests", "1");
//            connect.setRequestProperty("Accept", "text/html,application/xhtml+xml,application/xml;q=0.9,image/avif,image/webp,*/*;q=0.8");
//            String cookie = connect.getHeaderField("Set-Cookie").split(";")[0];
//
//            connect.setRequestProperty("Cookie", cookie);
//            connect.connect();
//
//
//
//            Scanner in = new Scanner(connect.getInputStream());
//
//            while(in.hasNextLine()){
//                System.out.println(in.nextLine());
//            }

            Elements body = doc
                    .select("table.outcell:nth-child(7) > tbody:nth-child(1)");

            System.out.println(body);

            for(Element e: body){
                webScrapedInfo += e.select("tr").toString();
            }


        } catch (Exception e){
            e.printStackTrace();
        }



        return webScrapedInfo;
    }

    public static void main(String[] args) throws IOException {
        CharacterWebscraper test = new CharacterWebscraper();
        System.out.println(test.webScrape());


        System.out.println("ran");

    }
}
