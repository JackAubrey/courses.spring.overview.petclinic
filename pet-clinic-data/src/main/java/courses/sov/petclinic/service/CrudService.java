/**
 * 
 */
package courses.sov.petclinic.service;

import java.util.Optional;
import java.util.Set;

/**
 * @author dcividin
 *
 */
public interface CrudService <T, ID> {
	Optional<T> findById(ID id);
	
	Set<T> findAll();
	
	T save(T object);
	
	void delete(T object);
	
	void deleteById(ID id);
	
	long count();
}
