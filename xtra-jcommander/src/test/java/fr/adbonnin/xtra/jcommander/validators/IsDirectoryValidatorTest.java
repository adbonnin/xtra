package fr.adbonnin.xtra.jcommander.validators;

import com.beust.jcommander.ParameterException;
import org.junit.Rule;
import org.junit.Test;
import org.junit.rules.TemporaryFolder;

import java.io.File;
import java.io.IOException;

import static fr.adbonnin.xtra.io.XtraFiles.tryCanonicalPath;
import static java.util.Arrays.asList;
import static java.util.Collections.singletonList;
import static org.junit.Assert.assertEquals;
import static org.junit.Assert.fail;

public class IsDirectoryValidatorTest {

    @Rule
    public TemporaryFolder temporaryFolder = new TemporaryFolder();

    @Test
    public void testValidate() throws IOException {
        // given
        IsDirectoryValidator validator = new IsDirectoryValidator();

        // expect no exception
        validator.validate("name", null);

        // expect no exception
        validator.validate("name", temporaryFolder.newFolder());

        // when
        File file = temporaryFolder.newFile();
        try {
            validator.validate("name", file);

            // then
            fail();
        }
        catch (ParameterException e) {
            // should throw an exception
            assertEquals("Parameter name should be a directory that exists (found " + tryCanonicalPath(file) + ")", e.getMessage());
        }
    }

    @Test
    public void testEvertyValidate() throws IOException {
        // given
        IsDirectoryValidator.Every validator = new IsDirectoryValidator.Every();

        // expect no exception
        validator.validate("name", null);

        // expect no exception
        validator.validate("name", singletonList(temporaryFolder.newFolder()));

        // when
        File file = temporaryFolder.newFile();
        try {
            validator.validate("name", asList(temporaryFolder.newFolder(), file));

            // then
            fail();
        }
        catch (ParameterException e) {
            // should throw an exception
            assertEquals("Parameter name should have a directory at index 1 that exists (found " + tryCanonicalPath(file) + ")", e.getMessage());
        }
    }
}
