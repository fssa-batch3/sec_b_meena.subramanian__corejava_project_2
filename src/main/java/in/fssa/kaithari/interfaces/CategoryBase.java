package in.fssa.kaithari.interfaces;

public interface  CategoryBase <T> {
	public abstract void create(T category);
	public abstract void updateName(int id, String categoryName);
	public abstract void delete(int id);
}
