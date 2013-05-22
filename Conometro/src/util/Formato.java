package util;

public class Formato {

	public static String formatTime(Integer hora, Integer minutos, Integer segundos) {
		String retorno = auxiliar(hora) + ":" + auxiliar(minutos) + ":"	+ auxiliar(segundos);
		return retorno;
	}
	
	private static String auxiliar(Integer i){
		String retorno = null;
		if (i < 10) {
			retorno = "0" + i;
		} else {
			retorno = i.toString();
		}
		return retorno;
	}
}
