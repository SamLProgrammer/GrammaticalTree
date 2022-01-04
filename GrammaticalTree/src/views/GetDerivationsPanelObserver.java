package views;

public class GetDerivationsPanelObserver {
	
	private GetDerivationsPanel getDerivationsPanel;
	
	public GetDerivationsPanelObserver(GetDerivationsPanel getDerivationsPanel) {
		this.getDerivationsPanel = getDerivationsPanel;
	}
	
	public void addDerivationRow() {
		getDerivationsPanel.addNewDerivationRow();
	}
}
