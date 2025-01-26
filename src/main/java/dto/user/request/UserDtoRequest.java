package dto.user.request;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

@JsonIgnoreProperties(ignoreUnknown = true)
public record UserDtoRequest(Long id, String firstName, String lastName, String email) {
}
