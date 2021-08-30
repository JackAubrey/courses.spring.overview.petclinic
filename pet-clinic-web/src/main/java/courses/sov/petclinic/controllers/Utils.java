/**
 * 
 */
package courses.sov.petclinic.controllers;

import courses.sov.petclinic.exceptions.BadRequestException;

/**
 * @author dcividin
 *
 */
public class Utils {
	/**
	 * 
	 */
	private Utils() {
		super();
	}
	
	/**
	 * 
	 * @param sId
	 * @return
	 */
	public static Long toLong(String sId) {
		try {
			return Long.valueOf(sId);
		} catch(NumberFormatException e) {
			throw new BadRequestException("The ID must to be a number. Unable to use the value ["+sId+"]", e);
		}
	}
}
