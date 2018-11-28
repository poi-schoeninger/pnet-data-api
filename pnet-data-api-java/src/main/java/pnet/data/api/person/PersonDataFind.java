package pnet.data.api.person;

import java.util.List;

import pnet.data.api.util.AbstractFind;
import pnet.data.api.util.FindFunction;
import pnet.data.api.util.Pair;
import pnet.data.api.util.RestrictActivity;
import pnet.data.api.util.RestrictAdvisorAssignmentCompany;
import pnet.data.api.util.RestrictAdvisorAssignmentDivision;
import pnet.data.api.util.RestrictAdvisorAssignmentType;
import pnet.data.api.util.RestrictCompanyId;
import pnet.data.api.util.RestrictControllingArea;
import pnet.data.api.util.RestrictCostCenter;
import pnet.data.api.util.RestrictEmail;
import pnet.data.api.util.RestrictFunction;
import pnet.data.api.util.RestrictGuid;
import pnet.data.api.util.RestrictId;
import pnet.data.api.util.RestrictPersonnelDepartment;
import pnet.data.api.util.RestrictPersonnelNumber;
import pnet.data.api.util.RestrictPreferredUserId;
import pnet.data.api.util.RestrictScroll;
import pnet.data.api.util.RestrictSupervisorPersonnelNumber;
import pnet.data.api.util.RestrictTenant;
import pnet.data.api.util.RestrictUpdatedAfter;

/**
 * Find interface for persons.
 *
 * @author ham
 */
public class PersonDataFind extends AbstractFind<PersonItemDTO, PersonDataFind> implements RestrictId<PersonDataFind>,
    RestrictTenant<PersonDataFind>, RestrictGuid<PersonDataFind>, RestrictPreferredUserId<PersonDataFind>,
    RestrictEmail<PersonDataFind>, RestrictCostCenter<PersonDataFind>, RestrictPersonnelNumber<PersonDataFind>,
    RestrictSupervisorPersonnelNumber<PersonDataFind>, RestrictControllingArea<PersonDataFind>,
    RestrictPersonnelDepartment<PersonDataFind>, RestrictCompanyId<PersonDataFind>, RestrictFunction<PersonDataFind>,
    RestrictActivity<PersonDataFind>, RestrictAdvisorAssignmentCompany<PersonDataFind>,
    RestrictAdvisorAssignmentType<PersonDataFind>, RestrictAdvisorAssignmentDivision<PersonDataFind>,
    RestrictUpdatedAfter<PersonDataFind>, RestrictScroll<PersonDataFind>
{

    public PersonDataFind(FindFunction<PersonItemDTO> findFunction, List<Pair<String, Object>> restrictItems)
    {
        super(findFunction, restrictItems);
    }

}