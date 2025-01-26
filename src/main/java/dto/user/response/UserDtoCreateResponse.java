package dto.user.response;

import entity.User;
import jakarta.ws.rs.core.Response;

public record UserDtoCreateResponse(
        int statusCode,
        String reasonPhrase,
        boolean success,
        String message,
        User user) {

    private static final String SUCCESS_MESSAGE = "User has been created successfully!";
    private static final String FAILURE_MESSAGE = "User has not created";

    public static UserDtoCreateResponse of(boolean isUserCreated, User user) {
        if (isUserCreated) {
            return new UserDtoCreateResponse(
                    Response.Status.OK.getStatusCode(),
                    Response.Status.OK.getReasonPhrase(),
                    true,
                    SUCCESS_MESSAGE,
                    user
            );
        } else {
            return new UserDtoCreateResponse(
                    Response.Status.NO_CONTENT.getStatusCode(),
                    Response.Status.NO_CONTENT.getReasonPhrase(),
                    false,
                    FAILURE_MESSAGE,
                    null
            );
        }
    }
}
