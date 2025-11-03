// ============================================
// BoardDTO.java - 데이터 전송 객체
// ============================================
package memberBoard.domain.dto;

public class BoardDTO {
    private int id;
    private String title;
    private String content;
    private int userId;
    private String createdAt;
    private String updatedAt;
    
    // 생성자
    public BoardDTO() {}
    
    public BoardDTO(int id, String title, String content, int userId, 
                    String createdAt, String updatedAt) {
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
    
    // Getters & Setters
    public int getId() { return id; }
    public void setId(int id) { this.id = id; }
    
    public String getTitle() { return title; }
    public void setTitle(String title) { this.title = title; }
    
    public String getContent() { return content; }
    public void setContent(String content) { this.content = content; }
    
    public int getUserId() { return userId; }
    public void setUserId(int userId) { this.userId = userId; }
    
    public String getCreatedAt() { return createdAt; }
    public void setCreatedAt(String createdAt) { this.createdAt = createdAt; }
    
    public String getUpdatedAt() { return updatedAt; }
    public void setUpdatedAt(String updatedAt) { this.updatedAt = updatedAt; }
    
    @Override
    public String toString() {
        return String.format("게시글 ID: %d, 제목: %s, 내용: %s, " +
                           "작성자ID: %d, 작성일: %s, 수정일: %s",
            id, title, content, userId, createdAt, updatedAt);
    }
}