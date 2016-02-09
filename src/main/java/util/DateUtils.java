package util;

public class DateUtils {
	
	public static Integer getMes(String prefixo)
	{
		if (prefixo.equals("Jan")) {
			return 1;
		} else if (prefixo.equals("Feb")) {
			return 2;
		} else if (prefixo.equals("Mar")) {
			return 3;
		} else if (prefixo.equals("Apr")) {
			return 4;
		} else if (prefixo.equals("May")) {
			return 5;
		} else if (prefixo.equals("Jun")) {
			return 6;
		} else if (prefixo.equals("Jul")) {
			return 7;
		} else if (prefixo.equals("Aug")) {
			return 8;
		} else if (prefixo.equals("Sep")) {
			return 9;
		} else if (prefixo.equals("Oct")) {
			return 10;
		} else if (prefixo.equals("Nov")) {
			return 11;
		} else if (prefixo.equals("Dez")) {
			return 12;
		} else
			return -1;
	}

}
