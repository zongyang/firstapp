package Model;

public class AreaModel {
	private String province;
	private String city;
	private String county;

	public AreaModel(String area) {
		area = (area == null || area.length() < 6) ? "000000" : area;
		
		this.province = area.substring(0, 2) + "0000";
		this.city = area.substring(0, 4) + "00";
		this.county = area.substring(0,6);
	}

	public String getProvince() {
		return province;
	}

	public String getCity() {
		return city;
	}

	public String getCounty() {
		return county;
	}
}
