package id.inixindosurabaya.rzandroiddashboard;

import android.annotation.SuppressLint;
import android.app.ProgressDialog;
import android.content.Intent;
import android.os.AsyncTask;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ListAdapter;
import android.widget.ListView;
import android.widget.SimpleAdapter;

import androidx.appcompat.app.AppCompatActivity;

import org.json.JSONArray;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.HashMap;

public class LihatSemuaData extends AppCompatActivity implements AdapterView.OnItemClickListener {
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
        // menampilkan JSON dari web server ke aplikasi android
        // membuat JSON Object
        JSONObject jsonObject = null;
        ArrayList<HashMap<String, String>> list = new ArrayList<>();

        try {
            jsonObject = new JSONObject(JSON_STRING);
            JSONArray result = jsonObject
                    .getJSONArray(Konfigurasi.TAG_JSON_ARRAY);
            // tampilkan nilai JSON
            for (int i = 0; i < result.length(); i++) {
                JSONObject jo = result.getJSONObject(i);
                String id = jo.getString(Konfigurasi.TAG_ID);
                String name = jo.getString(Konfigurasi.TAG_NAMA);

                HashMap<String, String> pegawai = new HashMap<>();
                pegawai.put(Konfigurasi.TAG_ID, id);
                pegawai.put(Konfigurasi.TAG_NAMA, name);
                list.add(pegawai);
            }
        } catch (Exception ex) {
            ex.printStackTrace();
        }

        // buat adapter untuk meletakkan data json array
        // kedalam list view
        ListAdapter adapter = new SimpleAdapter(
                getApplicationContext(), list,
                R.layout.list_item,
                new String[]{Konfigurasi.TAG_ID, Konfigurasi.TAG_NAMA},
                new int[]{R.id.id, R.id.name}
        );
        listView.setAdapter(adapter);

    }

    @Override
    public void onItemClick(AdapterView<?> parent,
                            View view, int position, long id) {
        // jika salah satu item diklik maka muncul detail
        Intent myIntent = new Intent(LihatSemuaData.this,
                LihatDetailData.class);
        HashMap<String, String> map =
                (HashMap)parent.getItemAtPosition(position);
        String pgwId = map.get(Konfigurasi.TAG_ID).toString();
        myIntent.putExtra(Konfigurasi.PGW_ID, pgwId);
        startActivity(myIntent);
    }
}
