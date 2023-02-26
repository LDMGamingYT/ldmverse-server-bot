package net.ldm.ldmverse_server_bot.bot.stat;

import java.util.Random;

public class UserStats {
    private long level;
    private long messagesSent;
    private float xp;

    UserStats(long level, long messagesSent, float xp) {
        this.level = level;
        this.messagesSent = messagesSent;
        this.xp = xp;
    }

    public UserStats setLevel(long level) {
        this.level = level;
        return this;
    }

    public UserStats setSentMessages(long messagesSent) {
        this.messagesSent = messagesSent;
        return this;
    }

    public UserStats setXp(long xp) {
        this.xp = xp;
        return this;
    }

    public UserStats incrementLevel(int amount) {
        this.level += amount;
        return this;
    }

    public UserStats incrementLevel() {
        return incrementLevel(1);
    }

    public UserStats incrementSentMessages(int amount) {
        this.messagesSent += amount;
        return this;
    }

    public UserStats incrementSentMessages() {
        return incrementSentMessages(1);
    }

    public UserStats incrementXp(float amount) {
        this.xp += amount;
        return this;
    }

    public UserStats incrementXp(float min, float max) {
        this.xp += new Random().nextFloat(min, max);
        return this;
    }

    public long getLevel() {
        return level;
    }

    public long getMessagesSent() {
        return messagesSent;
    }

    public float getXp() {
        return xp;
    }

    @Override
    public String toString() {
        return "UserStats[" +
                "level=" + level + ", " +
                "messagesSent=" + messagesSent + ", " +
                "xp=" + xp + ']';
    }
}
