/**
 * 
 */
package courses.sov.petclinic.service.map;

import java.util.HashSet;
import java.util.LinkedHashMap;
import java.util.Map;
import java.util.Optional;
import java.util.Set;
import java.util.concurrent.atomic.AtomicLong;

import courses.sov.petclinic.model.BaseEntity;
import courses.sov.petclinic.service.CrudService;

/**
 * @author dcividin
 *
 */
public abstract class AbstractMapCrudService<T extends BaseEntity, ID extends Number> implements CrudService<T, ID > {
	protected Map<Number, T> map = new LinkedHashMap<>();
	private AtomicLong sequence = new AtomicLong();
	
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
	
	public T save(T object) {
		if(object != null) {
			if(object.getId() == null) {
				object.setId(getNextId());
			}
			
			map.put(object.getId(), object);
			
			return object;
		} else {
			throw new RuntimeException("Object is null");
		}
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
	
	private Long getNextId() {
		return sequence.incrementAndGet();
	}
}
