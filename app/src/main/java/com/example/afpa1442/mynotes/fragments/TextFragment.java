package com.example.afpa1442.mynotes.fragments;

import android.app.Fragment;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.example.afpa1442.mynotes.NoteApplication;
import com.example.afpa1442.mynotes.R;
import com.example.afpa1442.mynotes.classes.Note;

/**
 * Created by Ibrahim on 25/10/2016.
 */
public class TextFragment extends Fragment {

    Note note;
    private NoteApplication application;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View rootView = inflater.inflate(R.layout.bottom_fragment, container, false);

        application = (NoteApplication) getActivity().getApplication();
        note = new Note();
        TextView tv = (TextView) rootView.findViewById(R.id.noteText);
        tv.setText("TEXTE DE LA REMARQUE");

        return rootView;
    }

}
