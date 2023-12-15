package model;

public class student {
	private Integer id;
	private String name;
	private Integer chi;
	private Integer eng;

	public student() {
		super();
		// TODO Auto-generated constructor stub
	}

	public student(String name, Integer chi, Integer eng) {
		super();
		this.name = name;
		this.chi = chi;
		this.eng = eng;
	}

	public Integer getId() {
		return id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public Integer getChi() {
		return chi;
	}

	public void setChi(Integer chi) {
		this.chi = chi;
	}

	public Integer getEng() {
		return eng;
	}

	public void setEng(Integer eng) {
		this.eng = eng;
	}

}
