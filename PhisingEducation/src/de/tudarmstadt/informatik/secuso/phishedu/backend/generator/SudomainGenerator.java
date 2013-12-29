package de.tudarmstadt.informatik.secuso.phishedu.backend.generator;

import de.tudarmstadt.informatik.secuso.phishedu.backend.PhishURL;

/**
 * This attack is baesed on using an IP address as hostname.
 * This might confuse the user so he can not be sure that it might not be the correct Host.
 * @author Clemens Bergmann <cbergmann@schuhklassert.de>
 *
 */
public class SudomainGenerator extends BaseGenerator {

	/**
	 * See {@link BaseGenerator#BaseGenerator(PhishURL)}
	 * @param object See {@link BaseGenerator#BaseGenerator(PhishURL)}
	 */
	public SudomainGenerator(PhishURL object) {super(object);}
	
	@Override
	public String[] getParts(){
		String[] parts = this.object.getParts();
		return parts;
	}
	
}
