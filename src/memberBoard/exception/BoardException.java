// ============================================
// BoardException.java - 게시글 예외
// ============================================
package memberBoard.exception;

public class BoardException extends Exception {
    private static final long serialVersionUID = 1L;
    
    public BoardException(String message) {
        super(message);
    }
    
    public BoardException(String message, Throwable cause) {
        super(message, cause);
    }
}