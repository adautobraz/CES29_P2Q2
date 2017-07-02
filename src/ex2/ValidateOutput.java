package ex2;

import java.text.Normalizer;
import java.util.regex.Pattern;

import javax.xml.bind.ValidationException;

public class ValidateOutput {
	
	private static final Pattern pattern = Pattern.compile("^[a-zA-Z0-9\\s]{0,20}$");
	
	public String validate(String mensagem, String input) throws ValidationException{
		String canonical = normalize(input);
		if (!pattern.matcher(canonical).matches()){
			throw new ValidationException("Formato improprio " + mensagem);
		}
		
		canonical = entityEncode(canonical);
		return canonical;
	}
	
	private String normalize(String input){
		String canonical = java.text.Normalizer.normalize(input, Normalizer.Form.NFKC);
		return canonical;
	}
	
	private static String entityEncode(String input){
		StringBuffer sb = new StringBuffer();
		
		for(int i = 0; i < input.length(); i++){
			char ch = input.charAt(i);
			if(Character.isLetterOrDigit(ch) || Character.isWhitespace(ch)){
				sb.append(ch);
			} else {
				sb.append("&#" + (int)ch + ";");
			}
		}
		return sb.toString();
	}
}
