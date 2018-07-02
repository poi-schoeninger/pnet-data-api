package pnet.data.api.person;

import java.util.Arrays;
import java.util.List;
import java.util.Locale;

import org.springframework.stereotype.Service;

import at.porscheinformatik.happyrest.GenericType;
import pnet.data.api.PnetDataClientException;
import pnet.data.api.client.DefaultPnetDataClientResultPage;
import pnet.data.api.client.PnetDataClientResultPage;
import pnet.data.api.client.context.AbstractPnetDataApiClient;
import pnet.data.api.client.context.PnetDataApiContext;
import pnet.data.api.util.GetById;
import pnet.data.api.util.Pair;

/**
 * Data-API client for {@link PersonDataDTO}s.
 */
@Service
public class PersonDataClient extends AbstractPnetDataApiClient<PersonDataClient> implements GetById<PersonDataDTO>
{

    public PersonDataClient(PnetDataApiContext context)
    {
        super(context);
    }

    @Override
    public PnetDataClientResultPage<PersonDataDTO> getAllByIds(List<Integer> ids, int pageIndex, int itemsPerPage)
        throws PnetDataClientException
    {
        return getAll(ids, null, null, null, null, pageIndex, itemsPerPage);
    }

    public PersonDataDTO getByGuid(String guid) throws PnetDataClientException
    {
        return getAllByGuids(Arrays.asList(guid), 0, 1).first();
    }

    public PnetDataClientResultPage<PersonDataDTO> getAllByGuids(List<String> guids, int pageIndex, int itemsPerPage)
        throws PnetDataClientException
    {
        return getAll(null, guids, null, null, null, pageIndex, itemsPerPage);
    }

    public PersonDataDTO getByPreferredUserId(String preferredUserId) throws PnetDataClientException
    {
        return getAllByPreferredUserIds(Arrays.asList(preferredUserId), 0, 1).first();
    }

    public PnetDataClientResultPage<PersonDataDTO> getAllByPreferredUserIds(List<String> preferredUserIds,
        int pageIndex, int itemsPerPage) throws PnetDataClientException
    {
        return getAll(null, null, preferredUserIds, null, null, pageIndex, itemsPerPage);
    }

    public PersonDataDTO getByEmail(String email) throws PnetDataClientException
    {
        return getAllByEmails(Arrays.asList(email), 0, 1).first();
    }

    public PnetDataClientResultPage<PersonDataDTO> getAllByEmails(List<String> emails, int pageIndex, int itemsPerPage)
        throws PnetDataClientException
    {
        return getAll(null, null, null, emails, null, pageIndex, itemsPerPage);
    }

    /**
     * Returns the person with the specified personnel number. Leading zeros will be ignored.
     *
     * @param personnelNumber the personnel number
     * @return the person, null if not found
     * @throws PnetDataClientException on occasion
     */
    public PersonDataDTO getByPersonnelNumber(String personnelNumber) throws PnetDataClientException
    {
        return getAllByPersonnelNumbers(Arrays.asList(personnelNumber), 0, 1).first();
    }

    public PnetDataClientResultPage<PersonDataDTO> getAllByPersonnelNumbers(List<String> personnelNumbers,
        int pageIndex, int itemsPerPage) throws PnetDataClientException
    {
        return getAll(null, null, null, null, personnelNumbers, pageIndex, itemsPerPage);
    }

    protected PnetDataClientResultPage<PersonDataDTO> getAll(List<Integer> ids, List<String> guids,
        List<String> preferredUserIds, List<String> emails, List<String> personnelNumbers, int pageIndex,
        int itemsPerPage) throws PnetDataClientException
    {
        return invoke(restCall -> {
            DefaultPnetDataClientResultPage<PersonDataDTO> resultPage = restCall //
                .parameters("id", ids)
                .parameters("guid", guids)
                .parameters("preferredUserId", preferredUserIds)
                .parameters("email", emails)
                .parameters("personnelNumber", personnelNumbers)
                .parameter("p", pageIndex)
                .parameter("pp", itemsPerPage)
                .get("/api/v1/persons/details", new GenericType.Of<DefaultPnetDataClientResultPage<PersonDataDTO>>()
                {
                });

            resultPage.setPageSupplier(
                index -> getAll(ids, guids, preferredUserIds, emails, personnelNumbers, index, itemsPerPage));

            return resultPage;
        });
    }

    public PersonDataSearch search()
    {
        return new PersonDataSearch(this::search, null);
    }

    protected PnetDataClientResultPage<PersonItemDTO> search(Locale language, String query,
        List<Pair<String, Object>> restricts, int pageIndex, int itemsPerPage) throws PnetDataClientException
    {
        return invoke(restCall -> {
            DefaultPnetDataClientResultPage<PersonItemDTO> resultPage = restCall
                .parameter("l", language)
                .parameter("q", query)
                .parameters(restricts)
                .parameter("p", pageIndex)
                .parameter("pp", itemsPerPage)
                .get("/api/v1/persons/search", new GenericType.Of<DefaultPnetDataClientResultPage<PersonItemDTO>>()
                {
                });

            resultPage.setPageSupplier(index -> search(language, query, restricts, index, itemsPerPage));

            return resultPage;
        });
    }

    public PersonDataFind find()
    {
        return new PersonDataFind(this::find, null);
    }

    protected PnetDataClientResultPage<PersonItemDTO> find(Locale language, List<Pair<String, Object>> restricts,
        int pageIndex, int itemsPerPage) throws PnetDataClientException
    {
        return invoke(restCall -> {
            DefaultPnetDataClientResultPage<PersonItemDTO> resultPage = restCall
                .parameters(restricts)
                .parameter("l", language)
                .parameter("p", pageIndex)
                .parameter("pp", itemsPerPage)
                .get("/api/v1/persons/find", new GenericType.Of<DefaultPnetDataClientResultPage<PersonItemDTO>>()
                {
                });

            resultPage.setPageSupplier(index -> find(language, restricts, index, itemsPerPage));

            return resultPage;
        });
    }
}
