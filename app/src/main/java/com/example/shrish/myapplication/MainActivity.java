package com.example.shrish.myapplication;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.GridLayout;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

public class MainActivity extends AppCompatActivity {
    //1=green  0 =red
    int activePlayer=1;boolean gameIsActive =true;int count=0;
    int gameState [] ={2,2,2,2,2,2,2,2,2};
    int winningPositions [][]={{0,1,2},{3,4,5},{6,7,8},{0,3,6},{1,4,7},{2,5,8},{0,4,8},{2,4,6}};



    public void dropIn(View view)
    {
        ImageView counter =(ImageView) view ;
        TextView txt =(TextView)findViewById(R.id.winner1);
        LinearLayout layout = (LinearLayout)findViewById(R.id.winner);
        //understand
        int tappedcounter = Integer.parseInt(counter.getTag().toString());
        if(gameState[tappedcounter]==2 && gameIsActive) {
            if (activePlayer == 1) {
                counter.setImageResource(R.drawable.circle1);
                activePlayer = 0;count++;
                gameState[tappedcounter]=1;
            } else {
                counter.setImageResource(R.drawable.circle2);
                activePlayer = 1;count++;
                gameState[tappedcounter]=0;
            }
            counter.setTranslationY(-1000f);
            counter.animate().translationYBy(1000f).rotationY(1800).setDuration(1000);

            for (int [] winningposition : winningPositions )
            {
                if (gameState[winningposition[0]]==gameState[winningposition[1]] && gameState[winningposition[1]]==gameState[winningposition[2]] &&gameState[winningposition[0]]!=2)
                {



                    if(gameState[winningposition[0]]==0)
                    txt.setText("Red Player Wins");
                    else if (gameState[winningposition[0]]==1)
                        txt.setText("Green Player Wins");
                    layout.setVisibility(View.VISIBLE);
                    gameIsActive = false;


                }
            }
        }

        if (gameIsActive && count==9) {
            txt.setText("DRAW");
            layout.setVisibility(View.VISIBLE);
            gameIsActive=false;
        }



    }
    public void playAgain(View view)
    {
        activePlayer=1;
        gameIsActive=true;


        LinearLayout linearLayout = (LinearLayout)findViewById(R.id.winner);
        GridLayout gridLayout = (GridLayout)findViewById(R.id.gridLayout);
        for (int i =0;i<gameState.length;i++)
        {
            gameState[i] =2;
        }
        linearLayout.setVisibility(View.INVISIBLE);
        for (int i=0;i<gridLayout.getChildCount();i++)
        {
            ((ImageView)gridLayout.getChildAt(i)).setImageResource(0);//p t n
        }
    }
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
    }
}
