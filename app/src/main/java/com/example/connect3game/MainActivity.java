package com.example.connect3game;

import androidx.appcompat.app.AppCompatActivity;

import android.media.Image;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.GridLayout;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import org.w3c.dom.Text;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
    }


    private int[] gameState = {2,2,2,2,2,2,2,2,2,2};
    private int[][] winningPositions = {{0,1,2}, {3,4,5}, {6,7,8}, {0,3,6}, {1,4,7}, {2,5,8}, {0,4,8}, {2,4,6}};
//    0:red 1:yellow 2:white
    private int chance = 0,cnt = 10;
    private boolean gameOver = false;
    public void dropin(View view) {
        ImageView counter = (ImageView) view;
        int tag = Integer.parseInt(counter.getTag().toString());
        if(gameState[tag] == 2 && !gameOver){
            gameState[tag] = chance;
            counter.setTranslationY(-1500);
            if(chance == 0) {
                counter.setImageResource(R.drawable.red);
                chance = 1;
            }
            else {
                counter.setImageResource(R.drawable.yellow);
                chance = 0;
            }
            counter.animate().translationYBy(1500).setDuration(300);

            for(int[] winningPosition:winningPositions){

                if(gameState[winningPosition[0]] == gameState[winningPosition[1]] && gameState[winningPosition[1]] == gameState[winningPosition[2]] && gameState[winningPosition[0]] != 2){
                    String winner = "";
                    if(chance == 0) winner = "Yellow";
                    else winner = "Red";
                    Toast.makeText(this,winner +" has won",Toast.LENGTH_SHORT).show();
                    gameOver = true;

                    TextView winning = (TextView) findViewById(R.id.winnerDeclaration);
                    Button replay = (Button) findViewById(R.id.replay);
                    winning.setText(winner + " is the winner!");
                    winning.setVisibility(View.VISIBLE);
                    replay.setVisibility(View.VISIBLE);
                }
            }
            for(int i=0;i<10;i++){
                if(gameState[i] != 2) cnt--;
            }
            if(cnt == 1 && !gameOver){
                TextView winning = (TextView) findViewById(R.id.winnerDeclaration);
                Button replay = (Button) findViewById(R.id.replay);
                winning.setText( "It's a tie!");
                winning.setVisibility(View.VISIBLE);
                replay.setVisibility(View.VISIBLE);
            }
            cnt = 10;
        }
    }

    public void playagain(View view) {
//        make textview and button invisible
        TextView winning = (TextView) findViewById(R.id.winnerDeclaration);
        Button replay = (Button) findViewById(R.id.replay);
        winning.setVisibility(View.INVISIBLE);
        replay.setVisibility(View.INVISIBLE);
//        set interface with no red and yellow
        androidx.gridlayout.widget.GridLayout gridLayout = (androidx.gridlayout.widget.GridLayout) findViewById(R.id.gridLayout);
        for(int i=0;i< gridLayout.getChildCount();i++){
            ImageView image = (ImageView) gridLayout.getChildAt(i);
            image.setImageDrawable(null);
        }
        for(int i=0;i<10;i++){
            gameState[i] = 2;
        }
        chance = 0;
        gameOver = false;
    }
}
