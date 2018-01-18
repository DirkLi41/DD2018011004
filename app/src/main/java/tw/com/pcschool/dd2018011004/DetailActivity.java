package tw.com.pcschool.dd2018011004;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.webkit.WebChromeClient;
import android.webkit.WebView;

public class DetailActivity extends AppCompatActivity {

    WebView wv1;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_detail);

        Intent it1 = getIntent();
        String link = it1.getStringExtra("Link");
        Log.d("NET", "Link:" + link);


        wv1 = (WebView) findViewById(R.id.webView);
        wv1.setWebChromeClient(new WebChromeClient());
        wv1.getSettings().setJavaScriptEnabled(true);
        wv1.loadUrl(link);
    }
}
