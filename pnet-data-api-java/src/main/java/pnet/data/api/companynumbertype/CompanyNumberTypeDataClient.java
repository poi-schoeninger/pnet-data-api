package pnet.data.api.companynumbertype;

import java.util.List;
import java.util.Locale;

import org.springframework.stereotype.Service;

import at.porscheinformatik.happyrest.GenericType;
import pnet.data.api.PnetDataClientException;
import pnet.data.api.client.DefaultPnetDataClientResultPage;
import pnet.data.api.client.PnetDataClientResultPage;
import pnet.data.api.client.context.AbstractPnetDataApiClient;
import pnet.data.api.client.context.PnetDataApiContext;
import pnet.data.api.util.GetByMatchcode;
import pnet.data.api.util.Pair;

/**
 * Data-API client for {@link CompanyNumberTypeDataDTO}s.
 *
 * @author cet
 */
@Service
public class CompanyNumberTypeDataClient extends AbstractPnetDataApiClient<CompanyNumberTypeDataClient>
    implements GetByMatchcode<CompanyNumberTypeDataDTO>
{

    public CompanyNumberTypeDataClient(PnetDataApiContext context)
    {
        super(context);
    }

    @Override
    public PnetDataClientResultPage<CompanyNumberTypeDataDTO> getAllByMatchcodes(List<String> matchcodes, int pageIndex,
        int itemsPerPage) throws PnetDataClientException
    {
        return invoke(restCall -> {
            DefaultPnetDataClientResultPage<CompanyNumberTypeDataDTO> resultPage = restCall //
                .parameters("mc", matchcodes)
                .parameter("p", pageIndex)
                .parameter("pp", itemsPerPage)
                .get("/api/v1/companynumbertypes/details",
                    new GenericType.Of<DefaultPnetDataClientResultPage<CompanyNumberTypeDataDTO>>()
                    {
                    });

            resultPage.setPageSupplier(index -> getAllByMatchcodes(matchcodes, index, itemsPerPage));

            return resultPage;
        });
    }

    public CompanyNumberTypeDataSearch search()
    {
        return new CompanyNumberTypeDataSearch(this::search, null);
    }

    protected PnetDataClientResultPage<CompanyNumberTypeItemDTO> search(Locale language, String query,
        List<Pair<String, Object>> restricts, int pageIndex, int itemsPerPage) throws PnetDataClientException
    {
        return invoke(restCall -> {
            DefaultPnetDataClientResultPage<CompanyNumberTypeItemDTO> resultPage = restCall
                .parameter("l", language)
                .parameter("q", query)
                .parameters(restricts)
                .parameter("p", pageIndex)
                .parameter("pp", itemsPerPage)
                .get("/api/v1/companynumbertypes/search",
                    new GenericType.Of<DefaultPnetDataClientResultPage<CompanyNumberTypeItemDTO>>()
                    {
                    });

            resultPage.setPageSupplier(index -> search(language, query, restricts, index, itemsPerPage));

            return resultPage;
        });
    }

    public CompanyNumberTypeDataFind find()
    {
        return new CompanyNumberTypeDataFind(this::find, null);
    }

    protected PnetDataClientResultPage<CompanyNumberTypeItemDTO> find(Locale language,
        List<Pair<String, Object>> restricts, int pageIndex, int itemsPerPage) throws PnetDataClientException
    {
        return invoke(restCall -> {
            DefaultPnetDataClientResultPage<CompanyNumberTypeItemDTO> resultPage = restCall
                .parameters(restricts)
                .parameter("l", language)
                .parameter("p", pageIndex)
                .parameter("pp", itemsPerPage)
                .get("/api/v1/companynumbertypes/find",
                    new GenericType.Of<DefaultPnetDataClientResultPage<CompanyNumberTypeItemDTO>>()
                    {
                    });

            resultPage.setPageSupplier(index -> find(language, restricts, index, itemsPerPage));

            return resultPage;
        });
    }
}
