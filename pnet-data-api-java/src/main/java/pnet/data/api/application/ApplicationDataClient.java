package pnet.data.api.application;

import java.util.List;
import java.util.Locale;

import org.springframework.stereotype.Service;
import org.springframework.web.client.HttpServerErrorException;
import org.springframework.web.client.ResourceAccessException;

import at.porscheinformatik.happyrest.GenericType;
import pnet.data.api.PnetDataApiAccessException;
import pnet.data.api.PnetDataApiException;
import pnet.data.api.PnetDataApiServerException;
import pnet.data.api.client.DefaultPnetDataClientResultPage;
import pnet.data.api.client.PnetDataClientResultPage;
import pnet.data.api.client.context.AbstractPnetDataApiClient;
import pnet.data.api.client.context.PnetDataApiContext;
import pnet.data.api.util.GetByMatchcode;
import pnet.data.api.util.Pair;

/**
 * Data-API client for {@link ApplicationDataDTO}s.
 *
 * @author cet
 */
@Service
public class ApplicationDataClient extends AbstractPnetDataApiClient<ApplicationDataClient>
    implements GetByMatchcode<ApplicationDataDTO>
{

    public ApplicationDataClient(PnetDataApiContext context)
    {
        super(context);
    }

    @Override
    public PnetDataClientResultPage<ApplicationDataDTO> getAllByMatchcodes(List<String> matchcodes, int pageIndex,
        int itemsPerPage) throws PnetDataApiException
    {
        try
        {
            DefaultPnetDataClientResultPage<ApplicationDataDTO> resultPage = createRestCall() //
                .parameters("mc", matchcodes)
                .parameter("p", pageIndex)
                .parameter("pp", itemsPerPage)
                .get("/api/v1/applications/details",
                    new GenericType.Of<DefaultPnetDataClientResultPage<ApplicationDataDTO>>()
                    {
                    });

            resultPage.setNextPageSupplier(() -> getAllByMatchcodes(matchcodes, pageIndex + 1, itemsPerPage));

            return resultPage;
        }
        catch (ResourceAccessException e)
        {
            throw new PnetDataApiAccessException(e);
        }
        catch (HttpServerErrorException e)
        {
            throw new PnetDataApiServerException(e);
        }
        catch (Exception | Error e)
        {
            throw new PnetDataApiException("Unhandled exception", e);
        }
    }

    public ApplicationDataSearch search()
    {
        return new ApplicationDataSearch(this::search, null);
    }

    protected PnetDataClientResultPage<ApplicationItemDTO> search(Locale language, String query,
        List<Pair<String, Object>> restricts, int pageIndex, int itemsPerPage) throws PnetDataApiException
    {
        try
        {
            DefaultPnetDataClientResultPage<ApplicationItemDTO> resultPage = createRestCall()
                .parameter("l", language)
                .parameter("q", query)
                .parameters(restricts)
                .parameter("p", pageIndex)
                .parameter("pp", itemsPerPage)
                .get("/api/v1/applications/search",
                    new GenericType.Of<DefaultPnetDataClientResultPage<ApplicationItemDTO>>()
                    {
                    });

            resultPage.setNextPageSupplier(() -> search(language, query, restricts, pageIndex + 1, itemsPerPage));

            return resultPage;
        }
        catch (ResourceAccessException e)
        {
            throw new PnetDataApiAccessException(e);
        }
        catch (HttpServerErrorException e)
        {
            throw new PnetDataApiServerException(e);
        }
        catch (Exception | Error e)
        {
            throw new PnetDataApiException("Unhandled exception", e);
        }
    }

    public ApplicationDataFind find()
    {
        return new ApplicationDataFind(this::find, null);
    }

    protected PnetDataClientResultPage<ApplicationItemDTO> find(Locale language, List<Pair<String, Object>> restricts,
        int pageIndex, int itemsPerPage) throws PnetDataApiException
    {
        try
        {
            DefaultPnetDataClientResultPage<ApplicationItemDTO> resultPage = createRestCall()
                .parameters(restricts)
                .parameter("l", language)
                .parameter("p", pageIndex)
                .parameter("pp", itemsPerPage)
                .get("/api/v1/applications/find",
                    new GenericType.Of<DefaultPnetDataClientResultPage<ApplicationItemDTO>>()
                    {
                    });

            resultPage.setNextPageSupplier(() -> find(language, restricts, pageIndex + 1, itemsPerPage));

            return resultPage;
        }
        catch (ResourceAccessException e)
        {
            throw new PnetDataApiAccessException(e);
        }
        catch (HttpServerErrorException e)
        {
            throw new PnetDataApiServerException(e);
        }
        catch (Exception | Error e)
        {
            throw new PnetDataApiException("Unhandled exception", e);
        }
    }
}
