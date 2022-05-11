package testeplugin;

import android.os.Bundle;

//import androidx.appcompat.app.AppCompatActivity;
import android.app.Activity;

import com.bitmovin.player.PlayerView;
import com.bitmovin.player.api.Player;
import com.bitmovin.player.api.source.SourceConfig;

public class PlayerActivity extends Activity {
    private PlayerView playerView;
    private Player player;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        String package_name = getApplication().getPackageName();
        setContentView(getApplication().getResources().getIdentifier("activity_player", "layout", package_name));

        // playerView = findViewById(R.id.bitmovinPlayerView);
        int playerViewId = getApplication().getResources().getIdentifier("bitmovinPlayerView", "id",
                package_name);
        playerView = findViewById(playerViewId);

        initializePlayer();
    }

    @Override
    protected void onStart() {
        playerView.onStart();
        super.onStart();
    }

    @Override
    protected void onResume() {
        super.onResume();
        playerView.onResume();
    }

    @Override
    protected void onPause() {
        playerView.onPause();
        super.onPause();
    }

    @Override
    protected void onStop() {
        playerView.onStop();
        super.onStop();
    }

    @Override
    protected void onDestroy() {
        playerView.onDestroy();
        super.onDestroy();
    }

    protected void initializePlayer() {
        player = Player.create(this);
        playerView.setPlayer(player);

        // load source using a source config
        player.load(SourceConfig.fromUrl("https://bitdash-a.akamaihd.net/content/sintel/sintel.mpd"));
    }
}