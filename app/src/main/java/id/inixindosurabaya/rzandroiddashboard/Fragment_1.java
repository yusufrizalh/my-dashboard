package id.inixindosurabaya.rzandroiddashboard;

import android.app.ProgressDialog;
import android.content.Intent;
import android.os.AsyncTask;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import androidx.fragment.app.Fragment;

import java.util.HashMap;

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
            startActivity(new Intent(view.getContext(),
                    LihatSemuaData.class));
        }
        else if (view == btnSimpan) {
            simpanDataPegawai();
        }
    }

    private void simpanDataPegawai() {
        // variable yg akan disimpan
        final String nama = editNama.getText().toString().trim();
        final String jabatan = editJabatan.getText().toString().trim();
        final String gaji = editGaji.getText().toString().trim();

        class SimpanDataPegawai extends AsyncTask<Void, Void, String> {
            ProgressDialog loading;

            @Override
            protected void onPreExecute() {
                super.onPreExecute();
                loading = ProgressDialog.show(
                        getView().getContext(),
                        "Menyimpan Data",
                        "Harap tunggu...",
                        false, false);
            }

            @Override
            protected void onPostExecute(String s) {
                super.onPostExecute(s);
                loading.dismiss();
                Toast.makeText(getView().getContext(),
                        "message: " + s,
                        Toast.LENGTH_LONG).show();
            }

            @Override
            protected String doInBackground(Void... voids) {
                HashMap<String, String> params = new HashMap<>();
                params.put(Konfigurasi.KEY_PGW_NAMA, nama);
                params.put(Konfigurasi.KEY_PGW_JABATAN, jabatan);
                params.put(Konfigurasi.KEY_PGW_GAJI, gaji);

                RequestHttpHandler request = new RequestHttpHandler();
                String s = request.sendPostRequest(Konfigurasi.URL_ADD, params);
                return s;
            }
        }
        SimpanDataPegawai simpan = new SimpanDataPegawai();
        simpan.execute();
    }
}
