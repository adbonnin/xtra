package fr.adbonnin.xtra.jcommander.validators;

import com.beust.jcommander.ParameterException;

import java.io.File;

import static fr.adbonnin.xtra.io.XtraFiles.tryCanonicalPath;

public class IsFileValidator extends AbstractValidator<File> {

    public static boolean validate(File file) {
        return file == null || file.isFile();
    }

    @Override
    public boolean evaluate(File file) {
        return validate(file);
    }

    @Override
    protected void throwValidateException(String name, File file) throws ParameterException {
        throw new ParameterException("Parameter " + name + " should be a file that exists (found " + tryCanonicalPath(file) + ")");
    }

    public static class Every extends AbstractValidator.Every<File> {

        @Override
        public boolean evaluate(File file, int index) {
            return IsFileValidator.validate(file);
        }

        @Override
        protected void throwValidateException(String name, File file, int index) throws ParameterException {
            throw new ParameterException("Parameter " + name + " should have a file at index " + index + " that exists (found " + tryCanonicalPath(file) + ")");
        }
    }
}
