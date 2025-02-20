package com.vluepixel.vetmanager.api.shared.adapter.in.util;

import lombok.AccessLevel;
import lombok.NoArgsConstructor;

/**
 * Regex constants.
 */
@NoArgsConstructor(access = AccessLevel.PRIVATE)
public final class RegexConstants {
    /**
     * Image URL regex.
     */
    public static final String IMAGE_URL = "^https://"
            + "([a-zA-Z0-9]([a-zA-Z0-9-]{0,61}[a-zA-Z0-9])?\\.)+[a-zA-Z]{2,}" // Domain
            + "(:\\d{1,5})?" // Optional port
            + "(/[^\\s?#]*)" // Route
            + "(\\.(?:jpg|jpeg|png|webp))?" // Optional image extension
            + "(\\?[^\\s#]*)?" // Optional parameters
            + "$";

    /**
     * URL regex. It allows only HTTPS URLs.
     */
    public static final String URL = "^https://"
            + "([a-zA-Z0-9]([a-zA-Z0-9-]{0,61}[a-zA-Z0-9])?\\.)+[a-zA-Z]{2,}" // Domain
            + "(:\\d{1,5})?" // Port optional
            + "(/[^\\s?#]*)?" // Route optional
            + "(\\?[^\\s#]*)?" // Additional parameters optional
            + "(#\\S*)?" // Anchor optional
            + "$";

    /**
     * Full version regex. It matches the version in the format "x.y.z".
     */
    public static final String FULL_VERSION = "(\\d+\\.\\d+\\.\\d+)";

    /**
     * Phone regex. It matches a peruvian phone number in the format "9xxxxxxxx".
     */
    public static final String PHONE = "^9[0-9]{8}$";

    /**
     * DNI regex. It matches a peruvian DNI in the format "xxxxxxxx".
     */
    public static final String DNI = "^\\d{8}$";

    /**
     * RUC regex. It matches a peruvian RUC in the format "xxxxxxxxxxx".
     */
    public static final String RUC = "^\\d{11}$";

    /**
     * Foreigner carnet. It matches a foreigner carnet in the format "xxxxxxxx".
     */
    public static final String FOREIGNER_CARNET = "^[a-zA-Z0-9]{12}$";
}
