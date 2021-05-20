/**
 * 
 */
package courses.sov.petclinic.service;

import java.util.Set;

/**
 * @author dcividin
 *
 */
public interface CrudService <T, ID> {
	T findById(ID id);
	
	Set<T> findAll();
	
	T save(T object);
	
	void delete(T object);
	
	void deleteById(ID id);
}
