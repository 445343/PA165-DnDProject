package mapping;

import java.util.Collection;
import java.util.List;
import java.util.Set;

/**
 * Bean mapper is used for mapping of entities to DTO and vice versa.
 * @author Boris Jadus
 */
public interface BeanMapper {
    <T> List<T> mapTo(Collection<?> objects, Class<T> mapToClass);

    <T> Set<T> mapToSet(Collection<?> objects, Class<T> mapToClass);

    <T> T mapTo(Object u, Class<T> mapToClass);
}
