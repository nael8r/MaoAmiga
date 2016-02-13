package util;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;

/*
	Classe para a conversão do formanto da data
*/
public class DateTimeFormat {
	// Molda o formato a ser retornado
	private static final SimpleDateFormat sdfData = new SimpleDateFormat("dd/MM/yyyy");
	
	// Método que realiza a conversão 
	public static Date formatDataParaBR(Date data)
	{
		String dataParaString = sdfData.format(data);
		
		try {
			return sdfData.parse(dataParaString);
		} catch (ParseException e) {
			e.printStackTrace();
			return null;
		}
	}
}
