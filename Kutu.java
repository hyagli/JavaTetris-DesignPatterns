/**
 *  Verilen Grafik alanina istenilen boyutta bir dikdörtgen çizen sınıf
 */

class Kutu implements Sekil{
	int Genislik;
	int Yukseklik;

	public Kutu(int Gen, int Yuk){
		Genislik = Gen;
		Yukseklik = Yuk;
	}

	public void Genislik(int Gen,int Yuk){
		Genislik = Gen;
		Yukseklik = Yuk;
	}

	public void Ciz(java.awt.Graphics g, int sx, int sy)
	{
		g.draw3DRect(sx-1,sy,Genislik,Yukseklik,true);
	}
}