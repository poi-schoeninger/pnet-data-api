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
package pnet.data.api.externalbrand;

import java.time.LocalDateTime;
import java.util.List;

import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import pnet.data.api.PnetDataApiException;
import pnet.data.api.ResultCollection;
import pnet.data.api.numbertype.NumberTypeDataDTO;
import pnet.data.api.util.ByMatchcode;

/**
 * API for external brands.
 *
 * @author ham
 */
@RestController
@RequestMapping("/api/v1/externalbrands")
public interface ExternalBrandDataFacade extends ByMatchcode<ExternalBrandDataDTO>
{

    /**
     * Returns multiple {@link ExternalBrandDataDTO}s each matching all specified restrictions. If one or more
     * restrictions are set each restriction will be applied (AND) and one of the values of each restriction must match
     * (OR). It is not possible to call this method without any restriction. The number of results is limited. The
     * {@link ResultCollection} may contain a call for more results.
     *
     * @param matchcodes the matchcodes for filtering, optional
     * @param ids the ids for filtering, optional
     * @return a collection of all found items, never null
     * @throws PnetDataApiException on occasion
     */
    @RequestMapping(value = "/details", method = RequestMethod.GET, produces = MediaType.APPLICATION_JSON_VALUE)
    ResultCollection<NumberTypeDataDTO> getAll(@RequestParam(value = "mc", required = false) List<String> matchcodes,
        @RequestParam(value = "id", required = false) List<String> ids) throws PnetDataApiException;

    /**
     * Searches for {@link ExternalBrandItemDTO} with the specified query.
     *
     * @param language the language
     * @param query the query
     * @param pageIndex the number of the page, 0-based
     * @param itemsPerPage the number of items per page
     * @return a page of the results, never null
     * @throws PnetDataApiException on occasion
     */
    @RequestMapping(value = "/search")
    List<ExternalBrandItemDTO> search(@RequestParam(value = "l") String language, @RequestParam("q") String query,
        @RequestParam(value = "p", defaultValue = "0") int pageIndex,
        @RequestParam(value = "pp", defaultValue = "10") int itemsPerPage) throws PnetDataApiException;

    /**
     * Finds multiple {@link ExternalBrandItemDTO}s each matching all specified restrictions. If one or more
     * restrictions are set each restriction will be applied (AND) and one of the values of each restriction must match
     * (OR).
     *
     * @param language the language
     * @param matchcodes the matchcodes for filtering, optional
     * @param ids the ids for filtering, optional
     * @param updatedAfter updated after the specified date and time, optional
     * @param pageIndex the number of the page, 0-based
     * @param itemsPerPage the number of items per page
     * @return a page of the results, never null
     * @throws PnetDataApiException on occasion
     */
    @RequestMapping(value = "/find")
    List<ExternalBrandItemDTO> find(@RequestParam(value = "l") String language,
        @RequestParam(value = "mc", required = false) List<String> matchcodes,
        @RequestParam(value = "id", required = false) List<String> ids,
        @RequestParam(value = "up", required = false) LocalDateTime updatedAfter,
        @RequestParam(value = "p", defaultValue = "0") int pageIndex,
        @RequestParam(value = "pp", defaultValue = "10") int itemsPerPage) throws PnetDataApiException;

}
