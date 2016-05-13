/**
 *  Parametre olarak verilen herhangi bir s�n�f�n i�ine
 *  verilen yaz�y� yazabilen Decorator s�n�f.
 */
import java.awt.*;


class Yazi_Decorator implements Sekil{
	/** Yaz�lacak yaz�n�n sabit ve de�i�ken b�l�mleri */
	String SabitYazi;
	String DegiskenYazi;

	/** i�ine yaz� yaz�lacak �ekil */
	Sekil Icerik;

	public Yazi_Decorator(Sekil icerik, String yazi){
		Icerik = icerik;
		SabitYazi = yazi;
	}

	public void Ciz(Graphics g, int sx, int sy)
	{
		/** Icerigin cizilmesini saglariz */
		Icerik.Ciz(g,sx,sy);

		String msj = SabitYazi+DegiskenYazi;
		int x,y;
		FontMetrics fm;
		fm=g.getFontMetrics();
		y=fm.getHeight();
		x=fm.stringWidth(msj);
		g.drawString(msj,sx+5,sy+5+y/2);
	}

	public void YaziBelirle(String YeniYazi){
		DegiskenYazi = YeniYazi;
	}
}