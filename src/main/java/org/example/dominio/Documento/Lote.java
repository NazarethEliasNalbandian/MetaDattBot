package org.example.dominio.Documento;

import lombok.Getter;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.List;
import java.util.stream.Stream;

@Getter
public class Lote {
    private String directorio;
    private List<Documento> documentos;
    private Path dirpath;

    public Lote(String directorio) {
        this.directorio = directorio;
        this.documentos = new ArrayList<>();
        this.dirpath = Paths.get(this.directorio);
    }

    public void cargar() throws IOException {
        // String directorio = "C:\\Users\\nazar\\OneDrive - UTN.BA\\Desktop\\CopiaMe_Java\\Copia.Me\\src\\test\\resources\\lote_prueba";

        try (Stream<Path> stream = Files.list(this.dirpath)) {
            List<Path> archivos = stream
                    .filter(path -> path.toString().endsWith(".txt"))
                    .toList();

            for (Path archivo : archivos) {
                String contenido = Files.readString(archivo);
                Documento doc = new Documento(archivo.getFileName().toString(), contenido);
                this.agregarDocumento(doc);
            }
        }
    }

    public void validar(String directorio){
        Path pathLote = Paths.get(directorio);
        if (!Files.exists(pathLote)) {
            System.err.println("'" + directorio + "' no existe...");
            System.exit(1);
        }
    }

    public void agregarDocumento(Documento doc) {
        documentos.add(doc);
    }
}
