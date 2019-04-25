package pnet.data.api.company;

import java.util.List;

import pnet.data.api.util.AbstractSearchWithAggregates;
import pnet.data.api.util.AggregateNumberPerBrand;
import pnet.data.api.util.AggregateNumberPerContractType;
import pnet.data.api.util.AggregateNumberPerTenant;
import pnet.data.api.util.AggregateNumberPerType;
import pnet.data.api.util.Pair;
import pnet.data.api.util.RestrictBrand;
import pnet.data.api.util.RestrictContractType;
import pnet.data.api.util.RestrictCountryCode;
import pnet.data.api.util.RestrictHeadquarter;
import pnet.data.api.util.RestrictLocation;
import pnet.data.api.util.RestrictTenant;
import pnet.data.api.util.RestrictType;
import pnet.data.api.util.SearchWithAggregatesFunction;

/**
 * Search interface for companies
 *
 * @author HAM
 */
public class CompanyDataSearch
    extends AbstractSearchWithAggregates<CompanyItemDTO, CompanyAggregatesDTO, CompanyDataSearch>
    implements RestrictTenant<CompanyDataSearch>, RestrictBrand<CompanyDataSearch>,
    RestrictCountryCode<CompanyDataSearch>, RestrictType<CompanyDataSearch>, RestrictContractType<CompanyDataSearch>,
    RestrictLocation<CompanyDataSearch>, RestrictHeadquarter<CompanyDataSearch>,
    AggregateNumberPerTenant<CompanyDataSearch>, AggregateNumberPerBrand<CompanyDataSearch>,
    AggregateNumberPerType<CompanyDataSearch>, AggregateNumberPerContractType<CompanyDataSearch>
{

    public CompanyDataSearch(SearchWithAggregatesFunction<CompanyItemDTO, CompanyAggregatesDTO> searchFunction,
        List<Pair<String, Object>> restricts)
    {
        super(searchFunction, restricts);
    }
}