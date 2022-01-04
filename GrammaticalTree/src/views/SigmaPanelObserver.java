package views;

public class SigmaPanelObserver {

	private GetSigmaPanel getTerminalsPanel;

	public SigmaPanelObserver(GetSigmaPanel getTerminalsPanel) {
		this.getTerminalsPanel = getTerminalsPanel;
	}
	
	public void addDerivationColumn() {
		getTerminalsPanel.addNewNoTerminalColumn();
	}

	public void removeCapturePanel(SigmaCapturePanel myInstance) {
		getTerminalsPanel.removeCapturePanel(myInstance);
	}

	public int fieldsSize() {
		return getTerminalsPanel.getFieldsListSize();
	}
}
