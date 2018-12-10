package finalproject.cryptotracker;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.MotionEvent;
import android.view.View;
import android.view.ViewGroup;
import android.view.Window;
import android.view.WindowManager;
import java.util.List;
import java.util.ArrayList;

public class MainActivity extends AppCompatActivity {
    private float touchX1,touchX2;
    private final int minSwipeDistance = 500;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        Window window = getWindow();
        window.setFlags(WindowManager.LayoutParams.FLAG_LAYOUT_NO_LIMITS,
                WindowManager.LayoutParams.FLAG_LAYOUT_NO_LIMITS);
        window.getDecorView().setSystemUiVisibility(View.SYSTEM_UI_FLAG_LAYOUT_FULLSCREEN);

        RecyclerView recyclerView = findViewById(R.id.coinRecycler);
        List<CoinItem> coins = new ArrayList<>();
        // Aggregate coins here
        coins.add(new CoinItem("BTC", 2000.12, "3.64"));
        CoinAdapter adapter = new CoinAdapter(this, coins);
        recyclerView.setAdapter(adapter);
        recyclerView.setLayoutManager(new LinearLayoutManager(this));
    }

    @Override
    public boolean onTouchEvent(MotionEvent event) {
        switch(event.getAction()) {
            case MotionEvent.ACTION_DOWN:
                touchX1 = event.getX();
                break;
            case MotionEvent.ACTION_UP:
                touchX2 = event.getX();
                float deltaX = touchX2 - touchX1;
                if (Math.abs(deltaX) > minSwipeDistance) {
                    startActivity(new Intent(MainActivity.this, NewsActivity.class));
                }
                break;
        }
        return super.onTouchEvent(event);
    }
}
