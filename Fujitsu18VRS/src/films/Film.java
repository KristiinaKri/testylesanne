package films;

public class Film {
	private String name;
	private Type type;

	public Film(String name, Type type) {
		this.name = name;
		this.type = type;
	}

	public String getName() {
		return name;
	}

	public String getType() {
		return type.getType();
	}

	public String setName(String name) {
		this.name = name;
		return name;
	}

	public String setType(int type) {
		this.type = new Type(type);
		return this.type.getType();
	}
}