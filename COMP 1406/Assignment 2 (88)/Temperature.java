public class Temperature{

	// Encapsulation of temperature and scale
	private double temp;
	private char scale;

  /* different scale names */
	public static String[] scales = {"Celsius", "Fahrenheit", "Kelvin"};

	/* Initializes a temperature object with given value in Celcius
	 *  
	 *  If the initial temperature is less than -273.15 then the temperature 
	 *  object will be initialized with -273.15C.
     *   
	 * @param temp is the initial temperature in Celsius.
	 */
  public Temperature(double temp){
	this.temp = temp;
	this.scale = 'C';
	if(temp < -273.15){
		temp = -273.15;
	}
  }

	/* Initializes a temperature object with given value using the specified scale
     * <par>
	 * If the temperature is lower than absolute zero, then set the temperature to
     * absolute zero (in whichever scale is specified).
     * <par>	 
 	 * Examples: new Temperature(12.3, "K")
     *           new Temperature(-90.2, "Celsius")
	 *
	 * @param temp is the initial temperature
	 * @param scale is the scale of initial temperature and must either be 
	 *        one of the Strings in the <code>scales</code> array, or 
	 *        the first letter (capitalized) of one of those strings.
	 */
  public Temperature(double temp, String scale){
  	this.temp = temp;
    this.scale = scale.charAt(0);
  }

	/* getter for the scale
	 *
	 * The output of this getter method must always be the first letter of one
	 * of the strings in the <code>scales</code> array, capitalized.
     *	 
	 * @return the current scale of the object as a single char (the first letter, 
	 *         capitalized of one of the strings from <code>scales</code>)
	 */
  public char getScale(){
	return scale;	
  }

	/* getter for the temperature
	 *
	 * @return the temperature of the object using the current scale
	 */
  public double getTemp(){
    return this.temp;
  }

    /* setter for scale
	 *
	 * 
	 *
	 * @param scale is the new scale of the temperature and must either be 
	 *        one of the Strings in the <code>scales</code> array, or 
	 *        the first letter (capitalized) of one of those strings.
	 */
  public void setScale(String scale){ 
    if(this.scale == 'C' && scale.charAt(0) == 'F'){
        temp = temp*9/5 + 32;
        this.scale = 'F';
    }else if(this.scale == 'C' && scale.charAt(0) == 'K'){
        temp = temp + 273.15;
        this.scale = 'K';
    }else if(this.scale == 'F' && scale.charAt(0) == 'C'){
        temp = (temp-32)*5/9;
        this.scale = 'C';
    }else if(this.scale == 'F' && scale.charAt(0) == 'K'){
        temp = (temp + 459.67)*5/9;
        this.scale = 'K';
    }else if(this.scale == 'K' && scale.charAt(0) == 'C'){
        temp = temp - 273.15;
        this.scale = 'C';
    }else if(this.scale == 'K' && scale.charAt(0) == 'F'){
        temp = temp*9/5 - 459.67;
        this.scale = 'F';
    }else{
    	return;
    }
  }

	/* setter for temperature
	 *
	 * @param temp is the new temperature (in the objects current scale)
	 */
  public void setTemp(double temp){
  	temp = temp;
  }

   /* setter for temperature
	*
	*
	* @param temp is the new temperature
	* @param scale is the scale of the new temperature. It must be 
    *        the first letter (capitalized) of one of the strings in
    *        the <code>scales</code> array.
    */	 
  public void setTemp(double temp, char scale){
  	temp = temp;
  	this.scale = scale;
  }

  	/* setter for temperature
	 *
	 * @param temp is the new temperature
	 * @param scale is the scale of the new temperature. It must be one
	 *        of the strings in the <code>scales</code> array, or the first letter
	 *        (capitalized) of one of those strings.
	 */
	public void setTemp(double temp, String scale){
	temp = temp;
	this.scale = scale.charAt(0);
  }

	/* ------------------------------------------------- */
	/* ------------------------------------------------- */
 	/*        do not change anything below this          */
  	/* ------------------------------------------------- */
	/* ------------------------------------------------- */
	
  /* String representation of a temperature object */
	@Override
  public String toString(){
    return "" + this.getTemp() + this.getScale();
  }
}