package com.millenial.smellslikebakin;

import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentTransaction;

import android.os.Bundle;
import android.widget.Toast;

public class MainActivity extends AppCompatActivity implements ListFragment.OnRecipeSelectedInterface {
    public static final String LIST_FRAGMENT = "list_fragment";
    public static final String VIEWPAGER_FRAGMENT = "viewpager_fragment";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        ListFragment savedFragment = (ListFragment) getSupportFragmentManager()
                .findFragmentByTag(LIST_FRAGMENT);
        if (savedFragment == null) {
            ListFragment fragment = new ListFragment();
            FragmentManager fragmentManager = getSupportFragmentManager();
            FragmentTransaction fragmentTransaction = fragmentManager.beginTransaction();
            fragmentTransaction.add(R.id.placeHolder, fragment, LIST_FRAGMENT);
            fragmentTransaction.commit();
        }

    }

    @Override
    public void onListRecipeSelected(int index) {
        ViewPagerFragment fragment = new ViewPagerFragment();
        Bundle bundle = new Bundle();
        bundle.putInt(ViewPagerFragment.KEY_RECIPE_INDEX, index);
        fragment.setArguments(bundle);
        FragmentManager fragmentManager = getSupportFragmentManager();
        FragmentTransaction fragmentTransaction = fragmentManager.beginTransaction();
        fragmentTransaction.replace(R.id.placeHolder, fragment, VIEWPAGER_FRAGMENT);
        fragmentTransaction.addToBackStack(null);
        fragmentTransaction.commit();
    }
}
