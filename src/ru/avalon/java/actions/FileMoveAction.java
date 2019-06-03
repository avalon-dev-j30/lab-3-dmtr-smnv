package ru.avalon.java.actions;

import java.io.*;
import java.nio.file.*;
import static java.nio.file.StandardCopyOption.*;

/**
 * Действие, которое перемещает файлы в пределах дискового пространства.
 */
public class FileMoveAction implements Action {

    // пременная для хранения текущего пути файла.
    private Path from;
    
    // переменная для хранения нового пути файла.
    private Path to;

    /**
     * Конструктор класса.
     * @param from - существующий путь перемещаемого файла.
     * @param to - новый путь перемещаемого файла.
     */
    public FileMoveAction(Path from, Path to) {
        this.from = from;
        this.to = to;
    }

    private void move() throws IOException {
        Files.move(from, to, REPLACE_EXISTING);
        try {
            close();
        } catch (Exception ignore) {
        } finally {
            service.shutdownNow();
        }
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public void run() {
        try {
            move();
        } catch (IOException e) {
            e.printStackTrace();
        } finally {
            try {
                close();
            } catch (Exception ignore) {
            } finally {
                service.shutdownNow();
            }
        }
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public void close() throws Exception {
        service.shutdown();
    }
}
