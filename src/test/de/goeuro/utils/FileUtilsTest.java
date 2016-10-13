package de.goeuro.utils;

import org.jukito.JukitoRunner;
import org.junit.Test;
import org.junit.runner.RunWith;

import java.io.File;

import static org.junit.Assert.*;

@RunWith(JukitoRunner.class)
public class FileUtilsTest {


    @Test
    public void getCSVFile_pathIsNull() throws Exception {
        String name = "Berlin";
        File file = FileUtils.getCSVFile(null,name);

        assertTrue(file.getName().startsWith(name));
        assertTrue(file.getName().endsWith(".csv"));
        assertTrue(file.getPath().startsWith(name));
    }

    @Test
    public void getCSVFile_withPath() throws Exception {
        String name = "Berlin";
        String path = "/temp/";
        File file = FileUtils.getCSVFile(path,name);

        assertTrue(file.getName().startsWith(name));
        assertTrue(file.getName().endsWith(".csv"));
        assertTrue(file.getPath().startsWith(path));
    }

    @Test(expected = RuntimeException.class)
    public void testGetFileWriter_throwsException() throws Exception {
        String name = "Berlin";
        String path = "/mytesttemp/";
        File file = FileUtils.getCSVFile(path,name);
        FileUtils.getFileWriter(file);
    }
}