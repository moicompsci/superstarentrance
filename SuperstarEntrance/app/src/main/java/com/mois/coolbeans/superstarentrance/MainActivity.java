package com.mois.coolbeans.superstarentrance;

import android.app.AlertDialog;
import android.content.Context;
import android.content.DialogInterface;
import android.media.MediaPlayer;
import android.os.AsyncTask;
import android.os.Environment;
import android.support.v7.app.ActionBarActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.Menu;
import android.view.View;
import android.widget.Button;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.RelativeLayout;


import com.google.android.gms.ads.AdRequest;
import com.google.android.gms.ads.AdView;

import java.io.File;
import java.util.Vector;

/*
Created By Moises Martinez
Special Thanks to:
Sound Editor: Daniel Zarza
Artist: Ezra Robles
 */

public class MainActivity extends ActionBarActivity {

    public static boolean musicNotPlaying;
    MediaPlayer mPlay;
    ImageButton pause_play;
    public static boolean nothingPressed = true;
    public static ImageView imageView;
    public static int superstarID;
    MyTask mtask;
    Button infinateLoopButton;
    Button infoButton;
    Button lightsButton;
    public volatile boolean lightsOff;
    public volatile boolean infinateLoop;
    public static View nextView;



    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        lightsOff = true;
        musicNotPlaying = true;

        /*
        if (savedInstanceState != null) {
            Toast.makeText(this, " Saved instance saved is not null " + Boolean.toString(savedInstanceState.getBoolean("idle")),                                  Toast.LENGTH_LONG).show();
            musicNotPlaying = savedInstanceState.getBoolean("idle");
        } else {

        }
        */

        mPlay = new MediaPlayer();

        nextView = findViewById(R.id.therock_button);
        imageView = (ImageView) findViewById(R.id.imageView);
        pause_play = (ImageButton) findViewById(R.id.pause_play);
        pause_play.setBackgroundResource(R.drawable.playicon3);



        /*
        Infinate Loop Button
         */
        infinateLoopButton = (Button) findViewById(R.id.infinateLoop_button);
        infinateLoopButton.setTextColor(getResources().getColor(R.color.darkgrey));
        infinateLoop = false;

        infinateLoopButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                if (infinateLoop==true) {
                    infinateLoop = false;
                    infinateLoopButton.setTextColor(getResources().getColor(R.color.darkgrey));
                } else if (infinateLoop==false) {
                    infinateLoop = true;
                    infinateLoopButton.setTextColor(getResources().getColor(R.color.primary_color));
                }
            }
        });

        /*
        Info Button
         */
        infoButton = (Button) findViewById(R.id.info_button);
        infoButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                new AlertDialog.Builder(MainActivity.this)
                        .setTitle("Surprise your friends with an entrance")
                        .setMessage("Made by a WWE fan. I do not own any of these sounds or photos, I am just a college student paying tuition. " +
                                " Please support the WWE by downloading the WWE APP")
                        .setPositiveButton("OK", new DialogInterface.OnClickListener() {
                            public void onClick(DialogInterface dialog, int id) {
                                dialog.cancel();
                            }
                        }).show();
            }
        });

        /*
        Light Switch
         */
        lightsButton = (Button) findViewById(R.id.lights_button);
        lightsOff = true; //lights or off in the opening
        lightsButton.setText("lights");
        lightsButton.setTextColor(getResources().getColor(R.color.lights_off));

        lightsButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                if (lightsOff) {


                    new AlertDialog.Builder(MainActivity.this).setTitle("Photosensitive Seizure Warning").setMessage("Some people (even with no history of seizures) may experience a seizure when exposed to certain images, including flashing lights or patterns that may appear in this app. These seizures may have a variety of symptoms, including lightheadedness, altered vision, eye or face twitching, jerking or shaking of arms or legs, disorientation, confusion, or momentary loss of awareness. Seizures may also cause loss of consciousness or convulsions that can lead to injury from falling down or striking nearby objects. Immediately stop using this app and consult a doctor if you experience any of these symptoms. By clicking 'agree' you read and understand that the developer of this app is not responsible for any of these conditions if they occur.")
                            .setPositiveButton("Go Back", new DialogInterface.OnClickListener() {
                                public void onClick(DialogInterface dialog, int id) {
                                    dialog.cancel();
                                }
                            }).setNegativeButton("I Agree", new DialogInterface.OnClickListener() {
                        public void onClick(DialogInterface dialog, int id) {
                            lightsOff = false; //turn lights on


                            mtask = new MyTask();
                            mtask.execute();



                            lightsButton.setTextColor(getResources().getColor(R.color.lights_on));
                            dialog.cancel();
                        }
                    }).show();


                } else if (!lightsOff) {
                    lightsOff = true; //turn lights off

                    lightsButton.setTextColor(getResources().getColor(R.color.lights_off));

                }
            }
        });


        AdView mAdView = (AdView) findViewById(R.id.adView);
        AdRequest adRequest = new AdRequest.Builder().build();
        mAdView.loadAd(adRequest);


    }

    @Override
    protected void onSaveInstanceState(Bundle outState) {
        super.onSaveInstanceState(outState);

    }

    @Override
    protected void onRestoreInstanceState(Bundle savedInstanceState) {
        super.onRestoreInstanceState(savedInstanceState);
        lightsOff = true;
        // musicNotPlaying = savedInstanceState.getBoolean("idle");
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.

        getMenuInflater().inflate(R.menu.main, menu);
        return true;
    }

    public void playtripleh(View v) {
        superstarID = 1;
        nothingPressed = false;
        if (!musicNotPlaying) {
            mPlay.stop();
            musicNotPlaying = true;
        }
        musicNotPlaying = false;
        mPlay = MediaPlayer.create(this, R.raw.timetoplaythegame);
        mPlay.start();
        imageView.setBackgroundResource(R.drawable.tripleh1);
        pause_play.setBackgroundResource(R.drawable.pause2);

        mPlay.setOnCompletionListener(new MediaPlayer.OnCompletionListener() {
            @Override
            public void onCompletion(MediaPlayer mp) {
                if (infinateLoop == false) {
                    musicNotPlaying = true;
                    pause_play.setBackgroundResource(R.drawable.playicon3);
                } else {
                    playtherock(nextView);
                }
            }
        });
    }

    public void playtherock(View v) {
        superstarID = 2;
        nothingPressed = false;
        if (!musicNotPlaying) {
            mPlay.stop();
            musicNotPlaying = true;
        }
        mPlay = MediaPlayer.create(this, R.raw.therocksays);
        mPlay.start();
        imageView.setBackgroundResource(R.drawable.therockicon);
        pause_play.setBackgroundResource(R.drawable.pause2);
        musicNotPlaying = false;
        mPlay.setOnCompletionListener(new MediaPlayer.OnCompletionListener() {
            @Override
            public void onCompletion(MediaPlayer mp) {

                if(infinateLoop==false){
                    musicNotPlaying = true;
                    pause_play.setBackgroundResource(R.drawable.playicon3);
                }else{
                    playstonecold(nextView);
                }
            }
        });
    }

    public void playstonecold(View v) {
        superstarID = 3;
        nothingPressed = false;
        if (!musicNotPlaying) {
            mPlay.stop();
            musicNotPlaying = true;
        }
        mPlay = MediaPlayer.create(this, R.raw.stonecold);
        mPlay.start();
        imageView.setBackgroundResource(R.drawable.stonecold2);
        pause_play.setBackgroundResource(R.drawable.pause2);
        musicNotPlaying = false;
        mPlay.setOnCompletionListener(new MediaPlayer.OnCompletionListener() {
            @Override
            public void onCompletion(MediaPlayer mp) {
                if(infinateLoop==false){
                    musicNotPlaying = true;
                    pause_play.setBackgroundResource(R.drawable.playicon3);
                }else{
                    playvincemcmahon(nextView);
                }
            }
        });
    }

    public void playvincemcmahon(View v) {
        superstarID = 4;
        nothingPressed = false;
        if (!musicNotPlaying) {
            mPlay.stop();
            musicNotPlaying = true;
        }
        mPlay = MediaPlayer.create(this, R.raw.vincemcmahon);
        mPlay.start();
        imageView.setBackgroundResource(R.drawable.vincemcmahon);
        pause_play.setBackgroundResource(R.drawable.pause2);
        musicNotPlaying = false;
        mPlay.setOnCompletionListener(new MediaPlayer.OnCompletionListener() {
            @Override
            public void onCompletion(MediaPlayer mp) {
                if(infinateLoop==false){
                    musicNotPlaying = true;
                    pause_play.setBackgroundResource(R.drawable.playicon3);
                }else{
                    playrandyorton(nextView);
                }
            }
        });
    }

    public void playrandyorton(View v) {
        superstarID = 5;
        nothingPressed = false;
        if (!musicNotPlaying) {
            mPlay.stop();
            musicNotPlaying = true;
        }
        mPlay = MediaPlayer.create(this, R.raw.randyorton);
        mPlay.start();
        imageView.setBackgroundResource(R.drawable.randyorton);
        pause_play.setBackgroundResource(R.drawable.pause2);
        musicNotPlaying = false;
        mPlay.setOnCompletionListener(new MediaPlayer.OnCompletionListener() {
            @Override
            public void onCompletion(MediaPlayer mp) {
                if(infinateLoop==false){
                    musicNotPlaying = true;
                    pause_play.setBackgroundResource(R.drawable.playicon3);
                }else{
                    playundertaker(nextView);
                }
            }
        });
    }

    public void playundertaker(View v) {
        superstarID = 6;
        nothingPressed = false;
        if (!musicNotPlaying) {
            mPlay.stop();
            musicNotPlaying = true;
        }
        mPlay = MediaPlayer.create(this, R.raw.undertakertheme);
        mPlay.start();
        imageView.setBackgroundResource(R.drawable.undertaker);
        pause_play.setBackgroundResource(R.drawable.pause2);
        musicNotPlaying = false;
        mPlay.setOnCompletionListener(new MediaPlayer.OnCompletionListener() {
            @Override
            public void onCompletion(MediaPlayer mp) {
                if(infinateLoop==false){
                    musicNotPlaying = true;
                    pause_play.setBackgroundResource(R.drawable.playicon3);
                }else{
                    playsethrollins(nextView);
                }
            }
        });
    }

    public void playsethrollins(View v) {
        superstarID = 7;
        nothingPressed = false;
        if (!musicNotPlaying) {
            mPlay.stop();
            musicNotPlaying = true;
        }
        mPlay = MediaPlayer.create(this, R.raw.sethrollins);
        mPlay.start();
        imageView.setBackgroundResource(R.drawable.sethrollins3);
        pause_play.setBackgroundResource(R.drawable.pause2);
        musicNotPlaying = false;
        mPlay.setOnCompletionListener(new MediaPlayer.OnCompletionListener() {
            @Override
            public void onCompletion(MediaPlayer mp) {
                if(infinateLoop==false){
                    musicNotPlaying = true;
                    pause_play.setBackgroundResource(R.drawable.playicon3);
                }else{
                    playjohncena(nextView);
                }
            }
        });
    }

    public void playjohncena(View v) {
        superstarID = 8;
        nothingPressed = false;
        if (!musicNotPlaying) {
            mPlay.stop();
            musicNotPlaying = true;
        }
        mPlay = MediaPlayer.create(this, R.raw.johncena);
        mPlay.start();
        imageView.setBackgroundResource(R.drawable.john_cena_1);
        pause_play.setBackgroundResource(R.drawable.pause2);
        musicNotPlaying = false;
        mPlay.setOnCompletionListener(new MediaPlayer.OnCompletionListener() {
            @Override
            public void onCompletion(MediaPlayer mp) {
                if(infinateLoop==false){
                    musicNotPlaying = true;
                    pause_play.setBackgroundResource(R.drawable.playicon3);
                }else{
                    playromanreigns(nextView);
                }
            }
        });
    }

    public void playromanreigns(View v) {
        superstarID = 9;
        nothingPressed = false;
        if (!musicNotPlaying) {
            mPlay.stop();
            musicNotPlaying = true;
        }
        mPlay = MediaPlayer.create(this, R.raw.romanreigns);
        mPlay.start();
        imageView.setBackgroundResource(R.drawable.romanreigns2);
        pause_play.setBackgroundResource(R.drawable.pause2);
        musicNotPlaying = false;
        mPlay.setOnCompletionListener(new MediaPlayer.OnCompletionListener() {
            @Override
            public void onCompletion(MediaPlayer mp) {
                if(infinateLoop==false){
                    musicNotPlaying = true;
                    pause_play.setBackgroundResource(R.drawable.playicon3);
                }else{
                    playbigshow(nextView);
                }
            }
        });
    }

    public void playbigshow(View v) {
        superstarID = 10;
        nothingPressed = false;
        if (!musicNotPlaying) {
            mPlay.stop();
            musicNotPlaying = true;
        }
        mPlay = MediaPlayer.create(this, R.raw.bigshow);
        mPlay.start();
        imageView.setBackgroundResource(R.drawable.bigshow2);
        pause_play.setBackgroundResource(R.drawable.pause2);
        musicNotPlaying = false;
        mPlay.setOnCompletionListener(new MediaPlayer.OnCompletionListener() {
            @Override
            public void onCompletion(MediaPlayer mp) {
                if(infinateLoop==false){
                    musicNotPlaying = true;
                    pause_play.setBackgroundResource(R.drawable.playicon3);
                }else{
                    playdeanammbrose(nextView);
                }
            }
        });
    }

    public void playdeanammbrose(View v) {
        superstarID = 11;
        nothingPressed = false;
        if (!musicNotPlaying) {
            mPlay.stop();
            musicNotPlaying = true;
        }
        mPlay = MediaPlayer.create(this, R.raw.deanamberrose);
        mPlay.start();
        imageView.setBackgroundResource(R.drawable.dean2);
        pause_play.setBackgroundResource(R.drawable.pause2);
        musicNotPlaying = false;
        mPlay.setOnCompletionListener(new MediaPlayer.OnCompletionListener() {
            @Override
            public void onCompletion(MediaPlayer mp) {
                if(infinateLoop==false){
                    musicNotPlaying = true;
                    pause_play.setBackgroundResource(R.drawable.playicon3);
                }else{
                    playbookert(nextView);
                }
            }
        });
    }



    public void playbookert(View v) {
        superstarID = 14;
        nothingPressed = false;
        if (!musicNotPlaying) {
            mPlay.stop();
            musicNotPlaying = true;
        }
        mPlay = MediaPlayer.create(this, R.raw.bookert);
        mPlay.start();
        imageView.setBackgroundResource(R.drawable.bookert1);
        pause_play.setBackgroundResource(R.drawable.pause2);
        musicNotPlaying = false;
        mPlay.setOnCompletionListener(new MediaPlayer.OnCompletionListener() {
            @Override
            public void onCompletion(MediaPlayer mp) {
                if(infinateLoop==false){
                    musicNotPlaying = true;
                    pause_play.setBackgroundResource(R.drawable.playicon3);
                }else{
                    playbraywyatt(nextView);
                }
            }
        });
    }

    public void playbraywyatt(View v) {
        superstarID = 15;
        nothingPressed = false;
        if (!musicNotPlaying) {
            mPlay.stop();
            musicNotPlaying = true;
        }
        mPlay = MediaPlayer.create(this, R.raw.braywyatt);
        mPlay.start();
        imageView.setBackgroundResource(R.drawable.braywyatt);
        pause_play.setBackgroundResource(R.drawable.pause2);
        musicNotPlaying = false;
        mPlay.setOnCompletionListener(new MediaPlayer.OnCompletionListener() {
            @Override
            public void onCompletion(MediaPlayer mp) {
                if(infinateLoop==false){
                    musicNotPlaying = true;
                    pause_play.setBackgroundResource(R.drawable.playicon3);
                }else{
                    playbrocklesnar(nextView);
                }
            }
        });
    }

    public void playbrocklesnar(View v) {
        superstarID = 16;
        nothingPressed = false;
        if (!musicNotPlaying) {
            mPlay.stop();
            musicNotPlaying = true;
        }
        mPlay = MediaPlayer.create(this, R.raw.brocklesnar);
        mPlay.start();
        imageView.setBackgroundResource(R.drawable.brocklesnar1);
        pause_play.setBackgroundResource(R.drawable.pause2);
        musicNotPlaying = false;
        mPlay.setOnCompletionListener(new MediaPlayer.OnCompletionListener() {
            @Override
            public void onCompletion(MediaPlayer mp) {
                if(infinateLoop==false){
                    musicNotPlaying = true;
                    pause_play.setBackgroundResource(R.drawable.playicon3);
                }else{
                    playchrisjericho(nextView);
                }
            }
        });
    }



    public void playchrisjericho(View v) {
        superstarID = 18;
        nothingPressed = false;
        if (!musicNotPlaying) {
            mPlay.stop();
            musicNotPlaying = true;
        }
        mPlay = MediaPlayer.create(this, R.raw.chrisjericho);
        mPlay.start();
        imageView.setBackgroundResource(R.drawable.chrisjericho1);
        pause_play.setBackgroundResource(R.drawable.pause2);
        musicNotPlaying = false;
        mPlay.setOnCompletionListener(new MediaPlayer.OnCompletionListener() {
            @Override
            public void onCompletion(MediaPlayer mp) {
                if(infinateLoop==false){
                    musicNotPlaying = true;
                    pause_play.setBackgroundResource(R.drawable.playicon3);
                }else{
                    playchristian(nextView);
                }
            }
        });
    }

    public void playchristian(View v) {
        superstarID = 18;
        nothingPressed = false;
        if (!musicNotPlaying) {
            mPlay.stop();
            musicNotPlaying = true;
        }
        mPlay = MediaPlayer.create(this, R.raw.christian);
        mPlay.start();
        imageView.setBackgroundResource(R.drawable.christian1);
        pause_play.setBackgroundResource(R.drawable.pause2);
        musicNotPlaying = false;
        mPlay.setOnCompletionListener(new MediaPlayer.OnCompletionListener() {
            @Override
            public void onCompletion(MediaPlayer mp) {
                if(infinateLoop==false){
                    musicNotPlaying = true;
                    pause_play.setBackgroundResource(R.drawable.playicon3);
                }else{
                    playcmpunk(nextView);
                }
            }
        });
    }

    public void playcmpunk(View v) {
        superstarID = 44; //took hogans spot
        nothingPressed = false;
        if (!musicNotPlaying) {
            mPlay.stop();
            musicNotPlaying = true;
        }
        mPlay = MediaPlayer.create(this, R.raw.cmpunkcultofpersonality);
        mPlay.start();
        imageView.setBackgroundResource(R.drawable.cmpunk);
        pause_play.setBackgroundResource(R.drawable.pause2);
        musicNotPlaying = false;
        mPlay.setOnCompletionListener(new MediaPlayer.OnCompletionListener() {
            @Override
            public void onCompletion(MediaPlayer mp) {
                if(infinateLoop==false){
                    musicNotPlaying = true;
                    pause_play.setBackgroundResource(R.drawable.playicon3);
                }else{
                    playdanielbryan(nextView);
                }
            }
        });
    }

    public void playdanielbryan(View v) {
        superstarID = 19;
        nothingPressed = false;
        if (!musicNotPlaying) {
            mPlay.stop();
            musicNotPlaying = true;
        }
        mPlay = MediaPlayer.create(this, R.raw.danielbryan2);
        mPlay.start();
        imageView.setBackgroundResource(R.drawable.danielbryan1);
        pause_play.setBackgroundResource(R.drawable.pause2);
        musicNotPlaying = false;
        mPlay.setOnCompletionListener(new MediaPlayer.OnCompletionListener() {
            @Override
            public void onCompletion(MediaPlayer mp) {
                if(infinateLoop==false){
                    musicNotPlaying = true;
                    pause_play.setBackgroundResource(R.drawable.playicon3);
                }else{
                    playdolphziggler(nextView);
                }
            }
        });
    }

    public void playdolphziggler(View v) {
        superstarID = 20;
        nothingPressed = false;
        if (!musicNotPlaying) {
            mPlay.stop();
            musicNotPlaying = true;
        }
        mPlay = MediaPlayer.create(this, R.raw.dolfzinger);
        mPlay.start();
        imageView.setBackgroundResource(R.drawable.dolphziggler);
        pause_play.setBackgroundResource(R.drawable.pause2);
        musicNotPlaying = false;
        mPlay.setOnCompletionListener(new MediaPlayer.OnCompletionListener() {
            @Override
            public void onCompletion(MediaPlayer mp) {
                if(infinateLoop==false){
                    musicNotPlaying = true;
                    pause_play.setBackgroundResource(R.drawable.playicon3);
                }else{
                    playfandango(nextView);
                }
            }
        });
    }



    public void playfandango(View v) {
        superstarID = 22;
        nothingPressed = false;
        if (!musicNotPlaying) {
            mPlay.stop();
            musicNotPlaying = true;
        }
        mPlay = MediaPlayer.create(this, R.raw.fadango);
        mPlay.start();
        imageView.setBackgroundResource(R.drawable.fandango);
        pause_play.setBackgroundResource(R.drawable.pause2);
        musicNotPlaying = false;
        mPlay.setOnCompletionListener(new MediaPlayer.OnCompletionListener() {
            @Override
            public void onCompletion(MediaPlayer mp) {
                if(infinateLoop==false){
                    musicNotPlaying = true;
                    pause_play.setBackgroundResource(R.drawable.playicon3);
                }else{
                    playgolddust(nextView);
                }
            }
        });
    }

    public void playgolddust(View v) {
        superstarID = 23;
        nothingPressed = false;
        if (!musicNotPlaying) {
            mPlay.stop();
            musicNotPlaying = true;
        }
        mPlay = MediaPlayer.create(this, R.raw.golddust);
        mPlay.start();
        imageView.setBackgroundResource(R.drawable.golddust1);
        pause_play.setBackgroundResource(R.drawable.pause2);
        musicNotPlaying = false;
        mPlay.setOnCompletionListener(new MediaPlayer.OnCompletionListener() {
            @Override
            public void onCompletion(MediaPlayer mp) {
                if(infinateLoop==false){
                    musicNotPlaying = true;
                    pause_play.setBackgroundResource(R.drawable.playicon3);
                }else{
                    playjackswagger(nextView);
                }
            }
        });
    }

    public void playjackswagger(View v) {
        superstarID = 24;
        nothingPressed = false;
        if (!musicNotPlaying) {
            mPlay.stop();
            musicNotPlaying = true;
        }
        mPlay = MediaPlayer.create(this, R.raw.jackswagger);
        mPlay.start();
        imageView.setBackgroundResource(R.drawable.jackswagger1);
        pause_play.setBackgroundResource(R.drawable.pause2);
        musicNotPlaying = false;
        mPlay.setOnCompletionListener(new MediaPlayer.OnCompletionListener() {
            @Override
            public void onCompletion(MediaPlayer mp) {
                if(infinateLoop==false){
                    musicNotPlaying = true;
                    pause_play.setBackgroundResource(R.drawable.playicon3);
                }else{
                    playkane(nextView);
                }
            }
        });
    }

    public void playkane(View v) {
        superstarID = 25;
        nothingPressed = false;
        if (!musicNotPlaying) {
            mPlay.stop();
            musicNotPlaying = true;
        }
        mPlay = MediaPlayer.create(this, R.raw.kane);
        mPlay.start();
        imageView.setBackgroundResource(R.drawable.kane1);
        pause_play.setBackgroundResource(R.drawable.pause2);
        musicNotPlaying = false;
        mPlay.setOnCompletionListener(new MediaPlayer.OnCompletionListener() {
            @Override
            public void onCompletion(MediaPlayer mp) {
                if(infinateLoop==false){
                    musicNotPlaying = true;
                    pause_play.setBackgroundResource(R.drawable.playicon3);
                }else{
                    playmiz(nextView);
                }
            }
        });
    }





    public void playmiz(View v) {
        superstarID = 29;
        nothingPressed = false;
        if (!musicNotPlaying) {
            mPlay.stop();
            musicNotPlaying = true;
        }
        mPlay = MediaPlayer.create(this, R.raw.themiz);
        mPlay.start();
        imageView.setBackgroundResource(R.drawable.themiz1);
        pause_play.setBackgroundResource(R.drawable.pause2);
        musicNotPlaying = false;
        mPlay.setOnCompletionListener(new MediaPlayer.OnCompletionListener() {
            @Override
            public void onCompletion(MediaPlayer mp) {
                if(infinateLoop==false){
                    musicNotPlaying = true;
                    pause_play.setBackgroundResource(R.drawable.playicon3);
                }else{
                    playneville(nextView);
                }
            }
        });
    }

    public void playneville(View v) {
        superstarID = 30;
        nothingPressed = false;
        if (!musicNotPlaying) {
            mPlay.stop();
            musicNotPlaying = true;
        }
        mPlay = MediaPlayer.create(this, R.raw.adrianneville);
        mPlay.start();
        imageView.setBackgroundResource(R.drawable.neville);
        pause_play.setBackgroundResource(R.drawable.pause2);
        musicNotPlaying = false;
        mPlay.setOnCompletionListener(new MediaPlayer.OnCompletionListener() {
            @Override
            public void onCompletion(MediaPlayer mp) {
                if(infinateLoop==false){
                    musicNotPlaying = true;
                    pause_play.setBackgroundResource(R.drawable.playicon3);
                }else{
                    playrtruth(nextView);
                }
            }
        });
    }

    public void playrtruth(View v) {
        superstarID = 31;
        nothingPressed = false;
        if (!musicNotPlaying) {
            mPlay.stop();
            musicNotPlaying = true;
        }
        mPlay = MediaPlayer.create(this, R.raw.rtruth);
        mPlay.start();
        imageView.setBackgroundResource(R.drawable.rtruth1);
        pause_play.setBackgroundResource(R.drawable.pause2);
        musicNotPlaying = false;
        mPlay.setOnCompletionListener(new MediaPlayer.OnCompletionListener() {
            @Override
            public void onCompletion(MediaPlayer mp) {
                if(infinateLoop==false){
                    musicNotPlaying = true;
                    pause_play.setBackgroundResource(R.drawable.playicon3);
                }else{
                    playrusev(nextView);
                }
            }
        });
    }

    public void playrusev(View v) {
        superstarID = 32;
        nothingPressed = false;
        if (!musicNotPlaying) {
            mPlay.stop();
            musicNotPlaying = true;
        }
        mPlay = MediaPlayer.create(this, R.raw.rusev);
        mPlay.start();
        imageView.setBackgroundResource(R.drawable.rusev1);
        pause_play.setBackgroundResource(R.drawable.pause2);
        musicNotPlaying = false;
        mPlay.setOnCompletionListener(new MediaPlayer.OnCompletionListener() {
            @Override
            public void onCompletion(MediaPlayer mp) {
                if(infinateLoop==false){
                    musicNotPlaying = true;
                    pause_play.setBackgroundResource(R.drawable.playicon3);
                }else{
                    playryback(nextView);
                }
            }
        });
    }

    public void playryback(View v) {
        superstarID = 33;
        nothingPressed = false;
        if (!musicNotPlaying) {
            mPlay.stop();
            musicNotPlaying = true;
        }
        mPlay = MediaPlayer.create(this, R.raw.ryback);
        mPlay.start();
        imageView.setBackgroundResource(R.drawable.ryback1);
        pause_play.setBackgroundResource(R.drawable.pause2);
        musicNotPlaying = false;
        mPlay.setOnCompletionListener(new MediaPlayer.OnCompletionListener() {
            @Override
            public void onCompletion(MediaPlayer mp) {
                if(infinateLoop==false){
                    musicNotPlaying = true;
                    pause_play.setBackgroundResource(R.drawable.playicon3);
                }else{
                    playstardust(nextView);
                }
            }
        });
    }



    public void playstardust(View v) {
        superstarID = 35;
        nothingPressed = false;
        if (!musicNotPlaying) {
            mPlay.stop();
            musicNotPlaying = true;
        }
        mPlay = MediaPlayer.create(this, R.raw.stardust);
        mPlay.start();
        imageView.setBackgroundResource(R.drawable.stardust);
        pause_play.setBackgroundResource(R.drawable.pause2);
        musicNotPlaying = false;
        mPlay.setOnCompletionListener(new MediaPlayer.OnCompletionListener() {
            @Override
            public void onCompletion(MediaPlayer mp) {
                if(infinateLoop==false){
                    musicNotPlaying = true;
                    pause_play.setBackgroundResource(R.drawable.playicon3);
                }else{
                    playbrethart(nextView);
                }
            }
        });
    }



    public void playbrethart(View v) {
        superstarID = 37;
        nothingPressed = false;
        if (!musicNotPlaying) {
            mPlay.stop();
            musicNotPlaying = true;
        }
        mPlay = MediaPlayer.create(this, R.raw.brethart);
        mPlay.start();
        imageView.setBackgroundResource(R.drawable.brethart1);
        pause_play.setBackgroundResource(R.drawable.pause2);
        musicNotPlaying = false;
        mPlay.setOnCompletionListener(new MediaPlayer.OnCompletionListener() {
            @Override
            public void onCompletion(MediaPlayer mp) {
                if(infinateLoop==false){
                    musicNotPlaying = true;
                    pause_play.setBackgroundResource(R.drawable.playicon3);
                }else{
                    playcactusjack(nextView);
                }
            }
        });
    }

    public void playcactusjack(View v) {
        superstarID = 38;
        nothingPressed = false;
        if (!musicNotPlaying) {
            mPlay.stop();
            musicNotPlaying = true;
        }
        mPlay = MediaPlayer.create(this, R.raw.cacktusjack);
        mPlay.start();
        imageView.setBackgroundResource(R.drawable.cactusjack);
        pause_play.setBackgroundResource(R.drawable.pause2);
        musicNotPlaying = false;
        mPlay.setOnCompletionListener(new MediaPlayer.OnCompletionListener() {
            @Override
            public void onCompletion(MediaPlayer mp) {
                if(infinateLoop==false){
                    musicNotPlaying = true;
                    pause_play.setBackgroundResource(R.drawable.playicon3);
                }else{
                    playedusty(nextView);
                }
            }
        });
    }

    public void playedusty(View v) {
        superstarID = 39;
        nothingPressed = false;
        if (!musicNotPlaying) {
            mPlay.stop();
            musicNotPlaying = true;
        }
        mPlay = MediaPlayer.create(this, R.raw.dustyrhoades);
        mPlay.start();
        imageView.setBackgroundResource(R.drawable.dustyrhodes);
        pause_play.setBackgroundResource(R.drawable.pause2);
        musicNotPlaying = false;
        mPlay.setOnCompletionListener(new MediaPlayer.OnCompletionListener() {
            @Override
            public void onCompletion(MediaPlayer mp) {
                if(infinateLoop==false){
                    musicNotPlaying = true;
                    pause_play.setBackgroundResource(R.drawable.playicon3);
                }else{
                    playeddie(nextView);
                }
            }
        });
    }

    public void playeddie(View v) {
        superstarID = 40;
        nothingPressed = false;
        if (!musicNotPlaying) {
            mPlay.stop();
            musicNotPlaying = true;
        }
        mPlay = MediaPlayer.create(this, R.raw.eddiegurrerroliecheatsteal);
        mPlay.start();
        imageView.setBackgroundResource(R.drawable.eddie2);
        pause_play.setBackgroundResource(R.drawable.pause2);
        musicNotPlaying = false;
        mPlay.setOnCompletionListener(new MediaPlayer.OnCompletionListener() {
            @Override
            public void onCompletion(MediaPlayer mp) {
                if(infinateLoop==false){
                    musicNotPlaying = true;
                    pause_play.setBackgroundResource(R.drawable.playicon3);
                }else{
                    playedge(nextView);
                }
            }
        });
    }

    public void playedge(View v) {
        superstarID = 41;
        nothingPressed = false;
        if (!musicNotPlaying) {
            mPlay.stop();
            musicNotPlaying = true;
        }
        mPlay = MediaPlayer.create(this, R.raw.egdeyouthinkyouknowme);
        mPlay.start();
        imageView.setBackgroundResource(R.drawable.edge1);
        pause_play.setBackgroundResource(R.drawable.pause2);
        musicNotPlaying = false;
        mPlay.setOnCompletionListener(new MediaPlayer.OnCompletionListener() {
            @Override
            public void onCompletion(MediaPlayer mp) {
                if(infinateLoop==false){
                    musicNotPlaying = true;
                    pause_play.setBackgroundResource(R.drawable.playicon3);
                }else{
                    playhacksawjim(nextView);
                }
            }
        });
    }



    public void playhacksawjim(View v) {
        superstarID = 43;
        nothingPressed = false;
        if (!musicNotPlaying) {
            mPlay.stop();
            musicNotPlaying = true;
        }
        mPlay = MediaPlayer.create(this, R.raw.hacksawjim1);
        mPlay.start();
        imageView.setBackgroundResource(R.drawable.hacksawjim);
        pause_play.setBackgroundResource(R.drawable.pause2);
        musicNotPlaying = false;
        mPlay.setOnCompletionListener(new MediaPlayer.OnCompletionListener() {
            @Override
            public void onCompletion(MediaPlayer mp) {
                if(infinateLoop==false){
                    musicNotPlaying = true;
                    pause_play.setBackgroundResource(R.drawable.playicon3);
                }else{
                    playjohncena2(nextView);
                }
            }
        });
    }

    public void playjohncena2(View v) {
        superstarID = 86;
        nothingPressed = false;
        if (!musicNotPlaying) {
            mPlay.stop();
            musicNotPlaying = true;
        }
        mPlay = MediaPlayer.create(this, R.raw.johncenawordlife);
        mPlay.start();
        imageView.setBackgroundResource(R.drawable.johncenawordlife);
        pause_play.setBackgroundResource(R.drawable.pause2);
        musicNotPlaying = false;
        mPlay.setOnCompletionListener(new MediaPlayer.OnCompletionListener() {
            @Override
            public void onCompletion(MediaPlayer mp) {
                if(infinateLoop==false){
                    musicNotPlaying = true;
                    pause_play.setBackgroundResource(R.drawable.playicon3);
                }else{
                    playkevinnash(nextView);
                }
            }
        });
    }


    public void playkevinnash(View v) {
        superstarID = 45;
        nothingPressed = false;
        if (!musicNotPlaying) {
            mPlay.stop();
            musicNotPlaying = true;
        }
        mPlay = MediaPlayer.create(this, R.raw.kevinnash);
        mPlay.start();
        imageView.setBackgroundResource(R.drawable.kevinnash);
        pause_play.setBackgroundResource(R.drawable.pause2);
        musicNotPlaying = false;
        mPlay.setOnCompletionListener(new MediaPlayer.OnCompletionListener() {
            @Override
            public void onCompletion(MediaPlayer mp) {
                if(infinateLoop==false){
                    musicNotPlaying = true;
                    pause_play.setBackgroundResource(R.drawable.playicon3);
                }else{
                    playkurtangle(nextView);
                }
            }
        });
    }

    public void playkurtangle(View v) {
        superstarID = 46;
        nothingPressed = false;
        if (!musicNotPlaying) {
            mPlay.stop();
            musicNotPlaying = true;
        }
        mPlay = MediaPlayer.create(this, R.raw.kurtangle);
        mPlay.start();
        imageView.setBackgroundResource(R.drawable.kurtangle1);
        pause_play.setBackgroundResource(R.drawable.pause2);
        musicNotPlaying = false;
        mPlay.setOnCompletionListener(new MediaPlayer.OnCompletionListener() {
            @Override
            public void onCompletion(MediaPlayer mp) {
                if(infinateLoop==false){
                    musicNotPlaying = true;
                    pause_play.setBackgroundResource(R.drawable.playicon3);
                }else{
                    playreymysterio(nextView);
                }
            }
        });
    }



    public void playreymysterio(View v) {
        superstarID = 87;
        nothingPressed = false;
        if (!musicNotPlaying) {
            mPlay.stop();
            musicNotPlaying = true;
        }
        mPlay = MediaPlayer.create(this, R.raw.reymysterio);
        mPlay.start();
        imageView.setBackgroundResource(R.drawable.reymysterio);
        pause_play.setBackgroundResource(R.drawable.pause2);
        musicNotPlaying = false;
        mPlay.setOnCompletionListener(new MediaPlayer.OnCompletionListener() {
            @Override
            public void onCompletion(MediaPlayer mp) {
                if(infinateLoop==false){
                    musicNotPlaying = true;
                    pause_play.setBackgroundResource(R.drawable.playicon3);
                }else{
                    playrickysteamboat(nextView);
                }
            }
        });
    }
    public void playrickysteamboat(View v) {
        superstarID = 48;
        nothingPressed = false;
        if (!musicNotPlaying) {
            mPlay.stop();
            musicNotPlaying = true;
        }
        mPlay = MediaPlayer.create(this, R.raw.rickysteamboat);
        mPlay.start();
        imageView.setBackgroundResource(R.drawable.rickysteamboat);
        pause_play.setBackgroundResource(R.drawable.pause2);
        musicNotPlaying = false;
        mPlay.setOnCompletionListener(new MediaPlayer.OnCompletionListener() {
            @Override
            public void onCompletion(MediaPlayer mp) {
                if(infinateLoop==false){
                    musicNotPlaying = true;
                    pause_play.setBackgroundResource(R.drawable.playicon3);
                }else{
                    playricflair(nextView);
                }
            }
        });
    }

    public void playricflair(View v) {
        superstarID = 49;
        nothingPressed = false;
        if (!musicNotPlaying) {
            mPlay.stop();
            musicNotPlaying = true;
        }
        mPlay = MediaPlayer.create(this, R.raw.ricflair);
        mPlay.start();
        imageView.setBackgroundResource(R.drawable.ricflair);
        pause_play.setBackgroundResource(R.drawable.pause2);
        musicNotPlaying = false;
        mPlay.setOnCompletionListener(new MediaPlayer.OnCompletionListener() {
            @Override
            public void onCompletion(MediaPlayer mp) {
                if(infinateLoop==false){
                    musicNotPlaying = true;
                    pause_play.setBackgroundResource(R.drawable.playicon3);
                }else{
                    playrobvandam(nextView);
                }
            }
        });
    }



    public void playrobvandam(View v) {
        superstarID = 51;
        nothingPressed = false;
        if (!musicNotPlaying) {
            mPlay.stop();
            musicNotPlaying = true;
        }
        mPlay = MediaPlayer.create(this, R.raw.robvandam);
        mPlay.start();
        imageView.setBackgroundResource(R.drawable.robvandam);
        pause_play.setBackgroundResource(R.drawable.pause2);
        musicNotPlaying = false;
        mPlay.setOnCompletionListener(new MediaPlayer.OnCompletionListener() {
            @Override
            public void onCompletion(MediaPlayer mp) {
                if(infinateLoop==false){
                    musicNotPlaying = true;
                    pause_play.setBackgroundResource(R.drawable.playicon3);
                }else{
                    playroddypiper(nextView);
                }
            }
        });
    }

    public void playroddypiper(View v) {
        superstarID = 52;
        nothingPressed = false;
        if (!musicNotPlaying) {
            mPlay.stop();
            musicNotPlaying = true;
        }
        mPlay = MediaPlayer.create(this, R.raw.rowdyroddy);
        mPlay.start();
        imageView.setBackgroundResource(R.drawable.roddypiper);
        pause_play.setBackgroundResource(R.drawable.pause2);
        musicNotPlaying = false;
        mPlay.setOnCompletionListener(new MediaPlayer.OnCompletionListener() {
            @Override
            public void onCompletion(MediaPlayer mp) {
                if(infinateLoop==false){
                    musicNotPlaying = true;
                    pause_play.setBackgroundResource(R.drawable.playicon3);
                }else{
                    playshanemcmahon(nextView);
                }
            }
        });
    }

    public void playshanemcmahon(View v) {
        superstarID = 53;
        nothingPressed = false;
        if (!musicNotPlaying) {
            mPlay.stop();
            musicNotPlaying = true;
        }
        mPlay = MediaPlayer.create(this, R.raw.shanemcmahon);
        mPlay.start();
        imageView.setBackgroundResource(R.drawable.shanemcmahon);
        pause_play.setBackgroundResource(R.drawable.pause2);
        musicNotPlaying = false;
        mPlay.setOnCompletionListener(new MediaPlayer.OnCompletionListener() {
            @Override
            public void onCompletion(MediaPlayer mp) {
                if(infinateLoop==false){
                    musicNotPlaying = true;
                    pause_play.setBackgroundResource(R.drawable.playicon3);
                }else{
                    playshawnmichaels(nextView);
                }
            }
        });
    }

    public void playshawnmichaels(View v) {
        superstarID = 54;
        nothingPressed = false;
        if (!musicNotPlaying) {
            mPlay.stop();
            musicNotPlaying = true;
        }
        mPlay = MediaPlayer.create(this, R.raw.shawnmichaels);
        mPlay.start();
        imageView.setBackgroundResource(R.drawable.shawnmichaels);
        pause_play.setBackgroundResource(R.drawable.pause2);
        musicNotPlaying = false;
        mPlay.setOnCompletionListener(new MediaPlayer.OnCompletionListener() {
            @Override
            public void onCompletion(MediaPlayer mp) {
                if(infinateLoop==false){
                    musicNotPlaying = true;
                    pause_play.setBackgroundResource(R.drawable.playicon3);
                }else{
                    playtheutlimatewarrior(nextView);
                }
            }
        });
    }

    public void playtheutlimatewarrior(View v) {
        superstarID = 55;
        nothingPressed = false;
        if (!musicNotPlaying) {
            mPlay.stop();
            musicNotPlaying = true;
        }
        mPlay = MediaPlayer.create(this, R.raw.ultimatewarrior);
        mPlay.start();
        imageView.setBackgroundResource(R.drawable.ultimatewarrior);
        pause_play.setBackgroundResource(R.drawable.pause2);
        musicNotPlaying = false;
        mPlay.setOnCompletionListener(new MediaPlayer.OnCompletionListener() {
            @Override
            public void onCompletion(MediaPlayer mp) {
                if(infinateLoop==false){
                    musicNotPlaying = true;
                    pause_play.setBackgroundResource(R.drawable.playicon3);
                }else{
                    playnikkibella(nextView);
                }
            }
        });
    }



    public void playnikkibella(View v) {
        superstarID = 57;
        nothingPressed = false;
        if (!musicNotPlaying) {
            mPlay.stop();
            musicNotPlaying = true;
        }
        mPlay = MediaPlayer.create(this, R.raw.nikkibella);
        mPlay.start();
        imageView.setBackgroundResource(R.drawable.nikki6);
        pause_play.setBackgroundResource(R.drawable.pause2);
        musicNotPlaying = false;
        mPlay.setOnCompletionListener(new MediaPlayer.OnCompletionListener() {
            @Override
            public void onCompletion(MediaPlayer mp) {
                if(infinateLoop==false){
                    musicNotPlaying = true;
                    pause_play.setBackgroundResource(R.drawable.playicon3);
                }else{
                    playpaige(nextView);
                }
            }
        });
    }

    public void playpaige(View v) {
        superstarID = 58;
        nothingPressed = false;
        if (!musicNotPlaying) {
            mPlay.stop();
            musicNotPlaying = true;
        }
        mPlay = MediaPlayer.create(this, R.raw.paige);
        mPlay.start();
        imageView.setBackgroundResource(R.drawable.paige);
        pause_play.setBackgroundResource(R.drawable.pause2);
        musicNotPlaying = false;
        mPlay.setOnCompletionListener(new MediaPlayer.OnCompletionListener() {
            @Override
            public void onCompletion(MediaPlayer mp) {
                if(infinateLoop==false){
                    musicNotPlaying = true;
                    pause_play.setBackgroundResource(R.drawable.playicon3);
                }else{
                    playalishiafox(nextView);
                }
            }
        });
    }



    public void playalishiafox(View v) {
        superstarID = 60;
        nothingPressed = false;
        if (!musicNotPlaying) {
            mPlay.stop();
            musicNotPlaying = true;
        }
        mPlay = MediaPlayer.create(this, R.raw.aliciafox);
        mPlay.start();
        imageView.setBackgroundResource(R.drawable.alishiafox);
        pause_play.setBackgroundResource(R.drawable.pause2);
        musicNotPlaying = false;
        mPlay.setOnCompletionListener(new MediaPlayer.OnCompletionListener() {
            @Override
            public void onCompletion(MediaPlayer mp) {
                if(infinateLoop==false){
                    musicNotPlaying = true;
                    pause_play.setBackgroundResource(R.drawable.playicon3);
                }else{
                    playevamarie(nextView);
                }
            }
        });
    }





    public void playevamarie(View v) {
        superstarID = 64;
        nothingPressed = false;
        if (!musicNotPlaying) {
            mPlay.stop();
            musicNotPlaying = true;
        }
        mPlay = MediaPlayer.create(this, R.raw.evamarie);
        mPlay.start();
        imageView.setBackgroundResource(R.drawable.evamarie);
        pause_play.setBackgroundResource(R.drawable.pause2);
        musicNotPlaying = false;
        mPlay.setOnCompletionListener(new MediaPlayer.OnCompletionListener() {
            @Override
            public void onCompletion(MediaPlayer mp) {
                if(infinateLoop==false){
                    musicNotPlaying = true;
                    pause_play.setBackgroundResource(R.drawable.playicon3);
                }else{
                    playlita(nextView);
                }
            }
        });
    }



    public void playlita(View v) {
        superstarID = 66;
        nothingPressed = false;
        if (!musicNotPlaying) {
            mPlay.stop();
            musicNotPlaying = true;
        }
        mPlay = MediaPlayer.create(this, R.raw.lita);
        mPlay.start();
        imageView.setBackgroundResource(R.drawable.lita1);
        pause_play.setBackgroundResource(R.drawable.pause2);
        musicNotPlaying = false;
        mPlay.setOnCompletionListener(new MediaPlayer.OnCompletionListener() {
            @Override
            public void onCompletion(MediaPlayer mp) {
                if(infinateLoop==false){
                    musicNotPlaying = true;
                    pause_play.setBackgroundResource(R.drawable.playicon3);
                }else{
                    playnaomi(nextView);
                }
            }
        });
    }

    public void playnaomi(View v) {
        superstarID = 67;
        nothingPressed = false;
        if (!musicNotPlaying) {
            mPlay.stop();
            musicNotPlaying = true;
        }
        mPlay = MediaPlayer.create(this, R.raw.naomi);
        mPlay.start();
        imageView.setBackgroundResource(R.drawable.naomi);
        pause_play.setBackgroundResource(R.drawable.pause2);
        musicNotPlaying = false;
        mPlay.setOnCompletionListener(new MediaPlayer.OnCompletionListener() {
            @Override
            public void onCompletion(MediaPlayer mp) {
                if(infinateLoop==false){
                    musicNotPlaying = true;
                    pause_play.setBackgroundResource(R.drawable.playicon3);
                }else{
                    playnatalya(nextView);
                }
            }
        });
    }

    public void playnatalya(View v) {
        superstarID = 68;
        nothingPressed = false;
        if (!musicNotPlaying) {
            mPlay.stop();
            musicNotPlaying = true;
        }
        mPlay = MediaPlayer.create(this, R.raw.natalya);
        mPlay.start();
        imageView.setBackgroundResource(R.drawable.natalya);
        pause_play.setBackgroundResource(R.drawable.pause2);
        musicNotPlaying = false;
        mPlay.setOnCompletionListener(new MediaPlayer.OnCompletionListener() {
            @Override
            public void onCompletion(MediaPlayer mp) {
                if(infinateLoop==false){
                    musicNotPlaying = true;
                    pause_play.setBackgroundResource(R.drawable.playicon3);
                }else{
                    playsable(nextView);
                }
            }
        });
    }

    public void playsable(View v) {
        superstarID = 85;
        nothingPressed = false;
        if (!musicNotPlaying) {
            mPlay.stop();
            musicNotPlaying = true;
        }
        mPlay = MediaPlayer.create(this, R.raw.stable);
        mPlay.start();
        imageView.setBackgroundResource(R.drawable.sable);
        pause_play.setBackgroundResource(R.drawable.pause2);
        musicNotPlaying = false;
        mPlay.setOnCompletionListener(new MediaPlayer.OnCompletionListener() {
            @Override
            public void onCompletion(MediaPlayer mp) {
                if(infinateLoop==false){
                    musicNotPlaying = true;
                    pause_play.setBackgroundResource(R.drawable.playicon3);
                }else{
                    playstacykeibler(nextView);
                }
            }
        });
    }

    public void playstacykeibler(View v) {
        superstarID = 69;
        nothingPressed = false;
        if (!musicNotPlaying) {
            mPlay.stop();
            musicNotPlaying = true;
        }
        mPlay = MediaPlayer.create(this, R.raw.stacykeibler);
        mPlay.start();
        imageView.setBackgroundResource(R.drawable.stacykeibler);
        pause_play.setBackgroundResource(R.drawable.pause2);
        musicNotPlaying = false;
        mPlay.setOnCompletionListener(new MediaPlayer.OnCompletionListener() {
            @Override
            public void onCompletion(MediaPlayer mp) {
                if(infinateLoop==false){
                    musicNotPlaying = true;
                    pause_play.setBackgroundResource(R.drawable.playicon3);
                }else{
                    playsummerrae(nextView);
                }
            }
        });
    }

    public void playsummerrae(View v) {
        superstarID = 70;
        nothingPressed = false;
        if (!musicNotPlaying) {
            mPlay.stop();
            musicNotPlaying = true;
        }
        mPlay = MediaPlayer.create(this, R.raw.summerrae);
        mPlay.start();
        imageView.setBackgroundResource(R.drawable.summerrae1);
        pause_play.setBackgroundResource(R.drawable.pause2);
        musicNotPlaying = false;
        mPlay.setOnCompletionListener(new MediaPlayer.OnCompletionListener() {
            @Override
            public void onCompletion(MediaPlayer mp) {
                if(infinateLoop==false){
                    musicNotPlaying = true;
                    pause_play.setBackgroundResource(R.drawable.playicon3);
                }else{
                    playtrishstratus(nextView);
                }
            }
        });
    }





    public void playtrishstratus(View v) {
        superstarID = 73;
        nothingPressed = false;
        if (!musicNotPlaying) {
            mPlay.stop();
            musicNotPlaying = true;
        }
        mPlay = MediaPlayer.create(this, R.raw.trishstratus);
        mPlay.start();
        imageView.setBackgroundResource(R.drawable.trishstratus1);
        pause_play.setBackgroundResource(R.drawable.pause2);
        musicNotPlaying = false;
        mPlay.setOnCompletionListener(new MediaPlayer.OnCompletionListener() {
            @Override
            public void onCompletion(MediaPlayer mp) {
                if(infinateLoop==false){
                    musicNotPlaying = true;
                    pause_play.setBackgroundResource(R.drawable.playicon3);
                }else{
                    playtheauthority(nextView);
                }
            }
        });
    }

    public void playtheauthority(View v) {
        superstarID = 74;
        nothingPressed = false;
        if (!musicNotPlaying) {
            mPlay.stop();
            musicNotPlaying = true;
        }
        mPlay = MediaPlayer.create(this, R.raw.theauthority);
        mPlay.start();
        imageView.setBackgroundResource(R.drawable.theauthority);
        pause_play.setBackgroundResource(R.drawable.pause2);
        musicNotPlaying = false;
        mPlay.setOnCompletionListener(new MediaPlayer.OnCompletionListener() {
            @Override
            public void onCompletion(MediaPlayer mp) {
                if(infinateLoop==false){
                    musicNotPlaying = true;
                    pause_play.setBackgroundResource(R.drawable.playicon3);
                }else{
                    playthenewday(nextView);
                }
            }
        });
    }

    public void playthenewday(View v) {
        superstarID = 75;
        nothingPressed = false;
        if (!musicNotPlaying) {
            mPlay.stop();
            musicNotPlaying = true;
        }
        mPlay = MediaPlayer.create(this, R.raw.newday);
        mPlay.start();
        imageView.setBackgroundResource(R.drawable.thenewday);
        pause_play.setBackgroundResource(R.drawable.pause2);
        musicNotPlaying = false;
        mPlay.setOnCompletionListener(new MediaPlayer.OnCompletionListener() {
            @Override
            public void onCompletion(MediaPlayer mp) {
                if(infinateLoop==false){
                    musicNotPlaying = true;
                    pause_play.setBackgroundResource(R.drawable.playicon3);
                }else{
                    playluchadragons(nextView);
                }
            }
        });
    }

    public void playluchadragons(View v) {
        superstarID = 76;
        nothingPressed = false;
        if (!musicNotPlaying) {
            mPlay.stop();
            musicNotPlaying = true;
        }
        mPlay = MediaPlayer.create(this, R.raw.luchadragons);
        mPlay.start();
        imageView.setBackgroundResource(R.drawable.luchadragons);
        pause_play.setBackgroundResource(R.drawable.pause2);
        musicNotPlaying = false;
        mPlay.setOnCompletionListener(new MediaPlayer.OnCompletionListener() {
            @Override
            public void onCompletion(MediaPlayer mp) {
                if(infinateLoop==false){
                    musicNotPlaying = true;
                    pause_play.setBackgroundResource(R.drawable.playicon3);
                }else{
                    playprimetimeplayers(nextView);
                }
            }
        });
    }

    public void playprimetimeplayers(View v) {
        superstarID = 77;
        nothingPressed = false;
        if (!musicNotPlaying) {
            mPlay.stop();
            musicNotPlaying = true;
        }
        mPlay = MediaPlayer.create(this, R.raw.primetime);
        mPlay.start();
        imageView.setBackgroundResource(R.drawable.primetimeplayers);
        pause_play.setBackgroundResource(R.drawable.pause2);
        musicNotPlaying = false;
        mPlay.setOnCompletionListener(new MediaPlayer.OnCompletionListener() {
            @Override
            public void onCompletion(MediaPlayer mp) {
                if(infinateLoop==false){
                    musicNotPlaying = true;
                    pause_play.setBackgroundResource(R.drawable.playicon3);
                }else{
                    playdx(nextView);
                }
            }
        });
    }

    public void playdx(View v) {
        superstarID = 78;
        nothingPressed = false;
        if (!musicNotPlaying) {
            mPlay.stop();
            musicNotPlaying = true;
        }
        mPlay = MediaPlayer.create(this, R.raw.dx);
        mPlay.start();
        imageView.setBackgroundResource(R.drawable.dx);
        pause_play.setBackgroundResource(R.drawable.pause2);
        musicNotPlaying = false;
        mPlay.setOnCompletionListener(new MediaPlayer.OnCompletionListener() {
            @Override
            public void onCompletion(MediaPlayer mp) {
                if(infinateLoop==false){
                    musicNotPlaying = true;
                    pause_play.setBackgroundResource(R.drawable.playicon3);
                }else{
                    playnwo(nextView);
                }
            }
        });
    }

    public void playnwo(View v) {
        superstarID = 79;
        nothingPressed = false;
        if (!musicNotPlaying) {
            mPlay.stop();
            musicNotPlaying = true;
        }
        mPlay = MediaPlayer.create(this, R.raw.newworldorder);
        mPlay.start();
        imageView.setBackgroundResource(R.drawable.nwo);
        pause_play.setBackgroundResource(R.drawable.pause2);
        musicNotPlaying = false;
        mPlay.setOnCompletionListener(new MediaPlayer.OnCompletionListener() {
            @Override
            public void onCompletion(MediaPlayer mp) {
                if(infinateLoop==false){
                    musicNotPlaying = true;
                    pause_play.setBackgroundResource(R.drawable.playicon3);
                }else{
                    playevolution(nextView);
                }
            }
        });
    }


    public void playevolution(View v) {
        superstarID = 81;
        nothingPressed = false;
        if (!musicNotPlaying) {
            mPlay.stop();
            musicNotPlaying = true;
        }
        mPlay = MediaPlayer.create(this, R.raw.evolution);
        mPlay.start();
        imageView.setBackgroundResource(R.drawable.evolution);
        pause_play.setBackgroundResource(R.drawable.pause2);
        musicNotPlaying = false;
        mPlay.setOnCompletionListener(new MediaPlayer.OnCompletionListener() {
            @Override
            public void onCompletion(MediaPlayer mp) {
                if(infinateLoop==false){
                    musicNotPlaying = true;
                    pause_play.setBackgroundResource(R.drawable.playicon3);
                }else{
                    playdudleyboyz(nextView);
                }
            }
        });
    }

    public void playdudleyboyz(View v) {
        superstarID = 82;
        nothingPressed = false;
        if (!musicNotPlaying) {
            mPlay.stop();
            musicNotPlaying = true;
        }
        mPlay = MediaPlayer.create(this, R.raw.dubleyboyz);
        mPlay.start();
        imageView.setBackgroundResource(R.drawable.dudleyboys);
        pause_play.setBackgroundResource(R.drawable.pause2);
        musicNotPlaying = false;
        mPlay.setOnCompletionListener(new MediaPlayer.OnCompletionListener() {
            @Override
            public void onCompletion(MediaPlayer mp) {
                if(infinateLoop==false){
                    musicNotPlaying = true;
                    pause_play.setBackgroundResource(R.drawable.playicon3);
                }else{
                    playtheroadwarriors(nextView);
                }
            }
        });
    }

    public void playtheroadwarriors(View v) {
        superstarID = 83;
        nothingPressed = false;
        if (!musicNotPlaying) {
            mPlay.stop();
            musicNotPlaying = true;
        }
        mPlay = MediaPlayer.create(this, R.raw.theroadwarrior);
        mPlay.start();
        imageView.setBackgroundResource(R.drawable.theroadwarriors);
        pause_play.setBackgroundResource(R.drawable.pause2);
        musicNotPlaying = false;
        mPlay.setOnCompletionListener(new MediaPlayer.OnCompletionListener() {
            @Override
            public void onCompletion(MediaPlayer mp) {
                if(infinateLoop==false){
                    musicNotPlaying = true;
                    pause_play.setBackgroundResource(R.drawable.playicon3);
                }else{
                    playrawacrossthenation(nextView);
                }
            }
        });
    }



    public void playrawacrossthenation(View v) {
        superstarID = 80;
        nothingPressed = false;
        if (!musicNotPlaying) {
            mPlay.stop();
            musicNotPlaying = true;
        }
        mPlay = MediaPlayer.create(this, R.raw.rawacrossthenation);
        mPlay.start();
        imageView.setBackgroundResource(R.drawable.raw2002);
        pause_play.setBackgroundResource(R.drawable.pause2);
        musicNotPlaying = false;
        mPlay.setOnCompletionListener(new MediaPlayer.OnCompletionListener() {
            @Override
            public void onCompletion(MediaPlayer mp) {
                if(infinateLoop==false){
                    musicNotPlaying = true;
                    pause_play.setBackgroundResource(R.drawable.playicon3);
                }else{
                    playrawtobeloved(nextView);  //start at the beginning
                }
            }
        });
    }

    public void playrawtobeloved(View v) {
        superstarID = 80;
        nothingPressed = false;
        if (!musicNotPlaying) {
            mPlay.stop();
            musicNotPlaying = true;
        }
        mPlay = MediaPlayer.create(this, R.raw.rawtobeloved);
        mPlay.start();
        imageView.setBackgroundResource(R.drawable.raw2006);
        pause_play.setBackgroundResource(R.drawable.pause2);
        musicNotPlaying = false;
        mPlay.setOnCompletionListener(new MediaPlayer.OnCompletionListener() {
            @Override
            public void onCompletion(MediaPlayer mp) {
                if(infinateLoop==false){
                    musicNotPlaying = true;
                    pause_play.setBackgroundResource(R.drawable.playicon3);
                }else{
                    playraw2010(nextView);  //start at the beginning
                }
            }
        });
    }
    public void playraw2010(View v) {
        superstarID = 80;
        nothingPressed = false;
        if (!musicNotPlaying) {
            mPlay.stop();
            musicNotPlaying = true;
        }
        mPlay = MediaPlayer.create(this, R.raw.rawtwentytentheme);
        mPlay.start();
        imageView.setBackgroundResource(R.drawable.raw2010);
        pause_play.setBackgroundResource(R.drawable.pause2);
        musicNotPlaying = false;
        mPlay.setOnCompletionListener(new MediaPlayer.OnCompletionListener() {
            @Override
            public void onCompletion(MediaPlayer mp) {
                if(infinateLoop==false){
                    musicNotPlaying = true;
                    pause_play.setBackgroundResource(R.drawable.playicon3);
                }else{
                    playsmackdown2015(nextView);  //start at the beginning
                }
            }
        });
    }

    public void playsmackdown2015(View v) {
        superstarID = 2;
        nothingPressed = false;
        if (!musicNotPlaying) {
            mPlay.stop();
            musicNotPlaying = true;
        }
        mPlay = MediaPlayer.create(this, R.raw.smackdowncurrentintro);
        mPlay.start();
        imageView.setBackgroundResource(R.drawable.smackdown2015);
        pause_play.setBackgroundResource(R.drawable.pause2);
        musicNotPlaying = false;
        mPlay.setOnCompletionListener(new MediaPlayer.OnCompletionListener() {
            @Override
            public void onCompletion(MediaPlayer mp) {
                if(infinateLoop==false){
                    musicNotPlaying = true;
                    pause_play.setBackgroundResource(R.drawable.playicon3);
                }else{
                    playtripleh(nextView);  //start at the beginning
                }
            }
        });
    }


    public void pauseOrPlaySound(View v) {

        if (musicNotPlaying) {
            //if sound is not playing, display pause button, and play music
            if (nothingPressed) {
                mPlay = MediaPlayer.create(this, R.raw.timetoplaythegame);
                //View tripleH = (View) findViewById(R.id.tripleh_button);
                //playtripleh(tripleH);
                imageView.setBackgroundResource(R.drawable.tripleh1);
                nothingPressed = false;

            }

            v.setBackgroundResource(R.drawable.pause2);
            musicNotPlaying = false;
            mPlay.start();
        } else if (!musicNotPlaying) {

            v.setBackgroundResource(R.drawable.playicon3);
            mPlay.pause();
            musicNotPlaying = true;
        }


    }


    @Override
    protected void onDestroy() {
        super.onDestroy();
        if (mPlay != null) {
            mPlay.stop();
        }
        nothingPressed = true;
        musicNotPlaying = true;
        //lightsOff = true;
       /*
        if (mtask != null) {
            mtask.cancel(true);
        }
        */


    }



/*

    private void changeBgColor() {
        mHandler.post(new Runnable() {
            public void run() {
                Log.i("Cause", "cause cause 111111");
                while (!lightsOff) {
                    Log.i("Cause", "cause cause 222222");
                    long chance = System.currentTimeMillis() % 8;
                    if (chance < 3) {
                        lightningView.setBackgroundColor(getResources().getColor(R.color.tripleHseq1));
                        Log.i("Cause", "cause cause 33333333");

                    } else if (chance < 6) {
                        lightningView.setBackgroundColor(getResources().getColor(R.color.tripleHseq2));
                        Log.i("Cause", "cause cause 33333333");
                    } else if (chance < 7) {
                        lightningView.setBackgroundColor(getResources().getColor(R.color.tripleHseq3));
                        Log.i("Cause", "cause cause 33333333");
                    } else {
                        lightningView.setBackgroundColor(getResources().getColor(R.color.tripleHseq4));
                        Log.i("Cause", "cause cause 33333333");
                    }
                    Log.i("Cause", "cause cause 4444444");
                    if (musicNotPlaying) {
                        removeLighting();
                        Log.i("Cause", "cause cause 55555");
                    }
                    try {
                        Thread.sleep(100);
                    } catch (InterruptedException e) {
                        e.printStackTrace();
                    }
                    Log.i("Cause", "cause cause 666666");
                }
            }
        });
    }

    */


    class MyTask extends AsyncTask<Void, Void, Void> {

        public RelativeLayout lightningView;
        public RelativeLayout bottomAdView;


        @Override
        protected void onPreExecute() {

            lightningView = (RelativeLayout) findViewById(R.id.lightning);
            bottomAdView = (RelativeLayout) findViewById(R.id.bottomlayout);

        }

        @Override
        protected Void doInBackground(Void... voids) {
            int x = 1;
            while (x < 100) {
                // Log.i("COLOR", "RACHED REACHED REACHED 22222222222");
                //Log.i("cancelled?", Boolean.toString(isCancelled()));
                //if (isCancelled()) {break;}
                //if(musicNotPlaying){break;}


                if (!lightsOff) {
                    runOnUiThread(new Runnable() {

                        @Override
                        public void run() {
                            updateLighting();
                            if (musicNotPlaying) {
                                removeLighting();
                            }
                        }
                    });

                }

                try {
                    Thread.sleep(300);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }

                x--;
                x++;

            }

            return null;
        }


        @Override
        protected void onCancelled() {
            Log.i("onCancelled", Boolean.toString(isCancelled()));
            lightsOff = true;
            musicNotPlaying = true;

        }

        @Override
        protected void onProgressUpdate(Void... values) {

        }

        @Override
        protected void onPostExecute(Void aVoid) {
            lightningView.setBackgroundColor(getResources().getColor(R.color.transparent));
            bottomAdView.setBackgroundColor(getResources().getColor(R.color.transparent));

        }

        public void updateLighting() {

/*
            if (superstarID <= 42) {
                if (superstarID <= 21) {
                    if (superstarID <= 11) {
                        if (superstarID <= 5) {
                            if (superstarID <= 3) {
                                if (superstarID == 1) {

                                } else if (superstarID == 2) {

                                } else if (superstarID == 3) {

                                }
                            } else if (superstarID == 4) {

                            } else if (superstarID == 5) {

                            }

                        } else if (superstarID <= 7) {
                            if (superstarID == 6) {

                            } else if (superstarID == 7) {

                            }
                        }else if(superstarID==8){

                        }else if(superstarID==9){

                        }else if(superstarID==10){

                        }else if(superstarID==11){

                        }

                    }
                }
            }
*/


            long chance = System.currentTimeMillis() % 8;

            if (superstarID == 58) {//paige
                if (chance < 3) {
                    lightningView.setBackgroundColor(getResources().getColor(R.color.white));
                    bottomAdView.setBackgroundColor(getResources().getColor(R.color.white));


                } else if (chance < 6) {
                    lightningView.setBackgroundColor(getResources().getColor(R.color.black));
                    bottomAdView.setBackgroundColor(getResources().getColor(R.color.black));

                } else if (chance < 7) {
                    lightningView.setBackgroundColor(getResources().getColor(R.color.grey));
                    bottomAdView.setBackgroundColor(getResources().getColor(R.color.grey));

                } else {
                    lightningView.setBackgroundColor(getResources().getColor(R.color.darkgrey));
                    bottomAdView.setBackgroundColor(getResources().getColor(R.color.darkgrey));

                }

            } else if (superstarID == 6) {//undertaker
                if (chance < 3) {
                    lightningView.setBackgroundColor(getResources().getColor(R.color.black));
                    bottomAdView.setBackgroundColor(getResources().getColor(R.color.black));


                } else if (chance < 6) {
                    lightningView.setBackgroundColor(getResources().getColor(R.color.deeppurple900));
                    bottomAdView.setBackgroundColor(getResources().getColor(R.color.deeppurple900));

                } else if (chance < 7) {
                    lightningView.setBackgroundColor(getResources().getColor(R.color.blue));
                    bottomAdView.setBackgroundColor(getResources().getColor(R.color.blue));

                } else {
                    lightningView.setBackgroundColor(getResources().getColor(R.color.darkblue));
                    bottomAdView.setBackgroundColor(getResources().getColor(R.color.darkblue));

                }
            } else if (superstarID == 2) {//the rock and smackdown
                if (chance < 3) {
                    lightningView.setBackgroundColor(getResources().getColor(R.color.blue));
                    bottomAdView.setBackgroundColor(getResources().getColor(R.color.blue));


                } else if (chance < 6) {
                    lightningView.setBackgroundColor(getResources().getColor(R.color.darkblue));
                    bottomAdView.setBackgroundColor(getResources().getColor(R.color.darkblue));

                } else if (chance < 7) {
                    lightningView.setBackgroundColor(getResources().getColor(R.color.white));
                    bottomAdView.setBackgroundColor(getResources().getColor(R.color.white));

                } else {
                    lightningView.setBackgroundColor(getResources().getColor(R.color.darkgrey));
                    bottomAdView.setBackgroundColor(getResources().getColor(R.color.darkgrey));

                }
            }else if (superstarID == 3) {//stonecold
                if (chance < 3) {
                    lightningView.setBackgroundColor(getResources().getColor(R.color.white));
                    bottomAdView.setBackgroundColor(getResources().getColor(R.color.white));


                } else if (chance < 6) {
                    lightningView.setBackgroundColor(getResources().getColor(R.color.grey));
                    bottomAdView.setBackgroundColor(getResources().getColor(R.color.grey));

                } else if (chance < 7) {
                    lightningView.setBackgroundColor(getResources().getColor(R.color.white));
                    bottomAdView.setBackgroundColor(getResources().getColor(R.color.white));

                } else {
                    lightningView.setBackgroundColor(getResources().getColor(R.color.darkgrey));
                    bottomAdView.setBackgroundColor(getResources().getColor(R.color.darkgrey));

                }
            }else if (superstarID == 4) {//vince
                if (chance < 3) {
                    lightningView.setBackgroundColor(getResources().getColor(R.color.primary_color));
                    bottomAdView.setBackgroundColor(getResources().getColor(R.color.primary_color));


                } else if (chance < 6) {
                    lightningView.setBackgroundColor(getResources().getColor(R.color.blue));
                    bottomAdView.setBackgroundColor(getResources().getColor(R.color.blue));

                } else if (chance < 7) {
                    lightningView.setBackgroundColor(getResources().getColor(R.color.primary_color_dark));
                    bottomAdView.setBackgroundColor(getResources().getColor(R.color.primary_color_dark));

                } else {
                    lightningView.setBackgroundColor(getResources().getColor(R.color.darkblue));
                    bottomAdView.setBackgroundColor(getResources().getColor(R.color.darkblue));

                }
            } else if (superstarID == 74) {//the authority
                if (chance < 3) {
                    lightningView.setBackgroundColor(getResources().getColor(R.color.primary_color));
                    bottomAdView.setBackgroundColor(getResources().getColor(R.color.primary_color));


                } else if (chance < 6) {
                    lightningView.setBackgroundColor(getResources().getColor(R.color.primary_color_dark));
                    bottomAdView.setBackgroundColor(getResources().getColor(R.color.primary_color_dark));

                } else if (chance < 7) {
                    lightningView.setBackgroundColor(getResources().getColor(R.color.black));
                    bottomAdView.setBackgroundColor(getResources().getColor(R.color.black));

                } else {
                    lightningView.setBackgroundColor(getResources().getColor(R.color.darkblue));
                    bottomAdView.setBackgroundColor(getResources().getColor(R.color.darkblue));

                }
            } else if (superstarID == 46) {
                if (chance < 3) {
                    lightningView.setBackgroundColor(getResources().getColor(R.color.primary_color));
                    bottomAdView.setBackgroundColor(getResources().getColor(R.color.primary_color));


                } else if (chance < 6) {
                    lightningView.setBackgroundColor(getResources().getColor(R.color.blue));
                    bottomAdView.setBackgroundColor(getResources().getColor(R.color.blue));

                } else if (chance < 7) {
                    lightningView.setBackgroundColor(getResources().getColor(R.color.white));
                    bottomAdView.setBackgroundColor(getResources().getColor(R.color.white));

                } else {
                    lightningView.setBackgroundColor(getResources().getColor(R.color.white));
                    bottomAdView.setBackgroundColor(getResources().getColor(R.color.white));

                }
            } else if (superstarID == 81) { //evolution
                if (chance < 3) {
                    lightningView.setBackgroundColor(getResources().getColor(R.color.yellow));
                    bottomAdView.setBackgroundColor(getResources().getColor(R.color.yellow));


                } else if (chance < 6) {
                    lightningView.setBackgroundColor(getResources().getColor(R.color.darkyellow));
                    bottomAdView.setBackgroundColor(getResources().getColor(R.color.darkyellow));

                } else if (chance < 7) {
                    lightningView.setBackgroundColor(getResources().getColor(R.color.amber));
                    bottomAdView.setBackgroundColor(getResources().getColor(R.color.amber));

                } else {
                    lightningView.setBackgroundColor(getResources().getColor(R.color.darkamber));
                    bottomAdView.setBackgroundColor(getResources().getColor(R.color.darkamber));

                }
            } else if (superstarID == 25) { //kane
                if (chance < 3) {
                    lightningView.setBackgroundColor(getResources().getColor(R.color.primary_color));
                    bottomAdView.setBackgroundColor(getResources().getColor(R.color.primary_color));


                } else if (chance < 6) {
                    lightningView.setBackgroundColor(getResources().getColor(R.color.primary_color_dark));
                    bottomAdView.setBackgroundColor(getResources().getColor(R.color.primary_color_dark));

                } else if (chance < 7) {
                    lightningView.setBackgroundColor(getResources().getColor(R.color.amber));
                    bottomAdView.setBackgroundColor(getResources().getColor(R.color.amber));

                } else {
                    lightningView.setBackgroundColor(getResources().getColor(R.color.black));
                    bottomAdView.setBackgroundColor(getResources().getColor(R.color.black));

                }
            } else if (superstarID == 80) { //raw
                if (chance < 3) {
                    lightningView.setBackgroundColor(getResources().getColor(R.color.primary_color));
                    bottomAdView.setBackgroundColor(getResources().getColor(R.color.primary_color));


                } else if (chance < 6) {
                    lightningView.setBackgroundColor(getResources().getColor(R.color.primary_color_dark));
                    bottomAdView.setBackgroundColor(getResources().getColor(R.color.primary_color_dark));

                } else if (chance < 7) {
                    lightningView.setBackgroundColor(getResources().getColor(R.color.white));
                    bottomAdView.setBackgroundColor(getResources().getColor(R.color.white));

                } else {
                    lightningView.setBackgroundColor(getResources().getColor(R.color.black));
                    bottomAdView.setBackgroundColor(getResources().getColor(R.color.black));

                }
            }else {
                if (chance < 3) {
                    lightningView.setBackgroundColor(getResources().getColor(R.color.tripleHseq1));
                    bottomAdView.setBackgroundColor(getResources().getColor(R.color.tripleHseq1));


                } else if (chance < 6) {
                    lightningView.setBackgroundColor(getResources().getColor(R.color.tripleHseq2));
                    bottomAdView.setBackgroundColor(getResources().getColor(R.color.tripleHseq2));

                } else if (chance < 7) {
                    lightningView.setBackgroundColor(getResources().getColor(R.color.tripleHseq3));
                    bottomAdView.setBackgroundColor(getResources().getColor(R.color.tripleHseq3));

                } else {
                    lightningView.setBackgroundColor(getResources().getColor(R.color.tripleHseq4));
                    bottomAdView.setBackgroundColor(getResources().getColor(R.color.tripleHseq4));

                }
            }


        }

        public void removeLighting() {
            lightningView.setBackgroundColor(getResources().getColor(R.color.transparent));
            bottomAdView.setBackgroundColor(getResources().getColor(R.color.transparent));

        }
    }

}
