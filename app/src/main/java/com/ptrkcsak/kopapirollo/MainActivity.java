package com.ptrkcsak.kopapirollo;

import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;

import android.content.DialogInterface;
import android.media.FaceDetector;
import android.os.Bundle;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import java.util.Random;

public class MainActivity extends AppCompatActivity {
    private int win, lose, draw;
    private int myArray[];
    private Button ko, papir, ollo;
    private TextView text_win, text_lose;
    private ImageView teValaszt, gepValaszt;
    private AlertDialog.Builder builderJatekVege;
    Random rnd = new Random();
    private Toast egyediToast;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        init();
        kiir();

        ko.setOnClickListener(view -> {
            teValaszt.setBackgroundResource(R.drawable.rock);
            if (gepValaszt() == 3) {
                Toast.makeText(MainActivity.this,
                        "A Kő üti az Ollót", Toast.LENGTH_SHORT).show();
                win++;
            } else if (gepValaszt() == 2){
                lose++;
                Toast.makeText(MainActivity.this,
                        "A Kő nem üti a Papírt", Toast.LENGTH_SHORT).show();
            } else {
                draw++;
            }
            kiir();
            ellenorzes();
        });

        papir.setOnClickListener(view -> {
            teValaszt.setBackgroundResource(R.drawable.paper);
            if (gepValaszt() == 1) {
                Toast.makeText(MainActivity.this,
                        "A Papír üti a Követ", Toast.LENGTH_SHORT).show();
                win++;
            } else if (gepValaszt() == 3){
                lose++;
                Toast.makeText(MainActivity.this,
                        "A Papír nem üti az Ollót", Toast.LENGTH_SHORT).show();
            } else {
                draw++;
            }
            kiir();
            ellenorzes();
        });

        ollo.setOnClickListener(view -> {
            teValaszt.setBackgroundResource(R.drawable.scissors);
            if (gepValaszt() == 2) {
                Toast.makeText(MainActivity.this,
                        "Az Olló üti a Papírt", Toast.LENGTH_SHORT).show();
                win++;
            } else if (gepValaszt() == 1){
                Toast.makeText(MainActivity.this,
                        "Az Olló nem üti a Követ", Toast.LENGTH_SHORT).show();
                lose++;
            } else {
                draw++;
            }
            kiir();
            ellenorzes();
        });

    }

    public void init() {
        ko = (Button)findViewById(R.id.ko);
        papir = (Button)findViewById(R.id.papir);
        ollo = (Button)findViewById(R.id.ollo);

        text_lose = (TextView)findViewById(R.id.text_lose);
        text_win = (TextView)findViewById(R.id.text_win);

        gepValaszt = (ImageView)findViewById(R.id.gepValaszt);
        teValaszt = (ImageView)findViewById(R.id.teValaszt);

        builderJatekVege = new AlertDialog.Builder(MainActivity.this);
        builderJatekVege.setCancelable(false)
                .setTitle("Nyert / Vesztet")
                .setMessage("Szeretne új játékot játszani?")
                .setNegativeButton("Nem", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialogInterface, int i) {
                        finish();
                    }
                })
                .setPositiveButton("Igen", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialogInterface, int i) {
                        ujJatek();
                        kiir();
                    }
                })
                .create();
    }

    public void kiir() {
        text_win.setText("Win: "+Integer.toString(win));
        text_lose.setText("Lose: "+Integer.toString(lose));

    }

    public int gepValaszt() {
        int[] items = new int[]{1,2,3};
        int valaszt = rnd.nextInt(items.length);
        switch (valaszt){
            case 0:
                gepValaszt.setBackgroundResource(R.drawable.rock);
                return items[valaszt];
            case 1:
                gepValaszt.setBackgroundResource(R.drawable.paper);
                return items[valaszt];
            case 2:
                gepValaszt.setBackgroundResource(R.drawable.scissors);
                return items[valaszt];
            default:
                return valaszt;
        }

    }

    private void ujJatek() {
        win = 0;
        lose = 0;
    }

    private void ellenorzes() {
        if (win >= 3 || lose >= 3){
            if (win>lose){
                builderJatekVege.setTitle("Nyertél").create();
                builderJatekVege.show();
            }else{
                builderJatekVege.setTitle("Vesztettél").create();
                builderJatekVege.show();
            }
        }
    }
}