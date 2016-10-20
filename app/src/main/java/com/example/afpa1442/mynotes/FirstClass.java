package com.example.afpa1442.mynotes;

import android.os.AsyncTask;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Toast;

import org.json.JSONArray;
import org.json.JSONObject;

import java.io.BufferedInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.URL;

/**
 * Created by Ibrahim on 20/10/2016.
 */

public class FirstClass extends AppCompatActivity {
        String result="";

        String resultat="";

        @Override
        protected void onCreate(Bundle savedInstanceState) {
            super.onCreate(savedInstanceState);
            setContentView(R.layout.activity_main);
        }

        public class Asynchrone extends AsyncTask<String,Integer,String> {
            @Override
            protected String doInBackground(String... params) {
                HttpURLConnection httpUrlConnection = null;
                try {

                    URL url = new URL("http://10.75.25.52/tp09webservices/testwebservice.php/5");
                    httpUrlConnection = (HttpURLConnection)
                            url.openConnection();
                    InputStream inStream = new
                            BufferedInputStream(httpUrlConnection.getInputStream());
                    int inChar;
                    final StringBuilder readStr = new StringBuilder();
                    while ((inChar = inStream.read()) != -1) {
                        readStr.append((char) inChar);
                    }
                    result = readStr.toString();
                    parseJsonFile(result);
                    httpUrlConnection.disconnect();
                } catch (MalformedURLException me) {
                    me.printStackTrace();
                } catch (IOException io) {
                    io.printStackTrace();
                } catch (Exception e) {
                    e.printStackTrace();
                }
                return resultat;
            }

            @Override
            protected void onPostExecute(String result) {
                super.onPostExecute(result);
                Toast.makeText(getBaseContext(), result, Toast.LENGTH_LONG).show();
            }
        }

        public void reception(View v){
            resultat="";
            com.example.afpa1442.mynotes.MainActivity.Asynchrone as=new com.example.afpa1442.mynotes.MainActivity.Asynchrone();
            as.execute("ok");
        }

        private void parseJsonFile (String jString) throws Exception {
            JSONObject jsonObj = new JSONObject(jString);

            JSONArray note = jsonObj.getJSONArray("notes");
            for (int i = 0; i < note.length(); i++) {
                String attributeId = note.getJSONObject(i).getString("id");
                resultat+="id: "+attributeId+"\n";
                String attributeName = note.getJSONObject(i).getString("title");
                resultat+="title: "+attributeName+"\n";
                String attributeAuthor = note.getJSONObject(i).getString("text");
                resultat+="text: "+attributeAuthor+"\n";
                String attributeIsbn = note.getJSONObject(i).getString("solved");
                resultat+="solved: "+attributeIsbn+"\n";
            }
        }
    }
}
