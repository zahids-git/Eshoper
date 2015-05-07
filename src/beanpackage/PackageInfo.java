package beanpackage;

public class PackageInfo {
	
	private int packId;
	private int pid;
	private double total_number;
	private double total_price;
	private String pdate;
	private String dayType;
	private int total_Sell;
	
	
	public String getDayType() {
		return dayType;
	}
	public void setDayType(String dayType) {
		this.dayType = dayType;
	}	
	public int getPackId() {
		return packId;
	}
	public void setPackId(int packId) {
		this.packId = packId;
	}
	public int getPid() {
		return pid;
	}
	public void setPid(int pid) {
		this.pid = pid;
	}
	public double getTotal_number() {
		return total_number;
	}
	public void setTotal_number(double total_number) {
		this.total_number = total_number;
	}
	public double getTotal_price() {
		return total_price;
	}
	public void setTotal_price(double total_price) {
		this.total_price = total_price;
	}
	public String getPdate() {
		return pdate;
	}
	public void setPdate(String pdate) {
		this.pdate = pdate;
	}
	public int getTotal_Sell() {
		return total_Sell;
	}
	public void setTotal_Sell(int total_Sell) {
		this.total_Sell = total_Sell;
	}

}
