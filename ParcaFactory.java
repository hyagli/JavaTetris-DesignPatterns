import java.lang.*;


/** Factory sýnýfý : TetrisParca
 *  YeniParca fonksiyonu istenilen türdeki parçayi döndürür
 *  Diðer sýnýflar parçanýn türünden haberdar olmazlar
 */
public final class ParcaFactory{
	public static Grid YeniParca(){
		int Tur = (int)(Math.random()*7.0);
		return YeniParca(Tur);
	}

	public static Grid YeniParca(int Tur){
		switch(Tur){
			case 0: return new ParcaT();
			case 1: return new ParcaKare();
			case 2: return new ParcaS1();
			case 3: return new ParcaS2();
			case 4: return new ParcaL1();
			case 5: return new ParcaL2();
			case 6: return new ParcaI();
		}
		return YeniParca();
	}

	public static Grid YeniParca(Grid eski){
		return new Grid(eski);
	}
}


/**
 *  PARÇA SINIFLARI
 */
class ParcaT extends Grid{
	ParcaT(){
		super(3,3);
		grid[0][0]=0;
		grid[1][0]=1;
		grid[2][0]=0;
		grid[0][1]=1;
		grid[1][1]=1;
		grid[2][1]=1;
		grid[0][2]=0;
		grid[1][2]=0;
		grid[2][2]=0;
	}
}

class ParcaKare extends Grid{
	ParcaKare(){
		super(2,2);
		grid[0][0]=1;
		grid[1][0]=1;
		grid[0][1]=1;
		grid[1][1]=1;
	}
}


class ParcaS1 extends Grid{
	ParcaS1(){
		super(3,3);
		grid[0][0]=0;
		grid[1][0]=1;
		grid[2][0]=0;
		grid[0][1]=0;
		grid[1][1]=1;
		grid[2][1]=1;
		grid[0][2]=0;
		grid[1][2]=0;
		grid[2][2]=1;
	}
}



class ParcaS2 extends Grid{
	ParcaS2(){
		super(3,3);
		grid[0][0]=0;
		grid[1][0]=1;
		grid[2][0]=0;
		grid[0][1]=1;
		grid[1][1]=1;
		grid[2][1]=0;
		grid[0][2]=1;
		grid[1][2]=0;
		grid[2][2]=0;
	}
}



class ParcaL1 extends Grid{
	ParcaL1(){
		super(3,3);
		grid[0][0]=0;
		grid[1][0]=0;
		grid[2][0]=1;
		grid[0][1]=1;
		grid[1][1]=1;
		grid[2][1]=1;
		grid[0][2]=0;
		grid[1][2]=0;
		grid[2][2]=0;
	}
}



class ParcaL2 extends Grid{
	ParcaL2(){
		super(3,3);
		grid[0][0]=1;
		grid[1][0]=0;
		grid[2][0]=0;
		grid[0][1]=1;
		grid[1][1]=1;
		grid[2][1]=1;
		grid[0][2]=0;
		grid[1][2]=0;
		grid[2][2]=0;
	}
}



class ParcaI extends Grid{
	ParcaI(){
		super(3,4);
		grid[0][0]=0;
		grid[1][0]=1;
		grid[2][0]=0;
		grid[0][1]=0;
		grid[1][1]=1;
		grid[2][1]=0;
		grid[0][2]=0;
		grid[1][2]=1;
		grid[2][2]=0;
		grid[0][3]=0;
		grid[1][3]=1;
		grid[2][3]=0;
	}
}