package spring.quartz;

import java.io.Serializable;
import java.util.Date;

public class Notice implements Serializable {

	/**  */
	private static final long serialVersionUID = 1L;
	
	private String id;
	
	private String noticeName;
	
	private String noticeContent;
	
	private Date startTime;
	
	private Integer playCount;
	
	private Integer playInterval;

	public String getNoticeName() {
		return noticeName;
	}

	public void setNoticeName(String noticeName) {
		this.noticeName = noticeName;
	}

	public String getNoticeContent() {
		return noticeContent;
	}

	public void setNoticeContent(String noticeContent) {
		this.noticeContent = noticeContent;
	}

	public Date getStartTime() {
		return startTime;
	}

	public void setStartTime(Date startTime) {
		this.startTime = startTime;
	}

	public Integer getPlayCount() {
		return playCount;
	}

	public void setPlayCount(Integer playCount) {
		this.playCount = playCount;
	}

	public Integer getPlayInterval() {
		return playInterval;
	}

	public void setPlayInterval(Integer playInterval) {
		this.playInterval = playInterval;
	}

	public String getId() {
		return id;
	}

	public void setId(String id) {
		this.id = id;
	}

	public Notice() {
	}

	@Override
	public String toString() {
		return "Notice [id=" + id + ", noticeName=" + noticeName + ", noticeContent=" + noticeContent + ", startTime="
				+ startTime + ", playCount=" + playCount + ", playInterval=" + playInterval + "]";
	}
}
