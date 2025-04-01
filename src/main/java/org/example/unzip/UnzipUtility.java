package org.example.unzip;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.util.zip.ZipEntry;
import java.util.zip.ZipInputStream;
import java.io.BufferedOutputStream;

public class UnzipUtility {
    /**
     * Descomprime el archivo ZIP especificado en el directorio de destino.
     * @param zipFile El archivo ZIP a descomprimir.
     * @param destDirectory La carpeta destino donde se extraerán los archivos.
     * @throws IOException Si ocurre algún error durante la extracción.
     */
    public static void unzip(File zipFile, String destDirectory) throws IOException {
        File destDir = new File(destDirectory);
        if (!destDir.exists()) {
            destDir.mkdirs();
        }

        try (ZipInputStream zipIn = new ZipInputStream(new FileInputStream(zipFile))) {
            ZipEntry entry = zipIn.getNextEntry();
            // Itera sobre las entradas del archivo zip
            while (entry != null) {
                String filePath = destDirectory + File.separator + entry.getName();
                if (!entry.isDirectory()) {
                    // Si es un archivo, extrae el archivo
                    extractFile(zipIn, filePath);
                } else {
                    // Si es un directorio, créalo
                    File dir = new File(filePath);
                    dir.mkdirs();
                }
                zipIn.closeEntry();
                entry = zipIn.getNextEntry();
            }
        }
    }

    /**
     * Extrae un archivo del flujo del ZIP.
     * @param zipIn El flujo del archivo ZIP.
     * @param filePath El path completo de destino donde se debe guardar el archivo.
     * @throws IOException Si ocurre un error durante la extracción.
     */
    private static void extractFile(ZipInputStream zipIn, String filePath) throws IOException {
        try (BufferedOutputStream bos = new BufferedOutputStream(new FileOutputStream(filePath))) {
            byte[] bytesIn = new byte[4096];
            int read;
            while ((read = zipIn.read(bytesIn)) != -1) {
                bos.write(bytesIn, 0, read);
            }
        }
    }
}
