package testeplugin;

import android.content.Context;
import android.content.Intent;
import android.widget.FrameLayout;
import android.view.ViewGroup;

import org.apache.cordova.CordovaPlugin;
import org.apache.cordova.CallbackContext;
import org.apache.cordova.CordovaWebView;
import org.apache.cordova.CordovaInterface;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import com.bitmovin.player.PlayerView;
import com.bitmovin.player.api.Player;
import com.bitmovin.player.api.source.SourceConfig;

/**
 * This class echoes a string called from JavaScript.
 */
public class testeplugin extends CordovaPlugin {

    private PlayerView playerView;
    private Player player;

    protected void initializePlayer() {
        player = Player.create(cordova.getActivity());
        playerView.setPlayer(player);

        // load source using a source config
        player.load(SourceConfig.fromUrl("https://bitdash-a.akamaihd.net/content/sintel/sintel.mpd"));
    }

    @Override
    public void initialize(CordovaInterface cordova, CordovaWebView webView) {

        FrameLayout layout = (FrameLayout) webView.getView().getParent();

        playerView = new PlayerView(layout.getContext());

        FrameLayout.LayoutParams params = new FrameLayout.LayoutParams(ViewGroup.LayoutParams.MATCH_PARENT, 500);
        params.setMargins(0, 50, 0, 0);
        playerView.setLayoutParams(params);

        layout.addView(playerView);

        initializePlayer();
    }

    @Override
    public boolean execute(String action, JSONArray args, CallbackContext callbackContext) throws JSONException {
        Context context = cordova.getActivity().getApplicationContext();
        System.out.println("GDEBUG: " + action);
        if (action.equals("new_activity")) {
            this.openNewActivity(context);
            return true;
        }
        return false;
    }

    private void openNewActivity(Context context) {
        System.out.println("GDEBUG: openNewActivity end");
        Intent intent = new Intent(context, PlayerActivity.class);
        this.cordova.getActivity().startActivity(intent);
        System.out.println("GDEBUG: openNewActivity end");
    }

    private void coolMethod(String message, CallbackContext callbackContext) {
        if (message != null && message.length() > 0) {
            callbackContext.success(message);
        } else {
            callbackContext.error("Expected one non-empty string argument.");
        }
    }
}
