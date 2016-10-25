package com.example.afpa1442.mynotes.fragments;

import android.app.Fragment;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.AdapterView.OnItemClickListener;
import android.widget.ArrayAdapter;
import android.widget.ListView;

import com.example.afpa1442.mynotes.NoteApplication;
import com.example.afpa1442.mynotes.R;
import com.example.afpa1442.mynotes.classes.Note;

public class NotesFragment extends Fragment implements OnItemClickListener{

    private ListView notesList;
//    ArrayList<Note> notes = new ArrayList<>();
    private NoteApplication application;


    public NotesFragment() {
        super();
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View rootView = inflater.inflate(R.layout.top_fragment, container, false);
        application = (NoteApplication) getActivity().getApplication();
        application.buildNotesList(0);
        ArrayAdapter<Note> adapter = new ArrayAdapter<>(getActivity(), R.layout.top_fragment, application.getNotes());
        notesList = (ListView) rootView.findViewById(R.id.notesList);
        notesList.setAdapter(adapter);
        notesList.setOnItemClickListener(this);
        return rootView;
    }


    @Override
    public void onItemClick(AdapterView<?> adapterView, View view, int i, long l) {
        application.setCurrentNote(i);
    }
}
