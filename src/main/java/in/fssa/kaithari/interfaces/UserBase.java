package in.fssa.kaithari.interfaces;


public interface UserBase <T>{

	public abstract void create(T object);

	public abstract void updateName(int id, String newName);

}