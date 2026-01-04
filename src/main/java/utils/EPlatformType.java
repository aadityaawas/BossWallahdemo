package utils;

import java.util.Locale;

// Enum for platforms i.e. Android, IOS, Microsoft
public enum EPlatformType {
    ANDROID,
    IOS;

    public static EPlatformType getPlatformType(String platformName) {
        return EPlatformType.valueOf(platformName.toUpperCase(Locale.ROOT));
    }
}
