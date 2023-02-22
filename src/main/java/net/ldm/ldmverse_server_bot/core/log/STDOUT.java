package net.ldm.ldmverse_server_bot.core.log;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import java.io.OutputStream;

public class STDOUT extends OutputStream {
    private static final Logger logger = LogManager.getLogger(STDOUT.class);

    private final StringBuilder builder = new StringBuilder();

    @Override
    public void write(int b) {
        char c = (char) b;
        if (c == '\n') {
            logger.info(builder.toString());
            builder.setLength(0);
        } else {
            builder.append(c);
        }
    }
}