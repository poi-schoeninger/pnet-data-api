package at.porscheinformatik.happyrest.util;

import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.Reader;
import java.nio.charset.Charset;

import at.porscheinformatik.happyrest.GenericType;
import at.porscheinformatik.happyrest.RestParser;
import at.porscheinformatik.happyrest.RestParserException;
import at.porscheinformatik.happyrest.RestUtils;

public class StringParser implements RestParser
{

    public static final StringParser INSTANCE = new StringParser();

    private static final GenericType<String> STRING_TYPE = GenericType.of(String.class);

    protected StringParser()
    {
        super();
    }

    @Override
    public boolean isContentTypeSupported(String contentType, GenericType<?> type)
    {
        return type.isAssignableFrom(STRING_TYPE);
    }

    @Override
    public <T> Object parse(String contentType, GenericType<?> type, InputStream in) throws RestParserException
    {
        Charset charset = RestUtils.extractContentTypeCharset(contentType);

        try (Reader reader = new InputStreamReader(in, charset))
        {
            return RestUtils.readFully(reader);
        }
        catch (IOException e)
        {
            throw new RestParserException("Failed to read chars", e);
        }
    }

}
