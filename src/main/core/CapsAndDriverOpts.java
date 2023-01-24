package src.main.core;

import org.openqa.selenium.remote.DesiredCapabilities;

public class CapsAndDriverOpts {

	private DesiredCapabilities capabilities;
	private String driverURL;
	
	public CapsAndDriverOpts(DesiredCapabilities caps, String dURL)
	{
		this.capabilities = caps;
		this.driverURL = dURL;
	}

	public CapsAndDriverOpts(DesiredCapabilities caps)
	{
		this.capabilities = caps;
	}

	public CapsAndDriverOpts() {
		// TODO Auto-generated constructor stub
	}

	public DesiredCapabilities getCapabilities() {
		return this.capabilities;
	}

	public void setCapabilities(DesiredCapabilities capabilities) {
		this.capabilities = capabilities;
	}

	public String getDriverURL() {
		System.out.println("getDriverURL = " + this.driverURL);
		return this.driverURL;
	}

	public void setDriverURL(String driverURL) {
		this.driverURL = driverURL;
	}
}
