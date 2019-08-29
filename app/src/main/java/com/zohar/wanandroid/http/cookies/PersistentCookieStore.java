package com.zohar.wanandroid.http.cookies;

import android.content.Context;
import android.content.SharedPreferences;
import android.text.TextUtils;
import android.util.Log;

import com.zohar.wanandroid.config.AppConstants;

import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.util.ArrayList;
import java.util.Collection;
import java.util.HashMap;
import java.util.List;
import java.util.Locale;
import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;

import okhttp3.Cookie;
import okhttp3.HttpUrl;

/**
 * Created by zohar on 2019/8/29 16:28
 * Describe:
 * PersistentCookieStore类实现CookieStore接口，用于管理Cookie；
 */
public class PersistentCookieStore implements CookieStore {
    /**
     *
     * <map>
     *     <string name="host_www.wanandroid.com">token_passwww.wanandroid.com,loginUserNamewww.wanandroid.com,token_pass_wanandroid_comwanandroid.com,loginUserName_wanandroid_comwanandroid.com,JSESSIONIDwww.wanandroid.com</string>
     *     <string name="cookie_loginUserName_wanandroid_comwanandroid.com">ACED000573720034636F6D2E7A6F6861722E77616E616E64726F69642E687474702E636F6F6B6965732E53657269616C697A61626C65436F6F6B696558765A8013AEB70C030000787074001C6C6F67696E557365724E616D655F77616E616E64726F69645F636F6D7400055A6F68617277080000016D771F393074000E77616E616E64726F69642E636F6D7400012F77040000000178</string>
     *     <string name="cookie_loginUserName_wanandroid_comwanandroid.com">ACED000573720034636F6D2E7A6F6861722E77616E616E64726F69642E687474702E636F6F6B6965732E53657269616C697A61626C65436F6F6B696558765A8013AEB70C030000787074001C6C6F67696E557365724E616D655F77616E616E64726F69645F636F6D74000C5A6F686172416E64726F696477080000016D77B3207874000E77616E616E64726F69642E636F6D7400012F77040000000178</string>
     *     <string name="cookie_JSESSIONIDwww.wanandroid.com">ACED000573720034636F6D2E7A6F6861722E77616E616E64726F69642E687474702E636F6F6B6965732E53657269616C697A61626C65436F6F6B696558765A8013AEB70C030000787074000A4A53455353494F4E4944740020303738463931464645313442313543354644394535343135354445424239373177080000E677D21FDBFF7400127777772E77616E616E64726F69642E636F6D7400012F77040101010078</string>
     *     <string name="cookie_token_pass_wanandroid_comwanandroid.com">ACED000573720034636F6D2E7A6F6861722E77616E616E64726F69642E687474702E636F6F6B6965732E53657269616C697A61626C65436F6F6B696558765A8013AEB70C0300007870740019746F6B656E5F706173735F77616E616E64726F69645F636F6D740020356439623930626362373036343031383365303964316537353565616438323377080000016D771F393074000E77616E616E64726F69642E636F6D7400012F77040000000178</string>
     *     <string name="cookie_token_passwww.wanandroid.com">ACED000573720034636F6D2E7A6F6861722E77616E616E64726F69642E687474702E636F6F6B6965732E53657269616C697A61626C65436F6F6B696558765A8013AEB70C030000787074000A746F6B656E5F70617373740020356439623930626362373036343031383365303964316537353565616438323377080000016D771F39307400127777772E77616E616E64726F69642E636F6D7400012F77040000010178</string>
     *     <string name="cookie_loginUserNamewww.wanandroid.com">ACED000573720034636F6D2E7A6F6861722E77616E616E64726F69642E687474702E636F6F6B6965732E53657269616C697A61626C65436F6F6B696558765A8013AEB70C030000787074000D6C6F67696E557365724E616D657400055A6F68617277080000016D771F39307400127777772E77616E616E64726F69642E636F6D7400012F77040000010178</string>
     * </map>
     * <string name="cookie_loginUserName_wanandroid_comwanandroid.com">ACED000573720034636F6D2E7A6F6861722E77616E616E64726F69642E687474702E636F6F6B6965732E53657269616C697A61626C65436F6F6B696558765A8013AEB70C030000787074001C6C6F67696E557365724E616D655F77616E616E64726F69645F636F6D74000C5A6F686172416E64726F696477080000016D77B3207874000E77616E616E64726F69642E636F6D7400012F77040000000178</string>
     */

    private static final String LOG_TAG = "PersistentCookieStore";

    private static final String HOST_NAME_PREFIX = "host_";
    //private static final String COOKIE_NAME_PREFIX = "cookie_";
    private final HashMap<String, ConcurrentHashMap<String, Cookie>> cookies;
    private final SharedPreferences cookiePrefs;
    private boolean omitNonPersistentCookies = false;

    /**
     * Construct a persistent cookie store.
     */
    public PersistentCookieStore(Context context) {
        this.cookiePrefs = context.getSharedPreferences(AppConstants.COOKIE_PREFS, 0);
        this.cookies = new HashMap<String, ConcurrentHashMap<String, Cookie>>();

        Map tempCookieMap = new HashMap<Object, Object>(cookiePrefs.getAll());
        for (Object key : tempCookieMap.keySet()) {
            if (!(key instanceof String) || !((String) key).contains(HOST_NAME_PREFIX)) {
                continue;
            }

            String cookieNames = (String) tempCookieMap.get(key);
            if (TextUtils.isEmpty(cookieNames)) {
                continue;
            }

            if (!this.cookies.containsKey(key)) {
                this.cookies.put((String) key, new ConcurrentHashMap<String, Cookie>());
            }

            String[] cookieNameArr = cookieNames.split(",");
            for (String name : cookieNameArr) {
                //String encodedCookie = this.cookiePrefs.getString("cookie_" + name, null);
                String encodedCookie = this.cookiePrefs.getString(name, null);
                if (encodedCookie == null) {
                    continue;
                }

                Cookie decodedCookie = this.decodeCookie(encodedCookie);
                if (decodedCookie != null) {
                    this.cookies.get(key).put(name, decodedCookie);
                }
            }
        }
        tempCookieMap.clear();

        clearExpired();
    }

    /** 移除失效cookie */
    private void clearExpired() {
        SharedPreferences.Editor prefsWriter = cookiePrefs.edit();

        for (String key : this.cookies.keySet()) {
            boolean changeFlag = false;

            for (ConcurrentHashMap.Entry<String, Cookie> entry : cookies.get(key).entrySet()) {
                String name = entry.getKey();
                Cookie cookie = entry.getValue();
                if (isCookieExpired(cookie)) {
                    // Clear cookies from local store
                    cookies.get(key).remove(name);

                    // Clear cookies from persistent store
                    //prefsWriter.remove(COOKIE_NAME_PREFIX + name);
                    prefsWriter.remove(name);

                    // We've cleared at least one
                    changeFlag = true;
                }
            }

            // Update names in persistent store
            if (changeFlag) {
                prefsWriter.putString(key, TextUtils.join(",", cookies.keySet()));
            }
        }

        prefsWriter.apply();
    }

    @Override
    public void add(HttpUrl httpUrl, Cookie cookie) {
        if (omitNonPersistentCookies && !cookie.persistent()) {
            return;
        }

        String name = this.cookieName(cookie);
        String hostKey = this.hostName(httpUrl);

        // Save cookie into local store, or remove if expired
        if(!this.cookies.containsKey(hostKey)) {
            this.cookies.put(hostKey, new ConcurrentHashMap<String, Cookie>());
        }
        cookies.get(hostKey).put(name, cookie);

        // Save cookie into persistent store
        SharedPreferences.Editor prefsWriter = cookiePrefs.edit();
        // 保存httpUrl对应的所有cookie的name
        prefsWriter.putString(hostKey, TextUtils.join(",", cookies.get(hostKey).keySet()));
        // 保存cookie
        //prefsWriter.putString(COOKIE_NAME_PREFIX + name, encodeCookie(new SerializableCookie(cookie)));
        prefsWriter.putString(name, encodeCookie(new SerializableCookie(cookie)));
        prefsWriter.apply();
    }

    @Override
    public void add(HttpUrl httpUrl, List<Cookie> cookies) {
        for (Cookie cookie : cookies) {
            if (isCookieExpired(cookie)) {
                continue;
            }
            this.add(httpUrl, cookie);
        }
    }

    @Override
    public List<Cookie> get(HttpUrl httpUrl) {
        return this.get(this.hostName(httpUrl));
    }

    @Override
    public List<Cookie> getCookies() {
        ArrayList<Cookie> result = new ArrayList<Cookie>();
        for (String hostKey : this.cookies.keySet()) {
            result.addAll(this.get(hostKey));
        }
        return result;
    }

    /** 获取cookie集合 */
    private List<Cookie> get(String hostKey) {
        ArrayList<Cookie> result = new ArrayList<Cookie>();

        if (this.cookies.containsKey(hostKey)) {
            Collection<Cookie> cookies = this.cookies.get(hostKey).values();
            for (Cookie cookie : cookies) {
                if (isCookieExpired(cookie)) {
                    this.remove(hostKey, cookie);
                }
                else {
                    result.add(cookie);
                }
            }
        }
        return result;
    }


    @Override
    public boolean remove(HttpUrl httpUrl, Cookie cookie) {
        return this.remove(this.hostName(httpUrl), cookie);
    }

    /** 从缓存中移除cookie */
    private boolean remove(String hostKey, Cookie cookie) {
        String name = this.cookieName(cookie);
        if (this.cookies.containsKey(hostKey) && this.cookies.get(hostKey).containsKey(name)) {
            // 从内存中移除httpUrl对应的cookie
            this.cookies.get(hostKey).remove(name);

            SharedPreferences.Editor prefsWriter = cookiePrefs.edit();

            // 从本地缓存中移出对应cookie
            //prefsWriter.remove(COOKIE_NAME_PREFIX + name);
            prefsWriter.remove(name);

            // 保存httpUrl对应的所有cookie的name
            prefsWriter.putString(hostKey, TextUtils.join(",", this.cookies.get(hostKey).keySet()));

            prefsWriter.apply();
            return true;
        }
        return false;
    }


    @Override
    public boolean removeAll() {
        SharedPreferences.Editor prefsWriter = cookiePrefs.edit();
        prefsWriter.clear();
        prefsWriter.apply();
        this.cookies.clear();
        return true;
    }


    public void setOmitNonPersistentCookies(boolean omitNonPersistentCookies) {
        this.omitNonPersistentCookies = omitNonPersistentCookies;
    }

    /** 判断cookie是否失效  */
    private boolean isCookieExpired(Cookie cookie) {
        return cookie.expiresAt() < System.currentTimeMillis();
    }

    private String hostName(HttpUrl httpUrl) {
        return httpUrl.host().startsWith(HOST_NAME_PREFIX) ? httpUrl.host() : HOST_NAME_PREFIX + httpUrl.host();
    }

    private String cookieName(Cookie cookie) {
        //return cookie == null ? null : cookie.name() + cookie.domain();
        return cookie == null ? null : cookie.name();
    }

    protected String encodeCookie(SerializableCookie cookie) {
        if (cookie == null)
            return null;
        ByteArrayOutputStream os = new ByteArrayOutputStream();
        try {
            ObjectOutputStream outputStream = new ObjectOutputStream(os);
            outputStream.writeObject(cookie);
        } catch (IOException e) {
            Log.d(LOG_TAG, "IOException in encodeCookie", e);
            return null;
        }

        return byteArrayToHexString(os.toByteArray());
    }

    protected Cookie decodeCookie(String cookieString) {
        byte[] bytes = hexStringToByteArray(cookieString);
        ByteArrayInputStream byteArrayInputStream = new ByteArrayInputStream(bytes);
        Cookie cookie = null;
        try {
            ObjectInputStream objectInputStream = new ObjectInputStream(byteArrayInputStream);
            cookie = ((SerializableCookie) objectInputStream.readObject()).getCookie();
        } catch (IOException e) {
            Log.d(LOG_TAG, "IOException in decodeCookie", e);
        } catch (ClassNotFoundException e) {
            Log.d(LOG_TAG, "ClassNotFoundException in decodeCookie", e);
        }
        return cookie;
    }

    protected String byteArrayToHexString(byte[] bytes) {
        StringBuilder sb = new StringBuilder(bytes.length * 2);
        for (byte element : bytes) {
            int v = element & 0xff;
            if (v < 16) {
                sb.append('0');
            }
            sb.append(Integer.toHexString(v));
        }
        return sb.toString().toUpperCase(Locale.US);
    }

    protected byte[] hexStringToByteArray(String hexString) {
        int len = hexString.length();
        byte[] data = new byte[len / 2];
        for (int i = 0; i < len; i += 2) {
            data[i / 2] = (byte) ((Character.digit(hexString.charAt(i), 16) << 4) + Character.digit(hexString.charAt(i + 1), 16));
        }
        return data;
    }
}
