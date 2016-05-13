import java.lang.*;
import java.awt.*;
import java.awt.event.*;

/**
 * Tetris oyununun applet versiyonu
 * Sistemden iki parametre alýnýyor: X ve Y
 * Bu parametreler oyunun alanýný belirliyor
 * YAZAR: Hüseyin Yaðlý   huseyinyagli@yahoo.com
 */

public class Tetris extends java.applet.Applet implements Runnable {
	/** Oyun durumu  */
	TetrisOyun Oyun;

	/** Oyunu kaydetmeye yarayan Memento sýnýfý */
	Memento Kayit;

	/** Oyunu çalýþtýran görev */
	private volatile Thread OyunCalistir;
	
	/** Oyun alan genisligi */
	int xs,ys;
	int x,y;

	/** Puanýn yazýlacaðý kutu */
	Yazi_Decorator Skor;

	/** Oyunun þu anki durumunu gösteren kutu */
	Yazi_Decorator Durum;

	/** Oyunun durum göstergeleri */
	boolean OyunBitti;
	boolean Mola;


	/**
	 * java.applet.Applet sýnýfýndaki init fonksiyonunun
	 * yerini alýyor. Deðiþkenler ilklenir, resize() ve getfocus() çaðrýlýr
       * ayrýca oyun tarafýndan kullanýlacak tuþlar için görevler atanýr
	 */

	public void init()
	{
		System.out.println("Applet: "+toString());
	
		try {
			x=Integer.parseInt(getParameter("X"));
		} catch (NullPointerException e) { x=10; }
		  catch (NumberFormatException e) { x=10; }

		try {
			y=Integer.parseInt(getParameter("Y"));
		} catch (NullPointerException e) { y=20; }
		  catch (NumberFormatException e) { y=20; }
		 
		Oyun = new TetrisOyun(x,y);
		Skor = new Yazi_Decorator(new Kutu(80,16),"Skor: ");
		Durum = new Yazi_Decorator(new Kutu(95,16),"Durum: ");
		Kayit = null;

		xs=x*12+16;  // Oyun alaný
		ys=y*12+4   // Oyun alaný
		       +60;	// Skor ve Durum
		resize(xs,ys);
		requestFocus();

		/** Basýlacak tuþlar için görev atama */
		addKeyListener(new KeyListener(){
		public void keyPressed(KeyEvent e){
			int Tus = e.getKeyCode();

			switch (Tus)
			{
				/** Baþtan baþlama */
				case KeyEvent.VK_R:
					OyunBitti = false;
					Oyun = new TetrisOyun(x,y);
					if(Kayit != null)
						Kayit.HedefDegistir(Oyun);
					if(!Mola && !OyunBitti) stop();
					start(); repaint(); break;
				/** Oyunu þu anki haliyle kaydetme */
				case KeyEvent.VK_K:
					Kayit = new Memento(Oyun);
					break;
				/** Oyunu en son kaydedilen haliyle yükleme */
				case KeyEvent.VK_Y:
					if(Kayit != null) Kayit.Yukle();
					repaint(); break;
				/** Oyunu duraklatma */
				case KeyEvent.VK_P:
					if(Mola && !OyunBitti){ start(); Mola = false; }
					else{ stop();  Mola = true;  }
					repaint(); break;
			}

			if(!OyunBitti && !Mola)
			{
				switch (Tus)
				{
					case KeyEvent.VK_LEFT : Oyun.SolaGit(); repaint(); break;
					case KeyEvent.VK_RIGHT: Oyun.SagaGit(); repaint(); break;
					case KeyEvent.VK_UP   : Oyun.SagaDon(); repaint(); break;
					case KeyEvent.VK_DOWN : OyunBitti = Oyun.Adim(true); repaint(); break;
					case KeyEvent.VK_Z    : Oyun.SolaDon(); repaint(); break;
					case KeyEvent.VK_X    : Oyun.SagaDon(); repaint(); break;
					case KeyEvent.VK_SPACE: Oyun.Dus();     repaint(); break;
				}
		  	}
		}
		public void keyReleased(KeyEvent e){}
		public void keyTyped(KeyEvent e){}
		});
	}

	// Oyuna baslama
	public void start()
	{
		if(OyunCalistir == null)
		{
			OyunCalistir = new Thread(this);
			OyunCalistir.start();
		}
	}

	// Oyunu durdurma
	public void stop()
	{
		if(OyunCalistir != null && OyunCalistir.isAlive())
			OyunCalistir.interrupt();
		OyunCalistir = null;
	}
	
	public void destroy()
	{
	}
   
	public void run()
	{
		OyunBitti = false;
		Mola = false;
		while(!OyunBitti && !Mola)
		{
			OyunBitti = Oyun.Adim(false);
			repaint();
			try {Thread.sleep(500);} catch (InterruptedException e){ return; }
		}
	}

	public void paint(Graphics g)
	{
		Oyun.Ciz(g,2,2);
		Skor.YaziBelirle((new Integer(Oyun.Skor())).toString());
		Skor.Ciz(g,5,ys-40);
		if(OyunBitti)
			Durum.YaziBelirle("Bitti");
		else if(Mola)
			Durum.YaziBelirle("Mola");
		else
			Durum.YaziBelirle("Oyunda");
		Durum.Ciz(g,90,ys-40);
		g.drawString("TETRIS",xs,20);
		g.drawString("Hüseyin",xs,50);
		g.drawString("Yaðlý",xs,65);
	}
}