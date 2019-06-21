package id.inixindosurabaya.rzandroiddashboard;

import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.os.AsyncTask;
import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;

import androidx.fragment.app.Fragment;

import com.squareup.picasso.Picasso;

import java.io.IOException;
import java.io.InputStream;
import java.net.MalformedURLException;
import java.net.URL;

class Fragment_4 extends Fragment {
    private ImageView img1;

    public View onCreateView(LayoutInflater inflater,
                             ViewGroup container,
                             Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_4_layout, null);

        img1 = view.findViewById(R.id.image_1);

        bukaGambar();

        return view;
    }

    private void bukaGambar() {
        String myURL = "https://static1.squarespace.com/static/59937b8f2994cae8c280ca6c/t/59d274149f8dce872983f1b0/1509032893403/Yonomi+-+Android+App+800x800.png";
        String myURL1 = "https://www.celcom.com.my/sites/default/files/images/image_device/device_card_huaweiP30.png";
        Picasso.get().load(myURL1).into(img1);
    }


}
