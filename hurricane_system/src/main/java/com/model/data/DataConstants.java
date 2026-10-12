package com.model.data;

import java.nio.file.Files;
import java.nio.file.Path;

public class DataConstants {

    protected static final String JSON_FOLDER = getJsonFolder();

    protected static final String USER_FILE = "users.json";
    protected static final String SHELTERS_FILE = "shelters.json";
    protected static final String REQUESTS_FILE = "assistanceRequests.json";
    protected static final String HURRICANES_FILE = "hurricaneEvents.json";

    private static String getJsonFolder() {
        if (Files.isDirectory(Path.of("json"))) {
            return "json/";
        }

        if (Files.isDirectory(Path.of("../json"))) {
            return "../json/";
        }

        return "json/";
    }
}