package net.ldm.ldmverse_server_bot.core.crash;

import org.apache.logging.log4j.Logger;
import org.apache.logging.log4j.core.LoggerContext;

public class LoggerExceptionHandler implements Thread.UncaughtExceptionHandler {
    private static final Logger LOG = LoggerContext.getContext().getLogger(LoggerExceptionHandler.class);
    @Override
    public void uncaughtException(Thread t, Throwable e) {
        LOG.fatal("Uncaught exception in thread "+t.getName(), e);
    }
}
