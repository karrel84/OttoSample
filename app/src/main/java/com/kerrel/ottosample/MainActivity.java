package com.kerrel.ottosample;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;

import com.squareup.otto.Subscribe;

/**
 * otto 이벤트 버스의 샘플이다.
 */
public class MainActivity extends AppCompatActivity {


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        // 해당 오브젝트를 이벤트 버스로 등록한다.
        BusProvider.getInstance().register(this);

        // 버스 이벤트
        findViewById(R.id.otto).setOnClickListener(onOttoEventListener);
        findViewById(R.id.otto2).setOnClickListener(onOttoEventListener2);

    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        // 이벤트를 해제해준다.
        BusProvider.getInstance().unregister(this);
    }

    /**
     * 이벤트 발행
     */
    private View.OnClickListener onOttoEventListener = new View.OnClickListener() {
        @Override
        public void onClick(View v) {
            BusProvider.getInstance().post(new PushEvent("onOttoEventListener"));
        }
    };

    /**
     * 이벤트 발행
     */
    private View.OnClickListener onOttoEventListener2 = new View.OnClickListener() {
        @Override
        public void onClick(View v) {
            BusProvider.getInstance().post(new PushEvent2("onOttoEventListener"));
        }
    };

    /**
     * @Subscribe 어노테이션만 붙이면 어떤 메소드든 이벤트를 받을 수 있다.
     * 이벤트의 종류를 구분하는건 전달되는 클래스의 타입이다.
     */
    @Subscribe
    public void event(PushEvent pushEvent) {
        // 이벤트가 발생한뒤 수행할 작업

        Log.e("TAG", "FnishLoad : " + pushEvent.msg);
    }

    @Subscribe
    public void event(PushEvent2 pushEvent) {
        // 이벤트가 발생한뒤 수행할 작업

        Log.e("TAG", "FnishLoad2 : " + pushEvent.msg);
    }


    public class PushEvent {
        private String msg;

        public PushEvent(String msg) {
            this.msg = msg;
        }
    }

    public class PushEvent2 {
        private String msg;

        public PushEvent2(String msg) {
            this.msg = msg;
        }
    }
}
