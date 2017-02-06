package phoenix.delta;

import android.content.Intent;
import android.content.pm.ActivityInfo;
import android.os.Bundle;
import android.support.v7.app.ActionBarActivity;
import android.view.View;
import android.widget.Button;

import droidrunjump.DroidRunJumpActivity;


public class GameSelection extends ActionBarActivity {

    Button playGame1Btn, playGame2Btn, playGame3Btn, playGame4Btn, continue_btn;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setRequestedOrientation(ActivityInfo.SCREEN_ORIENTATION_LANDSCAPE);
        setContentView(R.layout.activity_game_selection);

        Intent thisIntent = getIntent();
        final Procedure currProcedure = (Procedure) thisIntent.getSerializableExtra("PROCEDURE");

        playGame1Btn = (Button) findViewById(R.id.play_game1_btn);
        playGame1Btn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                currProcedure.startNewSession();
                currProcedure.currentSession.selectedGame = DroidRunJumpActivity.class;
                Intent game = new Intent(GameSelection.this, TrialMain.class);
                game.putExtra("PROCEDURE", currProcedure);
                startActivity(game);

            }
        });

        playGame2Btn = (Button) findViewById(R.id.play_game2_btn);
        playGame2Btn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                currProcedure.startNewSession();
                currProcedure.currentSession.selectedGame = dev.emmaguy.fruitninja.ui.MainActivity.class;
                Intent game = new Intent(GameSelection.this, TrialMain.class);
                game.putExtra("PROCEDURE", currProcedure);
                startActivity(game);
            }
        });

        playGame3Btn = (Button) findViewById(R.id.play_game3_btn);
        playGame3Btn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                currProcedure.startNewSession();
                currProcedure.currentSession.selectedGame = DroidRunJumpActivity.class;
                Intent game = new Intent(GameSelection.this, TrialMain.class);
                game.putExtra("PROCEDURE", currProcedure);
                startActivity(game);

            }
        });

        playGame4Btn = (Button) findViewById(R.id.play_game4_btn);
        playGame4Btn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                currProcedure.startNewSession();
                currProcedure.currentSession.selectedGame = DroidRunJumpActivity.class;
                Intent game = new Intent(GameSelection.this, TrialMain.class);
                game.putExtra("PROCEDURE", currProcedure);
                startActivity(game);

            }
        });

        continue_btn = (Button)findViewById(R.id.btn_continue);
        continue_btn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                currProcedure.startNewSession();
                currProcedure.currentSession.selectedGame = DroidRunJumpActivity.class;
                Intent game = new Intent(GameSelection.this, TrialMain.class);
                game.putExtra("PROCEDURE", currProcedure);
                startActivity(game);
            }
        });

    }

}

