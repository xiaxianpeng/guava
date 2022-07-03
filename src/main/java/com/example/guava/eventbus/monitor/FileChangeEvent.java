package com.example.guava.eventbus.monitor;

import java.nio.file.Path;
import java.nio.file.WatchEvent.Kind;
import lombok.Data;

/**
 * @author xianpeng.xia
 * on 2022/7/3 15:18
 */
@Data
public class FileChangeEvent {

    Kind<?> kind;
    Path path;

    public FileChangeEvent(Kind<?> kind, Path path) {
        this.kind = kind;
        this.path = path;
    }
}
