package com.example.rajesh.navdrawer;

import android.content.Context;
import android.net.Uri;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;


public class TicTacToe extends Fragment {

     Button b1;
    public static TicTacToe newInstance(String param1, String param2) {
        TicTacToe fragment = new TicTacToe();

        return fragment;
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment

        View root=inflater.inflate(R.layout.fragment_tic_tac_toe, container, false);

        b1=(Button)root.findViewById(R.id.button);

        return root;
    }


}
