package modele;

public class AlerteMobile {
	protected int alerte;
	protected int seuil;
	
	public AlerteMobile(String alerte,String seuil) {
		super();
		this.alerte = Integer.parseInt(alerte);
		this.seuil = Integer.parseInt(seuil);
	}
	
	public int getAlerte() {
		return alerte;
	}
	public void setAlerte(int alerte) {
		this.alerte = alerte;
	}
	public int getSeuil() {
		return seuil;
	}
	public void setSeuil(int seuil) {
		this.seuil = seuil;
	}

}
