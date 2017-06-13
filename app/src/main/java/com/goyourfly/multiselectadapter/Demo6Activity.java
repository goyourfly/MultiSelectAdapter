package com.goyourfly.multiselectadapter;

import android.graphics.Color;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.view.Gravity;
import android.view.MenuItem;

import com.goyourfly.multiple.adapter.MultipleAdapter;
import com.goyourfly.multiple.adapter.MultipleSelect;
import com.goyourfly.multiple.adapter.menu.MenuController;
import com.goyourfly.multiple.adapter.viewholder.view.CheckBoxFactory;
import com.goyourfly.multiple.adapter.menu.CustomMenuBar;

import org.jetbrains.annotations.NotNull;

/**
 * Created by gaoyufei on 2017/6/12.
 */

public class Demo6Activity extends RecyclerActivity {

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        MultipleAdapter adapter = MultipleSelect
                .with(this)
                .adapter(new DemoSectionAdapter())
                .ignoreViewType(new Integer[]{1})
                .decorateFactory(new CheckBoxFactory(Color.RED, 300, Gravity.RIGHT, 8))
                .customMenu(new CustomMenuBar(this,R.menu.menu_select,getResources().getColor(R.color.colorPrimaryDark),Gravity.TOP) {
                    @Override
                    public void onMenuItemClick(@NotNull MenuItem menuItem,MenuController controller) {

                    }
                })
                .build();
        getRecycler().setAdapter(adapter);
    }
}
