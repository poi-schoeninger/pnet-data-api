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
package pnet.data.api;

import java.io.Serializable;
import java.util.Iterator;
import java.util.List;

/**
 * Holding results of a search or find operation with paging information.
 *
 * @author ham
 * @param <T> the type of item
 */
public interface ResultPage<T> extends Iterable<T>, Serializable
{

    static <T> ResultPage<T> of(List<T> items, int itemsPerPage, int numberOfItems, int pageIndex, int numberOfPages)
    {
        return new ResultPage<T>()
        {
            private static final long serialVersionUID = -999167833058168881L;

            @Override
            public List<T> getItems()
            {
                return items;
            }

            @Override
            public int getItemsPerPage()
            {
                return itemsPerPage;
            }

            @Override
            public int getNumberOfItems()
            {
                return numberOfItems;
            }

            @Override
            public int getPageIndex()
            {
                return pageIndex;
            }

            @Override
            public int getNumberOfPages()
            {
                return numberOfPages;
            }
        };
    }

    /**
     * @return the list of items, never null.
     */
    List<T> getItems();

    /**
     * @return the first item, null if there isn't one
     */
    default T first()
    {
        List<T> items = getItems();

        return items != null && items.size() > 0 ? items.get(0) : null;
    }

    /**
     * @return the number of items on this page.
     */
    default int size()
    {
        return getItems().size();
    }

    @Override
    default Iterator<T> iterator()
    {
        return getItems().iterator();
    }

    /**
     * @return the number of items per page, which must not be the same a the number of items in this page.
     */
    int getItemsPerPage();

    /**
     * @return the total number of items.
     */
    int getNumberOfItems();

    /**
     * @return the index of this page, 0-based.
     */
    int getPageIndex();

    /**
     * @return the total number of pages.
     */
    int getNumberOfPages();

    /**
     * @return true if there is another page
     */
    default boolean hasNextPage()
    {
        return getPageIndex() < getNumberOfPages();
    }

}
