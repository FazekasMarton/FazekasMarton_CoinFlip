package com.example.coinflip;

import android.content.DialogInterface;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;

import java.util.Random;

public class MainActivity extends AppCompatActivity {

    private ImageView coin;
    private Button headButton;
    private Button tailButton;
    private TextView flipCounter;
    private TextView winCounter;
    private TextView loseCounter;
    private Random random;
    private int result;
    private int turn;
    private int lose;
    private int win;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        EdgeToEdge.enable(this);
        setContentView(R.layout.activity_main);
        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main), (v, insets) -> {
            Insets systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars());
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom);
            return insets;
        });
        init();
        headButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                flip(0);
            }
        });
        tailButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                flip(1);
            }
        });
    }

    public void flip(int guess){
        result = random.nextInt(2);
        if (result == 0){
            coin.setImageResource(R.drawable.heads);
            Toast.makeText(MainActivity.this, "Fej!", Toast.LENGTH_SHORT).show();
        } else {
            coin.setImageResource(R.drawable.tails);
            Toast.makeText(MainActivity.this, "Írás!", Toast.LENGTH_SHORT).show();
        }
        turn++;
        flipCounter.setText(String.valueOf(turn));
        if (result == guess){
            win++;
            winCounter.setText(String.valueOf(win));
        } else {
            lose++;
            loseCounter.setText(String.valueOf(lose));
        }
        if(turn >= 5){
            AlertDialog.Builder builder = new AlertDialog.Builder(this);
            if(win > lose){
                builder.setTitle("Győzelem");
            } else {
                builder.setTitle("Vereség");
            }
            builder.setMessage("Szeretnéd újrakezdeni a játékot?");
            builder.setPositiveButton("Igen", new DialogInterface.OnClickListener() {
                @Override
                public void onClick(DialogInterface dialog, int which) {
                    reset();
                }
            });

            builder.setNegativeButton("Nem", new DialogInterface.OnClickListener() {
                @Override
                public void onClick(DialogInterface dialog, int which) {
                    finish();
                }
            });
            builder.show();
        }
    }

    public void reset(){
        flipCounter.setText("0");
        winCounter.setText("0");
        loseCounter.setText("0");
        coin.setImageResource(R.drawable.heads);
        init();
    }

    public void init(){
        coin = findViewById(R.id.coin);
        headButton = findViewById(R.id.headButton);
        tailButton = findViewById(R.id.tailButton);
        flipCounter = findViewById(R.id.flipCounter);
        winCounter = findViewById(R.id.winCounter);
        loseCounter = findViewById(R.id.loseCounter);
        random = new Random();
        result = 0;
        turn = 0;
        win = 0;
        lose = 0;
    }
}