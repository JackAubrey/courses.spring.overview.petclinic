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

/**
 * @author dcividin
 *
 */
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
	 */
	public Pet() {
		super();
	}
	
	/**
	 * 
	 * @param name
	 */
	public Pet(String name) {
		this.setName(name);
	}
	
	/**
	 * @return the birthDate
	 */
	public LocalDate getBirthDate() {
		return birthDate;
	}
	/**
	 * @param birthDate the birthDate to set
	 */
	public Pet setBirthDate(LocalDate birthDate) {
		this.birthDate = birthDate;
		return this;
	}
	/**
	 * @return the type
	 */
	public PetType getType() {
		return type;
	}
	/**
	 * @param type the type to set
	 */
	public Pet setType(PetType type) {
		this.type = type;
		return this;
	}
	/**
	 * @return the owner
	 */
	public Owner getOwner() {
		return owner;
	}
	/**
	 * @param owner the owner to set
	 */
	public Pet setOwner(Owner owner) {
		this.owner = owner;
		return this;
	}

	/**
	 * @return the visits
	 */
	public Set<Visit> getVisits() {
		return visits;
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
		if(this.visits == null) {
			this.visits = new HashSet<>();
		}
		
		if(visit != null) {
			visit.setPet(this);
			this.visits.add(visit);
		}
	}
}
