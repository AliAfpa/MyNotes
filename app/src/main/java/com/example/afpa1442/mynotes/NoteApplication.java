package com.example.afpa1442.mynotes;

import android.app.Application;
import android.os.AsyncTask;
import android.util.Log;
import android.widget.Toast;

import com.example.afpa1442.mynotes.classes.Note;
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

    private final static int PARSE_PROJECTS = 0;
    private final static int PARSE_NOTES = 1;

    String result="";

    String resultat="";

    private ArrayList<Project> projects = new ArrayList<>();
    private ArrayList<Note> notes = new ArrayList<>();
    private Project currentProject = null;
    private Note currentNote = null;


    public class Asynchrone extends AsyncTask<String,Integer,String> {

        private String query;
        private int parseType;

        public Asynchrone(String query, int parseType){
            this.query = query;
            this.parseType = parseType;
        }

        @Override
        protected String doInBackground(String... params) {
            HttpURLConnection httpUrlConnection = null;
            try {

                URL url = new URL("http://10.75.25.80/tp09webservices/"+query);
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

                if(parseType == PARSE_PROJECTS)
                    parseProjects(result);

                else if(parseType == PARSE_NOTES)
                    parseNotes(result);

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
            if(!projects.isEmpty()) currentProject = projects.get(0);
            Toast.makeText(getBaseContext(), result, Toast.LENGTH_LONG).show();
        }
    }

    private void parseProjects(String jString) throws Exception {
        JSONObject jsonObj = new JSONObject(jString);
        JSONArray project = jsonObj.getJSONArray("projects");
        Project newProject;
        for (int i = 0; i < project.length(); i++) {
            int attributeId = project.getJSONObject(i).getInt("id");
            String attributeName = project.getJSONObject(i).getString("name");
            newProject = new Project(attributeId, attributeName);
            if(!projects.contains(newProject))
                projects.add(newProject);
        }
    }

    private void parseNotes(String jString) throws Exception {
        JSONObject jsonObj = new JSONObject(jString);
        JSONArray note = jsonObj.getJSONArray("projects");
        Note newNote;
        for (int i = 0; i < note.length(); i++) {
            int attributeId = note.getJSONObject(i).getInt("id");
            String attributeTitle = note.getJSONObject(i).getString("title");
            String attributeText = note.getJSONObject(i).getString("text");
            boolean attributeSolved = note.getJSONObject(i).getBoolean("solved");
            int attributeProjectId = note.getJSONObject(i).getInt("project_id");
            int attributeDevId = note.getJSONObject(i).getInt("dev_id");

            newNote = new Note(attributeId, attributeTitle, attributeText, attributeSolved, attributeProjectId, attributeDevId);
            if(!notes.contains(newNote))
                notes.add(newNote);
        }
    }

    public ArrayList<Project> getAllProjects(){
        resultat="";
        Asynchrone as=new Asynchrone("getProjects.php/", PARSE_PROJECTS);
        as.execute("ok");
        Log.d("projectsApp", projects.toString());
        return projects;
    }

    public ArrayList<Note> getNotes(int id){
        resultat="";
        Asynchrone as=new Asynchrone("getNotes.php/"+id, PARSE_PROJECTS);
        as.execute("ok");
        return notes;
    }

    public Project getCurrentProject(){
        return currentProject;
    }

    public void setCurrentProject(int id){
        currentProject = projects.get(id);
    }

    public Note getCurrentNote(){
        return currentNote;
    }

    public void setCurrentNote(int id){
        currentNote = notes.get(id);
    }

}
