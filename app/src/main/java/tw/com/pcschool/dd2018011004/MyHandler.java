package tw.com.pcschool.dd2018011004;

import android.util.Log;

import org.xml.sax.Attributes;
import org.xml.sax.Locator;
import org.xml.sax.SAXException;
import org.xml.sax.helpers.DefaultHandler;

import java.util.ArrayList;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

/**
 * Created by Student on 2018/1/10.
 */

public class MyHandler extends DefaultHandler {
    boolean isTitle = false;
    boolean isItem = false;
    //public ArrayList<String> titleList = new ArrayList<>();

    boolean isLink = false;
    StringBuilder linkSB = new StringBuilder();
    //public ArrayList<String> linkList = new ArrayList<>();

    boolean isDescription = false;
    public ArrayList<Mobile01NewsItem> newsItems = new ArrayList<>();
    Mobile01NewsItem Newsitem;

    StringBuilder descSB = new StringBuilder();

    @Override
    public void startElement(String uri, String localName, String qName, Attributes attributes) throws SAXException {
        super.startElement(uri, localName, qName, attributes);

        //Log.d("NET", qName);
//        if (qName.equals("item"))
//        {
//            isItem = true;
//            Newsitem = new Mobile01NewsItem();
//        }
//        if (qName.equals("title"))
//        {
//            isTitle = true;
//        }
//        if (qName.equals("link"))
//        {
//            isLink = true;
//        }
        switch(qName)               //java7之後switch才可以放字串
        {
            case "item":
                isItem = true;
                Newsitem = new Mobile01NewsItem();
                break;
            case "title":
                isTitle = true;
                break;
            case "link":
                isLink = true;
                break;
            case "description":
                isDescription = true;
                //descSB = new StringBuilder();
                break;
        }
    }

    @Override
    public void endElement(String uri, String localName, String qName) throws SAXException {
        super.endElement(uri, localName, qName);

//        if (qName.equals("item"))
//        {
//            isItem = false;
//            newsItems.add(Newsitem);
//        }
//        if (qName.equals("title"))
//        {
//            isTitle = false;
//        }
//        if (qName.equals("link"))
//        {
//            isLink = false;
//            if(isItem) {
//                //linkList.add(linkSB1.toString());
//                Newsitem.link = linkSB1.toString();
//                linkSB1 = new StringBuilder();
//            }
//        }
        switch(qName)
        {
            case "item":
                isItem = false;
                newsItems.add(Newsitem);
                break;
            case "title":
                isTitle = false;
                break;
            case "link":
                isLink = false;
                if(isItem) {
                    Newsitem.link = linkSB.toString();
                    linkSB = new StringBuilder();
                }
                break;
            case "description":
                isDescription = false;
                if(isItem) {
                    String str = descSB.toString();

                    Pattern pattern1 = Pattern.compile("https.*jpg");
                    Matcher m1 = pattern1.matcher(str);
                    String imgurl1 = "";
                    if (m1.find())
                    {
                        imgurl1 = m1.group(0);
                    }
                    Newsitem.imgurl = imgurl1;

                    str = str.replaceAll("<img.*/>", "");       //. *是正規表示法,且不能有空格
                    Newsitem.description = str;

                    descSB = new StringBuilder();
                }
                break;

        }
    }

    @Override
    public void characters(char[] ch, int start, int length) throws SAXException {
        super.characters(ch, start, length);

        if (isItem && isTitle)
        {
            Log.d("NET", new String(ch, start, length));

            //titleList.add(new String(ch, start, length));
            Newsitem.title = new String(ch, start, length);
        }
        if (isItem && isLink)
        {
            Log.d("NET", new String(ch, start, length));

            linkSB.append(new String(ch, start, length));
        }
        if (isItem && isDescription)
        {
            descSB.append(new String(ch, start, length));
        }
    }
}
