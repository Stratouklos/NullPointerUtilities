package com.nullpointerengineering.utilities.text;

import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.junit.runners.JUnit4;

import java.nio.file.FileSystems;
import java.nio.file.Path;
import java.util.LinkedList;
import java.util.List;

import static org.hamcrest.core.IsCollectionContaining.*;
import static org.junit.Assert.*;

@RunWith(JUnit4.class)
public class FileReaderTests {

    private static final Path FILE_PATH = FileSystems.getDefault().getPath("src/test/resources", "testInput");

    private FileReader readerUnderTest;
    private List<String> linesRead;

    @Before
    public void setup() {
        readerUnderTest = FileReader.getInstance(FILE_PATH);
        linesRead = new LinkedList<>();
    }

    @Test
    public void testOne() {
        readerUnderTest.read(new FileReader.LineParser() {
            @Override
            public void parse(String lineIn) {
                linesRead.add(lineIn);
            }
        });
        assertThat(linesRead, hasItems(
                "Mary had a little lamb",
                "whose fleece was white as snow.",
                "",
                "And everywhere that Mary went,",
                "the lamb was sure to go."));
    }
}