package fr.adbonnin.xtra.jcommander.converters;

import com.beust.jcommander.IStringConverter;

import java.io.File;

public class NullToCurrentFileConverter implements IStringConverter<File> {

    @Override
    public File convert(String value) {
        return null;
    }
}
