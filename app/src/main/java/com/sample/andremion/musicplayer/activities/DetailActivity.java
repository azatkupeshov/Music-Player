/*
 * Copyright (c) 2016. André Mion
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 *     http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */

package com.sample.andremion.musicplayer.activities;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.transition.Transition;
import android.view.View;
import android.widget.Toast;

import com.andremion.music.MusicCoverView;
import com.sample.andremion.musicplayer.R;
import com.sample.andremion.musicplayer.view.TransitionAdapter;

public class DetailActivity extends AppCompatActivity {

    private MusicCoverView mCoverView;

    private MusicCoverView.Callbacks callbacks = new MusicCoverView.Callbacks() {
        @Override
        public void onMorphEnd(MusicCoverView coverView) {
            // Nothing to do
        }

        @Override
        public void onRotateEnd(MusicCoverView coverView) {
            supportFinishAfterTransition();
        }
    };

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_detail);

        mCoverView = findViewById(R.id.cover);
        mCoverView.setCallbacks(callbacks);

        getWindow().getSharedElementEnterTransition().addListener(new TransitionAdapter() {
            @Override
            public void onTransitionEnd(Transition transition) {
                mCoverView.start();
            }
        });
    }

    @Override
    public void onBackPressed() {
        onFabClick(null);
        mCoverView.stop();
    }

    public void onFabClick(View view) {
        mCoverView.stop();
        Toast.makeText(this, "Stop", Toast.LENGTH_SHORT).show();
    }

}
