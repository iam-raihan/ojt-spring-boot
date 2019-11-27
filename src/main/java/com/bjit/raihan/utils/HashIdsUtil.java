package com.bjit.raihan.utils;

import org.hashids.Hashids;

/**
 * TODO - implement in project (repository / response / entity)
 * https://stackoverflow.com/questions/51017803/spring-data-rest-id-conversion-using-hashids
 */
public class HashIdsUtil {
    private static final String SALT = "super-secret-salt";
    private static Hashids hashids;

    static {
        hashids = new Hashids(SALT, 10);
    }

    public static String encode(Long id) {
        String hashId = hashids.encode(id);
        return hashId;
    }

    public static Long decode(String hashId) {
        try {
            Long id = hashids.decode(hashId)[0];
            return id;
        } catch (Exception ex) {
            String message = hashId + " is invalid hash ID!";
            System.out.println(message);
            throw new IllegalArgumentException(message);
        }
    }
}
