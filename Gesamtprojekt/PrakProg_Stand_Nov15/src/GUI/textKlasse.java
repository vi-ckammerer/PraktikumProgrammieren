package GUI;

public class textKlasse {

	
	private String textFuerGui= "Folgende Schritte müssen für eine erfolgreiche Suche beachtet werden:"
			+ "\n1. Als erstes sollte eine XML-Liste ausgewählt werden auf die später ausgewählte Texte durchsucht werden. "
			+ "Alternativ können Texte auch auf einzelne Wörter geprüft werden. Diese kann man in das untere Textfeld durch ein Leerzeichen getrennt eingeben."
			+ " Welche von beiden Alternativern ausgeführt wird, kann man über die Radiobuttons vor dem Textfeld und dem Dropdown-Menü  bestimmen."
			+ "\n2. Anschließend muss entweder noch die zu durchsuchende XML-Liste, ein ordner oder eine URL angegeben werden"
			+ "\n3. Die Suche kann durch klicken auf den Button \"Text auf Fremdwörter prüfen\"  gestartet werden";

	public String getTextFuerGui() {
		return textFuerGui;
	}

	public void setTextFuerGui(String textFuerGui) {
		this.textFuerGui = textFuerGui;
	}
	
}
