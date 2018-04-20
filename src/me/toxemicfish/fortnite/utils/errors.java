package me.toxemicfish.fortnite.utils;

public enum errors {

    NO_PERMISSION,
    PLAYER_ONLY,
    CONSOLE_ONLY,
    OP_ONLY;

    private errors() {
    }

    public static String getErrors(errors type) {
        String msg = "";
        if (type == NO_PERMISSION) {
            msg = chatUtils.formatWithPrefix("&cYou do not have the permission to use that command");
        }
        if (type == PLAYER_ONLY) {
            msg = chatUtils.formatWithPrefix("&cYou must be a player to use that command");
        }
        if (type == CONSOLE_ONLY) {
            msg = chatUtils.formatWithPrefix("&cOnly Console can use that command");
        }
        if (type == OP_ONLY) {
            msg = chatUtils.formatWithPrefix("&cOnly OP can use that command");
        }
        return msg;
    }

}
