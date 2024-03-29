package finalproject.cryptotracker;

import android.content.Context;
import android.content.res.Resources;
import android.graphics.Color;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import java.util.List;

public class CoinAdapter extends RecyclerView.Adapter<CoinAdapter.coinViewHolder> {
    private Context context;
    private List<CoinItem> coins;

    public CoinAdapter(Context context, List<CoinItem> coins) {
        this.context = context;
        this.coins = coins;
    }

    @Override
    public coinViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        LayoutInflater inflater = LayoutInflater.from(context);
        View view = inflater.inflate(R.layout.card_item, parent, false);
        return new coinViewHolder(view);
    }

    @Override
    public void onBindViewHolder(coinViewHolder holder, int position) {
        CoinItem coin = coins.get(position);
        holder.coinTicker.setText(coin.getTicker());
        holder.coinValue.setText(coin.getCurrentPrice());
        holder.percentChange.setText(coin.getPercentChange());

        Resources resources = context.getResources();
        int resource = resources.getIdentifier(coin.getTicker().toLowerCase(), "drawable",
                context.getPackageName());
        holder.coinPicture.setImageResource(resource);

        int color = Color.GREEN;
        if (coin.getPercentChange().contains("-")) {
            color = Color.RED;
        }
        holder.percentChange.setTextColor(color);
    }

    @Override
    public int getItemCount() {
        return coins.size();
    }

    public class coinViewHolder extends RecyclerView.ViewHolder {
        private ImageView coinPicture;
        private TextView coinTicker;
        private TextView coinValue;
        private TextView percentChange;

        public coinViewHolder(View itemView) {
            super(itemView);
            coinPicture = itemView.findViewById(R.id.coinPicture);
            coinTicker = itemView.findViewById(R.id.coinTicker);
            coinValue = itemView.findViewById(R.id.coinValue);
            percentChange = itemView.findViewById(R.id.percentChange);
        }
    }
}
