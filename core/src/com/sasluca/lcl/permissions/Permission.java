package com.sasluca.lcl.permissions;

//TODO: Add permissions here
public enum Permission
{
    TEST(1);

    private final int CODE;
    Permission(int code) { CODE = code; }

    public final int code() { return CODE; }

    public static final boolean exists(int i)
    {
        for (Permission p : values()) if (p.code() == i) return true;

        return false;
    }
}
