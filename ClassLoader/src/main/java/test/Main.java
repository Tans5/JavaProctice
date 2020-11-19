
package test;

import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;

public class Main {

    public static void main(String[] args) {
        ClassLoader loader = new TestClassLoader("/Users/tanstan/AndroidStudioProjects/ClassLoader/src/main/java/test/test/TestClass.class");
        try {
            Class clazz = loader.loadClass("test.TestClass");
            Method method = clazz.getDeclaredMethod("main", String[].class);
            // method.invoke(null);
            // Object instance = clazz.newInstance();
            // Method[] methods = clazz.getDeclaredMethods();
            method.invoke(null, (Object)args);
            System.out.println(clazz);
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        } catch (IllegalAccessException e) {
            e.printStackTrace();
        } catch (InvocationTargetException e) {
            e.printStackTrace();
        } catch (NoSuchMethodException e) {
            e.printStackTrace();
        }

    }
}
