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
import static org.junit.Assert.*;

public class IsFileValidatorTest {

    @Rule
    public TemporaryFolder temporaryFolder = new TemporaryFolder();

    @Test
    public void testValidate() throws IOException {
        // given
        IsFileValidator validator = new IsFileValidator();

        // expect no exception
        validator.validate("name", null);

        // expect no exception
        validator.validate("name", temporaryFolder.newFile());

        // when
        File folder = temporaryFolder.newFolder();
        try {
            validator.validate("name", folder);

            // then
            fail();
        }
        catch (ParameterException e) {
            // should throw an exception
            assertEquals("Parameter name should be a file that exists (found " + tryCanonicalPath(folder) + ")", e.getMessage());
        }
    }

    @Test
    public void testEvertyValidate() throws IOException {
        // given
        IsFileValidator.Every validator = new IsFileValidator.Every();

        // expect no exception
        validator.validate("name", null);

        // expect no exception
        validator.validate("name", singletonList(temporaryFolder.newFile()));

        // when
        File file = temporaryFolder.newFolder();
        try {
            validator.validate("name", asList(temporaryFolder.newFile(), file));

            // then
            fail();
        }
        catch (ParameterException e) {
            // should throw an exception
            assertEquals("Parameter name should have a file at index 1 that exists (found " + tryCanonicalPath(file) + ")", e.getMessage());
        }
    }
}
