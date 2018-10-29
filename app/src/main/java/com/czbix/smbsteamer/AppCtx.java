package com.czbix.smbsteamer;

import android.app.Application;
import android.content.Context;

import com.czbix.smbsteamer.helper.PreferenceHelper;
import com.czbix.smbsteamer.util.SmbUtils;
import com.czbix.smbsteamer.util.Utils;
import com.danikula.videocache.HttpProxyCacheServer;

public class AppCtx extends Application {
    private HttpProxyCacheServer proxy;
    @Override
    public void onCreate() {
        super.onCreate();
        initPrefs();
        initSmb();
    }
    private void initPrefs() {
        PreferenceHelper.getInstance().init(this);
    }
    private void initSmb() {
        SmbUtils.init();
    }
    public static HttpProxyCacheServer getProxy(Context context) {
        AppCtx app = (AppCtx) context.getApplicationContext();
        return app.proxy == null ? (app.proxy = app.newProxy()) : app.proxy;
    }
    private HttpProxyCacheServer newProxy() {
        return new HttpProxyCacheServer.Builder(this)
                .cacheDirectory(Utils.getVideoCacheDir(this))
                .maxCacheSize(12*1024*1024)
                .build();
    }
}
