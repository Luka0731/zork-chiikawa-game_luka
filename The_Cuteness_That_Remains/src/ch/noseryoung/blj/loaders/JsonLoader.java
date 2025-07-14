package ch.noseryoung.blj.loaders;

import java.io.File;
import java.io.InputStream;
import java.net.URL;
import java.nio.charset.StandardCharsets;
import java.util.*;

public abstract class JsonLoader {

    protected String readResourceFile(String filePath) {
        InputStream stream = getClass().getClassLoader().getResourceAsStream(filePath);
        if (stream == null) throw new RuntimeException("File not found: " + filePath);
        Scanner scanner = new Scanner(stream, StandardCharsets.UTF_8).useDelimiter("\\A");
        return scanner.hasNext() ? scanner.next() : "";
    }

    protected List<String> listJsonFiles(String resourceFolder) {
        List<String> fileNames = new ArrayList<>();
        URL folderUrl = getClass().getClassLoader().getResource(resourceFolder);
        if (folderUrl == null) throw new RuntimeException("Folder not found: " + resourceFolder);

        File folder = new File(folderUrl.getFile());
        File[] files = folder.listFiles();
        if (files == null) return fileNames;

        for (File file : files) {
            if (file.getName().endsWith(".json")) {
                fileNames.add(resourceFolder + "/" + file.getName());
            }
        }
        return fileNames;
    }
}