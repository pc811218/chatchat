package base.generic.text;

public enum ChatMessage {
	
	JOIN("==========%s已加入聊天!=========="),
	LEAVE("==========%s已離開...=========="),
	SPEAK("%s : %s"),
	WELCOME("[----來跟大家講點幹話吧----]");
	
	
	private String value;
	
	private ChatMessage(String text) {
		this.value = text;
	}
	
	public String val() {
		return value;
	}
}
