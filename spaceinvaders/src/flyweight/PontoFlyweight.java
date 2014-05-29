package flyweight;

public class PontoFlyweight {
	
	@SuppressWarnings("unused")
	private int x, y;
	
	public PontoFlyweight(int x, int y){
		this.x = x;
		this.y = y;
	}

	public int getX() {
		return x;
	}

	public void setX(int x) {
		this.x = x;
	}

	public int getY() {
		return y;
	}

	public void setY(int y) {
		this.y = y;
	}
	
}
