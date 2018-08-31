/*
 * Copyright (C) 2013 Onur-Hayri Bakici
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 *      http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */

package com.thehayro.infiniteviewpagersample;

import com.thehayro.view.ComparableInfiniteViewPagerAdapter;
import com.thehayro.view.InfiniteViewPager;

import android.app.Activity;
import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.LinearLayout;
import android.widget.TextView;

public class MyActivity extends Activity {
    /**
     * Called when the activity is first created.
     */
    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.main);

        final InfiniteViewPager viewPager = findViewById(R.id.infinite_viewpager);
        final MyInfinitePagerAdapter adapter = new MyInfinitePagerAdapter(0);
        adapter.setMinValue(-1);
        adapter.setMaxValue(4);
        viewPager.setAdapter(adapter);
        viewPager.setPageMargin(20);
        viewPager.addOnInfinitePageChangeListener(new InfiniteViewPager.OnInfinitePageChangeListener() {
            @Override
            public void onPageScrolled(final Object indicator, final float positionOffset,
                                       final int positionOffsetPixels) {
            }

            @Override
            public void onPageSelected(final Object indicator) {
            }

            @Override
            public void onPageScrollStateChanged(final int state) {
            }
        });

        final Button btn = findViewById(R.id.current_item_btn);
        btn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(final View v) {
                viewPager.setCurrentIndicator(6);
            }
        });
    }

    private class MyInfinitePagerAdapter extends ComparableInfiniteViewPagerAdapter<Integer> {

        /**
         * Standard constructor.
         *
         * @param initValue the initial indicator value the ViewPager should start with.
         */
        public MyInfinitePagerAdapter(final Integer initValue) {
            super(initValue);
        }

        @Override
        public ViewGroup instantiateItem(Integer indicator) {
            Log.d("InfiniteViewPager", "instantiating page " + indicator);
            LayoutInflater layoutInflater = LayoutInflater.from(MyActivity.this);
            final LinearLayout layout = (LinearLayout) layoutInflater.inflate(R.layout.complex_page_layout, null);
            final TextView text = layout.findViewById(R.id.moving_view_x);
            text.setText(String.format("Page %s", indicator));
            Log.i("InfiniteViewPager", String.format("textView.text() == %s", text.getText()));
            layout.setTag(indicator);
            return layout;
        }

        @Override
        public Integer getNextIndicator() {
            return getCurrentIndicator() + 1;
        }

        @Override
        public Integer getPreviousIndicator() {
            return getCurrentIndicator() - 1;
        }

        @Override
        public String getStringRepresentation(final Integer currentIndicator) {
            return String.valueOf(currentIndicator);
        }

        @Override
        public Integer convertToIndicator(final String representation) {
            return Integer.valueOf(representation);
        }
    }
}
