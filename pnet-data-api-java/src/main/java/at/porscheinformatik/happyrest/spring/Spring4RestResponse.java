package at.porscheinformatik.happyrest.spring;

import java.util.List;
import java.util.Locale;
import java.util.Map.Entry;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;

import at.porscheinformatik.happyrest.MediaType;
import at.porscheinformatik.happyrest.RestResponse;
import at.porscheinformatik.happyrest.RestUtils;
/**
 * Response for rest calls.
 * @param T the type of body
 * @deprecated will be removed soon. Switch to the java-11 branch, if you need this feature!
 */
@Deprecated
class Spring4RestResponse<T> implements RestResponse<T>
{

    private final ResponseEntity<T> response;

    Spring4RestResponse(ResponseEntity<T> response)
    {
        super();

        this.response = response;
    }

    @Override
    public int getStatusCode()
    {
        return response.getStatusCode().value();
    }

    @Override
    public String getStatusMessage()
    {
        return RestUtils.getHttpStatusMessage(getStatusCode(), HttpStatus.valueOf(getStatusCode()).getReasonPhrase());
    }

    @Override
    public boolean isInformational()
    {
        return response.getStatusCode().is1xxInformational();
    }

    @Override
    public boolean isSuccessful()
    {
        return response.getStatusCode().is2xxSuccessful();
    }

    @Override
    public boolean isRedirection()
    {
        return response.getStatusCode().is3xxRedirection();
    }

    @Override
    public boolean isError()
    {
        return response.getStatusCode().is4xxClientError() || response.getStatusCode().is5xxServerError();
    }

    @Override
    public T getBody()
    {
        return response.getBody();
    }

    @Override
    public List<String> getHeader(String key)
    {
        return response.getHeaders().get(key);
    }

    @Override
    public String getFirstHeader(String key)
    {
        List<String> header = getHeader(key);

        return header.isEmpty() ? null : header.get(0);
    }

    @Override
    public String getCacheControl()
    {
        return response.getHeaders().getCacheControl();
    }

    @Override
    public MediaType getContentType()
    {
        return SpringRestUtils.convertMediaType(response.getHeaders().getContentType(), MediaType.TEXT_PLAIN_UTF8);
    }

    @Override
    public Locale getContentLanguage()
    {
        String contentLanguage = response.getHeaders().getFirst("Content-Language");
        if (contentLanguage != null)
        {
            return new Locale(contentLanguage);
        }
        return null;
    }

    @Override
    public long getContentLength()
    {
        return response.getHeaders().getContentLength();
    }

    @Override
    public long getCreationDate()
    {
        return response.getHeaders().getDate();
    }

    @Override
    public long getExpiresDate()
    {
        return response.getHeaders().getExpires();
    }

    @Override
    public long getLastModified()
    {
        return response.getHeaders().getLastModified();
    }

    @Override
    public String toString()
    {
        StringBuilder builder = new StringBuilder();

        builder.append(getStatus()).append("\n");

        for (Entry<String, List<String>> entry : response.getHeaders().entrySet())
        {
            for (String value : entry.getValue())
            {
                builder.append(entry.getKey()).append(": ").append(value).append("\n");
            }
        }

        return builder.toString();
    }
}
