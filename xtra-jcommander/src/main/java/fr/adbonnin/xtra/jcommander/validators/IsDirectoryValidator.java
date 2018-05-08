package fr.adbonnin.xtra.jcommander.validators;

import com.beust.jcommander.ParameterException;

import java.io.File;

import static fr.adbonnin.xtra.io.XtraFiles.tryCanonicalPath;

public class IsDirectoryValidator extends AbstractValidator<File> {

    public static boolean validate(File file) {
        return file == null || file.isDirectory();
    }

    @Override
    public boolean evaluate(File file) {
        return validate(file);
    }

    @Override
    protected void throwValidateException(String name, File file) throws ParameterException {
        throw new ParameterException("Parameter " + name + " should be a directory that exists (found " + tryCanonicalPath(file) + ")");
    }

    public static class Every extends AbstractValidator.Every<File> {

        @Override
        public boolean evaluate(File file, int index) {
            return IsDirectoryValidator.validate(file);
        }

        @Override
        protected void throwValidateException(String name, File file, int index) throws ParameterException {
            throw new ParameterException("Parameter " + name + " should have a directory at index " + index + " that exists (found " + tryCanonicalPath(file) + ")");
        }
    }
}
