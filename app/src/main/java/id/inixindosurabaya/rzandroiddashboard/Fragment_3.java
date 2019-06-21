package id.inixindosurabaya.rzandroiddashboard;

import android.content.Context;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import androidx.fragment.app.Fragment;

class Fragment_3 extends Fragment {
    // GLOBAL VARIABLES
    private EditText editId, editNama;
    private Button btnSimpanPref;
    SharedPreferences preferences;
    public static final String keyid = "keyId";
    public static final String keynama = "keyNama";
    public static final String MyPref = "MyPref";

    public View onCreateView(LayoutInflater inflater,
                             ViewGroup container,
                             Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_3_layout, null);
        editId = view.findViewById(R.id.editId);
        editNama = view.findViewById(R.id.editNama);
        btnSimpanPref = view.findViewById(R.id.btnSimpanPreference);
        preferences = getContext().getSharedPreferences(MyPref, Context.MODE_PRIVATE);

        btnSimpanPref.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                // membaca edittext
                String id = editId.getText().toString();
                String nama = editNama.getText().toString();
                SharedPreferences.Editor editor =
                        preferences.edit();

                editor.putString(keyid, id);
                editor.putString(keynama, nama);
                editor.commit();
                Toast.makeText(view.getContext(),
                        "Menyimpan dalam Shared Preferences",
                        Toast.LENGTH_SHORT).show();
            }
        });

        return view;
    }
}
