package games.tictactoe;

import androidx.appcompat.app.AppCompatActivity;

import android.app.AlertDialog;
import android.content.DialogInterface;
import android.graphics.Color;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import java.util.ArrayList;
import java.util.List;

public class SecondActivity extends AppCompatActivity {

    TextView tvNamePlayer1;
    TextView tvNamePlayer2;
    TextView tvScorePlayer1;
    TextView tvScorePlayer2;
    TextView tvRound;

    Button btnEndGame;

    Button btn00;
    Button btn01;
    Button btn02;
    Button btn10;
    Button btn11;
    Button btn12;
    Button btn20;
    Button btn21;
    Button btn22;
    List<Button> buttonList = new ArrayList<>();

    int scorePlayer1 = 0;
    int scorePlayer2 = 0;
    int round = 1;
    int move = 0;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_second);

        tvRound = (TextView)findViewById(R.id.tvRound);

        tvNamePlayer1 = (TextView)findViewById(R.id.tvNamePlayer1);
        tvNamePlayer2 = (TextView)findViewById(R.id.tvNamePlayer2);

        tvNamePlayer1.setText(getIntent().getStringExtra("namePlayer1") + ":");
        tvNamePlayer2.setText(getIntent().getStringExtra("namePlayer2") + ":");

        tvScorePlayer1 = (TextView)findViewById(R.id.tvScorePlayer1);
        tvScorePlayer2 = (TextView)findViewById(R.id.tvScorePlayer2);

        btn00 = (Button)findViewById(R.id.btn00);
        btn01 = (Button)findViewById(R.id.btn01);
        btn02 = (Button)findViewById(R.id.btn02);
        btn10 = (Button)findViewById(R.id.btn10);
        btn11 = (Button)findViewById(R.id.btn11);
        btn12 = (Button)findViewById(R.id.btn12);
        btn20 = (Button)findViewById(R.id.btn20);
        btn21 = (Button)findViewById(R.id.btn21);
        btn22 = (Button)findViewById(R.id.btn22);

        buttonList.add(btn00);
        buttonList.add(btn01);
        buttonList.add(btn02);
        buttonList.add(btn10);
        buttonList.add(btn11);
        buttonList.add(btn12);
        buttonList.add(btn20);
        buttonList.add(btn21);
        buttonList.add(btn22);

        for (Button b : buttonList) {
            b.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    if(b.getText().toString().isEmpty()){
                        if(move % 2 == 0){
                            b.setTextColor(Color.parseColor("#F9AA64"));
                            b.setText("X");
                        }
                        else{
                            b.setTextColor(Color.parseColor("#64B3F9"));
                            b.setText("0");
                        }
                        move++;
                        String s = check();
                        if(s != null){
                            if(s.equals("X")){
                                Toast.makeText(SecondActivity.this, getIntent().getStringExtra("namePlayer1") + " won round " + round + "!", Toast.LENGTH_SHORT).show();
                                tvScorePlayer1.setText(String.valueOf(++scorePlayer1));
                            } else{
                                Toast.makeText(SecondActivity.this, getIntent().getStringExtra("namePlayer2") + " won round " + round + "!", Toast.LENGTH_SHORT).show();
                                tvScorePlayer2.setText(String.valueOf(++scorePlayer2));
                            }
                            restart();
                        }
                        else{
                            if(move == 9){
                                Toast.makeText(SecondActivity.this, "Unfortunately, no one won this round.", Toast.LENGTH_SHORT).show();
                                restart();
                            }
                        }
                    }
                }
            });
        }

        btnEndGame = (Button)findViewById(R.id.btnEnd);
        btnEndGame.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                AlertDialog.Builder builder = new AlertDialog.Builder(SecondActivity.this);
                builder.setTitle("End game confirmation");
                builder.setMessage("Are you sure you want to end this game?").setCancelable(false).setPositiveButton("Yes", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        AlertDialog.Builder builder = new AlertDialog.Builder(SecondActivity.this);
                        builder.setTitle("Winner announcement");
                        if(scorePlayer1 == scorePlayer2){
                            builder.setMessage("The game ends in a tie.").setCancelable(false).setPositiveButton("OK", new DialogInterface.OnClickListener() {
                                @Override
                                public void onClick(DialogInterface dialog, int which) {
                                    finish();
                                }
                            });
                        } else{
                            String winnerName = scorePlayer1 > scorePlayer2 ? tvNamePlayer1.getText().toString() : tvNamePlayer2.getText().toString();
                            builder.setMessage(winnerName.substring(0, winnerName.length() - 1) + " won this game!").setCancelable(false).setPositiveButton("OK", new DialogInterface.OnClickListener() {
                                @Override
                                public void onClick(DialogInterface dialog, int which) {
                                    finish();
                                }
                            });
                        }
                        AlertDialog alertDialog = builder.create();
                        alertDialog.show();
                        alertDialog.getButton(AlertDialog.BUTTON_POSITIVE);
                        int width = (int)(getResources().getDisplayMetrics().widthPixels * 0.75);
                        int height = (int)(getResources().getDisplayMetrics().heightPixels * 0.25);
                        alertDialog.getWindow().setLayout(width, height);
                    }
                }).setNegativeButton("No", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        dialog.cancel();
                    }
                });
                AlertDialog alertDialog = builder.create();
                alertDialog.show();
                alertDialog.getButton(AlertDialog.BUTTON_POSITIVE);
                alertDialog.getButton(AlertDialog.BUTTON_NEGATIVE);
                int width = (int)(getResources().getDisplayMetrics().widthPixels * 0.75);
                int height = (int)(getResources().getDisplayMetrics().heightPixels * 0.3);
                alertDialog.getWindow().setLayout(width, height);
            }
        });
    }

    @Override
    public void onBackPressed() {
        AlertDialog.Builder builder = new AlertDialog.Builder(SecondActivity.this);
        builder.setTitle("End game confirmation");
        builder.setMessage("Are you sure you want to end this game?").setCancelable(false).setPositiveButton("Yes", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {
                AlertDialog.Builder builder = new AlertDialog.Builder(SecondActivity.this);
                builder.setTitle("Winner announcement");
                if(scorePlayer1 == scorePlayer2){
                    builder.setMessage("The game ends in a tie.").setCancelable(false).setPositiveButton("OK", new DialogInterface.OnClickListener() {
                        @Override
                        public void onClick(DialogInterface dialog, int which) {
                            finish();
                        }
                    });
                } else{
                    String winnerName = scorePlayer1 > scorePlayer2 ? tvNamePlayer1.getText().toString() : tvNamePlayer2.getText().toString();
                    builder.setMessage(winnerName.substring(0, winnerName.length() - 1) + " won this game!").setCancelable(false).setPositiveButton("OK", new DialogInterface.OnClickListener() {
                        @Override
                        public void onClick(DialogInterface dialog, int which) {
                            finish();
                        }
                    });
                }
                AlertDialog alertDialog = builder.create();
                alertDialog.show();
                alertDialog.getButton(AlertDialog.BUTTON_POSITIVE);
                int width = (int)(getResources().getDisplayMetrics().widthPixels * 0.75);
                int height = (int)(getResources().getDisplayMetrics().heightPixels * 0.25);
                alertDialog.getWindow().setLayout(width, height);
            }
        }).setNegativeButton("No", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {
                dialog.cancel();
            }
        });
        AlertDialog alertDialog = builder.create();
        alertDialog.show();
        alertDialog.getButton(AlertDialog.BUTTON_POSITIVE);
        alertDialog.getButton(AlertDialog.BUTTON_NEGATIVE);
        int width = (int)(getResources().getDisplayMetrics().widthPixels * 0.75);
        int height = (int)(getResources().getDisplayMetrics().heightPixels * 0.3);
        alertDialog.getWindow().setLayout(width, height);
    }

    String check(){
        // check rows
        if(!btn00.getText().toString().isEmpty() && btn00.getText().toString().equals(btn01.getText().toString()) && btn00.getText().toString().equals(btn02.getText().toString())){
            return btn00.getText().toString();
        }
        if(!btn10.getText().toString().isEmpty() && btn10.getText().toString().equals(btn11.getText().toString()) && btn10.getText().toString().equals(btn12.getText().toString())){
            return btn10.getText().toString();
        }
        if(!btn20.getText().toString().isEmpty() && btn20.getText().toString().equals(btn21.getText().toString()) && btn20.getText().toString().equals(btn22.getText().toString())){
            return btn20.getText().toString();
        }

        // check columns
        if(!btn00.getText().toString().isEmpty() && btn00.getText().toString().equals(btn10.getText().toString()) && btn00.getText().toString().equals(btn20.getText().toString())){
            return btn00.getText().toString();
        }
        if(!btn01.getText().toString().isEmpty() && btn01.getText().toString().equals(btn11.getText().toString()) && btn01.getText().toString().equals(btn21.getText().toString())){
            return btn01.getText().toString();
        }
        if(!btn02.getText().toString().isEmpty() && btn02.getText().toString().equals(btn12.getText().toString()) && btn02.getText().toString().equals(btn22.getText().toString())){
            return btn02.getText().toString();
        }

        // check diagonals
        if(!btn00.getText().toString().isEmpty() && btn00.getText().toString().equals(btn11.getText().toString()) && btn00.getText().toString().equals(btn22.getText().toString())){
            return btn00.getText().toString();
        }
        if(!btn02.getText().toString().isEmpty() && btn02.getText().toString().equals(btn11.getText().toString()) && btn02.getText().toString().equals(btn20.getText().toString())){
            return btn02.getText().toString();
        }
        return null;
    }

    void restart(){
        for (Button b : buttonList) {
            b.setText("");
        }
        tvRound.setText("Round " + ++round);
        move = 0;
    }
}