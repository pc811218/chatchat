package chat.model.entity;

import java.io.Serializable;
import javax.persistence.*;
import java.util.Date;
import java.util.Set;


/**
 * The persistent class for the users database table.
 * 
 */
@Entity
@Table(name="users")
@NamedQuery(name="User.findAll", query="SELECT u FROM User u")
public class User implements Serializable {
	private static final long serialVersionUID = 1L;

	@Id
	@GeneratedValue(strategy=GenerationType.IDENTITY)
	private String name;

	@Temporal(TemporalType.TIMESTAMP)
	@Column(name="create_date")
	private Date createDate;

	@Column(name="use_times")
	private String useTimes;

	//bi-directional many-to-one association to MessageLog
	@OneToMany(mappedBy="user")
	private Set<MessageLog> messageLogs;

	public User() {
	}

	public String getName() {
		return this.name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public Date getCreateDate() {
		return this.createDate;
	}

	public void setCreateDate(Date createDate) {
		this.createDate = createDate;
	}

	public String getUseTimes() {
		return this.useTimes;
	}

	public void setUseTimes(String useTimes) {
		this.useTimes = useTimes;
	}

	public Set<MessageLog> getMessageLogs() {
		return this.messageLogs;
	}

	public void setMessageLogs(Set<MessageLog> messageLogs) {
		this.messageLogs = messageLogs;
	}

	public MessageLog addMessageLog(MessageLog messageLog) {
		getMessageLogs().add(messageLog);
		messageLog.setUser(this);

		return messageLog;
	}

	public MessageLog removeMessageLog(MessageLog messageLog) {
		getMessageLogs().remove(messageLog);
		messageLog.setUser(null);

		return messageLog;
	}

}