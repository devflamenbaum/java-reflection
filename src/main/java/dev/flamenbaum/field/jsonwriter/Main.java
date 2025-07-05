package dev.flamenbaum.field.jsonwriter;

import dev.flamenbaum.field.jsonwriter.data.Address;
import dev.flamenbaum.field.jsonwriter.data.Company;
import dev.flamenbaum.field.jsonwriter.data.Person;

import java.lang.reflect.Field;

public class Main {
    public static void main(String[] args) throws IllegalAccessException {
        Company company = new Company("Company X", "Rio de Janeiro", new Address("Rua xyz", (short) 9));
        Address address = new Address("Rua abc", (short) 10);
        Person person = new Person("Gabriel", true, 34, 1000.006f, address, company);
        System.out.println(objectToJson(person, 0));
    }

    public static String objectToJson(Object instance, int indentSize) throws IllegalAccessException {
        Field[] fields = instance.getClass().getDeclaredFields();
        StringBuilder stringBuilder = new StringBuilder();

        stringBuilder.append("{");
        stringBuilder.append("\n");

        for (int i = 0; i < fields.length; i++) {
            Field field = fields[i];
            field.setAccessible(true);

            if (field.isSynthetic()) {
                continue;
            }

            stringBuilder.append(indent(indentSize + 1));
            stringBuilder.append(formatStringValue(field.getName()));

            stringBuilder.append(":\t");

            if (field.getType().isPrimitive()) {
                stringBuilder.append(formatPrimitiveValue(field, instance));
            } else if (field.getType().equals(String.class)) {
                stringBuilder.append(formatStringValue(field.get(instance).toString()));
            } else {
                stringBuilder.append(objectToJson(field.get(instance), indentSize + 1));
            }

            if (i != fields.length - 1) {
                stringBuilder.append(",");
            }
            stringBuilder.append("\n");
        }

        stringBuilder.append(indent(indentSize));
        stringBuilder.append("}");
        return stringBuilder.toString();
    }

    private static String formatPrimitiveValue(Field field, Object parentInstance) throws IllegalAccessException {

        if(field.getType().equals(double.class) || field.getType().equals(float.class)) {
            return String.format("%.02f", field.get(parentInstance));
        }

        return field.get(parentInstance).toString();
    }

    private static String formatStringValue(String value) {
        return String.format("\"%s\"", value);
    }

    private static String indent(int size) {
        return "\t".repeat(Math.max(0, size));
    }
}
