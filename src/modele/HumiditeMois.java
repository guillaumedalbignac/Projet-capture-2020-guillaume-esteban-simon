package modele;

public class HumiditeMois {
	
	protected int idQuelJour;
	protected int min;
	protected int max;
	protected int moy;
	
	
	public HumiditeMois(String id,String min, String max, String moy) {
		super();
		this.idQuelJour = Integer.parseInt(id);
		this.min = Integer.parseInt(min);
		this.max = Integer.parseInt(max);
		this.moy = Integer.parseInt(moy);
	}
	
	public int getId() {
		return idQuelJour;
	}
	public void setId(int idQuelHeure) {
		this.idQuelJour = idQuelHeure;
	}
	public int getmin() {
		return min;
	}
	public void setMin(int min) {
		this.min = min;
	}
	public int getMax() {
		return max;
	}
	public void setMax(int max) {
		this.max = max;
	}
	public int getMoy() {
		return moy;
	}
	public void setMoy(int moy) {
		this.moy = moy;
	}

}


