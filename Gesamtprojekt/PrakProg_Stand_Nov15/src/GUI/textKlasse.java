package GUI;

public class textKlasse {

	
	private String textFuerGui= "Folgende Schritte m�ssen f�r eine erfolgreiche Suche beachtet werden:"
			+ "\n1. Als erstes sollte eine XML-Liste ausgew�hlt werden auf die sp�ter ausgew�hlte Texte durchsucht werden. "
			+ "Alternativ k�nnen Texte auch auf einzelne W�rter gepr�ft werden. Diese kann man in das untere Textfeld durch ein Leerzeichen getrennt eingeben."
			+ " Welche von beiden Alternativern ausgef�hrt wird, kann man �ber die Radiobuttons vor dem Textfeld und dem Dropdown-Men�  bestimmen."
			+ "\n2. Anschlie�end muss entweder noch die zu durchsuchende XML-Liste, ein ordner oder eine URL angegeben werden"
			+ "\n3. Die Suche kann durch klicken auf den Button \"Text auf Fremdw�rter pr�fen\"  gestartet werden";

	public String getTextFuerGui() {
		return textFuerGui;
	}

	public void setTextFuerGui(String textFuerGui) {
		this.textFuerGui = textFuerGui;
	}
	
}
