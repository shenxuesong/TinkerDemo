package com.shenxuesong.tinkerdemo;

import android.content.Context;
import android.content.pm.PackageInfo;
import android.content.pm.PackageManager;
import android.os.Bundle;
import android.os.Environment;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import com.tencent.bugly.beta.Beta;

public class MainActivity extends AppCompatActivity implements View.OnClickListener {

    /**
     * 当前版本:
     */
    private TextView mTvCurrentVersion;
    /**
     * 显示结果
     */
    private Button mBtnShowToast;
    /**
     * 加载补丁
     */
    private Button mBtnLoadPatch;
    /**
     * 测试so更新
     */
    private Button mBtnLoadLibrary;
    /**
     * 手动下载补丁包
     */
    private Button mBtnDownloadPatch;
    /**
     * 手动合成补丁
     */
    private Button mBtnPatchDownloaded;
    /**
     * 检查更新
     */
    private Button mBtnCheckUpgrade;
    /**
     * 退出程序
     */
    private Button mBtnKillSelf;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        initView();


    }

    private void initView() {
        mTvCurrentVersion = (TextView) findViewById(R.id.tvCurrentVersion);
        mTvCurrentVersion.setText("当前版本：" + getCurrentVersion(this));
        mBtnShowToast = (Button) findViewById(R.id.btnShowToast);
        mBtnShowToast.setOnClickListener(this);
        mBtnLoadPatch = (Button) findViewById(R.id.btnLoadPatch);
        mBtnLoadPatch.setOnClickListener(this);
        mBtnLoadLibrary = (Button) findViewById(R.id.btnLoadLibrary);
        mBtnLoadLibrary.setOnClickListener(this);
        mBtnDownloadPatch = (Button) findViewById(R.id.btnDownloadPatch);
        mBtnDownloadPatch.setOnClickListener(this);
        mBtnPatchDownloaded = (Button) findViewById(R.id.btnPatchDownloaded);
        mBtnPatchDownloaded.setOnClickListener(this);
        mBtnCheckUpgrade = (Button) findViewById(R.id.btnCheckUpgrade);
        mBtnCheckUpgrade.setOnClickListener(this);
        mBtnKillSelf = (Button) findViewById(R.id.btnKillSelf);
        mBtnKillSelf.setOnClickListener(this);
    }

    /**
     * 根据应用patch包前后来测试是否应用patch包成功.
     * <p>
     * 应用patch包前，提示"This is a bug class"
     * 应用patch包之后，提示"The bug has fixed"
     */
    public void testToast() {
        Toast.makeText(this, LoadBugClass.getBugString(this), Toast.LENGTH_SHORT).show();
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            default:
                break;
            case R.id.btnShowToast: //测试热更新功能
                testToast();
                break;
            case R.id.btnLoadPatch://杀死进程
                android.os.Process.killProcess(android.os.Process.myPid());
                break;
            case R.id.btnLoadLibrary:  //本地加载补丁测试
                Beta.applyTinkerPatch(getApplicationContext(), Environment.getExternalStorageDirectory().getAbsolutePath() + "/patch_signed_7zip.apk");
                break;
            case R.id.btnDownloadPatch: //// 本地加载so库测试

                break;
            case R.id.btnPatchDownloaded:
                Beta.downloadPatch();
                break;
            case R.id.btnCheckUpgrade:
                Beta.applyDownloadedPatch();
                break;
            case R.id.btnKillSelf:
                Beta.checkUpgrade();
                break;
        }
    }

    /**
     * 获取当前版本.
     *
     * @param context 上下文对象
     * @return 返回当前版本
     */
    public String getCurrentVersion(Context context) {
        try {
            PackageInfo packageInfo = context.getPackageManager().getPackageInfo(this.getPackageName(), PackageManager.GET_CONFIGURATIONS);
            int versionCode = packageInfo.versionCode;
            String versionName = packageInfo.versionName;

            return versionName + "." + versionCode;
        } catch (Exception e) {
            e.printStackTrace();
        }

        return "";

    }

    @Override
    public void onBackPressed() {
        super.onBackPressed();
        Log.e("MainActivity", "onBackPressed");

        Beta.unInit();
    }
}
