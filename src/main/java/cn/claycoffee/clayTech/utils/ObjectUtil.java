package cn.claycoffee.clayTech.utils;

import java.util.List;

import org.jetbrains.annotations.NotNull;

public class ObjectUtil {
    public static boolean ExistsInList(int a, int @NotNull [] b) {
        for (int i : b) {
            if (i == a) {
                return true;
            }
        }
        return false;
    }

    public static boolean ExistsInList(int a, @NotNull List<Integer> b) {
        for (int i : b) {
            if (i == a) {
                return true;
            }
        }
        return false;
    }

    public static boolean ExistsInList(Object a, @NotNull List<Object> b) {
        for (Object o : b) {
            if (o.equals(a)) {
                return true;
            }
        }
        return false;
    }

    public static boolean ExistsInList(String a, @NotNull List<String> b) {
        for (String s : b) {
            if (s.equals(a)) {
                return true;
            }
        }
        return false;
    }
}
