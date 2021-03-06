package pers.zhenfeng.core.util;

import redis.clients.jedis.Jedis;

import java.util.Properties;

/**
 * @author Grow-Worm
 * @date 2018/11/18
 */
public class RedisUtil {

    /**
     * 表示1小时
     */
    private final static Integer DEFAULT_EXPIRE_TIME = 60 * 60;
    private final static Jedis jedis;

    static {
        Properties properties = null;
        try {
            properties = FileUtil.getPropertiesFile(FileUtil.PATH, FileUtil.CHARSET_NAME);
        } catch (Exception e) {
            e.printStackTrace();
        }
        assert properties != null;

        String host = properties.getProperty("redis.host");
        String port = properties.getProperty("redis.port");
        String auth = properties.getProperty("redis.auth");

        assert host != null && port != null;

        jedis = new Jedis(host, Integer.parseInt(port));

        if (auth != null) {
            jedis.auth(auth);
        }
    }

    public static String getValueByKey(String key) {
        return jedis.get(key);
    }

    public static void setValue(String key, String value) {
        setValueTime(key, value, DEFAULT_EXPIRE_TIME);
    }

    public static void setValueTime(String key, String value, int expireTime) {
        jedis.set(key, value);
        jedis.expire(key, expireTime);
    }

    public static void remove(String key) {
        jedis.del(key);
    }
    public static void main(String[] args) {
        RedisUtil.setValue("password", "1234567890");
        System.out.println(RedisUtil.getValueByKey("password"));
    }
}
