package listeners;

import org.testng.IAnnotationTransformer;
import org.testng.annotations.*;
import java.lang.reflect.Constructor;
import java.lang.reflect.Method;

public class GlobalRetryAnalyzer implements IAnnotationTransformer {
    @Override
    public void transform(ITestAnnotation annotation, Class testClass, Constructor testConstructor, Method testMethod) {
        IAnnotationTransformer.super.transform(annotation, testClass, testConstructor, testMethod);
        // Setting the retry analyzer with @Test Annotations
        annotation.setRetryAnalyzer(FailedCasesHandler.class);
    }

    @Override
    public void transform(IConfigurationAnnotation annotation, Class testClass, Constructor testConstructor, Method testMethod) {
        IAnnotationTransformer.super.transform(annotation, testClass, testConstructor, testMethod);
    }

    @Override
    public void transform(IDataProviderAnnotation annotation, Method method) {
        IAnnotationTransformer.super.transform(annotation, method);
    }

    @Override
    public void transform(IFactoryAnnotation annotation, Method method) {
        IAnnotationTransformer.super.transform(annotation, method);
    }

    @Override
    public void transform(IListenersAnnotation annotation, Class<?> testClass) {
        IAnnotationTransformer.super.transform(annotation, testClass);
    }
}
