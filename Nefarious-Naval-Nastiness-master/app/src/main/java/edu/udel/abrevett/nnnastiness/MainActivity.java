package edu.udel.abrevett.nnnastiness;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

public class MainActivity extends AppCompatActivity{

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setTitle("Nefarious Naval Nastiness");
        setContentView(R.layout.activity_main);

        //Set Button and ImageView Listeners
        final Intent intent = new Intent(this, GameBoardActivity.class);
        intent.putExtra("Player1",'X');
        intent.putExtra("Player2",'Y');
        //startGame button
        Button startGame = findViewById(R.id.btnStartGame);
        startGame.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                startActivity(intent);
            }
        });
    }
}