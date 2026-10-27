package com.model.data;

import java.nio.file.Files;
import java.nio.file.Path;

/**
 * File names and directory configuration used by model persistence.
 */
public class DataConstants {

    /**
     * Creates a constants holder.
     */
    public DataConstants() {
    }

    /** Directory containing the JSON data files. */
    protected static final String JSON_FOLDER = getJsonFolder();

    /** JSON file containing user records. */
    protected static final String USER_FILE = "users.json";
    /** JSON file containing shelter records. */
    protected static final String SHELTERS_FILE = "shelters.json";
    /** JSON file containing assistance requests. */
    protected static final String REQUESTS_FILE = "assistanceRequests.json";
    /** JSON file containing hurricane events. */
    protected static final String HURRICANES_FILE = "hurricaneEvents.json";

    /**
     * Chooses the available JSON data directory, preferring {@code json} in
     * the current directory and then {@code ../json}.
     *
     * @return the selected directory path, ending in a separator
     */
    private static String getJsonFolder() {
        if (Files.isDirectory(Path.of("json"))) {
            return "json/";
        }

        if (Files.isDirectory(Path.of("../json"))) {
            return "../json/";
        }

        return "json/";
    }
	public static boolean isJUnitTest() {  
		for (StackTraceElement element : Thread.currentThread().getStackTrace()) {
		  if (element.getClassName().startsWith("org.junit.")) {
			return true;
		  }           
		}
		return false;
	  }
}