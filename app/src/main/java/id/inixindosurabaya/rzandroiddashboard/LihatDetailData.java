package id.inixindosurabaya.rzandroiddashboard;

import androidx.appcompat.app.AppCompatActivity;

import android.app.AlertDialog;
import android.app.ProgressDialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.os.AsyncTask;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.HashMap;

public class LihatDetailData extends AppCompatActivity implements View.OnClickListener {
    private EditText editId, editNama, editJabatan, editGaji;
    private Button btnUbah, btnHapus;
    private String id;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_lihat_detail_data);

        editId = findViewById(R.id.editIdPegawai);
        editNama = findViewById(R.id.editNamaPegawai);
        editJabatan = findViewById(R.id.editJabatanPegawai);
        editGaji = findViewById(R.id.editGajiPegawai);
        btnUbah = findViewById(R.id.btnUbahData);
        btnHapus = findViewById(R.id.btnHapusData);

        // ambil dari Intent class LihatSemuaData
        Intent myIntent = getIntent();
        id = myIntent.getStringExtra(Konfigurasi.PGW_ID);
        editId.setText(id);

        // event handling untuk button
        btnUbah.setOnClickListener(this);
        btnHapus.setOnClickListener(this);

        getJSON();

    }

    private void getJSON() {
        class GetJSON extends AsyncTask<Void, Void, String> {
            ProgressDialog loading;

            protected void onPreExecute() {
                super.onPreExecute();
                loading = ProgressDialog.show(LihatDetailData.this,
                        "Mengambil Data",
                        "Harap tunggu...",
                        false, false);
            }

            @Override
            protected String doInBackground(Void... voids) {
                RequestHttpHandler request = new RequestHttpHandler();
                String s = request.sendGetRequest(Konfigurasi.URL_GET_PGW, id);
                return s;
            }

            protected void onPostExecute(String s) {
                super.onPostExecute(s);
                loading.dismiss();
                tampilkanDetailDataPegawai(s);
            }
        }
        GetJSON getJSON = new GetJSON();
        getJSON.execute();
    }

    private void tampilkanDetailDataPegawai(String json) {
        try {
            JSONObject jsonObject = new JSONObject(json);
            JSONArray result = jsonObject
                    .getJSONArray(Konfigurasi.TAG_JSON_ARRAY);
            JSONObject x = result.getJSONObject(0);
            String name = x.getString(Konfigurasi.TAG_NAMA);
            String desg = x.getString(Konfigurasi.TAG_JABATAN);
            String sal = x.getString(Konfigurasi.TAG_GAJI);

            editNama.setText(name);
            editJabatan.setText(desg);
            editGaji.setText(sal);

        } catch (JSONException ex) {
            ex.printStackTrace();
        }
    }

    @Override
    public void onClick(View view) {
        if (view == btnUbah) {
            ubahDataPegawai();
        } else if (view == btnHapus) {
            hapusDataPegawai();
        }
    }

    private void hapusDataPegawai() {
        AlertDialog.Builder builder = new AlertDialog.Builder(this);
        builder.setTitle("Menghapus Data");
        builder.setMessage("Apakah yakin menghapus data?");
        builder.setPositiveButton(
                "YES", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int pilih) {
                        konfirmasiHapusData();
                        startActivity(new Intent(LihatDetailData.this,
                                LihatSemuaData.class));
                    }
                }
        );
        builder.setNegativeButton(
                "CANCEL", null);

        AlertDialog dialog = builder.create();
        dialog.show();
    }

    private void konfirmasiHapusData() {
        class KonfirmasiHapusData extends AsyncTask<Void, Void, String> {
            ProgressDialog loading;

            @Override
            protected void onPreExecute() {
                super.onPreExecute();
                loading = ProgressDialog.show(LihatDetailData.this,
                        "Menghapus Data",
                        "Harap tunggu...",
                        false, false);
            }

            @Override
            protected void onPostExecute(String s) {
                super.onPostExecute(s);
                loading.dismiss();
                Toast.makeText(LihatDetailData.this,
                        "message: " + s,
                        Toast.LENGTH_LONG).show();
            }

            @Override
            protected String doInBackground(Void... voids) {
                RequestHttpHandler request = new RequestHttpHandler();
                String s = request.sendGetRequest(Konfigurasi.URL_DELETE_PGW, id);
                return s;
            }
        }
        KonfirmasiHapusData konfirmasi = new KonfirmasiHapusData();
        konfirmasi.execute();
    }

    private void ubahDataPegawai() {
        // variable yg boleh diubah
        final String nama = editNama.getText().toString().trim();
        final String jabatan = editJabatan.getText().toString().trim();
        final String gaji = editGaji.getText().toString().trim();

        class UbahDataPegawai extends AsyncTask<Void, Void, String> {
            ProgressDialog loading;

            @Override
            protected void onPreExecute() {
                super.onPreExecute();
                loading = ProgressDialog.show(
                        LihatDetailData.this,
                        "Mengubah Data",
                        "Harap tunggu...",
                        false, false
                );
            }

            @Override
            protected void onPostExecute(String s) {
                super.onPostExecute(s);
                loading.dismiss();
                Toast.makeText(LihatDetailData.this,
                        "message: " + s,
                        Toast.LENGTH_LONG).show();
            }

            @Override
            protected String doInBackground(Void... voids) {
                HashMap<String, String> hashMap = new HashMap<>();
                hashMap.put(Konfigurasi.KEY_PGW_ID, id);
                hashMap.put(Konfigurasi.KEY_PGW_NAMA, nama);
                hashMap.put(Konfigurasi.KEY_PGW_JABATAN, jabatan);
                hashMap.put(Konfigurasi.KEY_PGW_GAJI, gaji);

                RequestHttpHandler request = new RequestHttpHandler();
                String s = request.sendPostRequest(
                        Konfigurasi.URL_UPDATE_PGW, hashMap);
                return s;
            }
        }
        UbahDataPegawai ubah = new UbahDataPegawai();
        ubah.execute();
    }
}
