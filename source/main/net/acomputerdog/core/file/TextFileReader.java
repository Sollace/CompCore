package net.acomputerdog.core.file;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.RandomAccessFile;
import java.util.ArrayList;
import java.util.List;

public class TextFileReader {
    private final File file;
    private final RandomAccessFile raf;

    public TextFileReader(String path) throws IOException {
        this(new File(path));
    }

    public TextFileReader(File file) throws IOException {
        if (!file.exists()) {
            throw new FileNotFoundException("File does not exist!");
        }
        this.file = file;
        raf = new RandomAccessFile(file, "r");
    }

    public String readLine() throws IOException {
        return raf.readLine();
    }

    public String readLineSafe() {
        try {
            return readLine();
        } catch (IOException ignored) {
            return null;
        }
    }

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

    public String[] readAllLines() throws IOException {
        raf.seek(0);
        return this.readAllRemainingLines();
    }

    public String[] readAllLinesSafe() {
        try {
            raf.seek(0);
        } catch (IOException e) {
            return new String[0];
        }
        return this.readAllRemainingLinesSafe();
    }

    public String[] readAllRemainingLines() throws IOException {
        List<String> strings = new ArrayList<String>();
        String str;
        while ((str = readLine()) != null) {
            strings.add(str);
        }
        return strings.toArray(new String[strings.size()]);
    }

    public String[] readAllRemainingLinesSafe() {
        List<String> strings = new ArrayList<String>();
        String str;
        while ((str = readLineSafe()) != null) {
            strings.add(str);
        }
        return strings.toArray(new String[strings.size()]);
    }

    public void close() {
        try {
            raf.close();
        } catch (IOException ignored) {
        }
    }

    public File getFile() {
        return file;
    }

    public RandomAccessFile getRaf() {
        return raf;
    }

    public void reset() throws IOException {
        raf.seek(0);
    }
}
