package app.sbaloan.activities;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentStatePagerAdapter;
import android.support.v4.view.ViewPager;
import android.support.v7.app.AppCompatActivity;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.Toast;

import java.util.HashMap;
import java.util.Map;

import app.sbaloan.R;
import app.sbaloan.fragments.FragmentText;
import app.sbaloan.fragments.SavedItemsFragment;
import app.sbaloan.fragments.SearchOptionsFragment;
import app.sbaloan.models.SearchDto;
import it.neokree.materialtabs.MaterialTab;
import it.neokree.materialtabs.MaterialTabHost;
import it.neokree.materialtabs.MaterialTabListener;

/**
 * Created by danielmurphy on 10/9/15.
 */
public class Main extends AppCompatActivity implements MaterialTabListener, View.OnClickListener {
    public static final String SEARCH_DTO = "search_dto";
    private MaterialTabHost tabHost;
    private ViewPager pager;
    private ViewPagerAdapter pagerAdapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.main);

        tabHost = (MaterialTabHost) this.findViewById(R.id.tabHost);
        pager = (ViewPager) this.findViewById(R.id.pager);

        // init view pager
        pagerAdapter = new ViewPagerAdapter(getSupportFragmentManager());
        pager.setAdapter(pagerAdapter);
        pager.addOnPageChangeListener(new ViewPager.OnPageChangeListener() {
            @Override
            public void onPageScrolled(int position, float positionOffset, int positionOffsetPixels) {
            }

            @Override
            public void onPageSelected(int position) {
                tabHost.setSelectedNavigationItem(position);

                //Fragment f = pagerAdapter.getItem(position);
               // if (f instanceof SavedItemsFragment) {
                //    (SavedItemsFragment)
                //}
            }

            @Override
            public void onPageScrollStateChanged(int state) {
            }
        });

        // insert all tabs from pagerAdapter data
        for (int i = 0; i < pagerAdapter.getCount(); i++) {
            tabHost.addTab(
                    tabHost.newTab()
                            .setText(pagerAdapter.getPageTitle(i))
                            .setTabListener(this)
            );
        }
    }

    @Override
    public void onTabSelected(MaterialTab tab) {
        // when the tab is clicked the pager swipe content to the tab position
        pager.setCurrentItem(tab.getPosition());

    }

    @Override
    public void onTabReselected(MaterialTab materialTab) {

    }

    @Override
    public void onTabUnselected(MaterialTab materialTab) {

    }

    @Override
    public void onClick(View view) {

    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.main_activity_action, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        try {
            //if (item.equals(executeBtn)) {
            pagerAdapter.runSearch(this);
            //}
        } catch (Exception e) {
            Toast.makeText(this, e.getMessage(), Toast.LENGTH_LONG);
        }

        return true;
    }


    private class ViewPagerAdapter extends FragmentStatePagerAdapter {
        private Map<Integer, Fragment> fragments = new HashMap<Integer, Fragment>();
        private final Integer FRAGMENT_SEARCH_OPTIONS = 0;
        private final Integer FRAGMENT_SAVED_ITEMS = 1;
        //private final Integer FRAGMENT_SAVED_SEARCHES = 2;

        public ViewPagerAdapter(FragmentManager fm) {
            super(fm);

            fragments.put(FRAGMENT_SEARCH_OPTIONS, new SearchOptionsFragment());
            fragments.put(FRAGMENT_SAVED_ITEMS, new SavedItemsFragment());
            //fragments.put(FRAGMENT_SAVED_SEARCHES, new SavedSearchesFragment());
        }

        public Fragment getItem(int num) {
            if (fragments.containsKey(num))
                return fragments.get(num);

            return new FragmentText();
        }

        public void runSearch(Activity parent) {
            try {
                Intent intent = new Intent(parent, SBALoanList.class);
                SearchDto dto = ((SearchOptionsFragment) fragments.get(FRAGMENT_SEARCH_OPTIONS)).getSearchDto();
                intent.putExtra(SEARCH_DTO, dto);
                startActivity(intent);
            } catch (Exception e) {
                Toast.makeText(parent, e.getMessage(), Toast.LENGTH_LONG).show();
            }
        }

        @Override
        public int getCount() {
            return fragments.size();
        }

        @Override
        public CharSequence getPageTitle(int position) {
            if (fragments.containsKey(position))
                return fragments.get(position).toString();

            return "null";
        }
    }
}
