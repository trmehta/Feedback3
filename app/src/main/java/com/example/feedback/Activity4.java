package com.example.feedback;

import android.app.Activity;
import android.content.Intent;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.os.AsyncTask;
import android.os.Bundle;
import android.os.Environment;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;

import org.json.JSONObject;

import java.io.ByteArrayOutputStream;
import java.util.List;

import retrofit.Callback;
import retrofit.RestAdapter;
import retrofit.RetrofitError;
import retrofit.client.Response;

/**
 * Created by trmehta on 1/23/2015.
 */

class FeedbackApiResponse {

    //public String html_attributions;
    public List<FeedbackObject> feedback;
}


public class Activity4 extends Activity {

    public static final String ENDPOINT = "http://www.swroopsingh.com";
     //get root url
     private static  final String PAYPAL_BODY = "{\"amount\":{\"value\":\"5\",\"currency\":\"USD\" }, \"payee\":{ \"id\":\"cdayanand-pre@paypal.com\", \"type\":\"EMAIL\" }, \"fee\":{ \"payer\":\"PAYER\"},\"payment_type\":\"PERSONAL\"}";


    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity4);

        //DatabaseHandler db = new DatabaseHandler(this);

        Button edit = (Button) findViewById(R.id.button);
        edit.setOnClickListener(new View.OnClickListener() {
            public void onClick(View view) {

                Intent myIntent = new Intent(view.getContext(), MainActivity.class);
                startActivityForResult(myIntent, 0);

            }
        });


        Bundle bd = getIntent().getExtras();
        String email = bd.getString("email");
        String des = bd.getString("description");
        String cat = bd.getString("category");
        FeedbackObject feed = new FeedbackObject(email, des,cat);
        //Bitmap bitmap = (Bitmap) getIntent().getParcelableExtra("BitmapImage");

        //convert bitmap to byte array
        String picturePath = Environment.getExternalStorageDirectory().toString()+"/Feedback/Images/test.jpg";
        Bitmap bmp = BitmapFactory.decodeFile(picturePath);
        ByteArrayOutputStream bos = new ByteArrayOutputStream();
        bmp.compress(Bitmap.CompressFormat.PNG, 100, bos);
        byte[] bArray = bos.toByteArray();

        /*Log.d("Insert: ", "Inserting ..");
        db.addFeedbackObject(new FeedbackObject(email, des,cat,bArray));
       // db.addFeedbackObject(new FeedbackObject(2,email, des,cat,bArray));

        // Reading all FeedbackObjects
        Log.d("Reading: ", "Reading all FeedbackObjects..");
        List<FeedbackObject> FeedbackObjects = db.getAllFeedbacks();*/
        //postData(feed);
        //requestData("");
        new GetFundingOptionsTask().execute();

       /* for (FeedbackObject cn : FeedbackObjects) {
           String log = "ID: " +" ,Email: " + cn.getUsername() + " ,Description: " + cn.getDescription() + " ,Category: " + cn.getCategory()+" ,Contact: " + cn.getContact()+" ,star: " + cn.getStar();
            //Writing FeedbackObjects to log
            Log.d("Name: ", log);
        }*/
    }

    private class GetFundingOptionsTask extends AsyncTask<Void,Void,String> {

        @Override
        public String doInBackground(Void... params) {
            JSONObject response = Connect.fundingOptionsRequest();
            if (response != null) {
                return response.toString();
            }
            return null;
        }

        @Override
        public void onPostExecute(String result) {
            //resp.setText(result.toString(), TextView.BufferType.EDITABLE);
            Log.d("Final:","ksjdhfksjh");



        }
    }

   /* public void postData(final FeedbackObject feed)
    {
        /*RestAdapter adapter = new RestAdapter.Builder()
                .setEndpoint(ENDPOINT)
                .build();
        Log.d("Entered:","22222");
        ResponseAPI api = adapter.create(ResponseAPI.class);
        api.addFeed(feed, new Callback<JSONObject>() {

            public void success(JSONObject jsonObject, Response response) {
                Gson gson = new Gson();
                String x= gson.toJson(feed);
                Log.d("JSON:",x);

                //getting http connection to service
                HttpClient httpclient = new DefaultHttpClient();
                HttpPost httppost = new HttpPost("https://www.stage2std095.qa.paypal.com:12450/v1/payments/personal-payments/funding-options");

                try {
                    httppost.setEntity(new StringEntity(PAYPAL_BODY));
                    httppost.addHeader("Content-Type", "application/json");
                    httppost.addHeader("X_PAYPAL_SECURITY_CONTEXT", "{\"scopes\":[\"*\"],\"subjects\":[{\"subject\":{\"id\":\"0\",\"auth_state\":\"LOGGEDIN\",\"account_number\":\"1453948006982829156\",\"auth_claims\":[\"USERNAME\",\"PASSWORD\"]}}],\"actor\":{\"id\":\"0\",\"account_number\":\"1453948006982829156\",\"auth_claims\":[\"USERNAME\",\"PASSWORD\"]}, \"global_session_id\": \"3ilsi34jsi32300Zsdkk23sdlfjlkjsd\"}");
                    //httppost.addHeader("PAYPAL_BODY", "{\"amount\":{\"value\":\"5\",\"currency\":\"USD\" }, \"payee\":{ \"id\":\"cdayanand-pre@paypal.com\", \"type\":\"EMAIL\" }, \"fee\":{ \"payer\":\"PAYER\"},\"payment_type\":\"PERSONAL\"}");

                    //httppost.addHeader("Cache-Control", "no-cache");
                    //BasicNameValuePair d = new BasicNameValuePair("pushDeviceTokenUri","This is second demo from curl");
                    //List<NameValuePair> nameValuePairs = new ArrayList<NameValuePair>(2);
                    //nameValuePairs.add(new BasicNameValuePair("pushDeviceTokenUri","This is second demo from curl"));
                    //nameValuePairs.add(new BasicNameValuePair("password", "pass"));

                   // httppost.setEntity(new UrlEncodedFormEntity(nameValuePairs));
                    Log.d("Check:", "sjkfdhksjdh11");
                    HttpResponse res = httpclient.execute(httppost);
                    Log.d("Check:", "sjkfdhksjdh");
                    Log.d("RESPOND", EntityUtils.toString(res.getEntity()));

                } catch (ClientProtocolException e) {
                    // TODO Auto-generated catch block
                } catch (IOException e) {
                    // TODO Auto-generated catch block
                }
            }

            @Override
            public void failure(RetrofitError retrofitError) {

            }
        });
    }
*/


    public  void requestData(String uri)
    {
        RestAdapter adapter = new RestAdapter.Builder()
                .setEndpoint(ENDPOINT)
                .build();
        Log.d("Entered:","kjsdhfkjs");
        ResponseAPI api = adapter.create(ResponseAPI.class);
        api.getFeed(new Callback<FeedbackApiResponse>() {
            @Override
            public void success(FeedbackApiResponse feedbackApiResponse, Response response) {
                List<FeedbackObject> feedbackList = feedbackApiResponse.feedback;

                for (FeedbackObject cn : feedbackList) {
                    String log = "ID: " +" ,Email: " + cn.getUsername() + " ,Description: " + cn.getDescription() + " ,Category: " + cn.getCategory();
                    //Writing FeedbackObjects to log
                    Log.d("Name: ", log);
                }
            }

            @Override
            public void failure(RetrofitError retrofitError) {
                retrofitError.printStackTrace();
            }
        });
        /*FeedbackAPI api = adapter.create(FeedbackAPI.class);
        api.getFeed(new Callback<List<FeedbackObject>>() {

            public void success(FeedbackApiResponse res, Response response) {

                List<FeedbackObject> feedbackList = res.results;

                for (FeedbackObject cn : feedbackList) {
                    String log = "ID: " +" ,Email: " + cn.getUsername() + " ,Description: " + cn.getDescription() + " ,Category: " + cn.getCategory();
                    //Writing FeedbackObjects to log
                    Log.d("Name: ", log);
                }

            }

            @Override
            public void success(List<FeedbackObject> feedbackObjects, Response response) {

            }

            @Override
            public void failure(RetrofitErroretrofitError) {
                   retrofitError.printStackTrace();
            }
        });*/

    }
    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.main, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        int id = item.getItemId();
        if (id == R.id.action_settings) {
            return true;
        }
        return super.onOptionsItemSelected(item);
    }
}


