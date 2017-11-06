package com.vinsofts.asus.apphero.ui.productmanager;

import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.vinsofts.asus.apphero.R;

public class ProductManagerFragment extends Fragment {

    public ProductManagerFragment() {

    }

    public static ProductManagerFragment newInstance() {
        ProductManagerFragment fragment = new ProductManagerFragment();
        return fragment;
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {

        return inflater.inflate(R.layout.fragment_product_manager, container, false);
    }
}
