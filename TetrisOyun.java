import java.awt.*;
import java.lang.*;


class TetrisOyun implements Sekil {
	Grid OyunAlani;
	Grid Parca;
	int FSkor;
	int px,py;

	public TetrisOyun(int xs,int ys)
	{
		OyunAlani=new Grid(xs,ys,1);
		Temizle();
		Parca = ParcaFactory.YeniParca();
		py = 0;
		px = (xs - Parca.BoyX()) / 2;
		FSkor=0;
	}
	
	void Temizle()
	{
		OyunAlani.Doldur(0,0,OyunAlani.BoyX(),OyunAlani.BoyY(),0);
	}

	public void Ciz(Graphics g, int x, int y)
	{
		g.drawRect(x,y,2+OyunAlani.BoyX()*12, 2+OyunAlani.BoyY()*12);
		for(int j=0;j<OyunAlani.BoyY();j++)
			for(int i=0;i<OyunAlani.BoyX();i++)
				if((OyunAlani.grid[i][j]!=0) || 
                           ((i>=px) && (i<px+Parca.BoyX()) && 
				   (j>=py) && (j<py+Parca.BoyY()) && 
				   (Parca.grid[i-px][j-py]!=0)))
				g.fillRect(3+x+i*12,3+y+j*12,10,10);
	}

	public boolean Adim(boolean Tusla)
	{
	  if(OyunAlani.Bosmu(Parca,px,py+1))
	  {
		// Parcayi indir
		OyunAlani.Indir(Parca,px,py);

		// Dolu satýrlarý temizle
		int SatirPuani = 50;
		for(int j=OyunAlani.BoyY()-1;j>=0;j--)
		while(SatirDolumu(j)==true)
		{
			SatirSil(j);
			// basit puanlama
			FSkor+=SatirPuani;
			SatirPuani *= 2;
		}

		// Yeni bir parça
		Parca = ParcaFactory.YeniParca();
		py=0;
		px=(OyunAlani.BoyX()-Parca.BoyX())/2;
		if(OyunAlani.Bosmu(Parca,px,py))
		return true;
	  }
	  py++;
	  if(Tusla)
		FSkor += 1;
	  return false;
	}

	private boolean SatirDolumu(int y)
	{
		for(int i=0;i<OyunAlani.BoyX();i++)
			if(OyunAlani.grid[i][y]==0)
				return false;
		return true;
	}

	private void SatirSil(int y)
	{
		for(int j=y;j>0;j--)
			for(int i=0;i<OyunAlani.BoyX();i++)
				OyunAlani.grid[i][j]=OyunAlani.grid[i][j-1];
		for(int i=0;i<OyunAlani.BoyX();i++)
			OyunAlani.grid[i][0]=0;
	}

// Veri alma fonksiyonlarý

	public int Skor()
	{
		return FSkor;
	}

// Oyun arayüz fonksiyonlarý

	public void SolaGit(int i)
	{
		if(OyunAlani.Bosmu(Parca,px-i,py))
			return;
		px-=i;
	}

	public void SolaGit()
	{
		SolaGit(1);
	}

	public void SagaGit(int i)
	{
		if(OyunAlani.Bosmu(Parca,px+i,py))
			return;
		px+=i;
	}

	public void SagaGit()
	{
		SagaGit(1);
	}

	public void SagaDon()
	{
		Grid test = ParcaFactory.YeniParca(Parca);
		test.SagaDon();
		if(!OyunAlani.Bosmu(test,px,py))
			Parca=test;
	}

	public void SolaDon()
	{
		Grid test = ParcaFactory.YeniParca(Parca);
		test.SolaDon();
		if(!OyunAlani.Bosmu(test,px,py))
			Parca=test;
	}

	public void Dus()
	{
		while(!OyunAlani.Bosmu(Parca,px,py+1)){
			py++;
			FSkor += 2;
		}
	}
}


class Memento{
	TetrisOyun Hedef;
	Grid OyunAlani;
	Grid Parca;
	int FSkor;
	int px,py;

	public Memento(TetrisOyun hedef){
		Hedef = hedef;
		OyunAlani = new Grid(Hedef.OyunAlani);
		Parca = new Grid(Hedef.Parca);
		FSkor = Hedef.FSkor;
		px = Hedef.px;
		py = Hedef.py;
	}
	
	public void HedefDegistir(TetrisOyun hedef){
		Hedef = hedef;
	}

	public void Yukle(){
		try{
			Hedef.OyunAlani = OyunAlani;
			Hedef.Parca = Parca;
			Hedef.FSkor = FSkor;
			Hedef.px = px;
			Hedef.py = py;
		}catch(NullPointerException e){
			System.out.println("Oyun eski haline getirilemedi");
		}
	}
}
