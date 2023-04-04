package co.sofkau.utils;

public enum ReqresResources {
    REQRES_BASE_URL("https://reqres.in/"),

    LIST_USERS("api/users?page=2"),
    GET_NOT_FOUND("api/users/23"),
    PATCH_UPDATE("api/users/2"),
    REGISTER_RESOURCE("api/register"),
    CREATE_USER("https://reqres.in/api/users"),
    LOGIN_RESOURCE("/api/login");


    private final String  value;

    ReqresResources(String value) {
        this.value = value;
    }

    public String getValue() {
        return value;
    }
}
