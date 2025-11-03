// ============================================
// Board.java - Entity
// ============================================
package memberBoard.domain.entity;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.Objects;

public class Board {
    private static final DateTimeFormatter FORMATTER = 
        DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss");
    
    private int id;
    private String title;
    private String content;
    private int userId;
    private LocalDateTime createdAt;
    private LocalDateTime updatedAt;
    
    // 생성자
    public Board(int id, String title, String content, int userId) {
        this.id = id;
        this.title = title;
        this.content = content;
        this.userId = userId;
        this.createdAt = LocalDateTime.now();
        this.updatedAt = LocalDateTime.now();
    }
    
    public Board(String title, String content, int userId) {
        this(0, title, content, userId);
    }
    
    // 비즈니스 로직
    public void update(String title, String content) {
        if (title != null && !title.isEmpty()) {
            this.title = title;
        }
        if (content != null && !content.isEmpty()) {
            this.content = content;
        }
        this.updatedAt = LocalDateTime.now();
    }
    
    public boolean isOwnedBy(int userId) {
        return this.userId == userId;
    }
    
    // Getters
    public int getId() { return id; }
    public String getTitle() { return title; }
    public String getContent() { return content; }
    public int getUserId() { return userId; }
    public String getCreatedAt() { return createdAt.format(FORMATTER); }
    public String getUpdatedAt() { return updatedAt.format(FORMATTER); }
    
    // Setters
    public void setId(int id) { this.id = id; }
    public void setTitle(String title) { 
        this.title = title;
        this.updatedAt = LocalDateTime.now();
    }
    public void setContent(String content) { 
        this.content = content;
        this.updatedAt = LocalDateTime.now();
    }
    
    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Board board = (Board) o;
        return id == board.id;
    }
    
    @Override
    public int hashCode() {
        return Objects.hash(id);
    }
    
    @Override
    public String toString() {
        return String.format("Board [ID=%d, 제목=%s, 작성자ID=%d, 작성일=%s, 수정일=%s]",
            id, title, userId, getCreatedAt(), getUpdatedAt());
    }
}