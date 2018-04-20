package me.toxemicfish.fortnite.utils;

public enum permission {

    GAME,
    RELOAD_CONFIG;

    private permission() {
    }

    public static String getPermission(permission type) {
        String perm = "";
        if (type == GAME) {
            perm = "fortnite.game";
        }
        if (type == RELOAD_CONFIG) {
            perm = "fortnite.RELOAD_CONFIG";
        }


        return perm;
    }

}
