package com.tim.doordashlite;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;

import butterknife.ButterKnife;
import toothpick.Scope;
import toothpick.Toothpick;

/**
 * Created by Home on 5/31/17.
 */

public abstract class BaseActivity extends AppCompatActivity {

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(getLayout());
        ButterKnife.bind(this);
        final Scope scope = Toothpick.openScopes(getApplication(), this);
        Toothpick.inject(this, scope);
    }

    @Override
    protected void onDestroy() {
        Toothpick.closeScope(this);
        super.onDestroy();
    }

    protected abstract int getLayout();
}
