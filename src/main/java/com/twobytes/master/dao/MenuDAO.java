package com.twobytes.master.dao;

import com.twobytes.model.Menu;

public interface MenuDAO {
	public Menu selectByID(Integer menuID) throws Exception;
}
