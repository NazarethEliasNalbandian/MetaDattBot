package Integracion;

import org.example.dominio.Analizador.AnalisisDeCopia;
import org.example.dominio.Documento.Lote;
import org.example.dominio.Reporte.ResultadoLote;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class CopiameAppIT {
    @Test
    public void testBronceTexto() throws Exception {
        // Armado del Escenario
        String directorio = "src/test/resources/lote1";
        Lote lote = new Lote(directorio);
        lote.validar(directorio);
        lote.cargar();
        float umbral = 0.5f;
        AnalisisDeCopia analisis = new AnalisisDeCopia(umbral, lote);
        // Ejecución
        ResultadoLote resultado = analisis.procesar();
        // Chequeo
        assertEquals(1, resultado.getPosiblesCopias().size(),
                "Hay al menos una copia, ya que Pepe y Raúl se copiaron ");
    }
}