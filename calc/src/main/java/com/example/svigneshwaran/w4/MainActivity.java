package com.example.svigneshwaran.w4;

import android.os.Bundle;
import android.app.Activity;
import android.view.View;
import android.webkit.WebView;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.GridView;
import android.widget.TextView;
import android.widget.Toast;

public class MainActivity extends Activity {
    GridView grid;
    String[] web = {"  7","  8","  9","  +",
            "  4","  5","  6","  -",
            "  1","  2","  3","  *",
            "   ","  0","  .","  /",
            "  =","  (","  )","   "
    } ;

String pas="";
    String html_value;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        setContentView(R.layout.activity_main);
        grid = (GridView)findViewById(R.id.grid);
        final Button cl=(Button)findViewById(R.id.clr);
        final Button dl=(Button)findViewById(R.id.del);

        ArrayAdapter<String> adapter = new ArrayAdapter<String>(this,
                android.R.layout.simple_list_item_1, web);
        grid.setAdapter(adapter);
        grid.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            final TextView dsp=(TextView)findViewById(R.id.textView);
            final TextView res=(TextView)findViewById(R.id.result);
            //char op;
            //Double val1,val2,ans;
            String l="";

                                    //MAIN

            WebView browser = (WebView) findViewById(R.id.wV);
            @Override
            public void onItemClick(AdapterView<?> parent, View view,
                                    int position, long id) {
                String t = web[+position];//identify the button
                t=t.substring(2,3);//filter last char
                if(t.equals("=")) {
                    pas=l;
                    html_value = "<!DOCTYPE html><html><body><font size=5;color=#0000FF><script>var x="+pas+";document.write (x);</script>" +
                            "</body></html>";
                    //Toast.makeText(MainActivity.this,pas, Toast.LENGTH_SHORT).show();
                    browser.getSettings().setJavaScriptEnabled(true);
                    browser.loadData(html_value, "text/html", "UTF-8");
                }
                else{
                    l = l.concat(t);
                    dsp.setText(l);
                    res.setText(l);
                }
                cl.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        dsp.setText("");
                        res.setText("");
                        //Toast.makeText(MainActivity.this,l, Toast.LENGTH_SHORT).show();
                        l= dsp.getText().toString();
                    }
                });
                dl.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        l= dsp.getText().toString();
                        int leng=l.length();
                        if(leng>0) {
                            leng -= 1;
                            l = l.substring(0, leng);
                            dsp.setText(l);
                            res.setText(l);
                        }
                        else
                            Toast.makeText(MainActivity.this,"ALL CLEAR", Toast.LENGTH_SHORT).show();
                    }
                });
            }
        });
    }
}
