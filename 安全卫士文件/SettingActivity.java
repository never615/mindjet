package com.itheima.mobilesafe.activites;

import android.app.Activity;
import android.content.SharedPreferences;
import android.content.SharedPreferences.Editor;
import android.os.Bundle;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.CheckBox;

import com.itheima.mobilesafe.R;
import com.itheima.mobilesafe.ui.SettingView;
import com.lidroid.xutils.ViewUtils;
import com.lidroid.xutils.view.annotation.ViewInject;

public class SettingActivity extends Activity {

	@ViewInject(R.id.sv_setting_update)
	private SettingView sv_setting_update;

	@ViewInject(R.id.cb_setting)
	private CheckBox cb_setting;

	private SharedPreferences sp;

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);

		setContentView(R.layout.activity_setting);
		ViewUtils.inject(this);

		sp = getSharedPreferences("config", MODE_PRIVATE);

		if (sp.getBoolean("update", true)) {
			sv_setting_update.setChecked(true);
			// sv_setting_update.setContent("自动更新开启");
		} else {
			sv_setting_update.setChecked(false);
			// sv_setting_update.setContent("自动更新关闭");
		}

		sv_setting_update.setOnClickListener(new OnClickListener() {

			@Override
			public void onClick(View v) {
				Editor editor = sp.edit();

				if (sv_setting_update.isChecked()) {
					sv_setting_update.setChecked(false);
					// sv_setting_update.setContent("自动更新关闭");
					editor.putBoolean("update", false);

				} else {
					sv_setting_update.setChecked(true);
					// sv_setting_update.setContent("自动更新开启");
					editor.putBoolean("update", true);
				}
				editor.commit();
			}
		});
	}
}
