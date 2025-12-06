package core.io;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.nio.file.StandardOpenOption;

public class ExportadorJSON {
	/**
	 * Exporta o conteúdo de um ficheiro JSON para um ficheiro TXT.
	 * @param jsonPath Caminho do ficheiro JSON de origem
	 * @param txtPath Caminho do ficheiro TXT de destino
	 * @throws IOException Se ocorrer erro de leitura ou escrita
	 */
	public static void exportJsonToTxt(String jsonPath, String txtPath) throws IOException {
		// Lê todo o conteúdo do ficheiro JSON
		String jsonContent = new String(Files.readAllBytes(Paths.get(jsonPath)));
		// Escreve o conteúdo no ficheiro TXT
		Files.write(Paths.get(txtPath), jsonContent.getBytes(), StandardOpenOption.CREATE, StandardOpenOption.TRUNCATE_EXISTING);
	}
}
