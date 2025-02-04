package pnet.data.api.util;

import java.util.Collection;

/**
 * Restricts the commercial register number.
 *
 * @author ham
 * @param <SELF> the type of the filter for chaining
 */
public interface RestrictCommercialRegisterNumber<SELF extends Restrict<SELF>> extends Restrict<SELF>
{
    default SELF commercialRegisterNumber(String... numbers)
    {
        return restrict("commercialRegisterNumber", (Object[]) numbers);
    }

    default SELF commercialRegisterNumbers(Collection<String> numbers)
    {
        return commercialRegisterNumber(numbers.toArray(new String[numbers.size()]));
    }
}
