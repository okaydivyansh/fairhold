package com.fairhold.dto.response;
//Why is this a DTO and not an Entity?
//Because User entity contains id, name, email, password, role and we should not return this to client because
// then we'd expose password even though it's hashed, it should never be sent to the client.So we create a separate
// DTO:LoginResponse which contains only: id, name, email, role, token & message
//This is one of the most important backend design principles: never expose your JPA entities directly in API responses.
import lombok.Builder;
import lombok.Getter;
//use @Getter?Normally, Java requires getters like this:
//public Long getId() {
//    return id;
//}
//public String getName() {
//    return name;
//}
//public String getEmail() {
//    return email;
//}
//But with @Getter,Lombok automatically generates: getId() getName() getEmail() getRole() getToken() getMessage()
//during compilation.
//why @Builder? because we without builder, we would need to write
//LoginResponse response = new LoginResponse();
//response.setId(user.getId());
//        response.setName(user.getName());
//        response.setEmail(user.getEmail());
//        response.setRole(user.getRole().name());
//        response.setToken(token);
//response.setMessage("Login successful");
@Getter
@Builder
public class LoginResponse {

    private Long id;

    private String name;

    private String email;

    private String role;

    private String token;

    private String message;
}