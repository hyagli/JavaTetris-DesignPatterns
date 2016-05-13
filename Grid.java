public class Grid {
	public int grid[][];
	public int def; // Ön deðer
	protected int x,y;
	
	public Grid(int sx,int sy,int sdeger)
	{
		x=sx;
		y=sy;
		def=sdeger;
		grid = new int[x][y];
		for(int i=0;i<x;i++)
			for(int j=0;j<y;j++)
				grid[i][j]=def;
	}

	public Grid(int sx,int sy)
	{
		this(sx,sy,0);
	}

	public Grid(Grid g)
	{
		x=g.x;
		y=g.y;
		def = g.def;
		grid = new int[x][y];
		for(int i=0;i<x;i++)
			for(int j=0;j<y;j++)
				grid[i][j]=g.grid[i][j];
	}

	public void Doldur(int sx,int sy,int w,int h,int deger)
	{
		for(int i=0;i<w;i++)
			for(int j=0;j<h;j++)
				if((sx+i<x) && (sy+j<y) && (sx+i>=0) && (sy+j>=0)){
					grid[sx+i][sy+j]=deger;
				}
	}

	public void SagaDon() // Saða döndürme
	{
		int yeniGrid[][] = new int[y][x];
		int s=y-1;
		
		for(int i=0;i<y;i++)
		for(int j=0;j<x;j++)
		{
			yeniGrid[i][j] = grid[j][s-i];
		}
		grid=yeniGrid;

		int tmp=x;
		x=y;
		y=tmp;
	}

	public void SolaDon() // Sola Döndürme
	{
		int yeniGrid[][] = new int[y][x];
		int s=x-1;
		for(int i=0;i<y;i++)
		for(int j=0;j<x;j++)
			yeniGrid[i][j]=grid[s-j][i];
		grid=yeniGrid;

		int tmp=x;
		x=y;
		y=tmp; 
	}

	public int BoyX() {return x;}
	public int BoyY() {return y;}

	public void Indir(Grid model,int sx,int sy)
	{
		int mx,my;

		mx=model.x;
		my=model.y;
		if(mx+sx>x) mx=x-sx;
		if(my+sy>y) my=y-sy;

		for(int i=0;i<mx;i++)
		for(int j=0;j<my;j++)
			if((sx+i<x) && (sy+j<y) &&  (sx+i>=0) && (sy+j>=0))
				grid[sx+i][sy+j]= grid[sx+i][sy+j] | model.grid[i][j];
	}

	public boolean Bosmu(Grid model,int sx,int sy)
	{
		int mx,my;

		mx=model.x;
		my=model.y;

		for(int i=0;i<mx;i++)
		for(int j=0;j<my;j++)
		{
			if((sx+i<x) && (sy+j<y) &&  (sx+i>=0) && (sy+j>=0)){
				if(grid[sx+i][sy+j]!=0 && model.grid[i][j]!=0)
					return true;
			}
			else{ 
				if(def!=0 && model.grid[i][j]!=0)
					return true;
			}
		} 
		return false;
	}
}