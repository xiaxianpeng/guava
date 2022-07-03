package com.example.guava.eventbus.monitor;

import com.google.common.eventbus.EventBus;
import java.nio.file.FileSystems;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.nio.file.StandardWatchEventKinds;
import java.nio.file.WatchEvent.Kind;
import java.nio.file.WatchKey;
import java.nio.file.WatchService;
import lombok.extern.slf4j.Slf4j;

/**
 * @author xianpeng.xia
 * on 2022/7/3 14:48
 */

@Slf4j
public class DirectoryTargetMonitor implements TargetMonitor {

    private final EventBus eventBus;
    private final Path path;
    private WatchService watchService;
    private volatile boolean start = false;

    public DirectoryTargetMonitor(EventBus eventBus, String targetPath) {
        this(eventBus, targetPath, "");
    }

    public DirectoryTargetMonitor(EventBus eventBus, String targetPath, String... morePaths) {
        this.eventBus = eventBus;
        this.path = Paths.get(targetPath, morePaths);
    }

    @Override
    public void startMonitor() throws Exception {
        this.watchService = FileSystems.getDefault().newWatchService();
        this.path.register(watchService
            , StandardWatchEventKinds.ENTRY_MODIFY
            , StandardWatchEventKinds.ENTRY_DELETE
            , StandardWatchEventKinds.ENTRY_CREATE
        );
        log.info("The directory [{}] is monitoring...", path);
        this.start = true;
        while (start) {
            WatchKey watchKey = null;
            try {
                watchKey = watchService.take();
                watchKey.pollEvents().forEach(event -> {
                    Kind<?> kind = event.kind();
                    Path path = (Path) event.context();
                    Path child = DirectoryTargetMonitor.this.path.resolve(path);
                    eventBus.post(new FileChangeEvent(kind, child));
                });
            } catch (InterruptedException e) {
                this.start = false;
                log.error("startMonitor error", e);
            } finally {
                if (watchKey != null) {
                    watchKey.reset();
                }
            }
        }
    }

    @Override
    public void stopMonitor() throws Exception {
        log.info("The directory [{}] monitor will be stop...", path);
        Thread.currentThread().interrupt();
        this.watchService.close();
        log.info("The directory [{}] monitor will be stop done...", path);
    }
}
