package wscube.com.firstapp;

import android.content.Context;
import android.content.Intent;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.squareup.picasso.Picasso;

import java.util.ArrayList;

/**
 * Created by wscube on 2/1/17.
 */

public class NewsRecyclerAdapter extends RecyclerView.Adapter<NewsRecyclerAdapter.ViewHolder> {
    ArrayList<NewsModel> newsModel = new ArrayList<NewsModel>();
    Context context;

    public NewsRecyclerAdapter(ArrayList<NewsModel> newsModel, Context applicationContext) {
        this.newsModel = newsModel;
        this.context = applicationContext;
    }

    @Override
    public NewsRecyclerAdapter.ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {

        View v = LayoutInflater.from(parent.getContext()).inflate(R.layout.news_content, parent, false);
        ViewHolder vh = new ViewHolder(v);
        return vh;
    }

    @Override
    public void onBindViewHolder(NewsRecyclerAdapter.ViewHolder holder, int position) {
        holder.txtTitle.setText(newsModel.get(position).getTitle());
        holder.txtDate.setText(newsModel.get(position).getDate());
        holder.txtDes.setText(newsModel.get(position).getDescription());
        Picasso.with(context).load(newsModel.get(position).getUrl()).into(holder.imgNews);
    }

    @Override
    public int getItemCount() {
        return newsModel.size();
    }

    public class ViewHolder extends RecyclerView.ViewHolder implements View.OnClickListener {
        TextView txtTitle, txtDate, txtDes;
        ImageView imgNews;
        RelativeLayout relMain;

        public ViewHolder(View itemView) {
            super(itemView);
            txtTitle = (TextView) itemView.findViewById(R.id.txtTitle);
            txtDate = (TextView) itemView.findViewById(R.id.txtDate);
            txtDes = (TextView) itemView.findViewById(R.id.txtDetail);
            imgNews = (ImageView) itemView.findViewById(R.id.imgNews);
            relMain = (RelativeLayout) itemView.findViewById(R.id.relMain);
            relMain.setOnClickListener(this);
        }

        @Override
        public void onClick(View view) {
            switch (view.getId()) {
                case R.id.relMain:
                    NewsModel model = newsModel.get(getAdapterPosition());
                    Intent intent = new Intent(context, News_Details.class);
                    intent.putExtra("newsModel", model);
                    intent.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
                    context.startActivity(intent);
                    break;
            }
        }
    }
}
