package com.example.coinflip;

import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;

import android.animation.AnimatorInflater;
import android.animation.AnimatorSet;
import android.content.DialogInterface;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import java.util.Random;

public class MainActivity extends AppCompatActivity {

    private Button buttonHead;
    private Button buttonTails;
    private TextView textView1;
    private TextView textView2;
    private TextView textView3;
    private ImageView imageView;
    private int dobasok;
    private int gyozelem;
    private int vereseg;
    public int tipp;        //0 head, 1 tails
    private AlertDialog.Builder alertDialog;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        init();
        AnimatorSet flipAnimation = (AnimatorSet) AnimatorInflater.loadAnimator(this, R.animator.flip_animation);

        buttonHead.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                tipp = 0;
                dobasok++;
                textView1.setText("Dobasok: " + dobasok);
                Random random = new Random();
                int rnd = random.nextInt(2);
                if(rnd == tipp)
                {   //win
                    flipAnimation.setTarget(imageView);
                    imageView.setVisibility(View.VISIBLE);

                    gyozelem++;
                    textView2.setText("Győzelem: " + gyozelem);
                    imageView.setImageResource(R.drawable.heads);
                    Toast.makeText(MainActivity.this, "fej", Toast.LENGTH_SHORT).show();
                }
                else
                {   //lose
                    flipAnimation.setTarget(imageView);
                    imageView.setVisibility(View.VISIBLE);
                    flipAnimation.start();
                    vereseg++;
                    textView3.setText("Vereség: " + vereseg);
                    imageView.setImageResource(R.drawable.tails);
                    Toast.makeText(MainActivity.this, "írás", Toast.LENGTH_SHORT).show();
                }
                if(dobasok >= 5 && vereseg > gyozelem || (dobasok >= 3 && vereseg > gyozelem))
                {
                    alertDialog.setTitle("Vesztettél!").create().show();
                } else if (dobasok >= 5 && vereseg < gyozelem || (dobasok >= 3 && vereseg < gyozelem)) {
                    alertDialog.setTitle("Nyertél!!").create().show();
                }


            }
        });
        buttonTails.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                tipp = 1;
                dobasok++;
                textView1.setText("Dobasok: " + dobasok);
                Random random = new Random();
                int rnd = random.nextInt(2);
                if(rnd == tipp)
                {   //win
                    flipAnimation.setTarget(imageView);
                    imageView.setVisibility(View.VISIBLE);
                    flipAnimation.start();
                    gyozelem++;
                    textView2.setText("Győzelem: " + gyozelem);
                    imageView.setImageResource(R.drawable.tails);
                    Toast.makeText(MainActivity.this, "írás", Toast.LENGTH_SHORT).show();
                }
                else
                {   //lose
                    flipAnimation.setTarget(imageView);
                    imageView.setVisibility(View.VISIBLE);
                    flipAnimation.start();
                    vereseg++;
                    textView3.setText("Vereség: " + vereseg);
                    imageView.setImageResource(R.drawable.heads);
                    Toast.makeText(MainActivity.this, "fej", Toast.LENGTH_SHORT).show();
                }
                if(dobasok >= 5 && vereseg > gyozelem || (dobasok >= 3 && vereseg > gyozelem))
                {
                    alertDialog.setTitle("Vesztettél!").create().show();
                } else if (dobasok >= 5 && vereseg < gyozelem || (dobasok >= 3 && vereseg < gyozelem)) {
                    alertDialog.setTitle("Nyertél!!").create().show();
                }
            }
        });
    }
    public void init()
    {
        buttonHead = findViewById(R.id.headButton);
        buttonTails = findViewById(R.id.tailButton);
        textView1 = findViewById(R.id.textView1);
        textView2 = findViewById(R.id.textView2);
        textView3 = findViewById(R.id.textView3);
        imageView = findViewById(R.id.imageView);
        alertDialog = new AlertDialog.Builder(MainActivity.this);
        alertDialog.setTitle("Vesztettél!").setMessage("Restart?").setNegativeButton("Nem", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialogInterface, int i) {
                        finish();
                    }
                })
                .setPositiveButton("Igen", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialogInterface, int i) {
                        Reset();
                    }
                })
                .setCancelable(false)
                .create();
    }
    public void Reset()
    {
        imageView.setImageResource(R.drawable.heads);
        gyozelem = 0;
        textView1.setText("Dobasok: 0");
        vereseg = 0;
        textView2.setText("Győzelem: 0");
        dobasok = 0;
        textView3.setText("Vereség: 0");
    }
}