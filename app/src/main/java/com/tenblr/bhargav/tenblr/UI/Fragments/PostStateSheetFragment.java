package com.tenblr.bhargav.tenblr.UI.Fragments;

import android.app.Activity;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.design.widget.BottomSheetDialogFragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.RadioGroup;

import com.tenblr.bhargav.tenblr.Communicators.PostStateCommunicator;
import com.tenblr.bhargav.tenblr.R;

/**
 * Created by bhargav on 16/11/16.
 */

public class PostStateSheetFragment extends BottomSheetDialogFragment {

    View rootView;
    RadioGroup stateGroup;
    Button btnCont;

    String state = "published";

    PostStateCommunicator comm;

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        rootView = inflater.inflate(R.layout.fragment_post_state,container,false);
        initViews();
        bindViews();
        return rootView;
    }

    @Override
    public void onAttach(Activity activity) {
        super.onAttach(activity);
        comm = (PostStateCommunicator) activity;
    }

    private void initViews() {
        stateGroup = (RadioGroup) rootView.findViewById(R.id.state_radio_roup);
        btnCont = (Button) rootView.findViewById(R.id.btn_state_cont);
    }

    private void bindViews() {
        stateGroup.setOnCheckedChangeListener(new RadioGroup.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(RadioGroup radioGroup, int i) {
                if(i == R.id.radio_queue) {
                    state = "queue";
                } else if(i == R.id.radio_draft) {
                    state = "draft";
                } else {
                    state = "published";
                }
                comm.postState(state);
            }
        });

        btnCont.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                comm.postState(state);
                dismiss();
            }
        });

    }
}
