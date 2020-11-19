package test;


import java.io.*;

public class TestClassLoader extends ClassLoader {

    final private String path;

    public TestClassLoader(String path) {
        this.path = path;
    }

    @Override
    protected Class<?> findClass(String name) throws ClassNotFoundException {
        try {
            InputStream inputStream = new BufferedInputStream(new FileInputStream(new File(path)), 1024);
            byte[] bytes = new byte[1024];
            ByteArrayOutputStream outputStream = new ByteArrayOutputStream();
            int readSize;
            while ((readSize = inputStream.read(bytes)) > 0) {
                outputStream.write(bytes, 0, readSize);
            }
            byte[] resultBytes = outputStream.toByteArray();
            return defineClass(name, resultBytes, 0, resultBytes.length);
        } catch (IOException e) {
            e.printStackTrace();
            return null;
        }
    }
}
