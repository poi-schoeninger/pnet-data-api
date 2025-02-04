package pnet.data.api.externalbrand;

import java.util.List;
import java.util.Locale;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import at.porscheinformatik.happyrest.GenericType;
import pnet.data.api.PnetDataClientException;
import pnet.data.api.client.DefaultPnetDataClientResultPage;
import pnet.data.api.client.PnetDataClientResultPage;
import pnet.data.api.client.context.AbstractPnetDataApiClient;
import pnet.data.api.client.context.PnetDataApiContext;
import pnet.data.api.util.Pair;

/**
 * Client for {@link ExternalBrandDataDTO}s.
 *
 * @author cet
 */
@Service
public class ExternalBrandDataClient extends AbstractPnetDataApiClient<ExternalBrandDataClient>
{

    @Autowired
    public ExternalBrandDataClient(PnetDataApiContext context)
    {
        super(context);
    }

    public ExternalBrandDataGet get()
    {
        return new ExternalBrandDataGet(this::get, null);
    }

    protected PnetDataClientResultPage<ExternalBrandDataDTO> get(List<Pair<String, Object>> restricts, int pageIndex,
        int itemsPerPage) throws PnetDataClientException
    {
        return invoke(restCall -> {
            DefaultPnetDataClientResultPage<ExternalBrandDataDTO> resultPage = restCall
                .parameters(restricts)
                .parameter("p", pageIndex)
                .parameter("pp", itemsPerPage)
                .path("/api/v1/externalbrands/details")
                .get(new GenericType.Of<DefaultPnetDataClientResultPage<ExternalBrandDataDTO>>()
                {
                    // intentionally left blank
                });

            resultPage.setPageSupplier(index -> get(restricts, index, itemsPerPage));

            return resultPage;
        });
    }

    public ExternalBrandDataSearch search()
    {
        return new ExternalBrandDataSearch(this::search, null);
    }

    protected PnetDataClientResultPage<ExternalBrandItemDTO> search(Locale language, String query,
        List<Pair<String, Object>> restricts, int pageIndex, int itemsPerPage) throws PnetDataClientException
    {
        return invoke(restCall -> {
            DefaultPnetDataClientResultPage<ExternalBrandItemDTO> resultPage = restCall
                .parameter("l", language)
                .parameter("q", query)
                .parameters(restricts)
                .parameter("p", pageIndex)
                .parameter("pp", itemsPerPage)
                .path("/api/v1/externalbrands/search")
                .get(new GenericType.Of<DefaultPnetDataClientResultPage<ExternalBrandItemDTO>>()
                {
                    // intentionally left blank
                });

            resultPage.setPageSupplier(index -> search(language, query, restricts, index, itemsPerPage));

            return resultPage;
        });
    }

    public ExternalBrandDataFind find()
    {
        return new ExternalBrandDataFind(this::find, null);
    }

    protected PnetDataClientResultPage<ExternalBrandItemDTO> find(Locale language, List<Pair<String, Object>> restricts,
        int pageIndex, int itemsPerPage) throws PnetDataClientException
    {
        return invoke(restCall -> {
            DefaultPnetDataClientResultPage<ExternalBrandItemDTO> resultPage = restCall
                .parameters(restricts)
                .parameter("l", language)
                .parameter("p", pageIndex)
                .parameter("pp", itemsPerPage)
                .path("/api/v1/externalbrands/find")
                .get(new GenericType.Of<DefaultPnetDataClientResultPage<ExternalBrandItemDTO>>()
                {
                    // intentionally left blank
                });

            resultPage.setPageSupplier(index -> find(language, restricts, index, itemsPerPage));

            return resultPage;
        });
    }
}
