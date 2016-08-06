package com.example.jiang.daojishi;

import android.app.Activity;
import android.app.Dialog;
import android.content.SharedPreferences;
import android.net.Uri;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.ButtonBarLayout;
import android.view.Display;
import android.view.View;
import android.view.Window;
import android.view.WindowManager;
import android.widget.BaseAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ListView;
import android.widget.TextView;

import com.google.android.gms.appindexing.Action;
import com.google.android.gms.appindexing.AppIndex;
import com.google.android.gms.common.api.GoogleApiClient;

import java.util.ArrayList;

public class MainActivity extends AppCompatActivity {

    private EditText editText;
    private ArrayList arrayList;
    private BaseAdapter baseAdapter;
    private SharedPreferences events;
    private int number = 0;

    /**
     * ATTENTION: This was auto-generated to implement the App Indexing API.
     * See https://g.co/AppIndexing/AndroidStudio for more information.
     */
    private GoogleApiClient client;
    //SharedPreferences texts = getSharedPreferences("texts", 0);


/*
    private View.OnClickListener mClickListener;
    Activity context;
    public MainActivity(Activity context) {
        super(context);
        this.context = context;
    }
    public MainActivity(Activity context, int theme, View.OnClickListener clickListener) {
        super(context, theme);
        this.context = context;
        this.mClickListener = clickListener;
    }
*/

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        Button button = (Button) findViewById(R.id.buttondaojishi);
        Button btntianjia = (Button) findViewById(R.id.button1tianjia);
        ListView listView = (ListView) findViewById(R.id.listView);
        final TextView textView = (TextView) findViewById(R.id.textView);
        final EditText editText = (EditText) findViewById(R.id.editText);
        events = getSharedPreferences("events", 0);
        arrayList = Unforgetlist();
        /*
        Button btn_save = (Button) findViewById(R.id.btn_save);
        EditText text_time1 = (EditText) findViewById(R.id.text_time1);
        EditText text_time2 = (EditText) findViewById(R.id.text_time2);
        EditText text_event = (EditText) findViewById(R.id.text_event);

        Window dialogWindow = this.getWindow();
        WindowManager m = context.getWindowManager();
        Display d = m.getDefaultDisplay(); // 获取屏幕宽、高用
        WindowManager.LayoutParams p = dialogWindow.getAttributes(); // 获取对话框当前的参数值
        // p.height = (int) (d.getHeight() * 0.6); // 高度设置为屏幕的0.6
        p.width = (int) (d.getWidth() * 0.8); // 宽度设置为屏幕的0.8
        dialogWindow.setAttributes(p);
        // 为按钮绑定点击事件监听器
        btn_save.setOnClickListener(mClickListener);

        this.setCancelable(true);
*/


        button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                new AlertDialog.Builder(MainActivity.this)
                        .setTitle("请输入")
                        .setIcon(android.R.drawable.ic_dialog_info)
                        .setView(new EditText(MainActivity.this))
                        .setPositiveButton("确定", null)
                        .setNegativeButton("取消", null)
                        .show();

            }
        });


        btntianjia.setOnClickListener(new View.OnClickListener(){

            @Override
            public void onClick(View v) {

                SharedPreferences events = getSharedPreferences("events", 0);
                String unforget = editText.getText().toString();
                textView.setText(unforget);
                number = events.getInt("number", 0);
                SharedPreferences.Editor editor = events.edit();
                number++;
                editor.putString("text" + number, unforget);
                editor.remove("number");
                editor.putInt("number", number);
                editor.commit();
                arrayList.clear();
                arrayList.addAll(Unforgetlist());
                baseAdapter.notifyDataSetChanged();
            }
        });



        /*public class MyListView extends ListView implements Runnable {
            private float mLastDownY = 0f;
            private int mDistance = 0;
            private int mStep = 10;
            private boolean mPositive = false;
            @Override
            public boolean onTouchEvent(MotionEvent event) {
                switch (event.getAction()) {
                    case MotionEvent.ACTION_DOWN:
                        if (mLastDownY == 0f && mDistance == 0) {
                            mLastDownY = event.getY();
                            return true;
                        }
                        break;
                    case MotionEvent.ACTION_CANCEL:
                        break;
                    case MotionEvent.ACTION_UP:
                        if (mDistance != 0) {
                            mStep = 1;
                            mPositive = (mDistance >= 0);
                            this.post(this);
                            return true;
                        }
                        mLastDownY = 0f;
                        mDistance = 0;
                        break;
                    case MotionEvent.ACTION_MOVE:
                        if (mLastDownY != 0f) {
                            mDistance = (int) (mLastDownY - event.getY());
                            if ((mDistance < 0 && getFirstVisiblePosition() == 0 && getChildAt(0).getTop() == 0) || (mDistance > 0 && getLastVisiblePosition() == getCount() - 1)) {
                                mDistance /= 2;
                                scrollTo(0, mDistance);
                                return true;
                            }
                        }
                        mDistance = 0;
                        break;
                }
                return super.onTouchEvent(event);
            }
            public void run() {
                mDistance += mDistance > 0 ? -mStep : mStep;
                scrollTo(0, mDistance);
                if ((mPositive && mDistance <= 0) || (!mPositive && mDistance >= 0)) {
                    scrollTo(0, 0);
                    mDistance = 0;
                    mLastDownY = 0f;
                    return;
                }
                mStep += 1;
                this.postDelayed(this, 10);
            }
        }*/

        // ATTENTION: This was auto-generated to implement the App Indexing API.
        // See https://g.co/AppIndexing/AndroidStudio for more information.
        client = new GoogleApiClient.Builder(this).addApi(AppIndex.API).build();
    }



    private ArrayList Unforgetlist() {
        ArrayList ufgetlist = new ArrayList<>();
        number = events.getInt("number", 0);
        for (int i = 0; i < number; i++) {
            String unforgetlist = events.getString("text" + i + 1, "");
            ufgetlist.add(unforgetlist);
        }
        return ufgetlist;
    }

   
    @Override
    public void onStart() {
        super.onStart();

        // ATTENTION: This was auto-generated to implement the App Indexing API.
        // See https://g.co/AppIndexing/AndroidStudio for more information.
        client.connect();
        Action viewAction = Action.newAction(
                Action.TYPE_VIEW, // TODO: choose an action type.
                "Main Page", // TODO: Define a title for the content shown.
                // TODO: If you have web page content that matches this app activity's content,
                // make sure this auto-generated web page URL is correct.
                // Otherwise, set the URL to null.
                Uri.parse("http://host/path"),
                // TODO: Make sure this auto-generated app URL is correct.
                Uri.parse("android-app://com.example.jiang.daojishi/http/host/path")
        );
        AppIndex.AppIndexApi.start(client, viewAction);
    }

    @Override
    public void onStop() {
        super.onStop();

        // ATTENTION: This was auto-generated to implement the App Indexing API.
        // See https://g.co/AppIndexing/AndroidStudio for more information.
        Action viewAction = Action.newAction(
                Action.TYPE_VIEW, // TODO: choose an action type.
                "Main Page", // TODO: Define a title for the content shown.
                // TODO: If you have web page content that matches this app activity's content,
                // make sure this auto-generated web page URL is correct.
                // Otherwise, set the URL to null.
                Uri.parse("http://host/path"),
                // TODO: Make sure this auto-generated app URL is correct.
                Uri.parse("android-app://com.example.jiang.daojishi/http/host/path")
        );
        AppIndex.AppIndexApi.end(client, viewAction);
        client.disconnect();
    }


}

public class CreateUserDialog extends Dialog {

    Activity context;

    private View.OnClickListener mClickListener;

    public CreateUserDialog(Activity context) {
        super(context);
        this.context = context;
    }

    public CreateUserDialog(Activity context, int theme, View.OnClickListener clickListener) {
        super(context, theme);
        this.context = context;
        this.mClickListener = clickListener;
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        this.setContentView(R.layout.activity_main);

        Button btn_save = (Button) findViewById(R.id.btn_save);
        EditText text_time1 = (EditText) findViewById(R.id.text_time1);
        EditText text_time2 = (EditText) findViewById(R.id.text_time2);
        EditText text_event = (EditText) findViewById(R.id.text_event);

        Window dialogWindow = this.getWindow();
        WindowManager m = context.getWindowManager();
        Display d = m.getDefaultDisplay(); // 获取屏幕宽、高用
        WindowManager.LayoutParams p = dialogWindow.getAttributes(); // 获取对话框当前的参数值
        // p.height = (int) (d.getHeight() * 0.6); // 高度设置为屏幕的0.6
        p.width = (int) (d.getWidth() * 0.8); // 宽度设置为屏幕的0.8
        dialogWindow.setAttributes(p);

        btn_save.setOnClickListener(mClickListener);

        this.setCancelable(true);
    }

    private View.OnClickListener onClickListener = new View.OnClickListener() {
        @Override
        public void onClick(View v) {

            switch (v.getId()) {
                case R.id.btn_save:

                    String time1 = CreateUserDialog.text_time1.getText().toString().trim();
                    String time2 = createUserDialog.text_time2.getText().toString().trim();
                    String event = createUserDialog.text_event.getText().toString().trim();

                    System.out.println(time1 + "——" + time2 + "——" + event);
                    break;
            }
        }
    };
}

