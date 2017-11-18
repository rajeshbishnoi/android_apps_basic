package wscube.com.firstapp;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import java.util.ArrayList;

/**
 * Created by wscube on 5/1/17.
 */
public class NavigationAdapter extends RecyclerView.Adapter<NavigationAdapter.ViewHolder>
{
    Context c;
    ArrayList<NavDrawerItem> arrItem;
    public NavigationAdapter()
    {

    }
    public NavigationAdapter(Context c, ArrayList<NavDrawerItem> arrItem)
    {
        this.c=c;
        this.arrItem=arrItem;
    }
    @Override
    public NavigationAdapter.ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view= LayoutInflater.from(c).inflate(R.layout.nav_drawer_row,parent,false);
        ViewHolder viewHolder=new ViewHolder(view);
        return viewHolder;
    }

    @Override
    public void onBindViewHolder(NavigationAdapter.ViewHolder holder, int position) {

        holder.tv.setText(arrItem.get(position).getTitle());
    }

    @Override
    public int getItemCount() {
        return arrItem.size();
    }

    public class ViewHolder extends RecyclerView.ViewHolder {
        TextView tv;
        public ViewHolder(View itemView) {
            super(itemView);
            tv= (TextView) itemView.findViewById(R.id.txtTitle);
        }
    }
}

