package dev.flamenbaum;

import java.util.Collection;
import java.util.HashMap;
import java.util.Map;

public class Main {

    public static void main(String[] args) throws ClassNotFoundException {
        Class<String> stringObject = String.class;

        Map<String, Integer> mapObject = new HashMap<>();

        Class<?> hashMapClass = mapObject.getClass();

        Class<?> squareClass = Class.forName("dev.flamenbaum.Main$Square");


//        printClassInfo(stringObject, hashMapClass, squareClass);

        var circle = new Drawable() {

            @Override
            public int getNumbersOfCorners() {
                return 0;
            }
        };

        printClassInfo(Collection.class, boolean.class, int [][].class, Color.class, circle.getClass());
    }

    private static class Square implements Drawable {

        @Override
        public int getNumbersOfCorners() {
            return 4;
        }
    }

    interface Drawable {
        int getNumbersOfCorners();
    }

    private enum Color {
        BLUE, RED, GREEN
    }
    private static void printClassInfo(Class<?> ... classes) {

        for(Class<?> clazz: classes) {
            System.out.printf("Class name: %s, class package name: %s%n",
                    clazz.getSimpleName(), clazz.getPackageName());

            Class<?> [] implementedInterfaces = clazz.getInterfaces();
            boolean b = clazz.getInterfaces().length > 0;
            for (Class<?> implementedInterface: implementedInterfaces) {
                System.out.printf("class: %s implements: %s%n", clazz.getSimpleName(), implementedInterface.getSimpleName());
            }

            System.out.println("Is array: " + clazz.isArray());
            System.out.println("Is primitive: " + clazz.isPrimitive());
            System.out.println("Is enum: " + clazz.isEnum());
            System.out.println("Is interface: " + clazz.isInterface());
            System.out.println("Is anonymous: " + clazz.isAnonymousClass());

            System.out.println();
            System.out.println();
        }

    }
}
