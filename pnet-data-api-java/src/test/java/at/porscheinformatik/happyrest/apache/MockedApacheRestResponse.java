package at.porscheinformatik.happyrest.apache;

import java.io.IOException;
import java.nio.charset.StandardCharsets;
import java.util.Arrays;
import java.util.Collections;
import java.util.List;
import java.util.stream.Collectors;

import org.apache.hc.client5.http.classic.methods.HttpUriRequestBase;
import org.apache.hc.core5.http.Header;

import at.porscheinformatik.happyrest.GenericType;
import at.porscheinformatik.happyrest.MockedRestResponse;
import at.porscheinformatik.happyrest.RestUtils;

public class MockedApacheRestResponse<T> extends MockedRestResponse<T>
{
    private final GenericType<T> responseType;
    private final HttpUriRequestBase request;
    private String requestBody;

    public MockedApacheRestResponse(GenericType<T> responseType, HttpUriRequestBase request)
    {
        super();

        this.responseType = responseType;
        this.request = request;

        try
        {
            requestBody = new String(RestUtils.readAllBytes(request.getEntity().getContent()), StandardCharsets.UTF_8);
        }
        catch (UnsupportedOperationException e)
        {
            throw new RuntimeException(e);
        }
        catch (IOException e)
        {
            throw new RuntimeException(e);
        }
    }

    public HttpUriRequestBase getRequest()
    {
        return request;
    }

    public GenericType<T> getResponseType()
    {
        return responseType;
    }

    @Override
    public String getRequestMethod()
    {
        return request.getMethod();
    }

    @Override
    public String getRequestUrl()
    {
        return request.getRequestUri();
    }

    @Override
    public List<String> getRequestHeader(String key)
    {
        Header[] headers = request.getHeaders(key);

        if (headers == null || headers.length == 0)
        {
            return Collections.emptyList();
        }

        return Arrays.stream(headers).map(Header::getValue).collect(Collectors.toList());
    }

    @Override
    public String getRequestBody()
    {
        return requestBody;
    }
}
