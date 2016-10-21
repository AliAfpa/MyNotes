package com.example.afpa1442.mynotes;

import android.app.Application;
import android.os.AsyncTask;
import android.widget.Toast;

import com.example.afpa1442.mynotes.classes.Project;

import org.json.JSONArray;
import org.json.JSONObject;

import java.io.BufferedInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.URL;
import java.util.ArrayList;

/**
 * Created by Ibrahim on 21/10/2016.
 */

public class NoteApplication extends Application {
    String result="";

    String resultat="";

    ArrayList<Project> projects = new ArrayList<>();

    public class Asynchrone extends AsyncTask<String,Integer,String> {
        @Override
        protected String doInBackground(String... params) {
            HttpURLConnection httpUrlConnection = null;
            try {

                URL url = new URL("http://10.75.25.52/tp09webservices/getProject.php/");
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

    public ArrayList<Project> getAllProjects(){
        resultat="";
        Asynchrone as=new Asynchrone();
        as.execute("ok");
        return projects;
    }

    private void parseJsonFile (String jString) throws Exception {
        JSONObject jsonObj = new JSONObject(jString);

        JSONArray project = jsonObj.getJSONArray("project");
        Project newProject;
        for (int i = 0; i < project.length(); i++) {
            String attributeId = project.getJSONObject(i).getString("id");
            String attributeName = project.getJSONObject(i).getString("name");
            newProject = new Project(Integer.valueOf(attributeId), attributeName);
            projects.add(newProject);

        }
    }
}
