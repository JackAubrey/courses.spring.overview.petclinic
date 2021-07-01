/**
 * 
 */
package courses.sov.petclinic.model;

import java.time.LocalDate;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.JoinColumn;
import javax.persistence.Lob;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

/**
 * @author dcividin
 *
 */
@Entity
@Table(name = "visits")
public class Visit extends BaseEntity {
	/**
	 * 
	 */
	private static final long serialVersionUID = 1383248854710978391L;
	
	@Column(name = "visit_date")
	private LocalDate date;
	
	@Column(name = "description")
	@Lob
	private String description;
	
	@ManyToOne
	@JoinColumn(name = "pet_id")
	private Pet pet;
	
	/**
	 * @return the date
	 */
	public LocalDate getDate() {
		return date;
	}
	/**
	 * @param date the date to set
	 */
	public void setDate(LocalDate date) {
		this.date = date;
	}
	/**
	 * @return the description
	 */
	public String getDescription() {
		return description;
	}
	/**
	 * @param description the description to set
	 */
	public void setDescription(String description) {
		this.description = description;
	}
	/**
	 * @return the pet
	 */
	public Pet getPet() {
		return pet;
	}
	/**
	 * @param pet the pet to set
	 */
	public void setPet(Pet pet) {
		this.pet = pet;
	}

}
