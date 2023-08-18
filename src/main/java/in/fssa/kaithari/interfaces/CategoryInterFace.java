package in.fssa.kaithari.interfaces;

import java.util.Set;

import in.fssa.kaithari.model.Category;

public interface CategoryInterFace extends CategoryBase <Category>{

	public abstract Set<Category> listAllCategroyByCategoryId(int categoryId);
	
}