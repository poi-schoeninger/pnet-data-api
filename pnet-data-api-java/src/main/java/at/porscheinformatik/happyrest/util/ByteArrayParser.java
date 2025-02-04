package at.porscheinformatik.happyrest.util;

import java.io.IOException;
import java.io.InputStream;
import java.lang.reflect.Array;
import java.util.Optional;

import at.porscheinformatik.happyrest.GenericType;
import at.porscheinformatik.happyrest.MediaType;
import at.porscheinformatik.happyrest.RestParser;
import at.porscheinformatik.happyrest.RestParserException;
import at.porscheinformatik.happyrest.RestUtils;

public class ByteArrayParser implements RestParser
{

    public static final ByteArrayParser INSTANCE = new ByteArrayParser();

    private static final GenericType<byte[]> BYTE_ARRAY_TYPE =
        GenericType.of(Array.newInstance(Byte.TYPE, 0).getClass());

    protected ByteArrayParser()
    {
        super();
    }

    @Override
    public boolean isContentTypeSupported(Optional<MediaType> contentType, GenericType<?> type)
    {
        return type.isAssignableFrom(BYTE_ARRAY_TYPE);
    }

    @Override
    public <T> byte[] parse(Optional<MediaType> contentType, GenericType<?> type, InputStream in)
        throws RestParserException
    {
        try
        {
            return RestUtils.readAllBytes(in);
        }
        catch (IOException e)
        {
            throw new RestParserException("Failed to read bytes", e);
        }
    }

}
