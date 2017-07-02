package ex2;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.util.Scanner;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class VulnerableClass {
	
	private Pattern filePattern = Pattern.compile("[^A-Za-z0-9._]");
	private ValidateOutput vo = new ValidateOutput();
	private Scanner _scanner;
	
	public void setScanner(Scanner scanner){
		_scanner = scanner;
	}
	
	public void vulnerableMethod(String FILENAME) throws Exception{
		//Validar nome do Arquivo
		
		if(FILENAME == null){
			throw new Exception("Nome de arquivo nulo");
		}
		
		if(FILENAME.length() < 1){
			throw new Exception("Nome de arquivo nao pode ser vazio");
		}
		Matcher fileMatcher = filePattern.matcher(FILENAME);
		if(fileMatcher.find()){
			throw new Exception("Nome de arquivo tem caracteres invalidos");
		}
		
		while (true) {
		    Scanner console = _scanner;
		    System.out.print("Digite a operacao desejada para realizar no arquivo <R para ler um arquivo, "
		    		+ "W para escrever em um arquivo>? ");
			
		    String opr= console.nextLine();
		    vo.validate("no comando digitado no console",opr);
		    if (opr.equals("R")){
				BufferedReader bufferReader = null;
				
				try {
					bufferReader = new BufferedReader(new FileReader(FILENAME));
					String sCurrentLine;
					while ((sCurrentLine = bufferReader.readLine()) != null) {
						vo.validate("na leitura da linha do arquivo", sCurrentLine);
						System.out.println(sCurrentLine);
					}

				} catch (IOException e) {
					e.printStackTrace();
				} 
			}
			
			else {
				  BufferedWriter buffWrite;
				  
				  try {
					buffWrite = new BufferedWriter(new FileWriter(FILENAME));
					String linha = "";
					System.out.println("Escreva algo: ");
				    linha = console.nextLine();
					vo.validate("na leitura da linha do console", linha);
				    buffWrite.append(linha + "\n");
				     
				} catch (IOException e) {
					e.printStackTrace();
				} 
			}
		    
		    console.close();
		}
	}
}
