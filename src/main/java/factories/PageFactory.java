package factories;

import java.lang.reflect.InvocationTargetException;
import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;

public class PageFactory {

    private final Map<Class<?>, Object> objectMap = new ConcurrentHashMap<>();

    public <T> T getPageObject(Class<T> className) {
        Class<?> implementedClass = createClassName(className);
        return className.cast(
                objectMap.computeIfAbsent(implementedClass, key -> {
                    try {
                        System.out.println("Creating object for class " + implementedClass);
                        return key.getDeclaredConstructor().newInstance();
                    } catch (InstantiationException | IllegalAccessException | NoSuchMethodException |
                             InvocationTargetException e) {
                        throw new RuntimeException("Class not found\n" + key.getName() + "\n" + e.getMessage());
                    }
                })
        );
    }

    private Class<?> createClassName(Class<?> type) {

        // Checking if the provided class is an interface or not
        if (!type.isInterface()) {
            throw new RuntimeException("Class " + type.getName() + " is not an interface");
        }

        // Getting the simple name of this provided interface
        String simpleName = type.getSimpleName();

        // Checking if this created number is empty
        if (simpleName.isEmpty()) {
            throw new RuntimeException("Class " + type.getName() + " has no simple name");
        }

        // Checking if this interface starts with "I"
        if (!simpleName.startsWith("I")) {
            throw new RuntimeException("Interface " + type.getName() + " is not a valid simple name, it should start with 'I'");
        }

        // Checking if this interface ends with "Container"
        if (!simpleName.endsWith("Container")) {
            throw new RuntimeException("Interface " + type.getName() + " is not a valid simple name, it should end with 'Container'");
        }

        simpleName = simpleName.substring(1, simpleName.length() - "Container".length());
        simpleName = simpleName + "PageObjects";

        System.out.println("Simple name is " + simpleName);
        String finalQualifiedName = "pages"+ "." + simpleName;
        System.out.println("PackageName is " + finalQualifiedName);

        try {
            return Class.forName(finalQualifiedName);
        }  catch (ClassNotFoundException e) {
            throw new RuntimeException(e);
        }
    }

    public void clearInstances() {
        objectMap.clear();
    }



}
