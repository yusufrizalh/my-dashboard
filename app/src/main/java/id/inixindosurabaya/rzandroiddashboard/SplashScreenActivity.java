package id.inixindosurabaya.rzandroiddashboard;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.graphics.PixelFormat;
import android.os.Bundle;
import android.view.Window;
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;
import android.widget.ImageView;
import android.widget.LinearLayout;

public class SplashScreenActivity extends AppCompatActivity {

    public void onAttachedToWindow() {
        super.onAttachedToWindow();
        Window myWindow = getWindow();
        myWindow.setFormat(PixelFormat.RGBA_8888);
    }

    Thread splashThread;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_splash_screen);

        startAnimations();  // nama method saja (procedure)
    }

    private void startAnimations() {    // perintah membuat method (function)
        Animation anim = AnimationUtils.loadAnimation(
                this, R.anim.alpha);
        anim.reset();
        LinearLayout linearLayout = (LinearLayout)findViewById(R.id.linear1);
        linearLayout.clearAnimation();
        linearLayout.startAnimation(anim);

        anim = AnimationUtils.loadAnimation(
                this, R.anim.translate);
        anim.reset();
        ImageView imageView = (ImageView)findViewById(R.id.splash1);
        imageView.clearAnimation();
        imageView.startAnimation(anim);

        splashThread = new Thread() {
              public void run() {
                  try {
                      int tunggu = 0;
                      while (tunggu < 5000) {
                          sleep(2000);
                          tunggu += 2000;
                      }
                      Intent myIntent = new Intent(
                              SplashScreenActivity.this,
                              MainActivity.class
                      );
                      myIntent.setFlags(Intent.FLAG_ACTIVITY_NO_ANIMATION);
                      startActivity(myIntent);
                      SplashScreenActivity.this.finish();
                  } catch (InterruptedException e) {
                      e.printStackTrace();
                  } finally {
                      SplashScreenActivity.this.finish();
                  }
              }
        };
    splashThread.start();
    }
}
