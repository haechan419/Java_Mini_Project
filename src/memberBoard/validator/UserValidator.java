// ============================================
// UserValidator.java - 사용자 입력 검증
// ============================================
package memberBoard.validator;

import memberBoard.config.AppConfig;
import memberBoard.domain.dto.UserDTO;
import memberBoard.exception.UserException;

public class UserValidator {
    
    public void validateRegistration(UserDTO userDTO) throws UserException {
        validateUsername(userDTO.getUsername());
        validatePassword(userDTO.getPassword());
        validateName(userDTO.getName());
        validatePhone(userDTO.getPhone());
        validateEmail(userDTO.getEmail());
    }
    
    public void validateUsername(String username) throws UserException {
        if (username == null || username.trim().isEmpty()) {
            throw new UserException("아이디를 입력해주세요.");
        }
        
        if (username.length() < 3 || username.length() > 20) {
            throw new UserException("아이디는 3-20자 사이여야 합니다.");
        }
        
        if (!username.matches("^[a-zA-Z0-9_]+$")) {
            throw new UserException("아이디는 영문, 숫자, 언더스코어만 사용 가능합니다.");
        }
    }
    
    public void validatePassword(String password) throws UserException {
        if (password == null || password.trim().isEmpty()) {
            throw new UserException("비밀번호를 입력해주세요.");
        }
        
        if (password.length() < AppConfig.MIN_PASSWORD_LENGTH || 
            password.length() > AppConfig.MAX_PASSWORD_LENGTH) {
            throw new UserException(
                String.format("비밀번호는 %d-%d자 사이여야 합니다.",
                    AppConfig.MIN_PASSWORD_LENGTH, 
                    AppConfig.MAX_PASSWORD_LENGTH)
            );
        }
    }
    
    public void validateName(String name) throws UserException {
        if (name == null || name.trim().isEmpty()) {
            throw new UserException("이름을 입력해주세요.");
        }
        
        if (name.length() < 2 || name.length() > 20) {
            throw new UserException("이름은 2-20자 사이여야 합니다.");
        }
    }
    
    public void validatePhone(String phone) throws UserException {
        if (phone == null || phone.trim().isEmpty()) {
            throw new UserException("연락처를 입력해주세요.");
        }
        
        if (!phone.matches(AppConfig.PHONE_REGEX)) {
            throw new UserException("올바른 전화번호 형식이 아닙니다. (010-0000-0000)");
        }
    }
    
    public void validateEmail(String email) throws UserException {
        if (email == null || email.trim().isEmpty()) {
            throw new UserException("이메일을 입력해주세요.");
        }
        
        if (!email.matches(AppConfig.EMAIL_REGEX)) {
            throw new UserException("올바른 이메일 형식이 아닙니다.");
        }
    }
}