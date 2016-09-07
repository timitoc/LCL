package com.sasluca.lcl.permissions;

import com.sasluca.lcl.utils.collections.LCLArray;
import com.sasluca.lcl.utils.collections.LCLMap;

public class LCLPermissionManager
{
    private static boolean m_Init;
    private static LCLMap<Integer, IPermission> m_Permissions = new LCLMap<>();

    private static LCLPermissionManager METHOD_CHAIN = new LCLPermissionManager();
    private LCLPermissionManager() {}

    public static void init(LCLArray<IPermission> permissions)
    {
        for (IPermission p : permissions)
        {
            if (!Permission.exists(p.getCode())) System.out.println("Permission code " + p.getCode() + " does not exist!");
            if (m_Permissions.containsKey(p.getCode())) System.out.println("Permission with code " + p.getCode() + " has already been added!");

            m_Permissions.put(p.getCode(), p);
        }
    }

    public static IPermission getPermission(int code) { return m_Permissions.get(code); }
}
