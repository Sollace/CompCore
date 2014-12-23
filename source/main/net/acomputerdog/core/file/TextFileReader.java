package net.acomputerdog.core.file;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.RandomAccessFile;
import java.util.ArrayList;
import java.util.List;

@Deprecated //There are better Java-API ways to do this, and this is written badly anyway.
/**
 * Class used to easily read a text file into an array of lines
 */
public class TextFileReader {
    /**
     * The file to read from
     */
    private final File file;
    /**
     * The RandomAccessFile used to read the file.
     */
    private final RandomAccessFile raf;

    /**
     * Creates a new TextFileReader
     *
     * @param path The path to the file
     * @throws IOException if IO error occurs.
     */
    public TextFileReader(String path) throws IOException {
        this(new File(path));
    }

    /**
     * Creates a new TextFileReader
     * @param file The file to read from
     * @throws IOException if IO error occurs.
     */
    public TextFileReader(File file) throws IOException {
        if (!file.exists()) {
            throw new FileNotFoundException("File does not exist!");
        }
        this.file = file;
        raf = new RandomAccessFile(file, "r");
    }

    /**
     * Reads the next line in the file
     * @return Return the next line of text
     * @throws IOException If IO error occurs.
     */
    public String readLine() throws IOException {
        return raf.readLine();
    }

    /**
     * Reads a line safely, without throwing an Exception
     * @return Return the next line of text, or null if an IO error occurs.
     */
    public String readLineSafe() {
        try {
            return readLine();
        } catch (IOException ignored) {
            return null;
        }
    }

    /**
     * Reads a specified number of lines into an array.
     * @param amount The number of lines
     * @return Return an array of lines
     * @throws IOException if IO error occurs
     */
    public String[] readLines(int amount) throws IOException {
        List<String> strings = new ArrayList<String>(amount);
        for (int count = 0; count <= amount; count++) {
            String str = readLine();
            if (str == null) {
                break;
            }
            strings.add(str);
        }
        return strings.toArray(new String[strings.size()]);
    }

    /**
     * Safely reads a number of lines
     * @param amount The number of lines to read
     * @return Return an array of lines.
     */
    public String[] readLinesSafe(int amount) {
        List<String> strings = new ArrayList<String>(amount);
        for (int count = 0; count <= amount; count++) {
            String str = readLineSafe();
            if (str == null) {
                break;
            }
            strings.add(str);
        }
        return strings.toArray(new String[strings.size()]);
    }

    /**
     * Read all lines in the file
     * @return Return an array of all lines in the file
     * @throws IOException if IO error occurs
     */
    public String[] readAllLines() throws IOException {
        raf.seek(0);
        return this.readAllRemainingLines();
    }

    /**
     * Read all lines in the file safely
     * @return Return an array of lines
     */
    public String[] readAllLinesSafe() {
        try {
            raf.seek(0);
        } catch (IOException e) {
            return new String[0];
        }
        return this.readAllRemainingLinesSafe();
    }

    /**
     * Reads all lines that have not been read
     * @return Return an array of unread lines
     * @throws IOException if IO error occurs.
     */
    public String[] readAllRemainingLines() throws IOException {
        List<String> strings = new ArrayList<String>();
        String str;
        while ((str = readLine()) != null) {
            strings.add(str);
        }
        return strings.toArray(new String[strings.size()]);
    }

    /**
     * Safely reads all lines that have not been read
     * @return Return an array of unread lines
     */
    public String[] readAllRemainingLinesSafe() {
        List<String> strings = new ArrayList<String>();
        String str;
        while ((str = readLineSafe()) != null) {
            strings.add(str);
        }
        return strings.toArray(new String[strings.size()]);
    }

    /**
     * Close the TextFileReader
     */
    public void close() {
        try {
            raf.close();
        } catch (IOException ignored) {
        }
    }

    /**
     * Gets the File that this TextFileReader reads from
     * @return return the File that this TextFileReader reads from
     */
    public File getFile() {
        return file;
    }

    /**
     * Get the RandomAccessFile that is reading the file
     * @return return the RandomAccessFile that is reading the file
     */
    public RandomAccessFile getRaf() {
        return raf;
    }

    /**
     * Resets the reader to the beginning of the file
     * @throws IOException if IO error occurs.
     */
    public void reset() throws IOException {
        raf.seek(0);
    }
}
