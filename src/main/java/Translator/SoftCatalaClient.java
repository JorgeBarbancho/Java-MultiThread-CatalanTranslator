package Translator;

import java.io.IOException;
import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;

public class SoftCatalaClient {
 
    public String translate(String word) {

        try {
            String urlS = "https://www.softcatala.org/diccionari-multilingue/paraula/";
            String result = "";           
            Elements childs = parsejador(urlS+word);
            for (Element child : childs) {
                result = result + child.text() + "/";
            }          
                
            return result;
            
        } catch (IOException ex) {
            System.out.println("Errorrrrrr");
            return null;
        }
    }
  
    public Elements parsejador(String urlS) throws IOException{
      
        Document doc = Jsoup.connect(urlS).get();
        Element elem = doc.getElementsByClass("col-sm-8 multilingue_list").first();
        Element ul = elem.children().get(0);
        Elements childs = ul.children();      
      
        return childs;
    }
}