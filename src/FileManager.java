import java.io.IOException;
import java.nio.file.DirectoryStream;
import java.nio.file.Files;
import java.nio.file.Path;
import java.util.ArrayList;
import java.util.List;

public class FileManager {
    private Path rootPath;
    private List<Path> fileList;

    public FileManager(Path rootPath) throws IOException {
        this.rootPath = rootPath;
        this.fileList = new ArrayList<>();
        collectionFileList(rootPath);
    }

    public List<Path> getFileList() {
        return fileList;
    }

    private void collectionFileList(Path path) throws IOException {
        // Добавляем только файлы
        if (Files.isRegularFile(path)) {
            Path relativePath = rootPath.relativize(path);
            fileList.add(relativePath);
        }

        //Добавляем содержимое директории
        if (Files.isDirectory(path)) {
            //Рекурсивно проходимся по всему содержимому директории
            // чтобы не писать код по вызову close для DirectoryStream, обернём вызов newDirectoryStream в try-with-resources
            try (DirectoryStream<Path> directoryStream = Files.newDirectoryStream(path)) {
                for (Path file : directoryStream) {
                    collectionFileList(file);
                }
            }
        }
    }
}
