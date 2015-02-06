package com.example.feedback;

import android.app.Activity;
import android.content.Intent;
import android.graphics.Bitmap;
import android.os.Bundle;
import android.os.Environment;
import android.view.View;
import android.widget.Button;

import java.io.ByteArrayOutputStream;
import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;

public class MainActivity extends Activity {

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
         setContentView(R.layout.first_activity);

        Button next = (Button) findViewById(R.id.button1);
        next.setOnClickListener(new View.OnClickListener() {
            public void onClick(View view) {
            	  Bitmap bitmap;
            	  View v1 = findViewById(R.id.LinearLayout01).getRootView();// get ur root view id
            	  v1.setDrawingCacheEnabled(true); 
            	  bitmap = Bitmap.createBitmap(v1.getDrawingCache());
            	  v1.setDrawingCacheEnabled(false);
            	  
            	  ByteArrayOutputStream bytes = new ByteArrayOutputStream();
            	  bitmap.compress(Bitmap.CompressFormat.JPEG, 40, bytes);
            	  
            	  File folder = new File(Environment.getExternalStorageDirectory().toString()+"/Feedback/Images");
            	  folder.mkdirs();
            	  String extStorageDirectory = folder.toString();
            	  
            	  File f = new File(extStorageDirectory + "/test.jpg");
            	  try {
					f.createNewFile();
					FileOutputStream fo = new FileOutputStream(f);
	            	  fo.write(bytes.toByteArray()); 
	            	  fo.close();
				} catch (IOException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
                /*LoadingScreenActivity.Page = "com.example.feedback.Activity2";
                Intent intent = new Intent(MainActivity.this, LoadingScreenActivity.class);
                startActivity(intent);*/
                Intent myIntent = new Intent(view.getContext(), Activity2.class);
                startActivityForResult(myIntent, 0);
            }

        });
    }
}