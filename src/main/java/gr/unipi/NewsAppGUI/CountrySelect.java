package gr.unipi.NewsAppGUI;

public class CountrySelect {
	
	
	private String countryShortcut;
	private String countryName;
	
	public CountrySelect(String countryShortcut, String countryName) {
		super();
		this.countryShortcut = countryShortcut;
		this.countryName = countryName;
	}

	public String getCountryShortcut() {
		return countryShortcut;
	}

	public void setCountryShortcut(String countryShortcut) {
		this.countryShortcut = countryShortcut;
	}

	public String getCountryName() {
		return countryName;
	}

	public void setCountryName(String countryName) {
		this.countryName = countryName;
	}
	
	

}
