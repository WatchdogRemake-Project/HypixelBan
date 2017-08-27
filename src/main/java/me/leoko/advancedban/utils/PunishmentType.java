package me.leoko.advancedban.utils;

/**
 * Created by Leoko @ dev.skamps.eu on 30.05.2016.
 */
public enum PunishmentType {
    BAN("Ban", null, false, "hypixelban.ban.perma"),
    TEMP_BAN("Tempban", BAN, true, "hypixelban.ban.temp"),
    IP_BAN("Ipban", BAN, false, "hypixelban.ipban.perma"),
    TEMP_IP_BAN("Tempipban", BAN, true, "hypixelban.ipban.temp"),
    MUTE("Mute", null, false, "hypixelban.mute.perma"),
    TEMP_MUTE("Tempmute", MUTE, true, "hypixelban.mute.temp"),
    WARNING("Warn", null, false, "hypixelban.warn.perma"),
    TEMP_WARNING("Tempwarn", WARNING, true, "hypixelban.warn.temp"),
    KICK("Kick", null, false, "hypixelban.kick.use");

    private final String name;
    private final String perms;
    private final PunishmentType basic;
    private final boolean temp;

    PunishmentType(String name, PunishmentType basic, boolean temp, String perms) {
        this.name = name;
        this.basic = basic;
        this.temp = temp;
        this.perms = perms;
    }

    public static PunishmentType fromCommandName(String cmd) {
        switch (cmd) {
            case "ban":
                return BAN;
            case "tempban":
                return TEMP_BAN;
            case "ban-ip":
            case "banip":
            case "ipban":
                return IP_BAN;
            case "tempipban":
            case "tipban":
                return TEMP_IP_BAN;
            case "mute":
                return MUTE;
            case "tempmute":
                return TEMP_MUTE;
            case "warn":
                return WARNING;
            case "tempwarn":
                return TEMP_WARNING;
            case "kick":
                return KICK;
            default:
                return null;
        }
    }

    public String getName() {
        return name.toLowerCase();
    }

    public String getPerms() {
        return perms;
    }

    public String getConfSection() {
        return name;
    }

    public PunishmentType getBasic() {
        return basic == null ? this : basic;
    }

    public boolean isTemp() {
        return temp;
    }
}
