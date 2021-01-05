package modele;

public class ApercuMobile {

	protected int mesureActuelle;
	protected int moyJour;
	protected int moyAnnee;	
	
	public ApercuMobile(String mesureActuelle, String moyJour, String moyAnnee) {
		super();
		this.mesureActuelle = Integer.parseInt(mesureActuelle);
		this.moyJour = Integer.parseInt(moyJour);
		this.moyAnnee = Integer.parseInt(moyAnnee);
	}
	

	public int getMesureActuelle() {
		return mesureActuelle;
	}
	public void setMesureActuelle(int mesureActuelle) {
		this.mesureActuelle = mesureActuelle;
	}
	public int getMoyJour() {
		return moyJour;
	}
	public void setMoyJour(int moyJour) {
		this.moyJour = moyJour;
	}
	public int getMoyAnnee() {
		return moyAnnee;
	}
	public void setMoyAnnee(int moyAnnee) {
		this.moyAnnee = moyAnnee;
	}

}
