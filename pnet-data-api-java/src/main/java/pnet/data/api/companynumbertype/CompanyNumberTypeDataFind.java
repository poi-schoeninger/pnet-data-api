package pnet.data.api.companynumbertype;

import java.util.List;

import pnet.data.api.util.AbstractFind;
import pnet.data.api.util.FindFunction;
import pnet.data.api.util.Pair;
import pnet.data.api.util.RestrictMatchcode;
import pnet.data.api.util.RestrictUpdatedAfter;

/**
 * <<<<<<< Updated upstream Find interface for {@link CompanyNumberTypeDataClient}. =======
 *
 * @author cet
 *
 *         >>>>>>> Stashed changes
 */
public class CompanyNumberTypeDataFind extends AbstractFind<CompanyNumberTypeItemDTO, CompanyNumberTypeDataFind>
    implements RestrictMatchcode<CompanyNumberTypeDataFind>, RestrictUpdatedAfter<CompanyNumberTypeDataFind>
{

    protected CompanyNumberTypeDataFind(FindFunction<CompanyNumberTypeItemDTO> findFunction,
        List<Pair<String, Object>> restricts)
    {
        super(findFunction, restricts);
    }

}
