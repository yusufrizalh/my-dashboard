package id.inixindosurabaya.rzandroiddashboard;

import android.content.Intent;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;

import androidx.fragment.app.Fragment;

class Fragment_1 extends Fragment implements View.OnClickListener {

    // GLOBAL VARIABLES
    private EditText editNama, editJabatan, editGaji;
    private Button btnSimpan, btnLihatData;

    public View onCreateView(LayoutInflater inflater,
                             ViewGroup container,
                             Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_1_layout, null);
        //return inflater.inflate(R.layout.fragment_1_layout, null);

        // mengenali semua komponen
        editNama = view.findViewById(R.id.editNama);
        editJabatan = view.findViewById(R.id.editJabatan);
        editGaji = view.findViewById(R.id.editGaji);
        btnSimpan = view.findViewById(R.id.btnSimpan);
        btnLihatData = view.findViewById(R.id.btnLihatData);

        // event handling untuk tiap button
        btnLihatData.setOnClickListener(this);
        btnSimpan.setOnClickListener(this);

        return view;
    }

    @Override
    public void onClick(View view) {
        // memilih button yg diklik
        if (view == btnLihatData) {
            startActivity(new Intent(view.getContext(), LihatSemuaData.class));
        }
        else if (view == btnSimpan) {
            simpanDataPegawai();
        }
    }

    private void simpanDataPegawai() {

    }
}
