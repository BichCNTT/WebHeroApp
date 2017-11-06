package com.vinsofts.asus.apphero.ui.home;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.TextView;

import com.vinsofts.asus.apphero.R;

import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;

/**
 * Created by AT on 11/2/2017.
 */

public class HomeAdapter extends BaseAdapter {
    List<String> listChoose = new ArrayList<>();
    Context context;

    public HomeAdapter(List<String> listChoose, Context context) {
        this.listChoose = listChoose;
        this.context = context;
    }

    @Override
    public int getCount() {
        return listChoose.size();
    }

    @Override
    public Object getItem(int i) {
        return listChoose.get(i);
    }

    @Override
    public long getItemId(int i) {
        return i;
    }

    class ViewHolder {
        @BindView(R.id.tv_name)
        TextView tvName;

        ViewHolder(View view) {
            ButterKnife.bind(this, view);
        }
    }

    @Override
    public View getView(int i, View view, ViewGroup viewGroup) {
        ViewHolder holder;
        if (view == null) {
            LayoutInflater inflater = (LayoutInflater) context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
            view = inflater.inflate(R.layout.row_choose, null);
            holder = new ViewHolder(view);
            view.setTag(holder);
        } else {
            holder = (ViewHolder) view.getTag();

        }
        holder.tvName.setText(listChoose.get(i));
        return view;
    }

}
