package views;

public class VariablesPanelObserver {

	private GetVariablesPanel getNoTerminalsPanel;

	public VariablesPanelObserver(GetVariablesPanel getNoTerminalsPanel) {
		this.getNoTerminalsPanel = getNoTerminalsPanel;
	}
	
	public void addDerivationColumn() {
		getNoTerminalsPanel.addNewNoTerminalColumn();
	}

	public void removeCapturePanel(VariablesCapturePanel myInstance) {
		getNoTerminalsPanel.removeCapturePanel(myInstance);
	}

	public int fieldsSize() {
		return getNoTerminalsPanel.getFieldsListSize();
	}
}
