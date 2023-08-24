package games.tictactoe;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

public class MainActivity extends AppCompatActivity {
    Button btnStartGame;
    EditText editTextPlayer1;
    EditText editTextPlayer2;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        btnStartGame = (Button)findViewById(R.id.btnStart);
        editTextPlayer1 = (EditText)findViewById(R.id.etPlayer1);
        editTextPlayer2 = (EditText)findViewById(R.id.etPlayer2);

        btnStartGame.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String namePlayer1 = editTextPlayer1.getText().toString();
                String namePlayer2 = editTextPlayer2.getText().toString();

                if(!namePlayer1.isEmpty() && !namePlayer2.isEmpty()){
                    Intent intent = new Intent(MainActivity.this, SecondActivity.class);
                    intent.putExtra("namePlayer1", namePlayer1);
                    intent.putExtra("namePlayer2", namePlayer2);
                    editTextPlayer1.setText("");
                    editTextPlayer2.setText("");
                    startActivity(intent);
                } else{
                    Toast.makeText(MainActivity.this, "Please enter player names!", Toast.LENGTH_SHORT).show();
                }
            }
        });
    }
}