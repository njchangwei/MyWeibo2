package saiberler.drupem.com.myweibo;

import android.os.Bundle;
import android.os.Handler;
import android.os.Message;

import com.sina.weibo.sdk.auth.AccessTokenKeeper;
import com.sina.weibo.sdk.auth.Oauth2AccessToken;

/**
 * Created by Administrator on 2017/7/11.
 */

public class SplashActivity extends BaseActivity {
    private Oauth2AccessToken oauth2AccessToken;
    private static final int WHAT_INTENT2LOGIN = 1;
    private static final int WHAT_INTENT2MAIN = 2;
    private static final long SPLASH_DUR_TIME = 1000;

    private Handler handler  = new Handler(){
        @Override
        public void handleMessage(Message msg) {
            super.handleMessage(msg);
            switch (msg.what){
                case WHAT_INTENT2LOGIN:

                    intent2Activity(LoginActivity.class);
                    finish();

                    break;
                case WHAT_INTENT2MAIN:

                    intent2Activity(MainActivity.class);
                    finish();

                    break;

            }
        }
    };

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_splash);
        oauth2AccessToken = AccessTokenKeeper.readAccessToken(this);
        if(oauth2AccessToken.isSessionValid()){
            handler.sendEmptyMessageDelayed(WHAT_INTENT2MAIN,SPLASH_DUR_TIME);
        }else{
            handler.sendEmptyMessageDelayed(WHAT_INTENT2LOGIN,SPLASH_DUR_TIME);
        }

    }
}
