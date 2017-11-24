/* Copyright 2017 Porsche Informatik GmbH
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 * http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */
package pnet.data.api.function;

import java.util.Collection;

import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import pnet.data.api.activity.ActivityMatchcode;
import pnet.data.api.brand.BrandMatchcode;
import pnet.data.api.companytype.CompanyTypeMatchcode;
import pnet.data.api.contracttype.ContractTypeMatchcode;
import pnet.data.api.infoarea.InfoareaMatchcode;
import pnet.data.api.numbertype.NumberTypeMatchcode;
import pnet.data.api.util.ByMatchcode;

/**
 * API for Functions.
 *
 * @author ham
 */
@RestController
@RequestMapping("/api/v1/functions")
public interface FunctionDataFacade extends ByMatchcode<FunctionMatchcode, FunctionDataDTO>
{

    /**
     * Returns multiple {@link FunctionDataDTO}s each matching all specified filters. The method is limited to a maximum
     * number of items per request. If no values are specified, it tries to return all items but may fail due to the
     * maximum number of items.
     *
     * @param matchcodes the matchcodes for filtering, optional
     * @param brandMatchcodes the matchcodes of brands for filtering, optional
     * @param companyTypeMatchcodes the matchcodes for company types for filtering, optional
     * @param contractTypeMatchcodes the matchcodes of contract types for filtering, optional
     * @param numberTypeMatchcodes the matchcodes of number types for filtering, optional
     * @param activityMatchcodes the matchcodes of activities for filtering, optional
     * @param infoareaMatchcodes the matchcodes of infoareas for filtering, optional
     * @return a collection of all found items
     */
    @RequestMapping(value = "/", method = RequestMethod.GET, produces = MediaType.APPLICATION_JSON_VALUE)
    Collection<FunctionDataDTO> getAll(
        @RequestParam(value = "matchcode", required = false) Collection<FunctionMatchcode> matchcodes,
        @RequestParam(value = "brand", required = false) Collection<BrandMatchcode> brandMatchcodes,
        @RequestParam(value = "companyType", required = false) Collection<CompanyTypeMatchcode> companyTypeMatchcodes,
        @RequestParam(value = "contractType",
            required = false) Collection<ContractTypeMatchcode> contractTypeMatchcodes,
        @RequestParam(value = "numberType", required = false) Collection<NumberTypeMatchcode> numberTypeMatchcodes,
        @RequestParam(value = "activity", required = false) Collection<ActivityMatchcode> activityMatchcodes,
        @RequestParam(value = "infoarea", required = false) Collection<InfoareaMatchcode> infoareaMatchcodes);

    /**
     * Searches for {@link FunctionItemDTO} with the specified query. If one or more filters are set each filter will be
     * applied (AND) and one of the values of each filter must match (OR). The method returns a stripped down item with
     * only a few properties.
     *
     * @param language the language
     * @param query the query
     * @param page the number of the page, 1-based
     * @param perPage the number of items per page
     * @param brandMatchcodes the matchcodes of brands for filtering, optional
     * @param companyTypeMatchcodes the matchcodes for company types for filtering, optional
     * @param contractTypeMatchcodes the matchcodes of contract types for filtering, optional
     * @param numberTypeMatchcodes the matchcodes of number types for filtering, optional
     * @param activityMatchcodes the matchcodes of activities for filtering, optional
     * @param infoareaMatchcodes the matchcodes of infoareas for filtering, optional
     * @return a collection of results
     */
    // CHECKSTYLE:OFF
    @RequestMapping(value = "/search")
    Collection<FunctionItemDTO> search(@RequestParam(value = "l") String language, @RequestParam("q") String query,
        @RequestParam(value = "p", defaultValue = "1") int page,
        @RequestParam(value = "pp", defaultValue = "10") int perPage,
        @RequestParam(value = "brand", required = false) Collection<BrandMatchcode> brandMatchcodes,
        @RequestParam(value = "companyType", required = false) Collection<CompanyTypeMatchcode> companyTypeMatchcodes,
        @RequestParam(value = "contractType",
            required = false) Collection<ContractTypeMatchcode> contractTypeMatchcodes,
        @RequestParam(value = "numberType", required = false) Collection<NumberTypeMatchcode> numberTypeMatchcodes,
        @RequestParam(value = "activity", required = false) Collection<ActivityMatchcode> activityMatchcodes,
        @RequestParam(value = "infoarea", required = false) Collection<InfoareaMatchcode> infoareaMatchcodes);
    // CHECKSTYLE:ON
}
