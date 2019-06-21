package id.inixindosurabaya.rzandroiddashboard;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.fragment.app.Fragment;

public class Tab1 extends Fragment {
    public View onCreateView(LayoutInflater inflater,
                             ViewGroup container,
                             Bundle savedInstancestate){
        View view = inflater.inflate(R.layout.tab1_layout,
                container, false);
        return view;
    }
}
