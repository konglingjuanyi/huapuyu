package ö��;

public enum Type {
	����((byte) 1), ������((byte) 0);

	private final Byte value;

	private Type(Byte value) {
		this.value = value;
	}

	public Byte getValue() {
		return value;
	}
}
