import { createData, fetchData, updateData, deleteData } from "../apiService";

// 1. Create a new Category (POST)
export const createCategory = async (category: any) => {
  return await createData("/api/categories", category);
};

// 2. Update an existing Category (PUT)
export const updateCategory = async (name: string, category: any) => {
  return await updateData(`/api/categories/${name}`, category);
};

// 3. Get a Category by name (GET)
export const getCategoryByName = async (name: string) => {
  return await fetchData(`/api/categories/${name}`);
};

// 4. Get all Categories (GET)
export const getAllCategories = async () => {
  return await fetchData("/api/categories");
};

// 5. Search categories by keyword (GET)
export const searchCategories = async (keyword: string) => {
  return await fetchData(`/api/categories/search?keyword=${keyword}`);
};

// 6. Get all Category names (GET)
export const getCategoryNames = async () => {
  return await fetchData("/api/categories/names");
};

// 7. Delete a Category by name (DELETE)
export const deleteCategory = async (name: string) => {
  return await deleteData(`/api/categories/${name}`);
};
