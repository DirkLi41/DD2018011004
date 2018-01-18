package tw.com.pcschool.dd2018011004;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.ListView;

import org.xml.sax.InputSource;
import org.xml.sax.SAXException;
import org.xml.sax.XMLReader;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.StringReader;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.ProtocolException;
import java.net.URL;

import javax.xml.parsers.ParserConfigurationException;
import javax.xml.parsers.SAXParser;
import javax.xml.parsers.SAXParserFactory;

public class MainActivity extends AppCompatActivity {

    ListView lv1;
//    ArrayAdapter<String> aada1;
    MyAdapter ada1;
    MyHandler dataHandler1;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        lv1 = (ListView) findViewById(R.id.listView);
        lv1.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> adapterView, View view, int i, long l) {
                Intent it1 = new Intent(MainActivity.this, DetailActivity.class);
                //it1.putExtra("Link", dataHandler1.linkList.get(i));
                it1.putExtra("Link", dataHandler1.newsItems.get(i).link);
                startActivity(it1);
            }
        });
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {

        getMenuInflater().inflate(R.menu.main_menu, menu);

        return super.onCreateOptionsMenu(menu);
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {

        switch (item.getItemId())
        {
            case R.id.menu_reload:
                new Thread()
                {
                    @Override
                    public void run() {
                        super.run();
                        String str_url = "https://www.mobile01.com/rss/news.xml";
                        URL url = null;
                        try {
                            url = new URL(str_url);
                            HttpURLConnection conn = (HttpURLConnection) url.openConnection();
                            conn.setRequestMethod("GET");
                            conn.connect();
                            InputStream inputStream = conn.getInputStream();
                            InputStreamReader isr = new InputStreamReader(inputStream);
                            BufferedReader br = new BufferedReader(isr);
                            StringBuilder sb = new StringBuilder();
                            String str;

                            while ((str = br.readLine()) != null)
                            {
                                sb.append(str);
                            }

                            String str1 = sb.toString();
                            Log.d("NET", str1);

                            dataHandler1 = new MyHandler();
                            SAXParserFactory spf = SAXParserFactory.newInstance();
                            SAXParser sp = spf.newSAXParser();
                            XMLReader xr = sp.getXMLReader();
                            xr.setContentHandler(dataHandler1);
                            xr.parse(new InputSource(new StringReader(str1)));

                            br.close();
                            isr.close();
                            inputStream.close();

                            runOnUiThread(new Runnable() {
                                @Override
                                public void run() {
//                                    aada1 = new ArrayAdapter<String>(
//                                            MainActivity.this,
//                                            android.R.layout.simple_list_item_1,
//                                            dataHandler1.titleList
//                                    );

//                                    String data[] = new String[dataHandler1.newsItems.size()];
//                                    for (int i=0;i<data.length;i++)
//                                    {
//                                        data[i] = dataHandler1.newsItems.get(i).title;
//                                    }
//
//                                    aada1 = new ArrayAdapter<String>(
//                                            MainActivity.this,
//                                            android.R.layout.simple_list_item_1,
//                                            data
//                                    );
                                    ada1 = new MyAdapter(MainActivity.this, dataHandler1.newsItems);
                                    lv1.setAdapter(ada1);
                                }
                            });

                        } catch (MalformedURLException e) {
                            e.printStackTrace();
                        } catch (ProtocolException e) {
                            e.printStackTrace();
                        } catch (IOException e) {
                            e.printStackTrace();
                        } catch (SAXException e) {
                            e.printStackTrace();
                        } catch (ParserConfigurationException e) {
                            e.printStackTrace();
                        }
                    }
                }.start();
                break;
        }

        return super.onOptionsItemSelected(item);



    }

    //    public void click1(View v)
//    {
//        new Thread()
//        {
//            @Override
//            public void run() {
//                super.run();
//
////                String str_url1 = "https://www.google.com.tw";
////                URL url = null;
////                try
////                {
////                    url = new URL(str_url1);
////                    HttpURLConnection conn1 = (HttpURLConnection) url.openConnection();
////                    conn1.setRequestMethod("GET");
////                    conn1.connect();
////                    InputStream is1 = conn1.getInputStream();
////                    InputStreamReader isr1 = new InputStreamReader(is1);
////                    BufferedReader br1 = new BufferedReader(isr1);
////                    String str1 = br1.readLine();
////                    Log.d("NET", str1);
////                    br1.close();
////                    isr1.close();
////                    is1.close();
////                } catch (ProtocolException e) {
////                    e.printStackTrace();
////                } catch (MalformedURLException e) {
////                    e.printStackTrace();
////                } catch (IOException e) {
////                    e.printStackTrace();
////                }
//
////                String str_url = "http://rate.bot.com.tw/xrt?Lang=zh-TW";
////                URL url = null;
////                try {
////                    url = new URL(str_url);
////                    HttpURLConnection conn = (HttpURLConnection) url.openConnection();
////                    conn.setRequestMethod("GET");
////                    conn.connect();
////                    InputStream inputStream = conn.getInputStream();
////                    InputStreamReader isr = new InputStreamReader(inputStream);
////                    BufferedReader br = new BufferedReader(isr);
////                    StringBuilder sb = new StringBuilder();
////                    String str;
////
////                    while ((str = br.readLine()) != null)
////                    {
////                        sb.append(str);
////                    }
////                    String str1 = sb.toString();
////                    Log.d("NET", str1);
////                    int index1 = str1.indexOf("日圓 (JPY)");
////                    int index2 = str1.indexOf("本行現金賣出", index1);
////                    int index3 = str1.indexOf("0.266", index2);
////                    Log.d("NET", "index1:" + index1 + "index2:" + index2 + "index3:" + index3);
////                    String data1 = str1.substring(index2+56, index2+61);
////                    Log.d("NET", data1);
////                    br.close();
////                    isr.close();
////                    inputStream.close();
////                } catch (MalformedURLException e) {
////                    e.printStackTrace();
////                } catch (ProtocolException e) {
////                    e.printStackTrace();
////                } catch (IOException e) {
////                    e.printStackTrace();
////                }
//                String str_url = "https://www.mobile01.com/rss/news.xml";
//                URL url = null;
//                try {
//                    url = new URL(str_url);
//                    HttpURLConnection conn = (HttpURLConnection) url.openConnection();
//                    conn.setRequestMethod("GET");
//                    conn.connect();
//                    InputStream inputStream = conn.getInputStream();
//                    InputStreamReader isr = new InputStreamReader(inputStream);
//                    BufferedReader br = new BufferedReader(isr);
//                    StringBuilder sb = new StringBuilder();
//                    String str;
//
//                    while ((str = br.readLine()) != null)
//                    {
//                        sb.append(str);
//                    }
//
//                    String str1 = sb.toString();
//                    Log.d("NET", str1);
//
//                    final MyHandler dataHandler1 = new MyHandler();
//                    SAXParserFactory spf = SAXParserFactory.newInstance();
//                    SAXParser sp = spf.newSAXParser();
//                    XMLReader xr = sp.getXMLReader();
//                    xr.setContentHandler(dataHandler1);
//                    xr.parse(new InputSource(new StringReader(str1)));
//
//                    br.close();
//                    isr.close();
//                    inputStream.close();
//
//                    runOnUiThread(new Runnable() {
//                        @Override
//                        public void run() {
//                            aada1 = new ArrayAdapter<String>(
//                                    MainActivity.this,
//                                    android.R.layout.simple_list_item_1,
//                                    dataHandler1.titleList
//                            );
//                            lv1.setAdapter(aada1);
//                        }
//                    });
//
//                } catch (MalformedURLException e) {
//                    e.printStackTrace();
//                } catch (ProtocolException e) {
//                    e.printStackTrace();
//                } catch (IOException e) {
//                    e.printStackTrace();
//                } catch (SAXException e) {
//                    e.printStackTrace();
//                } catch (ParserConfigurationException e) {
//                    e.printStackTrace();
//                }
//            }
//        }.start();
//    }
}
