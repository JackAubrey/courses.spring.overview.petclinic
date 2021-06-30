/**
 * 
 */
package courses.sov.petclinic.model;

import java.util.HashSet;
import java.util.Set;

import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.JoinColumn;
import javax.persistence.JoinTable;
import javax.persistence.ManyToMany;
import javax.persistence.Table;

/**
 * @author dcividin
 *
 */
@Entity
@Table(name = "vets")
public class Vet extends Person {
	/**
	 * 
	 */
	private static final long serialVersionUID = -6155651241409836039L;

	@ManyToMany(fetch = FetchType.EAGER)
	@JoinTable(name = "vet_specialtis", 
		joinColumns = @JoinColumn(name = "vet_id"),
		inverseJoinColumns = @JoinColumn(name = "specialty_id"))
	private Set<Specialty> specialties = new HashSet<>();

	/**
	 * @return the specialties
	 */
	public Set<Specialty> getSpecialties() {
		return specialties;
	}

	/**
	 * @param specialties the specialties to set
	 */
	public void setSpecialties(Set<Specialty> specialties) {
		this.specialties = specialties;
	}
	
	/**
	 * 
	 * @param e
	 */
	public void addSpecialty(Specialty e) {
		if(this.specialties == null) {
			this.specialties = new HashSet<>();
		}
		
		this.specialties.add(e);
	}
}
