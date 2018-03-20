package blog.model;

public class Comment {

	private int id=-1;
	private int article_id=-1;
	private String nickname=null;
	private String content=null;
	private String time=null;
	private String ip=null;

	/*
	 * bean的get和set大小写要规范否则el表达式会报错
	 * */
	
	@Override
	public String toString() {
		return "Comment [id=" + id + ", article_id=" + article_id + ", nickname=" + nickname + ", content=" + content
				+ ", time=" + time + "]";
	}

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public int getArticle_id() {
		return article_id;
	}

	public void setArticle_id(int article_id) {
		this.article_id = article_id;
	}

	public String getNickname() {
		return nickname;
	}

	public void setNickname(String nickname) {
		this.nickname = nickname;
	}

	public String getContent() {
		return content;
	}

	public void setContent(String content) {
		this.content = content;
	}

	public String getTime() {
		return time;
	}

	public void setTime(String time) {
		this.time = time;
	}
	
	public String getIp() {
		return ip;
	}

	public void setIp(String ip) {
		this.ip = ip;
	}

}
