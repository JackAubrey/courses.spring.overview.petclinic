/**
 * 
 */
package courses.sov.petclinic.model;

import java.time.LocalDate;
import java.util.HashSet;
import java.util.Set;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.persistence.Table;

import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

/**
 * @author dcividin
 *
 */
@Getter
@Setter
@NoArgsConstructor
@Entity
@Table(name = "pets")
public class Pet extends NamedEntity {
	/**
	 * 
	 */
	private static final long serialVersionUID = -5848317630792706665L;

	@ManyToOne
	@JoinColumn(name = "pet_type_id")
	private PetType type;
	
	@Column(name = "birth_date")
	private LocalDate birthDate;
	
	// the foreign key goes her because "the owner of the relationship" between parent and child
	// delegate this responsibility to the child.
	// this is the reason why we use @JoinColumn here an not in "owner" class
	@ManyToOne
	@JoinColumn(name = "owner_id")
	private Owner owner;
	
	@OneToMany(cascade = CascadeType.ALL, mappedBy = "pet")
	private Set<Visit> visits = new HashSet<>();
	
	/**
	 * 
	 * @param id
	 * @param name
	 * @param type
	 * @param birthDate
	 * @param owner
	 * @param visits
	 */
	@Builder
	public Pet(Long id, String name, PetType type, LocalDate birthDate, Owner owner, Set<Visit> visits) {
		super();
		setId(id);
		setName(name);
		setType(type);
		setBirthDate(birthDate);;
		setOwner(owner);
		setVisits(visits);
	}
	

	/**
	 * @param visits the visits to set
	 */
	public void setVisits(Set<Visit> visits) {
		this.visits = visits == null ? new HashSet<>() : new HashSet<>(visits);
		
		this.visits.forEach(v -> v.setPet(this));
	}
	
	/**
	 * @param visit the visit to set
	 */
	public void addVisit(Visit visit) {
		if(visit != null) {
			visit.setPet(this);
			this.visits.add(visit);
		}
	}
}
