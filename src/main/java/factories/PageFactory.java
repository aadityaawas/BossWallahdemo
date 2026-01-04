package factories;

import org.jspecify.annotations.NonNull;

import java.lang.reflect.InvocationTargetException;
import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;

public class PageFactory {
    // ConcurrentHashMap for maintaining all page object that are created in the test suite execution.
    // This is to save the object so that we don't need to create them again and again.
    private final Map<Class<?>, Object> objectMap = new ConcurrentHashMap<>();

    // main method to create the object for the provided class using reflections
    public <T> T getPageObject(Class<T> className) {
        Class<?> implementedClass = createClassName(className);

        // Will only compute if that object is not present in the objectMap.
        return className.cast(
                objectMap.computeIfAbsent(implementedClass, key -> {
                    try {
                        // Will provide the private, protected, and, public constructor
                        return key.getDeclaredConstructor().newInstance();
                    } catch (InstantiationException | IllegalAccessException | NoSuchMethodException |
                             InvocationTargetException e) {
                        throw new RuntimeException("Class not found\n" + key.getName() + "\n" + e.getMessage());
                    }
                })
        );
    }

    // Creating desired class name with string manipulations
    private Class<?> createClassName(Class<?> type) {
        // Creational Pattern requirements to create the instances of page object classes
        String packageName = "pages";
        String classNameSuffix = "PageObjects";
        String prefixToRemove = "I";
        String suffixToRemove = "Container";
        String dot = ".";

        // Checking if the provided class is an interface or not
        if (!type.isInterface()) {
            throw new RuntimeException("Class " + type.getName() + " is not an interface");
        }

        // Getting the simple name of this provided interface
        String simpleName = getSimpleClassName(type, prefixToRemove, suffixToRemove);

        // Removing the first character and last string from the class name, i.e. "I" and "Container"
        simpleName = simpleName.substring(1, simpleName.length() - suffixToRemove.length());
        simpleName = simpleName + classNameSuffix;

        // Final string value for creating the object of the required class, i.e. LoginPage
        String finalQualifiedName = packageName + dot + simpleName;
        try {
            return Class.forName(finalQualifiedName);
        } catch (ClassNotFoundException e) {
            throw new RuntimeException(e);
        }
    }

    // Helper method for creating simple class name
    private static @NonNull String getSimpleClassName(Class<?> type, String startIndexCharacters, String endIndexCharacters) {
        String simpleName = type.getSimpleName(); // e.g., ILoginContainer

        // Checking if this created number is empty
        if (simpleName.isEmpty()) {
            throw new RuntimeException("Class " + type.getName() + " has no simple name");
        }

        if (!simpleName.startsWith(startIndexCharacters) || !simpleName.endsWith(endIndexCharacters)) {
            throw new RuntimeException("Interface name must start with '" + startIndexCharacters + "' and end with '" + endIndexCharacters + "': " + simpleName);
        }
        return simpleName;
    }

    // clearing the objectMap for preventing memory leakage.
    public void clearInstances() {
        objectMap.clear();
    }
}
