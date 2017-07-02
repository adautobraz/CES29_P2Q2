package ex2;

import java.io.ByteArrayInputStream;
import java.util.Scanner;
import org.junit.*;
import org.junit.Rule;
import org.junit.Test;
import org.junit.rules.ExpectedException;

public class VulnerableClassTest {
		
	@Rule
    public ExpectedException thrown = ExpectedException.none();

	@Test
	public void whenFilenameIsNullThrowsException() throws Exception {
		thrown.expect(Exception.class);
		thrown.expectMessage("Nome de arquivo nulo");
		VulnerableClass classeTeste = new VulnerableClass();
		classeTeste.vulnerableMethod(null);
	}
	
	@Test
	public void whenFilenameIsEmptyThrowsException() throws Exception {
		thrown.expect(Exception.class);
		thrown.expectMessage("Nome de arquivo nao pode ser vazio");
		VulnerableClass classeTeste = new VulnerableClass();
		classeTeste.vulnerableMethod("");
	}
	
	@Test
	public void whenFilenameHasInvalidLetterThrowsException() throws Exception {
		thrown.expect(Exception.class);
		thrown.expectMessage("Nome de arquivo tem caracteres invalidos");
		VulnerableClass classeTeste = new VulnerableClass();
		classeTeste.vulnerableMethod("Realização.txt");
	}
	
	@Test
	public void whenFilenameHasInvalidSymbolThrowsException() throws Exception {
		thrown.expect(Exception.class);
		thrown.expectMessage("Nome de arquivo tem caracteres invalidos");
		VulnerableClass classeTeste = new VulnerableClass();
		classeTeste.vulnerableMethod("(Arquivo).txt");
	}
	
	@Test
	public void whenInvalidSymbolInCommandThrowsException() throws Exception {		
		thrown.expect(Exception.class);
		thrown.expectMessage("Formato improprio no comando digitado no console");
		VulnerableClass classeTeste = new VulnerableClass();
		
		String comando = "Ç";
		System.setIn(new ByteArrayInputStream(comando.getBytes()));
		classeTeste.setScanner(new Scanner(System.in));

		classeTeste.vulnerableMethod("nomeValido.txt");
	}
	
	@Test
	public void whenInvalidSymbolWhileReadingConsoleThrowsException() throws Exception {		
		thrown.expect(Exception.class);
		thrown.expectMessage("Formato improprio na leitura da linha do arquivo");
		VulnerableClass classeTeste = new VulnerableClass();
		
		String comando = "R\n";
		System.setIn(new ByteArrayInputStream(comando.getBytes()));
		classeTeste.setScanner(new Scanner(System.in));
		classeTeste.vulnerableMethod("input1.txt");
	}
	
}
