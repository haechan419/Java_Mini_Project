package memberBoard.domain.entity;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

public class Board {
	private int id; // 게시글 ID
	private String title; // 제목
	private String content; // 내용
	private int userId; // 작성자 (User FK)
	private String createdAt; // 작성일
	private String updatedAt; // 수정일

	public Board(int id, String title, String content, int userId) {
		this.id = id;
		this.title = title;
		this.content = content;
		this.userId = userId;
		this.createdAt = LocalDateTime.now().format(DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss"));
		this.updatedAt = this.createdAt;
	}

	public Board(String title, String content, int userId) {
		this(0, title, content, userId);
	}

	// Getter
	public int getId() {
		return id;
	}

	public String getTitle() {
		return title;
	}

	public String getContent() {
		return content;
	}

	public int getUserId() {
		return userId;
	}

	public String getCreatedAt() {
		return createdAt;
	}

	public String getUpdatedAt() {
		return updatedAt;
	}

	// Setter
	public void setTitle(String title) {
		this.title = title;
	}

	public void setContent(String content) {
		this.content = content;
	}

	// 수정일 갱신
	public void updateTimestamp() {
		this.updatedAt = LocalDateTime.now().format(DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss"));
	}

	@Override
	public String toString() {
		return "Board [ID=" + id + ", 제목=" + title + ", 작성자ID=" + userId + ", 작성일=" + createdAt + ", 수정일=" + updatedAt
				+ "]";
	}
}
