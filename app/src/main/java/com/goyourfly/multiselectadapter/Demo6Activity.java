package com.goyourfly.multiselectadapter;

import android.graphics.Color;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.view.Gravity;

import com.goyourfly.multiple.adapter.MultipleAdapter;
import com.goyourfly.multiple.adapter.MultipleSelect;
import com.goyourfly.multiple.adapter.binder.view.CheckBoxFactory;

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
                .build();
        getRecycler().setAdapter(adapter);
    }
}
