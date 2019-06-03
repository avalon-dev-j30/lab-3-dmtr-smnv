package ru.avalon.java.actions;

import java.io.*;
import java.nio.file.*;

/**
 * Действие, которое создает файлы в пределах дискового пространства.
 */
public class FileCreateAction implements Action {

    // переменная для хранения пути создаваемого файла.
    private Path to;

    /**
     * Конструктор класса.
     * @param to - путь создаваемого файла.
     */
    public FileCreateAction(Path to) {
        this.to = to;
    }

    private void create() throws IOException {
        Files.createFile(to);
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
            create();
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
