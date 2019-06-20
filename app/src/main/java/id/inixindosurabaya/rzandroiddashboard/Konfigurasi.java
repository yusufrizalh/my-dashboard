package id.inixindosurabaya.rzandroiddashboard;

public class Konfigurasi {
    // lokasi link dimana web service berada
    public static final String URL_ADD = "http://yusufrizalh1.000webhostapp.com/pegawai/tambahPgw.php";
    public static final String URL_GET_ALL = "http://yusufrizalh1.000webhostapp.com/pegawai/tampilSemuaPgw.php";
    public static final String URL_GET_PGW = "http://yusufrizalh1.000webhostapp.com/pegawai/tampilPgw.php?id=";
    public static final String URL_UPDATE_PGW = "http://yusufrizalh1.000webhostapp.com/pegawai/updatePgw.php";
    public static final String URL_DELETE_PGW = "http://yusufrizalh1.000webhostapp.com/pegawai/hapusPgw.php?id=";

    // key untuk kirim request ke PHP
    public static final String KEY_PGW_ID = "id";
    public static final String KEY_PGW_NAMA = "name";
    public static final String KEY_PGW_JABATAN = "desg";
    public static final String KEY_PGW_GAJI = "salary";

    // tag untuk JSON
    public static final String TAG_JSON_ARRAY = "result";
    public static final String TAG_ID = "id";
    public static final String TAG_NAMA = "name";
    public static final String TAG_JABATAN = "desg";
    public static final String TAG_GAJI = "salary";

    // variable ID pegawai
    public static final String PGW_ID = "pgw_id";

}
