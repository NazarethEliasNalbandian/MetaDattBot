package org.example.generadorLote;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.util.List;

public class GeneradorLote {
    public static void main(String[] args) throws IOException {
        String directorio = "src/test/resources/lote_prueba";
        Files.createDirectories(Path.of(directorio));

        List<String> contenidos = List.of("guitarra", "guitara", "gutarra", "guitarrra", "guitarto");

        for (int i = 0; i < contenidos.size(); i++) {
            Path archivo = Path.of(directorio, "doc" + (i + 1) + ".txt");
            Files.writeString(archivo, contenidos.get(i));
        }

        System.out.println("Archivos generados en: " + directorio);
    }
}
