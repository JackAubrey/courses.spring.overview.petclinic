/**
 * 
 */
package courses.sov.petclinic.service.map;

import java.util.HashMap;
import java.util.HashSet;
import java.util.Map;
import java.util.Optional;
import java.util.Set;

import courses.sov.petclinic.service.CrudService;

/**
 * @author dcividin
 *
 */
public abstract class AbstractMapCrudService<T, ID> implements CrudService<T, ID> {
	protected Map<ID, T> map = new HashMap<>();
	
	@Override
	public Set<T> findAll() {
		return new HashSet<>(map.values());
	}
	
	@Override
	public Optional<T> findById(ID id) {
		var object = map.get(id);
		
		if(object == null) {
			return Optional.empty();
		} else {
			return Optional.of(object);
		}
	}
	
	public T save(ID id, T object) {
		return map.put(id, object);
	}
	
	@Override
	public void deleteById(ID id) {
		if(id != null) {
			map.remove(id);
		}
	}
	
	@Override
	public void delete(T object) {
		map.entrySet().removeIf(e -> e.getValue().equals(object));
	}
}
