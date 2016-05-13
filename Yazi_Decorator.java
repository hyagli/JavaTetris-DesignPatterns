/**
 *  Parametre olarak verilen herhangi bir sýnýfýn içine
 *  verilen yazýyý yazabilen Decorator sýnýf.
 */
import java.awt.*;


class Yazi_Decorator implements Sekil{
	/** Yazýlacak yazýnýn sabit ve deðiþken bölümleri */
	String SabitYazi;
	String DegiskenYazi;

	/** içine yazý yazýlacak þekil */
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