package net.ldm.ldmverse_server_bot.core.crash;

import org.apache.logging.log4j.Logger;
import org.apache.logging.log4j.core.LoggerContext;

import java.time.LocalDateTime;

public class CrashExceptionHandler implements Thread.UncaughtExceptionHandler {
    private static final Logger LOG = LoggerContext.getContext().getLogger(CrashExceptionHandler.class);
    @Override
    public void uncaughtException(Thread t, Throwable e) {
        new CrashReport(t, e, LocalDateTime.now()).generateAndSave();
        LOG.fatal("Uncaught exception in thread "+t.getName(), e);
    }
}
