package com.vinsofts.asus.apphero.ui.home;

import android.os.Bundle;
import android.support.design.widget.NavigationView;
import android.support.v4.view.GravityCompat;
import android.support.v4.widget.DrawerLayout;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.View;
import android.widget.AdapterView;
import android.widget.FrameLayout;
import android.widget.ImageView;
import android.widget.ListView;
import android.widget.TextView;

import com.vinsofts.asus.apphero.R;

import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;

public class HomeActivity extends AppCompatActivity {

    public HomeAdapter homeAdapter;
    public List<String> listChoose = new ArrayList<>();
    @BindView(R.id.tool_bar_main)
    Toolbar toolBarMain;
    @BindView(R.id.frame_layout)
    FrameLayout frameLayout;
    @BindView(R.id.list_item)
    ListView listItem;
    @BindView(R.id.navigation_view_main)
    NavigationView navigationViewMain;
    @BindView(R.id.drawer_layout)
    DrawerLayout drawerLayout;
    @BindView(R.id.img_avatar)
    ImageView imgAvatar;
    @BindView(R.id.tv_email)
    TextView tvEmail;
    @BindView(R.id.tv_webSite)
    TextView tvWebSite;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_home);
        ButterKnife.bind(this);
        inItData();
        toolBarMain.setTitle(listChoose.get(0));
        homeAdapter = new HomeAdapter(listChoose, this);
        listItem.setAdapter(homeAdapter);
        homeAdapter.notifyDataSetChanged();
        listItem.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> adapterView, View view, int i, long l) {
                toolBarMain.setTitle(listChoose.get(i));
//                chuyển sang màn hình thứ i
                drawerLayout.closeDrawer(GravityCompat.START);
            }
        });
    }

    void inItData() {
        listChoose.add("Quản lý sản phẩm");
        listChoose.add("Quản lý danh mục");
        listChoose.add("Chăm sóc khách hàng");
        listChoose.add("Thông tin thanh toán");
        listChoose.add("Giới thiệu");
        listChoose.add("Liên hệ");
    }
}
