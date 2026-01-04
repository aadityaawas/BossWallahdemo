package utils;

import java.util.Locale;

public enum EPlatformType {
    ANDROID,
    IOS;

    public static EPlatformType getPlatformType(String platformName) {
        return EPlatformType.valueOf(platformName.toUpperCase(Locale.ROOT));
    }
}
