package com.example.springBaseCode;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

import java.util.HashMap;
import java.util.Map;

public class HttpStatusResolver {
    // 수동 매핑 테이블
    private static final Map<Class<? extends Throwable>, HttpStatus> STATUS_MAP = new HashMap<>();

    static {
        STATUS_MAP.put(IndexOutOfBoundsException.class, HttpStatus.BAD_REQUEST);
        STATUS_MAP.put(NullPointerException.class, HttpStatus.INTERNAL_SERVER_ERROR);
        STATUS_MAP.put(IllegalArgumentException.class, HttpStatus.BAD_REQUEST);
        STATUS_MAP.put(UnsupportedOperationException.class, HttpStatus.NOT_IMPLEMENTED);
    }

    public static HttpStatus resolve(Throwable e) {
        // 1. @ResponseStatus 우선 처리
        ResponseStatus annotation = e.getClass().getAnnotation(ResponseStatus.class);
        if (annotation != null) {
            return annotation.code();
        }

        // 2. 수동 매핑 테이블 처리 (상속 허용)
        for (Map.Entry<Class<? extends Throwable>, HttpStatus> entry : STATUS_MAP.entrySet()) {
            if (entry.getKey().isAssignableFrom(e.getClass())) {
                return entry.getValue();
            }
        }

        // 3. fallback: 서버 오류
        return HttpStatus.INTERNAL_SERVER_ERROR;
    }
}
