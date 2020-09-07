package org.androidtown.actionbar;

import android.graphics.Color;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentStatePagerAdapter;
import android.support.v4.view.ViewPager;
import android.support.v7.app.ActionBarActivity;

import com.neokree.materialtabs.MaterialTab;
import com.neokree.materialtabs.MaterialTabHost;
import com.neokree.materialtabs.MaterialTabListener;

/**
 * 툴바에 탭을 설정하는 방법을 알 수 있습니다.
 * 
 * @author Mike
 */
public class MainActivity extends ActionBarActivity {

	ViewPager pager;
	ViewPagerAdapter pagerAdapter;
	MaterialTabHost tabhost;
	
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_main);
		
		tabhost = (MaterialTabHost) this.findViewById(R.id.tabhost);
		pager = (ViewPager) this.findViewById(R.id.pager);
		
		// 뷰페이저 어댑터를 만듭니다.
		pagerAdapter = new ViewPagerAdapter(getSupportFragmentManager());
		pager.setAdapter(pagerAdapter);
		pager.setOnPageChangeListener(new ViewPager.SimpleOnPageChangeListener() {
            @Override
            public void onPageSelected(int position) {
            	tabhost.setSelectedNavigationItem(position);
            }
        });
		
		// 탭의 글자색을 지정합니다.
		tabhost.setTextColor(Color.RED);
		
		// 탭의 배경색을 지정합니다.
		tabhost.setPrimaryColor(Color.CYAN);

		// 탭을 추가합니다.
		for (int i = 0; i < pagerAdapter.getCount(); i++) {
			MaterialTab tab = tabhost.newTab();
			tab.setText(pagerAdapter.getPageTitle(i));
            tab.setTabListener(new ProductTabListener());
            
            tabhost.addTab(tab);
        }
		
		// 처음 선택된 탭을 지정합니다.
		tabhost.setSelectedNavigationItem(0);
	}
 
	/**
	 * 뷰페이저 어댑터를 정의합니다.
	 */
	private class ViewPagerAdapter extends FragmentStatePagerAdapter {

        public ViewPagerAdapter(FragmentManager fm) {
			super(fm);
		}

		public Fragment getItem(int index) {
			Fragment frag = null;
			
			if (index == 0) {
				frag = new Fragment01();
			} else if (index == 1) {
				frag = new Fragment02();
			} else if (index == 2) {
				frag = new Fragment03();
			}
			
            return frag;
        }

        @Override
        public int getCount() {
            return 3;
        }
        
        @Override
        public CharSequence getPageTitle(int position) {
        	switch(position) {
        	case 0: return "상품 #1";
        	case 1: return "상품 #2";
        	case 2: return "상품 #3";
        	default: return null;
        	}
        }
        
    }
	
    /**
     * 탭을 선택했을 때 처리할 리스너 정의
     */
	private class ProductTabListener implements MaterialTabListener {

		public ProductTabListener() {

		}

		@Override
		public void onTabSelected(MaterialTab tab) {
			pager.setCurrentItem(tab.getPosition());
		}

		@Override
		public void onTabReselected(MaterialTab tab) {
			 
		}

		@Override
		public void onTabUnselected(MaterialTab tab) {
			 
		}

	}
    
}
