package com.wangxingxing.widget.lsn23.frags;


import android.media.MediaPlayer;
import android.net.Uri;
import android.view.View;
import android.widget.ImageView;
import android.widget.VideoView;

import com.wangxingxing.widget.R;


/**
 * 直播界面，用于对接直播功能
 * 
 */
public class LiveFrag extends BaseFrag{
	private ImageView img_thumb;
	private VideoView video_view;

	@Override
	public int getLayoutId() {
		// TODO Auto-generated method stub
		return R.layout.frag_live;
	}

	@Override
	public void initView() {
		super.initView();
		img_thumb = view.findViewById(R.id.img_thumb);
		img_thumb.setVisibility(View.GONE);
		video_view = view.findViewById(R.id.video_view);
		video_view.setVisibility(View.VISIBLE);
		video_view.setVideoURI(Uri.parse("android.resource://" + getActivity().getPackageName() +"/" + R.raw.video_1));
		video_view.start();
		video_view.setOnCompletionListener(new MediaPlayer.OnCompletionListener() {

			@Override
			public void onCompletion(MediaPlayer mp) {
				video_view.setVideoURI(Uri.parse("android.resource://" + getActivity().getPackageName() +"/" + R.raw.video_1));
				//或 //mVideoView.setVideoPath(Uri.parse(_filePath));
				video_view.start();
			}
		});
	}
}