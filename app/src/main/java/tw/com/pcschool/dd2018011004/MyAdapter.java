package tw.com.pcschool.dd2018011004;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import com.squareup.picasso.Picasso;

import java.util.ArrayList;
import java.util.zip.Inflater;

/**
 * Created by Student on 2018/1/11.
 */

public class MyAdapter extends BaseAdapter {
    Context context;
    ArrayList<Mobile01NewsItem> myList;

    public MyAdapter(Context context, ArrayList<Mobile01NewsItem> myList)
    {
        this.context = context;
        this.myList = myList;
    }
    @Override
    public int getCount() {
        return myList.size();
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

        ViewHolder viewHolder1;
        if (view == null)
        {
            LayoutInflater inflater = LayoutInflater.from(context);
            view = inflater.inflate(R.layout.mylayout,null);
            viewHolder1 = new ViewHolder();
            viewHolder1.tv1 = (TextView) view.findViewById(R.id.textView);
            viewHolder1.tv2 = (TextView) view.findViewById(R.id.textView2);
            viewHolder1.iv1 = (ImageView) view.findViewById(R.id.imageView);
            view.setTag(viewHolder1);
        }
        else
        {
            viewHolder1 = (ViewHolder) view.getTag();
        }
        viewHolder1.tv1.setText(myList.get(i).title);
        viewHolder1.tv2.setText(myList.get(i).description);
        Picasso.with(context).load(myList.get(i).imgurl).into(viewHolder1.iv1);
        return view;
    }
    static class ViewHolder
    {
        TextView tv1;
        TextView tv2;
        ImageView iv1;
    }
}
