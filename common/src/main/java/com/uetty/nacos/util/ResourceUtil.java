package com.uetty.nacos.util;

import java.io.InputStream;

public class ResourceUtil {

    public static InputStream getResourceInputStream(Class<?> clz, String path) {
        return clz.getResourceAsStream(path);
    }
}
