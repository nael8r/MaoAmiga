package util;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;

public class DateTimeFormat {
	private static final SimpleDateFormat sdfData = new SimpleDateFormat("dd/MM/yyyy");
	
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
