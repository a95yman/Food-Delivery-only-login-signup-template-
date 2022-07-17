package com.example.fooddelivery;

import androidx.appcompat.app.AppCompatActivity;

import android.graphics.Point;
import android.media.MediaPlayer;
import android.net.Uri;
import android.os.Bundle;
import android.os.Handler;
import android.view.View;
import android.widget.CompoundButton;
import android.widget.EditText;
import android.widget.RadioButton;
import android.widget.Toast;
import android.widget.VideoView;

public class Splashscreen extends AppCompatActivity {
    VideoView videoView;
    Point p = new Point();
    int c = 0;
    RadioButton seller, buyer;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_splashscreen);
        getWindowManager().getDefaultDisplay().getSize(p);
        findViewById(R.id.loginPage).animate().translationX(p.x);
        findViewById(R.id.signupPage).animate().translationY(p.y);
        seller = findViewById(R.id.seller);
        buyer = findViewById(R.id.buyer);
        new Handler().postDelayed(new Runnable() {
            @Override
            public void run() {
                findViewById(R.id.loginPage).setVisibility(View.VISIBLE);
                findViewById(R.id.signupPage).setVisibility(View.VISIBLE);
                findViewById(R.id.login1).animate().alpha(1).setDuration(400);
                new Handler().postDelayed(new Runnable() {
                    @Override
                    public void run() {
                        findViewById(R.id.signup1).animate().alpha(1).setDuration(400);
                        new Handler().postDelayed(new Runnable() {
                            @Override
                            public void run() {
                                findViewById(R.id.login1).setOnClickListener(new View.OnClickListener() {
                                    @Override
                                    public void onClick(View view) {
                                        findViewById(R.id.lnr1).animate().translationX(-p.x).setDuration(400);
                                        new Handler().postDelayed(new Runnable() {
                                            @Override
                                            public void run() {
                                                c = 1;
                                                findViewById(R.id.loginPage).animate().translationX(0).setDuration(400);
                                                new Handler().postDelayed(new Runnable() {
                                                    @Override
                                                    public void run() {
                                                        findViewById(R.id.cover).animate().alpha(1).setDuration(0);
                                                    }
                                                }, 400);
                                            }
                                        }, 400);
                                    }
                                });
                                findViewById(R.id.signup1).setOnClickListener(new View.OnClickListener() {
                                    @Override
                                    public void onClick(View view) {
                                        new Handler().postDelayed(new Runnable() {
                                            @Override
                                            public void run() {
                                                findViewById(R.id.signupPage).animate().translationY(0).setDuration(400);
                                                new Handler().postDelayed(new Runnable() {
                                                    @Override
                                                    public void run() {
                                                        findViewById(R.id.cover).animate().alpha(1).setDuration(0);
                                                    }
                                                }, 400);
                                            }
                                        }, 400);
                                    }
                                });
                            }
                        }, 400);
                    }
                }, 400);
            }
        }, 1000);
        videoView = findViewById(R.id.videoview1);
        String path = "android.resource://" + getPackageName() + "/" + R.raw.vid1;
        videoView.setVideoURI(Uri.parse(path));
        videoView.setOnCompletionListener(new MediaPlayer.OnCompletionListener() {
            @Override
            public void onCompletion(MediaPlayer mediaPlayer) {
                videoView.start();
            }
        });
        videoView.start();
        findViewById(R.id.signup3).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                findViewById(R.id.loginPage).animate().translationY(-p.y).setDuration(400);
                findViewById(R.id.signupPage).animate().translationY(0).setDuration(400);
            }
        });
        findViewById(R.id.login3).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if(c==1) {
                    findViewById(R.id.loginPage).animate().translationY(0).setDuration(400);
                    findViewById(R.id.signupPage).animate().translationY(p.y).setDuration(400);
                }
                else{
                    c=1;
                    findViewById(R.id.loginPage).animate().translationX(0).setDuration(0);
                    findViewById(R.id.loginPage).animate().translationY(-p.y).setDuration(0);
                    findViewById(R.id.loginPage).animate().translationY(0).setDuration(400);
                    findViewById(R.id.signupPage).animate().translationY(p.y).setDuration(400);
                }
            }
        });
        seller.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(CompoundButton compoundButton, boolean b) {
                buyer.setChecked(!b);
            }
        });
        buyer.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(CompoundButton compoundButton, boolean b) {
                seller.setChecked(!b);
            }
        });
        findViewById(R.id.signup2).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                String email = ((EditText)findViewById(R.id.emailOrUsernameSignup)).getText().toString();
                String pass1 = ((EditText)findViewById(R.id.password1Signup)).getText().toString();
                String pass2 = ((EditText)findViewById(R.id.password2Signup)).getText().toString();
                if(!email.isEmpty()){
                    if(isEmailValid(email)){
                        Toast.makeText(Splashscreen.this, "good", Toast.LENGTH_SHORT).show();
                    }
                    else{
                        Toast.makeText(Splashscreen.this, "You need to enter a valid email address!", Toast.LENGTH_SHORT).show();
                    }
                }
                else{
                    Toast.makeText(Splashscreen.this, "You need to enter an email address!", Toast.LENGTH_SHORT).show();
                }
            }
        });
    }

    @Override
    protected void onResume() {
        super.onResume();
        if(videoView != null && !videoView.isPlaying())
            videoView.start();
    }

    boolean isEmailValid(String email) {
        return android.util.Patterns.EMAIL_ADDRESS.matcher(email).matches();
    }
}