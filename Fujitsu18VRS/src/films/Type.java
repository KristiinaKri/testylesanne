package films;

public class Type {
	private String type;
	private int no;

	public Type (int no) {
		this.no = no;
		if (no == 1) {
			this.type = "New release";
		} else if (no == 2) {
			this.type = "Regular film";
		} else if (no == 3) {
			this.type = "Old film";
		} else {
			throw new IllegalArgumentException("This film type is not available.");
		}
	}

	public String getType() {
		return type;
	}
/*
	public String setType(int type) {
		this.no = type;
		return getType();
	}
*/
}