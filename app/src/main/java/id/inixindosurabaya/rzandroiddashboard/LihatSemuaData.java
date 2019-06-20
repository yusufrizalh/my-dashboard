package id.inixindosurabaya.rzandroiddashboard;

import android.annotation.SuppressLint;
import android.app.ProgressDialog;
import android.os.AsyncTask;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ListView;

import androidx.appcompat.app.AppCompatActivity;

class LihatSemuaData extends AppCompatActivity implements AdapterView.OnItemClickListener {
    // GLOBAL VARIABLES
    private ListView listView;
    private String JSON_STRING;

    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_lihat_semua_data);

        listView = findViewById(R.id.listView);
        // event handling untuk listview
        listView.setOnItemClickListener(this);

        // method untuk ambil semua data JSON
        getJSON();
    }

    private void getJSON() {
        class GetJSON extends AsyncTask<Void, Void, String> {
            ProgressDialog loading;

            // method pendahuluan
            protected void onPreExecute() {
                super.onPreExecute();
                loading = ProgressDialog.show(
                        LihatSemuaData.this,
                        "Mengambil Data",
                        "Harap tunggu...",
                        false, false
                );
            }

            // saat proses
            @Override
            protected String doInBackground(Void... voids) {
                RequestHttpHandler request = new RequestHttpHandler();
                String s = request.sendGetRequest(Konfigurasi.URL_GET_ALL);
                return s;
            }

            // method akhiran
            protected void onPostExecute(String s) {
                super.onPostExecute(s);
                loading.dismiss();
                JSON_STRING = s;
                tampilkanSemuaDataPegawai();
            }
        }
        GetJSON getJSON = new GetJSON();
        getJSON.execute();
    }

    private void tampilkanSemuaDataPegawai() {
        
    }

    @Override
    public void onItemClick(AdapterView<?> adapterView, View view, int i, long l) {

    }
}
