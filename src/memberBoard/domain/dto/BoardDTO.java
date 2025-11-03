package memberBoard.domain.dto;

//DTO : 계층 간 (특히 클라이언트와 서버, 또는 컨트롤러와 서비스)데이터를 주고받기 위한 객체

public class BoardDTO {
    private int id;
    private String title;
    private String content;
    private int userId;
    private String createdAt;
    private String updatedAt;

    public BoardDTO() {}

    public BoardDTO(int id, String title, String content, int userId, String createdAt, String updatedAt) {
        this.id = id;
        this.title = title;
        this.content = content;
        this.userId = userId;
        this.createdAt = createdAt;
        this.updatedAt = updatedAt;
    }

    public BoardDTO(String title, String content, int userId) {
        this(0, title, content, userId, null, null);
    }

    // Getter & Setter

    public int getId() {
        return id;
    }
    public void setId(int id) {
        this.id = id;
    }

    public String getTitle() {
        return title;
    }
    public void setTitle(String title) {
        this.title = title;
    }

    public String getContent() {
        return content;
    }
    public void setContent(String content) {
        this.content = content;
    }

    public int getUserId() {
        return userId;
    }
    public void setUserId(int userId) {
        this.userId = userId;
    }

    public String getCreatedAt() {
        return createdAt;
    }
    public void setCreatedAt(String createdAt) {
        this.createdAt = createdAt;
    }

    public String getUpdatedAt() {
        return updatedAt;
    }
    public void setUpdatedAt(String updatedAt) {
        this.updatedAt = updatedAt;
    }

    @Override
    public String toString() {
        return "게시글 ID: " + id
             + ", 제목: " + title
             + ", 내용: " + content   // 내용 포함
             + ", 작성자ID: " + userId
             + ", 작성일: " + createdAt
             + ", 수정일: " + updatedAt;
    }

}
