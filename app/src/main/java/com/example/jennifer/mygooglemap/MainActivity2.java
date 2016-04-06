package com.example.jennifer.mygooglemap;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;

import android.view.View;
import android.widget.*;

public class MainActivity2 extends AppCompatActivity {
    TextView MyTextView01;
    View.OnClickListener listener01 = null;
    Button Mybutton01;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main2);

        MyTextView01=(TextView)findViewById(R.id.TextView01);            //設定R.id.button在程式中為物件Mybutton

        try {
            FileInputStream in = openFileInput("name");                  //開一個檔案供讀入資料，檔名"name"
            int length=in.available();                                   //取得檔案長度
            byte[] data = new byte[length];
            in.read(data);                                               //會把兩筆輸入併成一筆讀進來
            String str = new String(data);
            MyTextView01.setText(str);                                   //把讀進來的內容顯示在MyeditText51
            in.close();                                                  //關檔
        }
        catch(FileNotFoundException e){
        }
        catch (IOException e) {
        }

        listener01 = new View.OnClickListener() {                       //設定一個OnClick事件處理程
            public void onClick(View v) {                               //按下後顯示Main1Activity.class頁面
                MyTextView01.setText(" ");
                try{
                    FileOutputStream out = openFileOutput("name", MODE_PRIVATE); //開一個檔案供寫入資料，檔名"name"
                    out.close();                                                       //關檔
                }
                catch(FileNotFoundException e){
                }
                catch (IOException e) {
                }
            }
        };
        Mybutton01=(Button)findViewById(R.id.button01);                 //設定R.id.button在程式中為物件Mybutton
        Mybutton01.setOnClickListener(listener01);                       //設定物件Mybutton的OnClick事件處理程式為listener0，也就是OnClickListener()

    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_main_activity2, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        int id = item.getItemId();

        //noinspection SimplifiableIfStatement
        if (id == R.id.action_settings) {
            return true;
        }

        return super.onOptionsItemSelected(item);
    }
}
