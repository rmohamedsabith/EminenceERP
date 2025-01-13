import { createData, fetchData, updateData, deleteData } from "../apiService";

// 1. Create a new MainCategory (POST)
export const createMainCategory = async (MainCategory: any) => {
  return await createData("/api/mainCategories", MainCategory);
};

// 2. Update an existing MainCategory (PUT)
export const updateMainCategory = async (id: number, MainCategory: any) => {
  return await updateData(`/api/mainCategories/${id}`, MainCategory);
};

// 3. Get a MainCategory by name (GET)
export const getMainCategoryByName = async (name: string) => {
  return await fetchData(`/api/mainCategories/${name}`);
};

// 4. Get all mainCategories (GET)
export const getAllMainCategories = async () => {
  return await fetchData("/api/mainCategories");
};

// 5. Search mainCategories by keyword (GET)
export const searchMainCategories = async (keyword: string) => {
  return await fetchData(`/api/mainCategories/search?keyword=${keyword}`);
};

// 6. Get all MainCategory names (GET)
export const getMainCategoryNames = async () => {
  return await fetchData("/api/mainCategories/names");
};

// 7. Delete a MainCategory by name (DELETE)
export const deleteMainCategory = async (name: string) => {
  return await deleteData(`/api/mainCategories/${name}`);
};
