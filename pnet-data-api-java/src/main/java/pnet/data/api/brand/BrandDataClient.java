package pnet.data.api.brand;

import java.util.List;
import java.util.Locale;

import org.springframework.beans.factory.annotation.Autowired;
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
 * Client of {@link BrandDataDTO}s and {@link BrandItemDTO}s
 *
 * @author ham
 */
@Service
public class BrandDataClient extends AbstractPnetDataApiClient<BrandDataClient> implements GetByMatchcode<BrandDataDTO>
{

    @Autowired
    public BrandDataClient(PnetDataApiContext context)
    {
        super(context);
    }

    @Override
    public PnetDataClientResultPage<BrandDataDTO> getAllByMatchcodes(List<String> matchcodes, int pageIndex,
        int itemsPerPage) throws PnetDataApiException
    {
        try
        {
            DefaultPnetDataClientResultPage<BrandDataDTO> resultPage = createRestCall()
                .parameter("mc", matchcodes)
                .parameter("p", pageIndex)
                .parameter("pp", itemsPerPage)
                .get("/api/v1/brands/details", new GenericType.Of<DefaultPnetDataClientResultPage<BrandDataDTO>>()
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

    public BrandDataSearch search()
    {
        return new BrandDataSearch(getMapper(), this::search, null);
    }

    protected PnetDataClientResultPage<BrandItemDTO>

        search(Locale language, String query, List<Pair<String, Object>> restricts, int pageIndex, int itemsPerPage)
            throws PnetDataApiException
    {
        try
        {
            DefaultPnetDataClientResultPage<BrandItemDTO> resultPage = createRestCall()
                .parameter("l", language)
                .parameter("q", query)
                .parameters(restricts)
                .parameter("p", pageIndex)
                .parameter("pp", itemsPerPage)
                .get("/api/v1/brands/search", new GenericType.Of<DefaultPnetDataClientResultPage<BrandItemDTO>>()
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

    public BrandDataFind find()
    {
        return new BrandDataFind(getMapper(), this::find, null);
    }

    protected PnetDataClientResultPage<BrandItemDTO> find(Locale language, List<Pair<String, Object>> restricts,
        int pageIndex, int itemsPerPage) throws PnetDataApiException
    {
        try
        {
            DefaultPnetDataClientResultPage<BrandItemDTO> resultPage = createRestCall()
                .parameters(restricts)
                .parameter("l", language)
                .parameter("p", pageIndex)
                .parameter("pp", itemsPerPage)
                .get("/api/v1/brands/find", new GenericType.Of<DefaultPnetDataClientResultPage<BrandItemDTO>>()
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
