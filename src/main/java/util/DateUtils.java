package util;

public class DateUtils {
	
	public static Integer getMes(String prefixo)
	{
		if (prefixo.equals("Jan")) {
			return 0;
		} else if (prefixo.equals("Feb")) {
			return 1;
		} else if (prefixo.equals("Mar")) {
			return 2;
		} else if (prefixo.equals("Apr")) {
			return 3;
		} else if (prefixo.equals("May")) {
			return 4;
		} else if (prefixo.equals("Jun")) {
			return 5;
		} else if (prefixo.equals("Jul")) {
			return 6;
		} else if (prefixo.equals("Aug")) {
			return 7;
		} else if (prefixo.equals("Sep")) {
			return 8;
		} else if (prefixo.equals("Oct")) {
			return 9;
		} else if (prefixo.equals("Nov")) {
			return 10;
		} else if (prefixo.equals("Dez")) {
			return 11;
		} else return 0;
	}

}
