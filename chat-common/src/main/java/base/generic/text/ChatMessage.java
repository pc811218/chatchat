package base.generic.text;

public enum ChatMessage {
	
	JOIN("==========%s�w�[�J���!=========="),
	LEAVE("==========%s�w���}...=========="),
	SPEAK("%s : %s"),
	WELCOME("[----�Ӹ�j�a���I�F�ܧa----]");
	
	
	private String value;
	
	private ChatMessage(String text) {
		this.value = text;
	}
	
	public String val() {
		return value;
	}
}
