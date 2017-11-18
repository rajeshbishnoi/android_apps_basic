package wscube.com.firstapp;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import java.util.ArrayList;

/**
 * Created by wscube on 31/12/16.
 */
public class NewsListAdapter extends BaseAdapter{

    ArrayList<Bag> bg=new ArrayList<Bag>();
    Context c;
    public NewsListAdapter(Context c, ArrayList<Bag> bg)
    {
            this.bg=bg;
            this.c=c;
    }
    @Override
    public int getCount() {

        return bg.size();
    }

    @Override
    public Object getItem(int i) {
        return null;
    }

    @Override
    public long getItemId(int i) {
        return 0;
    }

    @Override
    public View getView(int i, View view, ViewGroup viewGroup) {

        View v= LayoutInflater.from(c).inflate(R.layout.row,viewGroup,false);
        TextView title,des,date;
        ImageView imgNews;
        title= (TextView) v.findViewById(R.id.title);
        des=(TextView)v.findViewById(R.id.des);
        date= (TextView) v.findViewById(R.id.date);
        imgNews=(ImageView)v.findViewById(R.id.imgNews);
        title.setText(bg.get(i).getTitle());
        des.setText(bg.get(i).getDes());
        date.setText(bg.get(i).getDate());
        imgNews.setImageBitmap(bg.get(i).getBmpImgNews());
        return v;
    }
}
