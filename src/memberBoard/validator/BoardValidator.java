// ============================================
// BoardValidator.java - 게시글 입력 검증
// ============================================
package memberBoard.validator;

import memberBoard.domain.dto.BoardDTO;
import memberBoard.exception.BoardException;

public class BoardValidator {
    
    private static final int MIN_TITLE_LENGTH = 2;
    private static final int MAX_TITLE_LENGTH = 100;
    private static final int MIN_CONTENT_LENGTH = 1;
    private static final int MAX_CONTENT_LENGTH = 5000;
    
    public void validateBoard(BoardDTO boardDTO) throws BoardException {
        validateTitle(boardDTO.getTitle());
        validateContent(boardDTO.getContent());
    }
    
    public void validateTitle(String title) throws BoardException {
        if (title == null || title.trim().isEmpty()) {
            throw new BoardException("제목을 입력해주세요.");
        }
        
        if (title.length() < MIN_TITLE_LENGTH || title.length() > MAX_TITLE_LENGTH) {
            throw new BoardException(
                String.format("제목은 %d-%d자 사이여야 합니다.",
                    MIN_TITLE_LENGTH, MAX_TITLE_LENGTH)
            );
        }
    }
    
    public void validateContent(String content) throws BoardException {
        if (content == null || content.trim().isEmpty()) {
            throw new BoardException("내용을 입력해주세요.");
        }
        
        if (content.length() < MIN_CONTENT_LENGTH || 
            content.length() > MAX_CONTENT_LENGTH) {
            throw new BoardException(
                String.format("내용은 %d-%d자 사이여야 합니다.",
                    MIN_CONTENT_LENGTH, MAX_CONTENT_LENGTH)
            );
        }
    }
}