package com.uetty.nacos.blogfetcher.entity;

import com.uetty.nacos.interfaces.Identifiable;
import lombok.Getter;

@Getter
public enum TaskStatus implements Identifiable {

    PENDING("pending", ""),
    IN_PROGRESS("in_progress", ""),
    COMPLETED("completed", ""),
    DOWNLOADED("downloaded", ""),
    FAILED("failed", ""),
    ;

    TaskStatus(String id, String decrypt) {
        this.id = id;
        this.decrypt = decrypt;
    }

    private String id;

    private String decrypt;

}
